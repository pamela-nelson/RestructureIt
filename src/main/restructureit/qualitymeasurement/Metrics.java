package restructureit.qualitymeasurement;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Metrics stores the components of the QMOOD Quality attributes and calculates the scores.
 * Attributes include: Reusability, Flexability, Understandability, Functionality, Extendability and Effectiveness.
 * An overall quality score is calculated here also.
 * 
 * @author Pamela
 *
 */
public class Metrics implements Serializable {
	
	//METRICS
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Design Size in Class(DSC): Total number of classes in the design.
	 */
	private double designSize;
	
	/**
	 * Number of hierarchies(NOH): Total number of class hierarchies in the design.
	 */
	private double hierarchies;
	
	/**
	 * Average Number of Ancestors(ANA): Average number of classes from which a class inherits information.
	 */
	private double abstraction;
	
	/**
	 * Data Access Metrics (DAM): Ratio of the number of private attributes to the total number of attributes declared in the class.
	 */
	private double encapsulation;
	
	/**
	 * Direct Class Coupling(DCC): Total count of different number of classes that a class is directly related to.
	 */
	private double coupling;
	
	/**
	 * Cohesion Among Methods in Class(CAM): Relatedness among methods of a class based upon the parameter list of methods.
	 */
	private double cohesion;
	
	/**
	 * Measure of Aggregation(MOA): Extent of part-whole relationship, realized by using attributes.
	 */
	private double composition;
	
	/**
	 * Measure of Functional Abstraction(MFA): Ratio of the number of methods inherited by a class to the total number of methods accessible by member methods of the class.
	 */
	private double inheritance;
	
	/**
	 * Number of Polymorphic Methods(NOP): Count of the methods that can exhibit polymorphic behaviour.
	 */
	private double polymorphism;
	
	/**
	 * Class Interface Size(CIS): Count of the number of public methods in a class.
	 */
	private double messaging;
	
	/**
	 * Number of Methods(NOM): Count of all the methods defined in a class.
	 */
	private double complexity;
	
	/**
	 * Lines of code (LOC): count of the total lines of code in the project.
	 */
	private int linesOfCode;
	
	//QUALITY ATTRIBUTES
	
	/**
	 * Score of how reusable a program is.
	 * (-0.25 * Coupling) + (0.25 * Cohesion) + (0.5 * Messaging) + (0.5 * Design Size)
	 */
	private double reusability;
	
	/**
	 * Score of how flexible a program is.
	 * (0.25 * Encapsulation) – (0.25 * Coupling) + (0.5 * Composition) + (0.5 * Polymorphism)
	 */
	private double flexability;
	
	
	/**
	 * Score of how understandable the program is.
	 * (-0.33 * Abstraction) + (0.33 * Encapsulation) – (0.33 * Coupling) + (0.33 * Cohesion) – (0.33 * Polymorphism)
     * – (0.33 * Complexity) – (0.33 * Design Size)
	 */
	private double understandability;
	
	/**
	 * Score for the functionality of the program.
	 * (0.12 * Cohesion) + (0.22 * Polymorphism) + (0.22 * Messaging) + (0.22 * Design Size) + (0.22 * Hierarchies)
	 */
	private double functionality;
	
	
	/**
	 * Score for how extendable a program is.
	 * (0.5 * Abstraction) – (0.5 * Coupling) + (0.5 * Inheritance) + (0.5 * Polymorphism)
	 */
	private double extendability;
	
	/**
	 * Score for effective the code of the program is.
	 * (0.2 * Abstraction) + (0.2 * Encapsulation) + (0.2 * Composition) + (0.2 * Inheritance) + (0.2 * Polymorphism)
	 */
	private double effectiveness;
	
	/**
	 * A score for the program that combines all the Quality Attribute scores.
	 */
	private double qualityScore;
	
	//FLAGS
	
	/**
	 * A list to indicate which quality metrics have been set.
	 */
	private ArrayList<Boolean> qualityMetricIsSet;
	
	/**
	 * A list to indicate which quality attributes have been calculated.
	 */
	private ArrayList<Boolean> qualityAttributeIsCalculated;
	
	/**
	 * Number of quality metrics.
	 */
	private final int numMetrics = 11;
	
	/**
	 * Number of quality attributes.
	 */
	private final int numQualityAttributes = 6;
	
	/**
	 * Enums representing the location of the metric in isSet list.
	 */
	private enum MetricLoc { DESIGN_SIZE, HIERARCHIES, ABSTRACTION, ENCAPSULATION, COUPLING, COHESION, COMPOSITION, INHERITANCE, POLYMORPHISM, MESSAGING, COMPLEXITY }
	
