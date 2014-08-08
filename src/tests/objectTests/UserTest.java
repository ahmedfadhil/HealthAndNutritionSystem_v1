package tests.objectTests;


import hns.objects.Record;
import hns.objects.User;
import junit.framework.TestCase;

public class UserTest extends TestCase {
	
	public UserTest(String arg0){
		
		super(arg0);
	}

	public void testInvalid1() throws Exception{
		System.out.println("\nStarting testUser:Invalid userID input");
		try{
			User user = new User(-200, "Doe", "John");
		}
		catch (IllegalArgumentException e){
			
		}
		System.out.println("Finished testUser:Invalid userID input");
	}
	
	public void testInvalid2() throws Exception{
		System.out.println("\nStarting testUser:Invalid LastName input");
		try{
			User user = new User(200, "", "John");
		}
		catch (IllegalArgumentException e){
			
		}
		System.out.println("Finished testUser:Invalid LastName input");
	}
	
	public void testInvalid3() throws Exception{
		System.out.println("\nStarting testUser:Invalid FirstName input");
		try{
			User user = new User(200, "Doe", "      ");
		}
		catch (IllegalArgumentException e){
			
		}
		System.out.println("Finished testUser:Invalid FirstName input");
	}
	
	public void testInvalid4() throws Exception{
		System.out.println("\nStarting testUser:Invalid LastName update");
		User user = new User(200, "Doe", "John");
		try{
			user.updateLastName(null);
			user.updateLastName("");
			user.updateLastName("     ");
		}
		catch (IllegalArgumentException e){
			
		}
		System.out.println("Finished testUser:Invalid LastName update");
	}
	
	public void testInvalid5() throws Exception{
		System.out.println("\nStarting testUser:Invalid FirstName update");
		User user = new User(200, "Doe", "John");
		try{
			user.updateFirstName(null);
			user.updateFirstName("");
			user.updateFirstName("     ");
		}
		catch (IllegalArgumentException e){
			
		}
		System.out.println("Finished testUser:Invalid FirstName update");
	}
	
	public void testTypical() throws Exception{
		System.out.println("\nStarting testUser:valid input and update");

		User user = new User(200, "Doe", "John");

		assertNotNull(user);
		assertTrue(user instanceof User);

		assertEquals(user.getUserID(), 200);
		assertTrue("Doe".equals(user.getLastName()));
		assertTrue("John".equals(user.getFirstName()));
		
		user.updateFirstName("Charmander");
		assertEquals(user.getFirstName(), "Charmander");
		
		user.updateLastName("Squirtle");
		assertEquals(user.getLastName(), "Squirtle");
		

		System.out.println("Finished testUser:valid input and update");
	}
	

	
}

