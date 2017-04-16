package restructureit.qualitymeasurement;

import java.util.Map;

/**
 * Quality report contains the results of refactoring a project.
 * Metrics before and after are stored as well as details of changes in individual quality metrics
 * and what refactorings were applied and how many times they were applied.
 * 
 * @author Pamela
 *
 */
public class QualityReport {
	
	//PROJECT DETAILS
	
	/**
	 * Name of the project the quality report relates to.
	 */
	private String projectName;
	
	/**
	 * Location of the project the quality report relates to.
	 */
	private String projectLocation;
	
	//PROGRAM METRICS BEFORE/AFTER
	
	/**
	 * Metrics of program before refactoring.
	 */
	private Metrics beforeRefactoring;
	
	/**
	 * Metrics of program after refactoring.
	 */
	private Metrics afterRefactoring;
	
	//METRIC CHANGES
	
	/**
	 * Change in designsize metric after refactoring.
	 */
	private double designSizeChange;
	
	/**
	 * Change in hierarchies metric after refactoring.
	 */
	private double hierarchiesChange;
	
	/**
	 * Change in abstraction metric after refactoring.
	 */
	private double abstractionChange;
	
	/**
	 * Change in encapsulation metric after refactoring.
	 */
	private double encapsulationChange;
	
	/**
	 * Change in coupling metric after refactoring.
	 */
	private double couplingChange;
	
	/**
	 * Change in cohesion metric after refactoring.
	 */
	private double cohesionChange;
	
	/**
	 * Change in composition metric after refactoring.
	 */
	private double compositionChange;
	
	/**
	 * Change in inheritance metric after refactoring.
	 */
	private double inheritanceChange;
	
	/**
	 * Change in polymorphism metric after refactoring.
	 */
	private double polymorphismChange;
	
	/**
	 * Change in messaging metric after refactoring.
	 */
	private double messagingChange;
	
	/**
	 * Change in complexity metric after refactoring.
	 */
	private double complexityChange;
	
	/**
	 * Change in lines of code metric after refactoring.
	 */
	private double linesOfCodeChange;
	
	//QUALITY ATTRIBUTE CHANGES
	
	/**
	 * Change in reusability quality attribute after refactoring.
	 */
	private double reusabilityChange;
	
	/**
	 * Change in flexability quality attribute after refactoring.
	 */
	private double flexabilityChange;
	
	/**
	 * Change in understandability quality attribute after refactoring.
	 */
	private double understandabiltyChange;
	
	/**
	 * Change in functionality quality attribute after refactoring.
	 */
	private double functionalityChange;
	
	/**
	 * Change in extendability quality attribute after refactoring.
	 */
	private double extendabilityChange;
	
	/**
	 * Change in effectiveness quality attribute after refactoring.
	 */
	private double effectivenessChange;
	
	/**
	 * Change in quality score quality attribute after refactoring.
	 */
	private double qualityScoreChange;
	
	//REFACTORING STATS
	
	/**
	 * Refactorings that were appllied to the project and how many times.
	 */
	private Map<String, Integer> refactoringsApplied;

