/**
 * 
 */
package restructureit.qualitymeasurement;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionality of the Metrics Class.
 * @author Pamela
 * 
 */
public class MetricsTests {

	private Metrics metrics;
	private final double delta = 0.000001;

	/**
	 * Sets up the metrics object for each test.
	 */
	@Before
	public void setUp() {
		metrics = new Metrics();
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setDesignSize(double)}.
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetDesignSizeAttributesNotCalculated() throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		metrics.setDesignSize(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("DESIGN_SIZE")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getDesignSize(), 0);
		Assert.assertEquals("Reusability should not be updated", 0, metrics.getReusability(), delta);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Functionality should not be updated", 0, metrics.getFunctionality(), delta);
		Assert.assertEquals(0, metrics.getQualityScore(), delta);
		Assert.assertTrue("Design Size should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setDesignSize(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetDesignSizeAttributesCalculated() throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		setUpDesignAttributes();
		
		metrics.setDesignSize(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("DESIGN_SIZE")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getDesignSize(), 0);
		Assert.assertEquals("Reusability should be updated", 3, metrics.getReusability(), delta);
		Assert.assertEquals("Understandability should be updated", -2.31, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Functionality should be updated", 1.88, metrics.getFunctionality(), delta);
		Assert.assertTrue("Design Size should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setDesignSize(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetDesignSizeAttributesInvalidValue() throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		metrics.setDesignSize(-30);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("DESIGN_SIZE")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(0, metrics.getDesignSize(), 0);
		Assert.assertEquals("Reusability should not be updated", 0, metrics.getReusability(), delta);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Functionality should not be updated", 0, metrics.getFunctionality(), delta);
		Assert.assertEquals(0, metrics.getQualityScore(), delta);
		Assert.assertFalse("Design Size should be set as false", qualityAttributeIsCalculatedValue.get(enumPosition));
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setHierarchies(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetHierarchiesAttributesCalculated() throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		setUpDesignAttributes();
		
		metrics.setHierarchies(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("HIERARCHIES")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getHierarchies(), 0);
		Assert.assertEquals("Functionality should be updated", 1.88, metrics.getFunctionality(), delta);
		Assert.assertTrue("Hierarchies should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setHierarchies(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetHierarchiesAttributesNotCalculated() throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		metrics.setHierarchies(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("HIERARCHIES")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getHierarchies(), 0);
		Assert.assertEquals("Functionality should not be updated", 0, metrics.getFunctionality(), delta);
		Assert.assertEquals(0, metrics.getQualityScore(), delta);
		Assert.assertTrue("Hierarchies should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setHierarchies(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetHierarchiesInvalidValue() throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		metrics.setHierarchies(-5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("HIERARCHIES")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(0, metrics.getHierarchies(), 0);
		Assert.assertEquals("Functionality should not be updated", 0, metrics.getFunctionality(), delta);
		Assert.assertFalse("Design Size should be set as false", qualityAttributeIsCalculatedValue.get(enumPosition));
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setAbstraction(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetAbstractionAttributesCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		setUpDesignAttributes();
		
		metrics.setAbstraction(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("ABSTRACTION")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getAbstraction(), 0);
		Assert.assertEquals("Understandability should be updated", -2.31, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Extendability should be updated", 3, metrics.getExtendability(), delta);
		Assert.assertEquals("Effectiveness should be updated", 1.8, metrics.getEffectiveness(), delta);
		Assert.assertTrue("Abstraction should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setAbstraction(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetAbstractionAttributesNotCalculated() throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		
		metrics.setAbstraction(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("ABSTRACTION")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getAbstraction(), 0);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Extendability should not be updated", 0, metrics.getExtendability(), delta);
		Assert.assertEquals("Effectiveness should not be updated", 0, metrics.getEffectiveness(), delta);
		Assert.assertTrue("Abstraction should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setAbstraction(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetAbstractionInvalidValue() throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		metrics.setAbstraction(-5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("ABSTRACTION")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(0, metrics.getAbstraction(), 0);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Extendability should not be updated", 0, metrics.getExtendability(), delta);
		Assert.assertEquals("Effectiveness should not be updated", 0, metrics.getEffectiveness(), delta);
		Assert.assertFalse("Abstraction should be set as false", qualityAttributeIsCalculatedValue.get(enumPosition));
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setEncapsulation(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetEncapsulationAttributesCalculated() throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		setUpDesignAttributes();
		
		metrics.setEncapsulation(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("ENCAPSULATION")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getEncapsulation(), 0);
		Assert.assertEquals("Understandability should be updated", 0.33, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Flexability should be updated", 2, metrics.getFlexability(), delta);
		Assert.assertEquals("Effectiveness should be updated", 1.8, metrics.getEffectiveness(), delta);
		Assert.assertTrue("Encapsulation should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setEncapsulation(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetEncapsulationAttributesNotCalculated() throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		metrics.setEncapsulation(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("ENCAPSULATION")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getEncapsulation(), 0);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Flexability should not be updated", 0, metrics.getFlexability(), delta);
		Assert.assertEquals("Effectiveness should not be updated", 0, metrics.getEffectiveness(), delta);
		Assert.assertTrue("Encapsulation should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setEncapsulation(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetEncapsulationInvalidValue() throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		metrics.setEncapsulation(-5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("ENCAPSULATION")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(0, metrics.getEncapsulation(), 0);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Flexability should not be updated", 0, metrics.getFlexability(), delta);
		Assert.assertEquals("Effectiveness should not be updated", 0, metrics.getEffectiveness(), delta);
		Assert.assertFalse("Encapsulation should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setCoupling(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetCouplingAttributesCalculated() throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		setUpDesignAttributes();
		
		metrics.setCoupling(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("COUPLING")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getCoupling(), 0);
		Assert.assertEquals("Understandability should be updated", -2.31, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Flexability should be updated", 0, metrics.getFlexability(), delta);
		Assert.assertEquals("Extendability should be updated", -1, metrics.getExtendability(), delta);
		Assert.assertEquals("Reusability should be updated", 0, metrics.getReusability(), delta);
		Assert.assertTrue("Coupling should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setCoupling(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void testSetCouplingAttributesNotCalculated() throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		
		metrics.setCoupling(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("COUPLING")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getCoupling(), 0);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Flexability should not be updated", 0, metrics.getFlexability(), delta);
		Assert.assertEquals("Extendability should not be updated", 0, metrics.getExtendability(), delta);
		Assert.assertEquals("Reusability should not be updated", 0, metrics.getReusability(), delta);
		Assert.assertTrue("Coupling should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setCoupling(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetCouplingInvalidValue() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		
		metrics.setCoupling(-5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("COUPLING")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(0, metrics.getCoupling(), 0);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Flexability should not be updated", 0, metrics.getFlexability(), delta);
		Assert.assertEquals("Extendability should not be updated", 0, metrics.getExtendability(), delta);
		Assert.assertEquals("Reusability should not be updated", 0, metrics.getReusability(), delta);
		Assert.assertFalse("Coupling should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setCohesion(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetCohesionAttributesCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		setUpDesignAttributes();
		
		metrics.setCohesion(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("COHESION")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getCohesion(), 0);
		Assert.assertEquals("Understandability should be updated", 0.33, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Functionality should be updated", 1.48, metrics.getFunctionality(), delta);
		Assert.assertEquals("Reusability should be updated", 2, metrics.getReusability(), delta);
		Assert.assertTrue("Cohesion should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setCohesion(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetCohesionAttributesNotCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		
		metrics.setCohesion(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("COHESION")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getCohesion(), 0);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Functionality should not be updated", 0, metrics.getFunctionality(), delta);
		Assert.assertEquals("Reusability should not be updated", 0, metrics.getReusability(), delta);
		Assert.assertTrue("Cohesion should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setCohesion(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetCohesionInvalidValue() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		metrics.setCohesion(-5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("COHESION")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(0, metrics.getCohesion(), 0);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Functionality should not be updated", 0, metrics.getFunctionality(), delta);
		Assert.assertEquals("Reusability should not be updated", 0, metrics.getReusability(), delta);
		Assert.assertFalse("Cohesion should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setComposition(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetCompositionAttributesCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		setUpDesignAttributes();
		
		metrics.setComposition(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("COMPOSITION")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getComposition(), 0);
		Assert.assertEquals("Flexability should be updated", 3, metrics.getFlexability(), delta);
		Assert.assertEquals("Effectiveness should be updated", 1.8, metrics.getEffectiveness(), delta);
		Assert.assertTrue("Compositon should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setComposition(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetCompositionAttributesNotCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		
		metrics.setComposition(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("COMPOSITION")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getComposition(), 0);
		Assert.assertEquals("Flexability should be not updated", 0, metrics.getFlexability(), delta);
		Assert.assertEquals("Effectiveness should not be updated", 0, metrics.getEffectiveness(), delta);
		Assert.assertTrue("Compositon should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setComposition(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetCompositionInvalidValue() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		metrics.setComposition(-5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("COMPOSITION")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(0, metrics.getComposition(), 0);
		Assert.assertEquals("Flexability should be not updated", 0, metrics.getFlexability(), delta);
		Assert.assertEquals("Effectiveness should not be updated", 0, metrics.getEffectiveness(), delta);
		Assert.assertFalse("Compositon should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setInheritance(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetInheritanceAttributesCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		setUpDesignAttributes();
		
		metrics.setInheritance(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("INHERITANCE")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getInheritance(), 0);
		Assert.assertEquals("Extendability should be updated", 3, metrics.getExtendability(), delta);
		Assert.assertEquals("Effectiveness should be updated", 1.8, metrics.getEffectiveness(), delta);
		Assert.assertTrue("Inheritance should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setInheritance(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetInheritanceAttributesNotCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		
		metrics.setInheritance(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("INHERITANCE")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getInheritance(), 0);
		Assert.assertEquals("Extendability should not be updated", 0, metrics.getExtendability(), delta);
		Assert.assertEquals("Effectiveness should not be updated", 0, metrics.getEffectiveness(), delta);
		Assert.assertTrue("Inheritance should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setInheritance(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetInheritanceInvalidValue() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		metrics.setInheritance(-5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("INHERITANCE")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(0, metrics.getInheritance(), 0);
		Assert.assertEquals("Extendability should not be updated", 0, metrics.getExtendability(), delta);
		Assert.assertEquals("Effectiveness should not be updated", 0, metrics.getEffectiveness(), delta);
		Assert.assertFalse("Inheritance should be set as false", qualityAttributeIsCalculatedValue.get(enumPosition));
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setPolymorphism(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetPolymorphismAttributesCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		setUpDesignAttributes();
		
		metrics.setPolymorphism(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("POLYMORPHISM")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getPolymorphism(), 0);
		Assert.assertEquals("Extendability should be updated", 3, metrics.getExtendability(), delta);
		Assert.assertEquals("Effectiveness should be updated", 1.8, metrics.getEffectiveness(), delta);
		Assert.assertEquals("Flexability should be updated", 3, metrics.getFlexability(), delta);
		Assert.assertEquals("Understandability should be updated", -2.31, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Functionality should be updated", 1.88, metrics.getFunctionality(), delta);
		Assert.assertTrue("Polymorphism should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setPolymorphism(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetPolymorphismAttributesNotCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		metrics.setPolymorphism(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("POLYMORPHISM")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getPolymorphism(), 0);
		Assert.assertEquals("Extendability should not be updated", 0, metrics.getExtendability(), delta);
		Assert.assertEquals("Effectiveness should not be updated", 0, metrics.getEffectiveness(), delta);
		Assert.assertEquals("Flexability should not be updated", 0, metrics.getFlexability(), delta);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Functionality should not be updated", 0, metrics.getFunctionality(), delta);
		Assert.assertTrue("Polymorphism should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setPolymorphism(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetPolymorphismInvalidValue() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		metrics.setPolymorphism(-5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("POLYMORPHISM")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(0, metrics.getPolymorphism(), 0);
		Assert.assertEquals("Extendability should not be updated", 0, metrics.getExtendability(), delta);
		Assert.assertEquals("Effectiveness should not be updated", 0, metrics.getEffectiveness(), delta);
		Assert.assertEquals("Flexability should not be updated", 0, metrics.getFlexability(), delta);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertEquals("Functionality should not be updated", 0, metrics.getFunctionality(), delta);
		Assert.assertFalse("Polymorphism should be set as false", qualityAttributeIsCalculatedValue.get(enumPosition));
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setMessaging(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetMessagingCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		setUpDesignAttributes();
		
		metrics.setMessaging(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("MESSAGING")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getMessaging(), 0);
		Assert.assertEquals("Reusability should be updated", 3, metrics.getReusability(), delta);
		Assert.assertEquals("Functionality should be updated", 1.88, metrics.getFunctionality(), delta);
		Assert.assertTrue("Messaging should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setMessaging(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetMessagingNotCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {

		metrics.setMessaging(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("MESSAGING")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getMessaging(), 0);
		Assert.assertEquals("Reusability should not be updated", 0, metrics.getReusability(), delta);
		Assert.assertEquals("Functionality should not be updated", 0, metrics.getFunctionality(), delta);
		Assert.assertTrue("Messaging should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setMessaging(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetMessagingInvalidValue() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		
		metrics.setMessaging(-5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("MESSAGING")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(0, metrics.getMessaging(), 0);
		Assert.assertEquals("Reusability should not be updated", 0, metrics.getReusability(), delta);
		Assert.assertEquals("Functionality should not be updated", 0, metrics.getFunctionality(), delta);
		Assert.assertFalse("Messaging should be set as false", qualityAttributeIsCalculatedValue.get(enumPosition));
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setComplexity(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetComplexityAttributesCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		setUpDesignAttributes();
		
		metrics.setComplexity(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("COMPLEXITY")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getComplexity(), 0);
		Assert.assertEquals("Understandability should be updated", -2.31, metrics.getUnderstandability(), delta);
		Assert.assertTrue("Messaging should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setComplexity(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetComplexityAttributesNotCalculated() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		metrics.setComplexity(5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("COMPLEXITY")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(5, metrics.getComplexity(), 0);
		Assert.assertEquals("Understandability should not be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertTrue("Messaging should be set as true", qualityAttributeIsCalculatedValue.get(enumPosition));
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#setComplexity(double)}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSetComplexityAttributesInvalidValue() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		metrics.setComplexity(-5);
		
		Field qualityMetricIsSetInstance = Metrics.class.getDeclaredField("qualityMetricIsSet");
		qualityMetricIsSetInstance.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Boolean> qualityAttributeIsCalculatedValue = (ArrayList<Boolean>) qualityMetricIsSetInstance.get(metrics);
		
		Class<?> metricLocEnum = Class.forName("restructureit.qualitymeasurement.Metrics$MetricLoc");
		Object[] metricEnums = metricLocEnum.getEnumConstants();
		int enumPosition = 0;
		
		for (int i = 0; i < metricEnums.length; i++) {
			if (metricEnums[i].toString().equals("COMPLEXITY")) {
				enumPosition = i;
			}
		}
		
		Assert.assertEquals(0, metrics.getComplexity(), 0);
		Assert.assertEquals("Understandability should be updated", 0, metrics.getUnderstandability(), delta);
		Assert.assertFalse("Messaging should be set as false", qualityAttributeIsCalculatedValue.get(enumPosition));
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateQualityAttributes()}.
	 */
	@Test
	public void testCalculateQualityAttributes() {
		metrics.setCoupling(1);
		metrics.setCohesion(1);
		metrics.setMessaging(1);
		metrics.setDesignSize(1);
		metrics.setHierarchies(1);
		metrics.setAbstraction(1);
		metrics.setComposition(1);
		metrics.setInheritance(1);
		metrics.setPolymorphism(1);
		metrics.setComplexity(1);
		metrics.setEncapsulation(1);
		
		metrics.calculateQualityAttributes();
		
		Assert.assertEquals(1, metrics.getExtendability(), delta);
		Assert.assertEquals(-0.99, metrics.getUnderstandability(), delta);
		Assert.assertEquals(1, metrics.getEffectiveness(), delta);
		Assert.assertEquals(1, metrics.getFlexability(), delta);
		Assert.assertEquals(1, metrics.getReusability(), delta);
		Assert.assertEquals(1, metrics.getFunctionality(), delta);
	}

	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#isAllQualityAttributesCalculated()}.
	 */
	@Test
	public void testIsAllQualityAttributesCalculatedAllCalculated() {
		setUpDesignAttributes();
		Assert.assertTrue(metrics.isAllQualityAttributesCalculated());
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#isAllQualityAttributesCalculated()}.
	 */
	@Test
	public void testIsAllQualityAttributesCalculatedNotAllCalculated() {
		Assert.assertFalse(metrics.isAllQualityAttributesCalculated());
	}
	
	//Private MethodTests
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateReusability()}.
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testCalculateReusabilityAllRequiredValuesSet() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		metrics.setCoupling(1);
		metrics.setCohesion(1);
		metrics.setMessaging(1);
		metrics.setDesignSize(1);
		
		Method method = metrics.getClass().getDeclaredMethod("calculateReusability");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(1, metrics.getReusability(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateReusability()}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCalculateReusabilityMissingRequiredValues() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		metrics.setCoupling(1);
		metrics.setCohesion(1);
		metrics.setMessaging(1);
		
		Method method = metrics.getClass().getDeclaredMethod("calculateReusability");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(0, metrics.getReusability(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateFlexability()}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCalculateFlexabilityAllRequiredValuesSet() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		metrics.setEncapsulation(1);
		metrics.setCoupling(1);
		metrics.setComposition(1);
		metrics.setPolymorphism(1);
		
		Method method = metrics.getClass().getDeclaredMethod("calculateFlexability");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(1, metrics.getFlexability(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateFlexability()}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCalculateFlexabilityMissingRequiredValues() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		metrics.setEncapsulation(1);
		metrics.setCoupling(1);
		metrics.setComposition(1);;
		
		Method method = metrics.getClass().getDeclaredMethod("calculateFlexability");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(0, metrics.getFlexability(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateUnderstandability()}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCalculateUnderstandabilityAllRequiredValuesSet() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		metrics.setAbstraction(1);
		metrics.setEncapsulation(1);
		metrics.setCoupling(1);
		metrics.setComposition(1);
		metrics.setCohesion(1);
		metrics.setPolymorphism(1);
		metrics.setComplexity(1);
		metrics.setDesignSize(1);
		
		Method method = metrics.getClass().getDeclaredMethod("calculateUnderstandability");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(-0.99, metrics.getUnderstandability(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateUnderstandability()}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCalculateUnderstandabilityMissingRequiredValues() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		metrics.setAbstraction(1);
		metrics.setEncapsulation(1);
		metrics.setCoupling(1);
		metrics.setComposition(1);
		metrics.setCohesion(1);
		metrics.setPolymorphism(1);
		metrics.setComplexity(1);
		
		Method method = metrics.getClass().getDeclaredMethod("calculateUnderstandability");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(0, metrics.getUnderstandability(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateFunctionality()}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCalculateFunctionalityAllRequiredValuesSet() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		metrics.setEncapsulation(1);
		metrics.setCohesion(1);
		metrics.setPolymorphism(1);
		metrics.setDesignSize(1);
		metrics.setHierarchies(1);
		metrics.setMessaging(1);
		
		Method method = metrics.getClass().getDeclaredMethod("calculateFunctionality");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(1, metrics.getFunctionality(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateFunctionality()}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCalculateFunctionalityMissingRequiredValues() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		metrics.setEncapsulation(1);
		metrics.setCohesion(1);
		metrics.setPolymorphism(1);
		metrics.setDesignSize(1);
		metrics.setHierarchies(1);
		
		Method method = metrics.getClass().getDeclaredMethod("calculateFunctionality");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(0, metrics.getFunctionality(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateExtendability()}.
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void testCalculateExtendabilityAllRequiredValuesSet() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		metrics.setAbstraction(1);
		metrics.setCoupling(1);
		metrics.setPolymorphism(1);
		metrics.setInheritance(1);
		
		Method method = metrics.getClass().getDeclaredMethod("calculateExtendability");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(1, metrics.getExtendability(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateExtendability()}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCalculateExtendabilityMissingRequiredValues() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		metrics.setAbstraction(1);
		metrics.setCoupling(1);
		metrics.setPolymorphism(1);
		
		Method method = metrics.getClass().getDeclaredMethod("calculateExtendability");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(0, metrics.getExtendability(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateEffectiveness()}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCalculateEffectivenessAllRequiredValuesSet() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		metrics.setAbstraction(1);
		metrics.setEncapsulation(1);
		metrics.setPolymorphism(1);
		metrics.setInheritance(1);
		metrics.setComposition(1);
		
		Method method = metrics.getClass().getDeclaredMethod("calculateEffectiveness");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(1, metrics.getEffectiveness(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateEffectiveness()}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCalculateEffectivenessMissingRequiredValues() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		metrics.setAbstraction(1);
		metrics.setEncapsulation(1);
		metrics.setPolymorphism(1);
		metrics.setInheritance(1);
		
		Method method = metrics.getClass().getDeclaredMethod("calculateEffectiveness");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(0, metrics.getEffectiveness(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateQualityScore()}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCalculateQualityScoreAllRequiredValuesSet() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		setUpDesignAttributes();
		
		Method method = metrics.getClass().getDeclaredMethod("calculateQualityScore");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(4.01, metrics.getQualityScore(), delta);
	}
	
	/**
	 * Test method for {@link restructureit.qualitymeasurement.Metrics#calculateQualityScore()}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testCalculateQualityScoreMissingRequiredValues() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = metrics.getClass().getDeclaredMethod("calculateQualityScore");
		method.setAccessible(true);
		method.invoke(metrics);
		
		Assert.assertEquals(0, metrics.getQualityScore(), delta);
	}
	
	//PRIVATE METHODS
	
	private void setUpDesignAttributes() {
		metrics.setCoupling(1);
		metrics.setCohesion(1);
		metrics.setMessaging(1);
		metrics.setDesignSize(1);
		metrics.setHierarchies(1);
		metrics.setAbstraction(1);
		metrics.setComposition(1);
		metrics.setInheritance(1);
		metrics.setPolymorphism(1);
		metrics.setComplexity(1);
		metrics.setEncapsulation(1);
		
		metrics.calculateQualityAttributes();
	}

}
