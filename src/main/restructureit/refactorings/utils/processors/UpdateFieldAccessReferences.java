package restructureit.refactorings.utils.processors;


import restructureit.refactorings.EncapsulateField;
import restructureit.refactorings.utils.templates.GetterInvocationTemplate;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtFieldRead;
import spoon.reflect.code.CtStatement;
import spoon.reflect.reference.CtFieldReference;
import spoon.template.Template;

/**
 * A utility class that assists with the EncapsulateField refactoring. 
 * This class updates all field access references to use the new getters and setters created.
 * @see EncapsulateField
 * 
 * @author Pamela
 */
public class UpdateFieldAccessReferences extends AbstractProcessor<CtFieldAccess<?>> {

	/**
	 * Determines whether the field should be processed.
	 * Only fields that have had getters/setters generated for them
	 * should be processed.
	 * @param candidate Field of AST to check
	 * @return should field be processed
	 */
	public boolean isToBeProcessed(final CtFieldAccess<?> candidate) {
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
	public void process(final CtFieldAccess<?> fieldReference) {
		
		// TODO Auto-generated method stub
		CtFieldReference<?> field = fieldReference.getVariable();
		
		//requires Getter
		if (fieldReference instanceof CtFieldRead) {
			Template<?> getterTemplate = new GetterInvocationTemplate(field.getType(), field.getSimpleName(), 
					fieldReference);
			
			CtStatement getterReference = (CtStatement) getterTemplate.apply(null);
			if (getterReference != null) {
				System.out.println("IM IN HERE!");
				fieldReference.replace(getterReference);
			}
			
		} /*else if (fieldReference instanceof CtAssignment) {
			CtAssignment<?, ?> assignment = (CtAssignment<?, ?>) fieldReference;
			
			Template setterTemplate = new UpdateFieldReferencesTemplate(field.getType(), field.getSimpleName(), 
					fieldReference, assignment.getAssignment());
			
			CtBlock<?> setterReference = Substitution.substituteMethodBody(assignment.getParent(CtClass.class), 
					setterTemplate, "setterInvocation");
			if (setterReference.getStatements().size() == 1) {
				assignment.replace(setterReference.getStatements().get(0));
			}
		}*/
	}
}