	 /**
	 * Enums representing the location of the quality attribute in qualityAttributeIsCalculated list.
	 */
	private enum AttributeLoc { REUSABILITY, FLEXABILITY, UNDERSTANDABILITY, FUNCTIONALITY, EXTENDIBILITY, EFFECTIVENESS }
	
	//CONSTRUCTOR
	
	/**
	 * Creates a new instance of Metrics.
	 */
	public Metrics() {
		qualityMetricIsSet = new ArrayList<Boolean>();
		qualityAttributeIsCalculated = new ArrayList<Boolean>();
		
		for (int i = 0; i < numMetrics; i++) {
			qualityMetricIsSet.add(false);
		}
		
		for (int i = 0; i < numQualityAttributes; i++) {
			qualityAttributeIsCalculated.add(false);
		}
	}
	
	
	//GETTERS AND SETTERS
	
	/**
	 * @return the designSize
	 */
	public double getDesignSize() {
		
		return designSize;
	}
	
	/**
	 * @param designSize the designSize to set
	 */
	public void setDesignSize(final double designSize) {
		if (designSize > 0) {
			this.designSize = designSize;
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.REUSABILITY.ordinal())) {
				calculateReusability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.UNDERSTANDABILITY.ordinal())) {
				calculateUnderstandability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.FUNCTIONALITY.ordinal())) {
				calculateFunctionality();
				calculateQualityScore();
			}
			
			qualityMetricIsSet.set(MetricLoc.DESIGN_SIZE.ordinal(), true);
		}
	}
	
	/**
	 * @return the hierarchies
	 */
	public double getHierarchies() {
		return hierarchies;
	}
	
	/**
	 * @param hierarchies the hierarchies to set
	 */
	public void setHierarchies(final double hierarchies) {
		if (hierarchies >= 0) {
			this.hierarchies = hierarchies;
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.FUNCTIONALITY.ordinal())) {
				calculateFunctionality();
				calculateQualityScore();
			}
			
			qualityMetricIsSet.set(MetricLoc.HIERARCHIES.ordinal(), true);
		}
		
	}
	
	/**
	 * @return the abstraction
	 */
	public double getAbstraction() {
		return abstraction;
	}
	
	/**
	 * @param abstraction the abstraction to set
	 */
	public void setAbstraction(final double abstraction) {
		if (abstraction >= 0) {
			this.abstraction = abstraction;
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.UNDERSTANDABILITY.ordinal())) {
				calculateUnderstandability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.EXTENDIBILITY.ordinal())) {
				calculateExtendability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.EFFECTIVENESS.ordinal())) {
				calculateEffectiveness();
				calculateQualityScore();
			}
			
			qualityMetricIsSet.set(MetricLoc.ABSTRACTION.ordinal(), true);
		}
	}
	
	/**
	 * @return the encapsulation
	 */
	public double getEncapsulation() {
		return encapsulation;
	}
	
	/**
	 * @param encapsulation the encapsulation to set
	 */
	public void setEncapsulation(final double encapsulation) {
		if (encapsulation > 0) {
			this.encapsulation = encapsulation;
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.FLEXABILITY.ordinal())) {
				calculateFlexability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.UNDERSTANDABILITY.ordinal())) {
				calculateUnderstandability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.EFFECTIVENESS.ordinal())) {
				calculateEffectiveness();
				calculateQualityScore();
			}
			
			qualityMetricIsSet.set(MetricLoc.ENCAPSULATION.ordinal(), true);
		}
		
		
	}
	
	/**
	 * @return the coupling
	 */
	public double getCoupling() {
		return coupling;
	}
	
	/**
	 * @param coupling the coupling to set
	 */
	public void setCoupling(final double coupling) {
		if (coupling >= 0) {
			this.coupling = coupling;
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.REUSABILITY.ordinal())) {
				calculateReusability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.FLEXABILITY.ordinal())) {
				calculateFlexability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.UNDERSTANDABILITY.ordinal())) {
				calculateUnderstandability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.EXTENDIBILITY.ordinal())) {
				calculateExtendability();
				calculateQualityScore();
			}
			
			qualityMetricIsSet.set(MetricLoc.COUPLING.ordinal(), true);
		}
	}
	
	/**
	 * @return the cohesion
	 */
	public double getCohesion() {
		return cohesion;
	}
	
	/**
	 * @param cohesion the cohesion to set
	 */
	public void setCohesion(final double cohesion) {
		if (cohesion >= 0) {
			this.cohesion = cohesion;
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.REUSABILITY.ordinal())) {
				calculateReusability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.UNDERSTANDABILITY.ordinal())) {
				calculateUnderstandability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.FUNCTIONALITY.ordinal())) {
				calculateFunctionality();
				calculateQualityScore();
			}
			
			qualityMetricIsSet.set(MetricLoc.COHESION.ordinal(), true);
		}
	}
	
	/**
	 * @return the composition
	 */
	public double getComposition() {
		return composition;
	}
	
	/**
	 * @param composition the composition to set
	 */
	public void setComposition(final double composition) {
		if (composition >= 0) {
			this.composition = composition;
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.FLEXABILITY.ordinal())) {
				calculateFlexability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.EFFECTIVENESS.ordinal())) {
				calculateEffectiveness();
				calculateQualityScore();
			}
			
			qualityMetricIsSet.set(MetricLoc.COMPOSITION.ordinal(), true);
		}
	}
	
	/**
	 * @return the inheritance
	 */
	public double getInheritance() {
		return inheritance;
	}
	/**
	 * @param inheritance the inheritance to set
	 */
	public void setInheritance(final double inheritance) {
		if (inheritance > 0) {
			this.inheritance = inheritance;
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.EXTENDIBILITY.ordinal())) {
				calculateExtendability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.EFFECTIVENESS.ordinal())) {
				calculateEffectiveness();
				calculateQualityScore();
			}
			
			qualityMetricIsSet.set(MetricLoc.INHERITANCE.ordinal(), true);
		}
	}
	
	/**
	 * @return the polymorphism
	 */
	public double getPolymorphism() {
		return polymorphism;
	}
	
	/**
	 * @param polymorphism the polymorphism to set
	 */
	public void setPolymorphism(final double polymorphism) {
		if (polymorphism >= 0) {
			this.polymorphism = polymorphism;
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.FLEXABILITY.ordinal())) {
				calculateFlexability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.UNDERSTANDABILITY.ordinal())) {
				calculateUnderstandability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.FUNCTIONALITY.ordinal())) {
				calculateFunctionality();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.EXTENDIBILITY.ordinal())) {
				calculateExtendability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.EFFECTIVENESS.ordinal())) {
				calculateEffectiveness();
				calculateQualityScore();
			}
			
			qualityMetricIsSet.set(MetricLoc.POLYMORPHISM.ordinal(), true);
		}
	}
	
	/**
	 * @return the messaging
	 */
	public double getMessaging() {
		return messaging;
	}
	
	/**
	 * @param messaging the messaging to set
	 */
	public void setMessaging(final double messaging) {
		if (messaging >= 0) {
			this.messaging = messaging;
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.REUSABILITY.ordinal())) {
				calculateReusability();
				calculateQualityScore();
			}
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.FUNCTIONALITY.ordinal())) {
				calculateFunctionality();
				calculateQualityScore();
			}
			
			qualityMetricIsSet.set(MetricLoc.MESSAGING.ordinal(), true);
		}
	}
	
	/**
	 * @return the complexity
	 */
	public double getComplexity() {
		return complexity;
	}
	
	/**
	 * @param complexity the complexity to set
	 */
	public void setComplexity(final double complexity) {
		if (complexity > 0) {
			this.complexity = complexity;
			
			if (qualityAttributeIsCalculated.get(AttributeLoc.UNDERSTANDABILITY.ordinal())) {
				calculateUnderstandability();
				calculateQualityScore();
			}
			
			qualityMetricIsSet.set(MetricLoc.COMPLEXITY.ordinal(), true);
		}
	}
	
	/**
	 * @return the linesOfCode
	 */
	public int getLinesOfCode() {
		return linesOfCode;
	}


	/**
	 * @param linesOfCode the linesOfCode to set
	 */
	public void setLinesOfCode(final int linesOfCode) {
		this.linesOfCode = linesOfCode;
	}


	/**
	 * @return the reusability
	 */
	public double getReusability() {
		return reusability;
	}
	
	/**
	 * @return the flexability
	 */
	public double getFlexability() {
		return flexability;
	}
	
	/**
	 * @return the understandability
	 */
	public double getUnderstandability() {
		return understandability;
	}
	
	/**
	 * @return the functionality
	 */
	public double getFunctionality() {
		return functionality;
	}
	
	/**
	 * @return the extendability
	 */
	public double getExtendability() {
		return extendability;
	}
	
	/**
	 * @return the effectiveness
	 */
	public double getEffectiveness() {
		return effectiveness;
	}

	
	/**
	 * @return the qualityScore
	 */
	public double getQualityScore() {
		return qualityScore;
	}
	
	//PUBLIC METHODS
	
	/**
	 * Calculates all Quality Attributes scores.
	 */
	public void calculateQualityAttributes() {
		calculateReusability();
		calculateFlexability();
		calculateUnderstandability();
		calculateFunctionality();
		calculateExtendability();
		calculateEffectiveness();
		calculateQualityScore();
	}
	
	/**
	 * Indicates if all Quality attributes have been calculated.
	 * @return boolean indicating whether all quality attributes are calculated
	 */
	public boolean isAllQualityAttributesCalculated() {
		return !qualityAttributeIsCalculated.contains(false);
	}
	
	//PRIVATE METHODS
	
	/**
	 * Calculates Reusability Score.
	 */
	private void calculateReusability() {
		//Check all needed values are set
		if (qualityMetricIsSet.get(MetricLoc.COUPLING.ordinal()) 
			&& qualityMetricIsSet.get(MetricLoc.COHESION.ordinal()) 
			&& qualityMetricIsSet.get(MetricLoc.MESSAGING.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.DESIGN_SIZE.ordinal())) {
			
			reusability = (-0.25 * coupling) + (0.25 * cohesion) + (0.5 * messaging) + (0.5 * designSize);
			
			//set calculated boolean
			qualityAttributeIsCalculated.set(AttributeLoc.REUSABILITY.ordinal(), true);
		}
	}
	
	/**
	 * Calculates Flexability Score.
	 */
	private void calculateFlexability() {
		//Check all needed values are set
		if (qualityMetricIsSet.get(MetricLoc.ENCAPSULATION.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.COUPLING.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.COMPOSITION.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.POLYMORPHISM.ordinal())) {
			
			flexability = (0.25 * encapsulation) - (0.25 * coupling) + (0.5 * composition) + (0.5 * polymorphism);
			
			//set calculated boolean
			qualityAttributeIsCalculated.set(AttributeLoc.FLEXABILITY.ordinal(), true);
		}
	}
	
	/**
	 * Calculates Understandability Score.
	 */
	private void calculateUnderstandability() {
		//Check all needed values are set
		if (qualityMetricIsSet.get(MetricLoc.ABSTRACTION.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.ENCAPSULATION.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.COUPLING.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.COHESION.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.POLYMORPHISM.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.COMPLEXITY.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.DESIGN_SIZE.ordinal())) {
			
			understandability = (-0.33 * abstraction) + (0.33 * encapsulation) - (0.33 * coupling) + (0.33 * cohesion)
								 - (0.33 * polymorphism) - (0.33 * complexity) - (0.33 * designSize);
			
			//set calculated boolean
			qualityAttributeIsCalculated.set(AttributeLoc.UNDERSTANDABILITY.ordinal(), true);
		}
	}
	
	/**
	 * Calculates Functionality Score.
	 */
	private void calculateFunctionality() {
		//Check all needed values are set
		if (qualityMetricIsSet.get(MetricLoc.COHESION.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.POLYMORPHISM.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.MESSAGING.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.DESIGN_SIZE.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.HIERARCHIES.ordinal())) {
			
			functionality = (0.12 * cohesion) + (0.22 * polymorphism) + (0.22 * messaging) + (0.22 * designSize) + (0.22 * hierarchies);
			
			//set calculated boolean
			qualityAttributeIsCalculated.set(AttributeLoc.FUNCTIONALITY.ordinal(), true);
		}
	}
	
	/**
	 * Calculate Extendability Score.
	 */
	private void calculateExtendability() {
		//Check all needed values are set
		if (qualityMetricIsSet.get(MetricLoc.ABSTRACTION.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.COUPLING.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.INHERITANCE.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.POLYMORPHISM.ordinal())) {
			
			extendability = (0.5 * abstraction) - (0.5 * coupling) + (0.5 * inheritance) + (0.5 * polymorphism);
			
			//set calculated boolean
			qualityAttributeIsCalculated.set(AttributeLoc.EXTENDIBILITY.ordinal(), true);
		}
	}
	
	/**
	 * Calculate Effectiveness Score.
	 */
	private void calculateEffectiveness() {
		//Check all needed values are set
		if (qualityMetricIsSet.get(MetricLoc.ABSTRACTION.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.ENCAPSULATION.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.COMPOSITION.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.INHERITANCE.ordinal())
			&& qualityMetricIsSet.get(MetricLoc.POLYMORPHISM.ordinal())) {
			
			effectiveness = (0.2 * abstraction) + (0.2 * encapsulation) + (0.2 * composition) + (0.2 * inheritance) + (0.2 * polymorphism);
			
			//set calculated boolean
			qualityAttributeIsCalculated.set(AttributeLoc.EFFECTIVENESS.ordinal(), true);
		}
	}
	
	/**
	 * Calculate Total Quality Score.
	 */
	private void calculateQualityScore() {
		if (isAllQualityAttributesCalculated()) {
				qualityScore = reusability + understandability + flexability + functionality + extendability + effectiveness;
			}
	}
}