	//GETTERS AND SETTERS
	
	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(final String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the projectLocation
	 */
	public String getProjectLocation() {
		return projectLocation;
	}

	/**
	 * @param projectLocation the projectLocation to set
	 */
	public void setProjectLocation(final String projectLocation) {
		this.projectLocation = projectLocation;
	}

	/**
	 * @return the beforeRefactoring
	 */
	public Metrics getBeforeRefactoring() {
		return beforeRefactoring;
	}

	/**
	 * @param beforeRefactoring the beforeRefactoring to set
	 */
	public void setBeforeRefactoring(final Metrics beforeRefactoring) {
		this.beforeRefactoring = beforeRefactoring;
		
		if (afterRefactoring != null) {
			calculateQualityChanges();
		}
	}

	/**
	 * @return the afterRefactoring
	 */
	public Metrics getAfterRefactoring() {
		return afterRefactoring;
	}

	/**
	 * @param afterRefactoring the afterRefactoring to set
	 */
	public void setAfterRefactoring(final Metrics afterRefactoring) {
		this.afterRefactoring = afterRefactoring;
		
		if (beforeRefactoring != null) {
			calculateQualityChanges();
		}
	}

	/**
	 * @return the abstractionChange
	 */
	public double getAbstractionChange() {
		return abstractionChange;
	}

	/**
	 * @param abstractionChange the abstractionChange to set
	 */
	public void setAbstractionChange(final double abstractionChange) {
		this.abstractionChange = abstractionChange;
	}

	/**
	 * @return the refactoringsApplied
	 */
	public Map<String, Integer> getRefactoringsApplied() {
		return refactoringsApplied;
	}

	/**
	 * @param refactoringsApplied the refactoringsApplied to set
	 */
	public void setRefactoringsApplied(final Map<String, Integer> refactoringsApplied) {
		this.refactoringsApplied = refactoringsApplied;
	}

	/**
	 * @return the designSizeChange
	 */
	public double getDesignSizeChange() {
		return designSizeChange;
	}

	/**
	 * @return the hierarchiesChange
	 */
	public double getHierarchiesChange() {
		return hierarchiesChange;
	}

	/**
	 * @return the encapsulationChange
	 */
	public double getEncapsulationChange() {
		return encapsulationChange;
	}

	/**
	 * @return the couplingChange
	 */
	public double getCouplingChange() {
		return couplingChange;
	}

	/**
	 * @return the cohesionChange
	 */
	public double getCohesionChange() {
		return cohesionChange;
	}

	/**
	 * @return the compositionChange
	 */
	public double getCompositionChange() {
		return compositionChange;
	}

	/**
	 * @return the inheritanceChange
	 */
	public double getInheritanceChange() {
		return inheritanceChange;
	}

	/**
	 * @return the polymorphismChange
	 */
	public double getPolymorphismChange() {
		return polymorphismChange;
	}

	/**
	 * @return the messagingChange
	 */
	public double getMessagingChange() {
		return messagingChange;
	}

	/**
	 * @return the complexityChange
	 */
	public double getComplexityChange() {
		return complexityChange;
	}

	/**
	 * @return the linesOfCodeChange
	 */
	public double getLinesOfCodeChange() {
		return linesOfCodeChange;
	}

	/**
	 * @return the reusabilityChange
	 */
	public double getReusabilityChange() {
		return reusabilityChange;
	}

	/**
	 * @return the flexabilityChange
	 */
	public double getFlexabilityChange() {
		return flexabilityChange;
	}

	/**
	 * @return the understandabiltyChange
	 */
	public double getUnderstandabiltyChange() {
		return understandabiltyChange;
	}

	/**
	 * @return the functionalityChange
	 */
	public double getFunctionalityChange() {
		return functionalityChange;
	}

	/**
	 * @return the extendabilityChange
	 */
	public double getExtendabilityChange() {
		return extendabilityChange;
	}

	/**
	 * @return the effectivenessChange
	 */
	public double getEffectivenessChange() {
		return effectivenessChange;
	}

	/**
	 * @return the qualityScoreChange
	 */
	public double getQualityScoreChange() {
		return qualityScoreChange;
	}
	
	
	/**
	 * Calculates the quality changes of the refactored code.
	 */
	public void calculateQualityChanges() {
		
		if (beforeRefactoring != null && afterRefactoring != null) {
			
			//metric changes
			designSizeChange = afterRefactoring.getDesignSize() - beforeRefactoring.getDesignSize();
			hierarchiesChange = afterRefactoring.getHierarchies() - beforeRefactoring.getHierarchies();
			abstractionChange = afterRefactoring.getAbstraction() - beforeRefactoring.getAbstraction();
			encapsulationChange = afterRefactoring.getEncapsulation() - beforeRefactoring.getEncapsulation();
			couplingChange = afterRefactoring.getCoupling() - beforeRefactoring.getCoupling();
			cohesionChange = afterRefactoring.getCohesion() - beforeRefactoring.getCohesion();
			compositionChange = afterRefactoring.getComposition() - beforeRefactoring.getComposition();
			inheritanceChange = afterRefactoring.getInheritance() - beforeRefactoring.getInheritance();
			polymorphismChange = afterRefactoring.getPolymorphism() - beforeRefactoring.getPolymorphism();
			messagingChange = afterRefactoring.getMessaging() - beforeRefactoring.getMessaging();
			complexityChange = afterRefactoring.getComplexity() - beforeRefactoring.getComplexity();
			linesOfCodeChange = afterRefactoring.getLinesOfCode() - beforeRefactoring.getLinesOfCode();
			
			//quality attribute changes
			reusabilityChange = afterRefactoring.getReusability() - beforeRefactoring.getReusability();
			flexabilityChange = afterRefactoring.getFlexability() - beforeRefactoring.getFlexability();
			understandabiltyChange = afterRefactoring.getUnderstandability() - beforeRefactoring.getUnderstandability();
			functionalityChange = afterRefactoring.getFunctionality() - beforeRefactoring.getFunctionality();
			extendabilityChange = afterRefactoring.getExtendability() - beforeRefactoring.getExtendability();
			effectivenessChange = afterRefactoring.getEffectiveness() - beforeRefactoring.getEffectiveness();
			qualityScoreChange = afterRefactoring.getQualityScore() - beforeRefactoring.getQualityScore();	
		}
	}

}
