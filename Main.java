package CarbonEmissionCalculator;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	DatabaseConnection db = new DatabaseConnection();

	public static void main(String[] args) throws SQLException {

		Scanner s = new Scanner(System.in);
		UserDAO userDao = new UserDAO();
		ActivityDAO activityDao = new ActivityDAO();
		EmissionFactorDAO efDAO = new EmissionFactorDAO();

		System.out.println("1-Add User");
		System.out.println("2-Login");
		System.out.println("3-Delete User");
		System.out.println("4-Add EmissionFactors");
		System.out.println("5-Login and Update Activities");

		int choice = s.nextInt();

		if (choice == 1) {
			System.out.println("Enter new user name");
			String username = s.next();
			System.out.println("Enter the new password");
			String password = s.next();
			userDao.addUser(username, password);
			System.out.println();

		}

		if (choice == 2) {
			System.err.println("Enter the username:");
			String name = s.next();
			System.out.println("Enter the password");
			String pass = s.next();

			if (userDao.authenticate(name, pass)) {
				System.out.println("Login successful!");

				System.out.print("Enter activity type (e.g., car, bus, train): ");
				String activityType = s.next();
				System.out.print("Enter distance traveled (in km): ");
				float distance = s.nextFloat();

				int userId = userDao.getUserId(name);
				activityDao.logActivity(userId, activityType, distance);
				System.out.println("Activity logged and emission calculated.");
				
				
			} else {
				System.out.println("Login failed!");
			}

			s.close();
		}
		
		if(choice==3) {
			System.out.println("Enter the name:");
			String name=s.next();
			userDao.deleteUser(name);
		}

		if (choice == 4) {
			System.out.println("Enter activity type:");
			String activityType = s.next();

			System.out.println("Enter emission factor per unit:");
			float factorPerUnit = s.nextFloat();

			try {
				efDAO.addEmissionFactor(activityType, factorPerUnit);
			} catch (SQLException e) {
				System.out.println("Failed to add emission factor.");
				e.printStackTrace();
			}
		}
		if (choice==5) {
			System.err.println("Enter the username:");
			String name = s.next();
			System.out.println("Enter the password");
			String pass = s.next();

			if (userDao.authenticate(name, pass)) {
				System.out.println("Login successful!");
				
				activityDao.getAllActivities();
				System.out.println("Enter the Id to update");
				int id=s.nextInt();
				System.out.println("Enter the type");
				String type=s.next();
				System.out.println("Enter the distance");
				float distance=s.nextFloat();
				activityDao.updateActivity(id, type, distance);
				
			}
			
			else {
				System.out.println("Login failed!");
			}

			s.close();
		}

	}

}
