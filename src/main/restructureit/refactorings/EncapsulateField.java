package restructureit.refactorings;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtFieldReference;
import spoon.support.reflect.reference.CtTypeReferenceImpl;

/**
 * Performs the refactoring Encapsulate Field. It makes a public field private and adds getters
 * and setters to allow access.
 * 
 * Reference: http://www.refactoring.com/catalog/encapsulateField.html
 * 
 * @author Pamela
 *
 */
public class EncapsulateField extends AbstractProcessor<CtField<?>> {

	/**
	 * List containing all processed fields to be used with TODO processor to update references.
	 */
	private static List<CtFieldReference<?>> processedFields = new ArrayList<CtFieldReference<?>>();
	
	/**
	 * Gets list of the processed fields.
	 * @return processed Fields
	 */
	public static List<CtFieldReference<?>> getProcessedFields() {
		return processedFields;
	}
	
	/**
	 * Determines whether a candidate field is a public field that should be encapsulated.
	 * @param candidate Field of AST to check
	 * @return field should be refactored
	 */
	public boolean isToBeProcessed(final CtField<?> candidate) {
		return candidate.hasModifier(ModifierKind.PUBLIC);
	}
	
	/**
	 * Applies the encapsulate refactoring to the candidate field.
	 * @param field to be refactored
	 */
	public void process(final CtField<?> field) {
		
		//Add field to processed fields list
		processedFields.add(field.getReference());
		
		//Set field access modifier to private.
		field.removeModifier(ModifierKind.PUBLIC);
		field.addModifier(ModifierKind.PRIVATE);
		
		//create getter method
		CtMethod getter = getFactory().Core().createMethod();
		getter.setSimpleName("get" + field.getSimpleName().substring(0, 1).toUpperCase()
				+ field.getSimpleName().substring(1));
		getter.addModifier(ModifierKind.PUBLIC);
		getter.setType(field.getType());
		
		CtCodeSnippetStatement getterStatement = getFactory().Code().createCodeSnippetStatement("return " + field.getSimpleName());
        CtBlock getterBody = getFactory().Code().createCtBlock(getterStatement);
        getter.setBody(getterBody);
        CtClass<?> candidateClass = field.getParent(CtClass.class);
        candidateClass.addMethod(getter);
        
        //create setter method
        if (!field.hasModifier(ModifierKind.FINAL)) {
        	CtMethod setter = getFactory().Core().createMethod();
    		setter.setSimpleName("set" + field.getSimpleName().substring(0, 1).toUpperCase()
    				+ field.getSimpleName().substring(1));
    		setter.addModifier(ModifierKind.PUBLIC);
    		CtTypeReferenceImpl<?> voidReturnType = new CtTypeReferenceImpl<>();
    		voidReturnType.setSimpleName("void");
    		setter.setType(voidReturnType);
    		
    		CtParameter parameter = getFactory().Core().createParameter();
    		parameter.setType(field.getType());
    		parameter.setSimpleName(field.getSimpleName());
    		setter.addParameter(parameter);
    		
    		CtCodeSnippetStatement setterStatement = getFactory().Code().createCodeSnippetStatement("this." + field.getSimpleName() + " = " + parameter.getSimpleName());
            CtBlock setterBody = getFactory().Code().createCtBlock(setterStatement);
            setter.setBody(setterBody);
            candidateClass.addMethod(setter);
        } 
	}
}
