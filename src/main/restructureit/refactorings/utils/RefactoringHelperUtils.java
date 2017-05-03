package restructureit.refactorings.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.visitor.Query;
import spoon.reflect.visitor.filter.AbstractFilter;

/**
 * Class contains static methods which help during the refactoring process.
 * @author Pamela
 *
 */
public final class RefactoringHelperUtils {
	
	/**
	 * 
	 */
	private RefactoringHelperUtils() {
		
	}
	
	
	//METHOD INVOCATION HELPERS
	/**
	 * Gets the signature of the method invoked .
	 * contains return type, name and parameter types in the format:
	 * returnType methodName(ParameterType,...)
	 * @param methodInvocation method invoked
	 * @return signature of invoked method
	 */
	public static String getMethodInvocationSignature(final CtInvocation<?> methodInvocation) {
		String methodSignature = "";
		Method methodReference = methodInvocation.getExecutable().getActualMethod();
		//add return type
		methodSignature += methodReference.getReturnType().getSimpleName() + " ";
		
		//add method name
		methodSignature += methodReference.getName() + "(";
		
		Class<?>[] parameterTypes = methodReference.getParameterTypes();
		
		for (Class<?> parameterType : parameterTypes) {
			methodSignature += parameterType.getName() + ",";
		}
		
		if (parameterTypes.length == 0) {
			methodSignature += ")";
		} else {
			methodSignature = methodSignature.substring(0, methodSignature.length() - 1) + ")";
		}
		
		return methodSignature;
	}
	
	//PACKAGE HELPERS
	/**
	 * Get root package of the project.
	 * @param ctClass class in the project
	 * @return root package of project
	 */
	public static CtPackage getRootPackage(final CtClass<?> ctClass) {
		CtPackage rootPackage = ctClass.getPackage();
		while (rootPackage.getParent(CtPackage.class) != null) {
			rootPackage = rootPackage.getParent(CtPackage.class);
		}
		
		return rootPackage;
	}
	
	//CLASS HELPERS
	/**
	 * Retrieves all the direct and indirect children of a given class.
	 * @param parentClass class to get children of
	 * @return children of given class
	 */
	public static List<CtClass<?>> getAllSubClasses(final CtClass<?> parentClass) {
		return Query.getElements(parentClass.getFactory(), new AbstractFilter<CtClass<?>>(CtClass.class) {
			@Override
			public boolean matches(final CtClass<?> element) {
				CtClass<?> candidateSuperClass;
				CtClass<?> tempElement = element;
				
				if (element.getSuperclass() != null
						&& element.getSuperclass().getDeclaration() != null) {
					
					while (tempElement.getSuperclass() != null
							&& tempElement.getSuperclass().getDeclaration() != null) {
						candidateSuperClass = (CtClass<?>) tempElement.getSuperclass().getDeclaration();
						
						if (parentClass.equals(candidateSuperClass)) {
							return true;
						}
						
						if (tempElement.getSuperclass() != null
								&& tempElement.getSuperclass().getDeclaration() != null) {
							tempElement = (CtClass<?>) tempElement.getSuperclass().getDeclaration();
						}
					}		
				}	
				return false;
			}
		});
	}
	
	/**
	 * Retrieves all the direct children of a given superclass.
	 * @param parentClass Class to get children of
	 * @return Children of given class
	 */
	public static List<CtClass<?>> getSubClasses(final CtClass<?> parentClass) {
		return Query.getElements(parentClass.getFactory(), new AbstractFilter<CtClass<?>>(CtClass.class) {
			@Override
			public boolean matches(final CtClass<?> element) {
				return element.getSuperclass() != null 
						&& parentClass.equals(element.getSuperclass().getDeclaration());
			}
		});
	}
	
	/**
	 * Returns the highest subclasses in the hierarchy from a list of subclasses.
	 * @param subClasses subClasses
	 * @return highest subclasses
	 */
	public static  List<CtClass<?>> getHighestSubClasses(final List<CtClass<?>> subClasses) {
		List<CtClass<?>> highestSubClasses = new ArrayList<CtClass<?>>(subClasses);
		
		for (CtClass<?> subClass : subClasses) {
			List<?> classSubClasses = RefactoringHelperUtils.getAllSubClasses(subClass); 
				highestSubClasses.removeAll(classSubClasses);
		}
		
		return highestSubClasses;
	}
	
	//FIELD HELPERS
	/**
	 * Retrieves all the fields from field accesses.
	 * @param fieldsAccess field accesses
	 * @return fields
	 */
	public static List<CtField<?>> getFieldsFromFieldAccesses(final List<CtFieldAccess<?>> fieldsAccess) {
		return fieldsAccess.stream()
				.map(ctFieldAccess -> ctFieldAccess.getVariable().getDeclaration())
				.collect(Collectors.toList());
	}
}
