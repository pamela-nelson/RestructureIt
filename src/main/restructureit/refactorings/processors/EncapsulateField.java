package restructureit.refactorings.processors;

import java.util.List;

import restructureit.refactorings.utils.RefactoringHelperUtils;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtFieldRead;
import spoon.reflect.code.CtFieldWrite;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.InterfaceFactory;
import spoon.reflect.factory.TypeFactory;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Performs the refactoring Encapsulate Field. It makes a public field private and adds getters
 * and setters to allow access.
 * 
 * Reference: http://www.refactoring.com/catalog/encapsulateField.html
 *
 */
public class EncapsulateField extends AbstractProcessor<CtField<?>> {

	/**
	 * Total number of times the encapsulateField refactoring was performed.
	 */
	private static int timesApplied = 0;
	
	/**
	 * Type Factory to generate CtElement Types.
	 */
	private TypeFactory typeFactory = new TypeFactory();
	
	/**
	 * Root Package of the project.
	 */
	private CtPackage rootPackage;
	
	/**
	 * @return the timesApplied
	 */
	public static int getTimesApplied() {
		return timesApplied;
	}
	
	/**
	 * Resets times applied back to zero.
	 */
	public static void resetTimesApplied() {
		timesApplied = 0;
	}
	
	/**
	 * Determines whether a candidate field is a public field that should be encapsulated.
	 * @param candidateField  field of AST to check
	 * @return field should be refactored
	 */
	public boolean isToBeProcessed(final CtField<?> candidateField) {	
		
		//Check if field is public
		if (!candidateField.hasModifier(ModifierKind.PUBLIC)) {
			return false;
		}
		
		//Check field is not collection type
		if (isCollection(candidateField)) {
			return false;
		}
		
		return true;	
	}
	
	/**
	 * Applies the encapsulate refactoring to the candidate field.
	 * @param candidateField to be refactored
	 */
	public void process(final CtField<?> candidateField) {
		timesApplied++;
		
		if (rootPackage == null) {
			CtClass<?> enclosingClass = (CtClass<?>) candidateField.getParent(CtClass.class);
			rootPackage = RefactoringHelperUtils.getRootPackage(enclosingClass);	
		}
		
		//Set field access modifier to private.
		candidateField.removeModifier(ModifierKind.PUBLIC);
		candidateField.addModifier(ModifierKind.PRIVATE);
		
		//create getter method
		CtMethod getter = getFactory().Core().createMethod();
		getter.setSimpleName("get" + candidateField.getSimpleName().substring(0, 1).toUpperCase()
				+ candidateField.getSimpleName().substring(1));
		getter.addModifier(ModifierKind.PUBLIC);
		getter.setType(candidateField.getType());
		
		CtCodeSnippetStatement getterStatement = getFactory().Code().createCodeSnippetStatement("return " + candidateField.getSimpleName());
        CtBlock getterBody = getFactory().Code().createCtBlock(getterStatement);
        getter.setBody(getterBody);
        CtClass<?> candidateClass = candidateField.getParent(CtClass.class);
        candidateClass.addMethod(getter);
        
        //create setter method
        if (!candidateField.hasModifier(ModifierKind.FINAL)) {
        	CtMethod setter = getFactory().Core().createMethod();
    		setter.setSimpleName("set" + candidateField.getSimpleName().substring(0, 1).toUpperCase()
    				+ candidateField.getSimpleName().substring(1));
    		setter.addModifier(ModifierKind.PUBLIC);
    		setter.setType(typeFactory.voidPrimitiveType());
    		
    		CtParameter parameter = getFactory().Core().createParameter();
    		parameter.setType(candidateField.getType());
    		parameter.setSimpleName(candidateField.getSimpleName());
    		setter.addParameter(parameter);
    		
    		CtCodeSnippetStatement setterStatement = getFactory().Code().createCodeSnippetStatement("this." + candidateField.getSimpleName() + " = " + parameter.getSimpleName());
            CtBlock setterBody = getFactory().Code().createCtBlock(setterStatement);
            setter.setBody(setterBody);
            candidateClass.addMethod(setter);
        } 
        
        //Update Field Access References
        List<CtFieldAccess> allFieldAccesses = rootPackage.filterChildren(new TypeFilter(CtFieldAccess.class)).list();
        
        for (CtFieldAccess<?> fieldAccess : allFieldAccesses) {
        	
        	CtFieldReference<?> field = fieldAccess.getVariable();
        	
        	//Reference inside class
        	if (field.equals(candidateField.getReference())
        		&& field.getDeclaringType().getSimpleName().equals(candidateField.getDeclaringType().getSimpleName())) {
        		CtClass<?> enclosingClass = (CtClass<?>) field.getDeclaringType().getDeclaration();
        		String fieldName = field.getSimpleName().toUpperCase().substring(0, 1) + field.getSimpleName().substring(1);
        		
        		//requires Getter
        		if (fieldAccess instanceof CtFieldRead) {
        			
        			//retrieve getter
        			List<CtMethod> getterReference = enclosingClass.filterChildren((CtMethod method) -> method.getSimpleName().equals("get" + fieldName) 
        																	&& method.hasModifier(ModifierKind.PUBLIC) 
        																	&& !method.getType().equals(new TypeFactory().VOID))
        																	.list();
        			
        			CtInvocation<?> getterInvocation = getFactory().createInvocation();
        			getterInvocation.setExecutable(getterReference.get(0).getReference());
        			getterInvocation.setTarget(fieldAccess.getTarget());

        			fieldAccess.replace(getterInvocation);
        			
        		} else if (fieldAccess instanceof CtFieldWrite 
        				   && fieldAccess.getParent() instanceof CtAssignment
        				   && !candidateField.getModifiers().contains(ModifierKind.FINAL)) {
        			
        			CtAssignment<?, ?> assignment = (CtAssignment<?, ?>) fieldAccess.getParent();
        			
        			List<CtMethod> setter = enclosingClass.filterChildren((CtMethod method) -> method.getSimpleName().equals("set" + fieldName) 
        																	&& method.hasModifier(ModifierKind.PUBLIC) 
        																	&& method.getType().equals(new TypeFactory().VOID_PRIMITIVE))
        																	.list();
        			
        			CtInvocation<?> setterInvocation = getFactory().createInvocation();
        			
        			setterInvocation.addArgument(assignment.getAssignment());
        			
        			setterInvocation.setTarget(fieldAccess.getTarget());

        			setterInvocation.setExecutable(setter.get(0).getReference());

        			fieldAccess.getParent().replace(setterInvocation);
        		}
        	} 
        }
	}
	
	/**
	 * Indicates if a field is a collection type.
	 * @param candidateField field to check
	 * @return if collection type
	 */
	private boolean isCollection(final CtField<?> candidateField) {
		String typeName = candidateField.getType().getSimpleName();
		
		if (candidateField.getType().getPackage() != null
			&& candidateField.getType().getPackage().getSimpleName().equals("java.util")) {
			if (typeName.contains("List") || typeName.equals("Stack") || typeName.equals("Vector")) {
				return true;
			} else if (typeName.contains("Set")) {
				return true;
			} else if (typeName.contains("Map") || typeName.equals("Hashtable") || typeName.equals("Properties")) {
				return true;
			}
		}
		
		return false;
	}
}
