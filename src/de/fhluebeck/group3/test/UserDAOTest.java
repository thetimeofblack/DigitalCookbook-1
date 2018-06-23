package de.fhluebeck.group3.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.fhluebeck.group3.DAO.UserDAO;
import de.fhluebeck.group3.model.User;

/**
 * JUnit Test class of UserDAO.
 * 
 * @author Hua Yichen on 2018/06/23.
 */
public class UserDAOTest {

	private User newUser = null;

	/**
	 * Before testing user, we create two user objects.
	 */
	@Before
	public void setUp() throws Exception {

		// create new User for insertion.
		newUser = new User("testUser", "123456");

		// Add user to the Database.
		UserDAO.addUser(newUser);

	}

	/**
	 * Test whether addUser function is valid.
	 */
	@Test
	public void testAddUserFunction() throws Exception {

		User addedUser = UserDAO.validatePassword(newUser.getUsername(), newUser.getPassword());

		assertNotNull(addedUser);
		// If it works, this cannot be null
		assertEquals(addedUser.getUsername(), newUser.getUsername());
		assertNotNull(addedUser.getUserId());
		assertTrue(addedUser.getStatus() == 1);

	}

	/**
	 * Test whether deleteUser function is valid.
	 */
	@Test
	public void testDeleteUserFunction() throws Exception {

		int testUserId = 5;

		assertTrue(UserDAO.deleteUser(testUserId));

		assertNull(UserDAO.getUserById(testUserId));

	}

}
