package de.fhluebeck.group3.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.fhluebeck.group3.DAO.StepDAO;
import de.fhluebeck.group3.model.Step;

/**
 * Test for StepDAO.
 * 
 * @author kong Yu on 2018/6/23.
 */
public class StepDAOTest {
	private Step newStep1 = null;
	private Step newStep2 = null;
	private List<Step> stepListTest = new ArrayList<Step>();

	/**
	 * Before testing, we create two step objects and a step list
	 */
	@Before
	public void setUp() throws Exception {
		newStep1 = new Step("testStepContent1", 1, 8);
		newStep2 = new Step("testStepContent2", 2, 8);
		stepListTest.add(newStep1);
		stepListTest.add(newStep2);
	}

	/**
	 * Test whether updateBatchSteps is valid
	 */
	@Test
	public void testUpdateBatchSteps() throws Exception {
		assertNotNull(stepListTest);
		assertTrue(StepDAO.updateBatchSteps(stepListTest));

	}

	/**
	 * Test whether addBatchSteps is valid
	 */
	@Test
	public void testAddBatchSteps() throws Exception {
		assertTrue(StepDAO.addBatchSteps(stepListTest));
		assertTrue(stepListTest.get(0).getStatus() == 1);
	}

	/**
	 * Test searchStepByRecipeId
	 */
	@Test
	public void testSearchStepByRecipeId() throws Exception {
		int testRecipeId = 2;
		assertNotNull(StepDAO.searchStepByRecipeId(testRecipeId));

	}

	/**
	 * Test deleteStepById
	 */
	@Test
	public void testDeleteStepById() throws Exception {
		assertTrue(StepDAO.deleteStepById(2));

	}

	/**
	 * Test batchDeleteSteps
	 */
	@Test
	public void testBatchDeleteSteps() throws Exception {
		assertTrue(StepDAO.batchDeleteSteps(stepListTest));

	}
}
