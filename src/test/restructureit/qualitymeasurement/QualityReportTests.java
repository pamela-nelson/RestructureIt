/**
 * 
 */
package restructureit.qualitymeasurement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionality of the QualityReport Class.
 * @author Pamela
 *
 */
public class QualityReportTests {

	private QualityReport qualityReport;
	private Metrics beforeRefactoring;
	private Metrics afterRefactoring;
	private final double delta = 0.000001;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		qualityReport = new QualityReport();
		beforeRefactoring = setUpMetrics(1);
		afterRefactoring = setUpMetrics(2);
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.QualityReport#setBeforeRefactoring(restructureit.qualitymeasurement.Metrics)}.
	 */
	@Test
	public void testSetBeforeRefactoringAfterRefactoringNull() {
		qualityReport.setBeforeRefactoring(beforeRefactoring);
		
		Assert.assertEquals(0, qualityReport.getAbstractionChange(), delta);
		Assert.assertEquals(0, qualityReport.getCohesionChange(), delta);
		Assert.assertEquals(0, qualityReport.getComplexityChange(), delta);
		Assert.assertEquals(0, qualityReport.getCompositionChange(), delta);
		Assert.assertEquals(0, qualityReport.getCouplingChange(), delta);
		Assert.assertEquals(0, qualityReport.getDesignSizeChange(), delta);
		Assert.assertEquals(0, qualityReport.getEffectivenessChange(), delta);
		Assert.assertEquals(0, qualityReport.getEncapsulationChange(), delta);
		Assert.assertEquals(0, qualityReport.getExtendabilityChange(), delta);
		Assert.assertEquals(0, qualityReport.getFlexabilityChange(), delta);
		Assert.assertEquals(0, qualityReport.getFunctionalityChange(), delta);
		Assert.assertEquals(0, qualityReport.getHierarchiesChange(), delta);
		Assert.assertEquals(0, qualityReport.getInheritanceChange(), delta);
		Assert.assertEquals(0, qualityReport.getLinesOfCodeChange(), delta);
		Assert.assertEquals(0, qualityReport.getMessagingChange(), delta);
		Assert.assertEquals(0, qualityReport.getPolymorphismChange(), delta);
		Assert.assertEquals(0, qualityReport.getQualityScoreChange(), delta);
		Assert.assertEquals(0, qualityReport.getReusabilityChange(), delta);
		Assert.assertEquals(0, qualityReport.getUnderstandabiltyChange(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.QualityReport#setBeforeRefactoring(restructureit.qualitymeasurement.Metrics)}.
	 */
	@Test
	public void testSetBeforeRefactoringAfterRefactoringNotNull() {
		afterRefactoring.setLinesOfCode(2000);
		qualityReport.setAfterRefactoring(afterRefactoring);
		qualityReport.setBeforeRefactoring(beforeRefactoring);
		
		Assert.assertEquals(1, qualityReport.getAbstractionChange(), delta);
		Assert.assertEquals(1, qualityReport.getCohesionChange(), delta);
		Assert.assertEquals(1, qualityReport.getComplexityChange(), delta);
		Assert.assertEquals(1, qualityReport.getCompositionChange(), delta);
		Assert.assertEquals(1, qualityReport.getCouplingChange(), delta);
		Assert.assertEquals(1, qualityReport.getDesignSizeChange(), delta);
		Assert.assertEquals(1, qualityReport.getEffectivenessChange(), delta);
		Assert.assertEquals(1, qualityReport.getEncapsulationChange(), delta);
		Assert.assertEquals(1, qualityReport.getExtendabilityChange(), delta);
		Assert.assertEquals(1, qualityReport.getFlexabilityChange(), delta);
		Assert.assertEquals(1, qualityReport.getFunctionalityChange(), delta);
		Assert.assertEquals(1, qualityReport.getHierarchiesChange(), delta);
		Assert.assertEquals(1, qualityReport.getInheritanceChange(), delta);
		Assert.assertEquals(1000, qualityReport.getLinesOfCodeChange(), delta);
		Assert.assertEquals(1, qualityReport.getMessagingChange(), delta);
		Assert.assertEquals(1, qualityReport.getPolymorphismChange(), delta);
		Assert.assertEquals(4.01, qualityReport.getQualityScoreChange(), delta);
		Assert.assertEquals(1, qualityReport.getReusabilityChange(), delta);
		Assert.assertEquals(-0.99, qualityReport.getUnderstandabiltyChange(), delta);
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.QualityReport#setAfterRefactoring(restructureit.qualitymeasurement.Metrics)}.
	 */
	@Test
	public void testSetAfterRefactoringBeforeRefactoringNull() {
		qualityReport.setAfterRefactoring(afterRefactoring);
		
		Assert.assertEquals(0, qualityReport.getAbstractionChange(), delta);
		Assert.assertEquals(0, qualityReport.getCohesionChange(), delta);
		Assert.assertEquals(0, qualityReport.getComplexityChange(), delta);
		Assert.assertEquals(0, qualityReport.getCompositionChange(), delta);
		Assert.assertEquals(0, qualityReport.getCouplingChange(), delta);
		Assert.assertEquals(0, qualityReport.getDesignSizeChange(), delta);
		Assert.assertEquals(0, qualityReport.getEffectivenessChange(), delta);
		Assert.assertEquals(0, qualityReport.getEncapsulationChange(), delta);
		Assert.assertEquals(0, qualityReport.getExtendabilityChange(), delta);
		Assert.assertEquals(0, qualityReport.getFlexabilityChange(), delta);
		Assert.assertEquals(0, qualityReport.getFunctionalityChange(), delta);
		Assert.assertEquals(0, qualityReport.getHierarchiesChange(), delta);
		Assert.assertEquals(0, qualityReport.getInheritanceChange(), delta);
		Assert.assertEquals(0, qualityReport.getLinesOfCodeChange(), delta);
		Assert.assertEquals(0, qualityReport.getMessagingChange(), delta);
		Assert.assertEquals(0, qualityReport.getPolymorphismChange(), delta);
		Assert.assertEquals(0, qualityReport.getQualityScoreChange(), delta);
		Assert.assertEquals(0, qualityReport.getReusabilityChange(), delta);
		Assert.assertEquals(0, qualityReport.getUnderstandabiltyChange(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.QualityReport#setAfterRefactoring(restructureit.qualitymeasurement.Metrics)}.
	 */
	@Test
	public void testSetAfterRefactoringBeforeRefactoringNotNull() {
		qualityReport.setBeforeRefactoring(beforeRefactoring);
		afterRefactoring.setLinesOfCode(2000);
		qualityReport.setAfterRefactoring(afterRefactoring);
		
		Assert.assertEquals(1, qualityReport.getAbstractionChange(), delta);
		Assert.assertEquals(1, qualityReport.getCohesionChange(), delta);
		Assert.assertEquals(1, qualityReport.getComplexityChange(), delta);
		Assert.assertEquals(1, qualityReport.getCompositionChange(), delta);
		Assert.assertEquals(1, qualityReport.getCouplingChange(), delta);
		Assert.assertEquals(1, qualityReport.getDesignSizeChange(), delta);
		Assert.assertEquals(1, qualityReport.getEffectivenessChange(), delta);
		Assert.assertEquals(1, qualityReport.getEncapsulationChange(), delta);
		Assert.assertEquals(1, qualityReport.getExtendabilityChange(), delta);
		Assert.assertEquals(1, qualityReport.getFlexabilityChange(), delta);
		Assert.assertEquals(1, qualityReport.getFunctionalityChange(), delta);
		Assert.assertEquals(1, qualityReport.getHierarchiesChange(), delta);
		Assert.assertEquals(1, qualityReport.getInheritanceChange(), delta);
		Assert.assertEquals(1000, qualityReport.getLinesOfCodeChange(), delta);
		Assert.assertEquals(1, qualityReport.getMessagingChange(), delta);
		Assert.assertEquals(1, qualityReport.getPolymorphismChange(), delta);
		Assert.assertEquals(4.01, qualityReport.getQualityScoreChange(), delta);
		Assert.assertEquals(1, qualityReport.getReusabilityChange(), delta);
		Assert.assertEquals(-0.99, qualityReport.getUnderstandabiltyChange(), delta);
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.QualityReport#calculateQualityChanges()}.
	 */
	@Test
	public void testCalculateQualityChangesAllRequiredValues() {
		qualityReport.setBeforeRefactoring(beforeRefactoring);
		afterRefactoring.setLinesOfCode(2000);
		qualityReport.setAfterRefactoring(afterRefactoring);
		qualityReport.calculateQualityChanges();
		
		Assert.assertEquals(1, qualityReport.getAbstractionChange(), delta);
		Assert.assertEquals(1, qualityReport.getCohesionChange(), delta);
		Assert.assertEquals(1, qualityReport.getComplexityChange(), delta);
		Assert.assertEquals(1, qualityReport.getCompositionChange(), delta);
		Assert.assertEquals(1, qualityReport.getCouplingChange(), delta);
		Assert.assertEquals(1, qualityReport.getDesignSizeChange(), delta);
		Assert.assertEquals(1, qualityReport.getEffectivenessChange(), delta);
		Assert.assertEquals(1, qualityReport.getEncapsulationChange(), delta);
		Assert.assertEquals(1, qualityReport.getExtendabilityChange(), delta);
		Assert.assertEquals(1, qualityReport.getFlexabilityChange(), delta);
		Assert.assertEquals(1, qualityReport.getFunctionalityChange(), delta);
		Assert.assertEquals(1, qualityReport.getHierarchiesChange(), delta);
		Assert.assertEquals(1, qualityReport.getInheritanceChange(), delta);
		Assert.assertEquals(1000, qualityReport.getLinesOfCodeChange(), delta);
		Assert.assertEquals(1, qualityReport.getMessagingChange(), delta);
		Assert.assertEquals(1, qualityReport.getPolymorphismChange(), delta);
		Assert.assertEquals(4.01, qualityReport.getQualityScoreChange(), delta);
		Assert.assertEquals(1, qualityReport.getReusabilityChange(), delta);
		Assert.assertEquals(-0.99, qualityReport.getUnderstandabiltyChange(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.QualityReport#calculateQualityChanges()}.
	 */
	@Test
	public void testCalculateQualityChangesMissingRequiredValues() {
		qualityReport.setAfterRefactoring(afterRefactoring);
		qualityReport.calculateQualityChanges();
		
		Assert.assertEquals(0, qualityReport.getAbstractionChange(), delta);
		Assert.assertEquals(0, qualityReport.getCohesionChange(), delta);
		Assert.assertEquals(0, qualityReport.getComplexityChange(), delta);
		Assert.assertEquals(0, qualityReport.getCompositionChange(), delta);
		Assert.assertEquals(0, qualityReport.getCouplingChange(), delta);
		Assert.assertEquals(0, qualityReport.getDesignSizeChange(), delta);
		Assert.assertEquals(0, qualityReport.getEffectivenessChange(), delta);
		Assert.assertEquals(0, qualityReport.getEncapsulationChange(), delta);
		Assert.assertEquals(0, qualityReport.getExtendabilityChange(), delta);
		Assert.assertEquals(0, qualityReport.getFlexabilityChange(), delta);
		Assert.assertEquals(0, qualityReport.getFunctionalityChange(), delta);
		Assert.assertEquals(0, qualityReport.getHierarchiesChange(), delta);
		Assert.assertEquals(0, qualityReport.getInheritanceChange(), delta);
		Assert.assertEquals(0, qualityReport.getLinesOfCodeChange(), delta);
		Assert.assertEquals(0, qualityReport.getMessagingChange(), delta);
		Assert.assertEquals(0, qualityReport.getPolymorphismChange(), delta);
		Assert.assertEquals(0, qualityReport.getQualityScoreChange(), delta);
		Assert.assertEquals(0, qualityReport.getReusabilityChange(), delta);
		Assert.assertEquals(0, qualityReport.getUnderstandabiltyChange(), delta);
	}
	
	//Private Method
	
	private Metrics setUpMetrics(int value) {
		Metrics metrics = new Metrics();
		
		metrics.setCoupling(value);
		metrics.setCohesion(value);
		metrics.setMessaging(value);
		metrics.setDesignSize(value);
		metrics.setHierarchies(value);
		metrics.setAbstraction(value);
		metrics.setComposition(value);
		metrics.setInheritance(value);
		metrics.setPolymorphism(value);
		metrics.setComplexity(value);
		metrics.setEncapsulation(value);
		metrics.setLinesOfCode(1000);
		
		metrics.calculateQualityAttributes();
		
		return metrics;
	}

}
