package restructureit.refactorings.processors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import restructureit.refactorings.utils.RefactoringHelperUtils;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFieldRead;
import spoon.reflect.code.CtFieldWrite;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.TypeFactory;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Performs the refactoring Encapsulate Collection. It makes a collection field private and adds getter
 * giving unmodifiable reference add method and remove method.
 * 
 * Reference: https://refactoring.com/catalog/encapsulateCollection.html
 *
 */
public class EncapsulateCollection extends AbstractProcessor<CtField<?>> {
	
	/**
	 * Total number of times the encapsulate collection refactoring was performed.
	 */
	private static int timesApplied = 0;
	
	/**
	 * Type Factory to generate CtElement Types.
	 */
	private TypeFactory typeFactory = new TypeFactory();
	
	/**
	 * The collection type of the field.
	 */
	private String collectionType;
	
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
	 * Determines whether a candidate field is a collection field that should be encapsulated.
	 * @param candidateField  field of AST to check
	 * @return field should be refactored
	 */
	public boolean isToBeProcessed(final CtField<?> candidateField) {
		
		if (rootPackage == null) {
			CtClass<?> enclosingClass = (CtClass<?>) candidateField.getParent(CtClass.class);
			rootPackage = RefactoringHelperUtils.getRootPackage(enclosingClass);	
		}
		
		//Check field is a collection type
		if (!isCollection(candidateField)) {
			return false;
		}
		
		//Check if collection type is specified (needed for method creation)
		if (candidateField.getType().getActualTypeArguments().size() == 0) {
			return false;
		}
		
		//Check if file is already encapsulated
		if (candidateField.getModifiers().contains(ModifierKind.PRIVATE)
			&& hasCollectionAccessors(candidateField)) {
			return false;
		}
		
		//Check if field has setter making it impossible to refactor
		if (collectionHasSetter(candidateField)) {
			return false;
		}
		
		//Check if collection uses more than 2 basic type parameters
		if (!hasSimpleTypes(candidateField)) {
			return false;
		}
		
		//Check if collection is written to outside class making refactoring impossible
		//as encapsulation making field private
		//Example list = new list;
		if (collectionIsAssignedOutsideClass(candidateField)) {
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
		
		CtClass<?> enclosingClass = (CtClass<?>) candidateField.getParent(CtClass.class);
		
		//Change field to private
		if (candidateField.getModifiers().contains(ModifierKind.PUBLIC)) {
			candidateField.removeModifier(ModifierKind.PUBLIC);
		} else if (candidateField.getModifiers().contains(ModifierKind.PROTECTED)) {
			candidateField.removeModifier(ModifierKind.PROTECTED);
		}
		
		if (!candidateField.getModifiers().contains(ModifierKind.PRIVATE)) {
			candidateField.addModifier(ModifierKind.PRIVATE);
		}
		
		//Add methods to class
		String fieldName = candidateField.getSimpleName().toUpperCase().substring(0, 1) + candidateField.getSimpleName().substring(1);
		
		//Create Getter method
		CtMethod getter = getFactory().Core().createMethod();
		getter.setSimpleName("get" + fieldName);
		getter.addModifier(ModifierKind.PUBLIC);
		
		CtCodeSnippetStatement getterStatement = null;
		
		if (collectionType.equals("List")) {
			getter.setType(typeFactory.createReference(List.class));
			getterStatement = getFactory().Code().createCodeSnippetStatement("return " + "java.util.Collections.unmodifiableList(" + candidateField.getSimpleName() + ")");
		} else if (collectionType.equals("Set")) {
			getter.setType(typeFactory.createReference(Set.class));
			getterStatement = getFactory().Code().createCodeSnippetStatement("return " + "java.util.Collections.unmodifiableSet(" + candidateField.getSimpleName() + ")");
		} else if (collectionType.equals("Map")) {
			getter.setType(typeFactory.createReference(Map.class));
			getterStatement = getFactory().Code().createCodeSnippetStatement("return " + "java.util.Collections.unmodifiableMap(" + candidateField.getSimpleName() + ")");
		}
        CtBlock getterBody = getFactory().Code().createCtBlock(getterStatement);
        getter.setBody(getterBody);
        
        //Check if getter already exists if so replace
        List<CtMethod<?>> getterReference = enclosingClass.filterChildren((CtMethod<?> method) -> method.getSimpleName().equals("get" + fieldName) 
															&& method.hasModifier(ModifierKind.PUBLIC) 
															&& !method.getType().equals(new TypeFactory().VOID_PRIMITIVE))
															.list();
		
		if (getterReference.size() == 1) {
			getterReference.get(0).replace(getter);
		} else {
			enclosingClass.addMethod(getter);
		}
        
        //Create add Method
        CtMethod adder = getFactory().Core().createMethod();
        adder.setSimpleName("add" + fieldName.substring(0, fieldName.length() - 1));
        adder.addModifier(ModifierKind.PUBLIC);
		adder.setType(typeFactory.voidPrimitiveType());
		
		CtParameter adderParameter1 = getFactory().Core().createParameter();
		CtParameter adderParameter2 = getFactory().Core().createParameter();
		
		CtCodeSnippetStatement adderStatement = null;
		
		if (collectionType.equals("List") || collectionType.equals("Set")) {
			adderParameter1.setType(candidateField.getType().getActualTypeArguments().get(0));
			adderParameter1.setSimpleName(candidateField.getSimpleName().substring(0, candidateField.getSimpleName().length() - 1));
			adder.addParameter(adderParameter1);
			
			adderStatement = getFactory().Code().createCodeSnippetStatement(candidateField.getSimpleName() + ".add(" + adderParameter1.getSimpleName() + ")");
		} else if (collectionType.equals("Map")) {
			adderParameter1.setType(candidateField.getType().getActualTypeArguments().get(0));
			adderParameter1.setSimpleName(candidateField.getSimpleName().substring(0, candidateField.getSimpleName().length() - 1) + "Key");
			adder.addParameter(adderParameter1);
			
			adderParameter2.setType(candidateField.getType().getActualTypeArguments().get(1));
			adderParameter2.setSimpleName(candidateField.getSimpleName().substring(0, candidateField.getSimpleName().length() - 1) + "Value");
			adder.addParameter(adderParameter2);
			
			adderStatement = getFactory().Code().createCodeSnippetStatement(candidateField.getSimpleName() + ".put(" + adderParameter1.getSimpleName() + ", " + adderParameter2.getSimpleName() + ")");
		}
		
        CtBlock adderBody = getFactory().Code().createCtBlock(adderStatement);
        adder.setBody(adderBody);
        enclosingClass.addMethod(adder);
        
        //Create Remove Method
        CtMethod remover = getFactory().Core().createMethod();
        remover.setSimpleName("remove" + fieldName.substring(0, fieldName.length() - 1));
        remover.addModifier(ModifierKind.PUBLIC);
        remover.setType(typeFactory.voidPrimitiveType());
		
		CtCodeSnippetStatement removerStatement = null;
		
		CtParameter removerParameter = getFactory().Core().createParameter();
		
		removerParameter.setType(candidateField.getType().getActualTypeArguments().get(0));
		removerParameter.setSimpleName(candidateField.getSimpleName().substring(0, candidateField.getSimpleName().length() - 1));
		remover.addParameter(removerParameter);
		
		removerStatement = getFactory().Code().createCodeSnippetStatement(candidateField.getSimpleName() + ".remove(" + removerParameter.getSimpleName() + ")");
		
		CtBlock removerBody = getFactory().Code().createCtBlock(removerStatement);
        remover.setBody(removerBody);
        enclosingClass.addMethod(remover);
        
        List<CtInvocation> collectionAddInvocations = null;
        List<CtInvocation> collectionRemoveInvocations = null;
        List<CtFieldRead> collectionGetAccesses = null;
        
        //Update references
        if (collectionType.equals("List") || collectionType.equals("Set")) {
        	
        	collectionAddInvocations = rootPackage.filterChildren(new TypeFilter<>(CtInvocation.class)).list();
        	
        	collectionAddInvocations = rootPackage.filterChildren((CtInvocation invocation) -> invocation.getTarget() != null
        																&& invocation.getTarget().getType().equals(candidateField.getType())
																		&& invocation.getExecutable().getSimpleName().equals("add")
																		&& invocation.getExecutable().getType().equals(new TypeFactory().BOOLEAN_PRIMITIVE))
																		.list();
        	
        	collectionRemoveInvocations = rootPackage.filterChildren((CtInvocation invocation) -> invocation.getTarget() != null
																		&& invocation.getTarget().getType().equals(candidateField.getType())
																		&& invocation.getExecutable().getSimpleName().equals("remove")
																		&& invocation.getExecutable().getType().equals(new TypeFactory().BOOLEAN_PRIMITIVE))
																		.list();
        	
        } else {
        	collectionAddInvocations = rootPackage.filterChildren((CtInvocation invocation) -> invocation.getTarget() != null
					&& invocation.getTarget().getType().equals(candidateField.getType())
					&& invocation.getExecutable().getSimpleName().equals("put")
					&& !invocation.getExecutable().getType().equals(new TypeFactory().VOID_PRIMITIVE))
					.list();
        	
        	collectionRemoveInvocations = rootPackage.filterChildren((CtInvocation invocation) -> invocation.getTarget() != null
					&& invocation.getTarget().getType().equals(candidateField.getType())
					&& invocation.getExecutable().getSimpleName().equals("remove")
					&& !invocation.getExecutable().getType().equals(new TypeFactory().VOID_PRIMITIVE))
					.list();
        }
        
        collectionGetAccesses = rootPackage.filterChildren((CtFieldRead getCollection) -> getCollection.getVariable().equals(candidateField.getReference()))
        														.list();
        
        //update calls to add to addmethod eg. names.add(item) changes to addName(item)
    	for (CtInvocation<?> collectionAddInvocation : collectionAddInvocations) {
    		if (collectionAddInvocation.getTarget() instanceof CtFieldRead) {
    			
    			CtFieldRead<?> fieldAccessed = (CtFieldRead<?>) collectionAddInvocation.getTarget();
    			
    			if (fieldAccessed.getVariable().getSimpleName().equals(candidateField.getSimpleName())
    					&& fieldAccessed.getVariable().getDeclaringType().equals(enclosingClass.getReference())) {
    				
    				CtInvocation<?> addInvocation = getFactory().createInvocation();
        			
        			addInvocation.addArgument(collectionAddInvocation.getArguments().get(0));
        			
        			if (fieldAccessed.getTarget() != null) {
        				addInvocation.setTarget(fieldAccessed.getTarget());
        			} else {
        				addInvocation.setTarget(collectionAddInvocation.getTarget());
        			}
        			addInvocation.setExecutable(getAddMethodReference(candidateField).getReference());
        			
        			collectionAddInvocation.replace(addInvocation);
    			}
    		}
    	}
    	
    	//update calls to remove to removemethod eg. names.remove(item) changes to removeName(item)
    	for (CtInvocation<?> collectionRemoveInvocation : collectionRemoveInvocations) {
    		if (collectionRemoveInvocation.getTarget() instanceof CtFieldRead) {
    			
    			CtFieldRead<?> fieldAccessed = (CtFieldRead<?>) collectionRemoveInvocation.getTarget();
    			
    			if (fieldAccessed.getVariable().getSimpleName().equals(candidateField.getSimpleName())
    					&& fieldAccessed.getVariable().getDeclaringType().equals(enclosingClass.getReference())) {
    				
    				CtInvocation<?> removeInvocation = getFactory().createInvocation();
        			
    				removeInvocation.addArgument(collectionRemoveInvocation.getArguments().get(0));
    				
    				if (fieldAccessed.getTarget() != null) {
    					removeInvocation.setTarget(fieldAccessed.getTarget());
        			} else {
        				removeInvocation.setTarget(collectionRemoveInvocation.getTarget());
        			}
    				removeInvocation.setExecutable(getRemoveMethodReference(candidateField).getReference());
        			
    				collectionRemoveInvocation.replace(removeInvocation);
    			}
    		}
    	}
    	
    	//replace read accesses to getter eg. list = names to list = getNames()
    	for (CtFieldRead<?> collectionAccess : collectionGetAccesses) {
    				
    		CtInvocation<?> getterInvocation = getFactory().createInvocation();

    		getterInvocation.setTarget(collectionAccess.getTarget());
    		getterInvocation.setExecutable(getGetterMethodReference(candidateField).getReference());

    		collectionAccess.replace(getterInvocation);			
    	}
    	
    	//initialise collection in declaration and remove other intitalisation calls
    	List<CtConstructorCall> fieldInitialisations = enclosingClass.filterChildren(new TypeFilter<>(CtConstructorCall.class)).list();
    	
    	CtConstructorCall<?> constructorCall = null;
    	for (CtConstructorCall<?> fieldInitialisation : fieldInitialisations) {
    		if (fieldInitialisation.getParent() instanceof CtAssignment) {
    			CtAssignment<?, ?> assignment = (CtAssignment<?, ?>) fieldInitialisation.getParent();
    			
    			if (assignment.getAssigned() instanceof CtFieldWrite) {
    				CtFieldWrite<?> fieldWrite = (CtFieldWrite<?>) assignment.getAssigned();
    				
    				if (fieldWrite.getVariable().equals(candidateField.getReference())) {
    					constructorCall = fieldInitialisation;
    					fieldInitialisation.getParent().delete();
					}
    			}	
    		}
    	}
    	
    	if (candidateField.getDefaultExpression() == null) {
    		if (constructorCall == null) {
    			if (candidateField.getType().equals(typeFactory.createReference(List.class))) {
    				candidateField.setDefaultExpression((CtExpression) getFactory().createConstructorCall(typeFactory.createReference(ArrayList.class)));
    			} else if (candidateField.getType().equals(typeFactory.createReference(Set.class))) {
    				candidateField.setDefaultExpression((CtExpression) getFactory().createConstructorCall(typeFactory.createReference(HashSet.class)));
    			} else if (candidateField.getType().equals(typeFactory.createReference(Map.class))) {
    				candidateField.setDefaultExpression((CtExpression) getFactory().createConstructorCall(typeFactory.createReference(HashMap.class)));
    			}
    		} else {
    			candidateField.setDefaultExpression((CtExpression) constructorCall);
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
				collectionType = "List";
				return true;
			} else if (typeName.contains("Set")) {
				collectionType = "Set";
				return true;
			} /*else if (typeName.contains("Map") || typeName.equals("Hashtable") || typeName.equals("Properties")) {
				collectionType = "Map";
				return true;
			}*/
		}
		
		return false;
	}

	/**
	 * Indicates if field has all collection accessors.
	 * @param candidateField collection
	 * @return collection already encapsulated
	 */
	private boolean hasCollectionAccessors(final CtField<?> candidateField) {
		CtClass<?> enclosingClass = (CtClass<?>) candidateField.getParent(CtClass.class);
		
		String fieldName = candidateField.getSimpleName().toUpperCase().substring(0, 1) + candidateField.getSimpleName().substring(1);
		
		List<CtMethod<?>> getterReference = enclosingClass.filterChildren((CtMethod<?> method) -> method.getSimpleName().equals("get" + fieldName) 
				&& !method.getType().equals(new TypeFactory().VOID_PRIMITIVE))
				.list();
		
		if (getterReference.size() == 0) {
			return false;
		}
		
		List<CtMethod<?>> addReference = enclosingClass.filterChildren((CtMethod<?> method) -> method.getSimpleName().equals("add" + fieldName.substring(0, fieldName.length() - 1)) 
				&& method.getType().equals(new TypeFactory().VOID_PRIMITIVE))
				.list();
		
		if (addReference.size() == 0) {
			return false;
		}
		
		List<CtMethod<?>> removeReference = enclosingClass.filterChildren((CtMethod<?> method) -> method.getSimpleName().equals("remove" + fieldName.substring(0, fieldName.length() - 1)) 
				&& method.getType().equals(new TypeFactory().VOID_PRIMITIVE))
				.list();
		
		if (removeReference.size() == 0) {
			return false;
		}

		return true;
	}
	
	/**
	 * Indicates if candidate collection has 2 basic types or less.
	 * example List<String> and Map<String, Integer>
	 * @param candidateField field
	 * @return if field type has 2 or less types
	 */
	private boolean hasSimpleTypes(final CtField<?> candidateField) {
		CtTypeReference<?> typeReference = candidateField.getType();
		List<CtTypeReference<?>> simpleTypes = typeReference.getActualTypeArguments();
		
		for (CtTypeReference<?> type : simpleTypes) {
			if (type.getActualTypeArguments().size() != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Determines if the candidate collection has a setter method.
	 * @param candidateField collection to check
	 * @return collection has setter method
	 */
	private boolean collectionHasSetter(final CtField<?> candidateField) {
		
		CtClass<?> enclosingClass = (CtClass<?>) candidateField.getParent(CtClass.class);
		
		String fieldName = candidateField.getSimpleName().toUpperCase().substring(0, 1) + candidateField.getSimpleName().substring(1);
		
		List<CtMethod<?>> setterReference = enclosingClass.filterChildren((CtMethod<?> method) -> method.getSimpleName().equals("set" + fieldName) 
				&& method.getType().equals(new TypeFactory().VOID_PRIMITIVE))
				.list();
		
		return (setterReference.size() == 1);
	}
	
	/**
	 * Determines if the candidate collection is written to outside its enclosing class.
	 * @param candidateField collection to check
	 * @return if candidate collection is assigned to outside its class
	 */
	private boolean collectionIsAssignedOutsideClass(final CtField<?> candidateField) {
		
		CtClass<?> enclosingClass = (CtClass<?>) candidateField.getParent(CtClass.class);
		
		List<CtFieldWrite<?>> collectionWrites = rootPackage.filterChildren((CtFieldWrite<?> collectionWrite) -> collectionWrite.getVariable().equals(candidateField.getReference()))
																			 .list();
		
		for (CtFieldWrite<?> collectionWrite : collectionWrites) {
			CtAssignment<?, ?> assignment = (CtAssignment<?, ?>) collectionWrite.getParent();
			
			if (assignment.getAssignment().getParent(CtClass.class) != enclosingClass) {
				return true;
			} 
		}
		
		return false;
	}
	
	/**
	 * Retrieves the add method reference for this collection.
	 * @param candidateField collection
	 * @return add method
	 */
	private CtMethod getAddMethodReference(final CtField<?> candidateField) {
		CtClass<?> enclosingClass = (CtClass<?>) candidateField.getParent(CtClass.class);
		
		String fieldName = candidateField.getSimpleName().toUpperCase().substring(0, 1) + candidateField.getSimpleName().substring(1, candidateField.getSimpleName().length() - 1);
		
		List<CtMethod<?>> adderReference = enclosingClass.filterChildren((CtMethod<?> method) -> method.getSimpleName().equals("add" + fieldName) 
				&& method.getType().equals(new TypeFactory().VOID_PRIMITIVE))
				.list();
		
		return adderReference.get(0);
	}
	
	/**
	 * Retrieves the remove method for this collection.
	 * @param candidateField collection
	 * @return remove method
	 */
	private CtMethod getRemoveMethodReference(final CtField<?> candidateField) {
		CtClass<?> enclosingClass = (CtClass<?>) candidateField.getParent(CtClass.class);
		
		String fieldName = candidateField.getSimpleName().toUpperCase().substring(0, 1) + candidateField.getSimpleName().substring(1, candidateField.getSimpleName().length() - 1);
		
		List<CtMethod<?>> removerReference = enclosingClass.filterChildren((CtMethod<?> method) -> method.getSimpleName().equals("remove" + fieldName) 
				&& method.getType().equals(new TypeFactory().VOID_PRIMITIVE))
				.list();
		
		return removerReference.get(0);
	}
	
	/**
	 * Retrieves the getter method for this collection.
	 * @param candidateField collection
	 * @return get method
	 */
	private CtMethod getGetterMethodReference(final CtField<?> candidateField) {
		CtClass<?> enclosingClass = (CtClass<?>) candidateField.getParent(CtClass.class);
		
		String fieldName = candidateField.getSimpleName().toUpperCase().substring(0, 1) + candidateField.getSimpleName().substring(1);
		
		List<CtMethod<?>> getterReference = enclosingClass.filterChildren((CtMethod<?> method) -> method.getSimpleName().equals("get" + fieldName) 
				&& !method.getType().equals(new TypeFactory().VOID_PRIMITIVE))
				.list();
		
		return getterReference.get(0);
	}
}
