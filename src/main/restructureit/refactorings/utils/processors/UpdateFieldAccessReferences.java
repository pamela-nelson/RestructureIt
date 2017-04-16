package restructureit.refactorings.utils.processors;


import restructureit.refactorings.processors.EncapsulateField;
import restructureit.refactorings.utils.templates.FieldAccessTemplate;
import restructureit.refactorings.utils.templates.GetterInvocationTemplate;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFieldRead;
import spoon.reflect.code.CtThisAccess;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.visitor.filter.NameFilter;
import spoon.template.Substitution;
import spoon.template.Template;
import spoon.template.TemplateMatcher;

/**
 * A utility class that assists with the EncapsulateField refactoring. 
 * This class updates all field access references to use the new getters and setters created.
 * @see EncapsulateField
 * 
 * @author Pamela
 */
public class UpdateFieldAccessReferences extends AbstractProcessor<CtFieldRead<?>> {

	/**
	 * Determines whether the field should be processed.
	 * Only fields that have had getters/setters generated for them
	 * should be processed.
	 * @param candidate Field of AST to check
	 * @return should field be processed
	 */
	public boolean isToBeProcessed(final CtFieldRead<?> candidate) {
		CtFieldReference<?> field = candidate.getVariable();
		
		if (EncapsulateField.getProcessedFields().contains(field)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Updates the references to processed field to use getters and setters generated
	 * instead of direct access.
	 * @param fieldReference field access to be refactored to use getters/setters.
	 */
	public void process(final CtFieldRead<?> fieldReference) {
		
		// TODO Auto-generated method stub
		CtFieldReference<?> field = fieldReference.getVariable();
		
		CtClass<?> templateClass = getFactory().Class().get(GetterInvocationTemplate.class);
		CtMethod<?> templateRoot = (CtMethod<?>) templateClass.getElements(new NameFilter("get_Field_")).get(0);
		
		TemplateMatcher templateMatcher = new TemplateMatcher(templateRoot);
		if (templateMatcher.find(fieldReference.getParent(CtMethod.class)).size() == 0) {
		
        
		//requires Getter
			Template<?> getterTemplate = new FieldAccessTemplate(field.getType(), field.getSimpleName(), 
					fieldReference, null);
			
			CtBlock<?> getterBlah = Substitution.substituteMethodBody(fieldReference.getParent(CtClass.class), getterTemplate, 
					"getterInvocation");
			//getterBlah.getStatement(0);
			//CtExecutableReference<?> reference = getterBlah.getStatement(0).
			
			CtExpression<?> targetObject = (CtExpression) fieldReference.getTarget();
			
			//Direct call to getter
			if (targetObject instanceof CtThisAccess<?>) {
				//String fieldName = field.getSimpleName().substring(0, 1).toUpperCase() + field.getSimpleName().substring(1);
				//CtCodeSnippetExpression<?> getterReference = getFactory().Code().createCodeSnippetExpression("get" + fieldName + "()");
				//getterReference.compile();
				//CtBlock<?> getterReferenceBody = getFactory().Code().createCtBlock(getterReference);
				fieldReference.replace(getterBlah.getStatement(0));
			} 
			
			
		/*} else if (fieldReference instanceof CtAssignment) {
			CtAssignment<?, ?> assignment = (CtAssignment<?, ?>) fieldReference;
			
			Template setterTemplate = new UpdateFieldReferencesTemplate(field.getType(), field.getSimpleName(), 
					fieldReference, assignment.getAssignment());
			
			CtBlock<?> setterReference = Substitution.substituteMethodBody(assignment.getParent(CtClass.class), 
					setterTemplate, "setterInvocation");
			if (setterReference.getStatements().size() == 1) {
				assignment.replace(setterReference.getStatements().get(0));
			}
		}*/
		} else {
			System.out.println("BOOOOOOOOOO!");
		}
	}
}
