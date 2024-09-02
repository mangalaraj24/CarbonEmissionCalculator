package CarbonEmissionCalculator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {

	public void logActivity(int userId, String activityType, float distance) {
		float emission = calculateEmission(activityType, distance);
		String add = "INSERT INTO activities (user_id, activity_type, distance, emission) VALUES (?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(add)) {
			stmt.setInt(1, userId);
			stmt.setString(2, activityType);
			stmt.setFloat(3, distance);
			stmt.setFloat(4, emission);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private float calculateEmission(String activityType, float distance) {

		String query = "SELECT factor_per_unit FROM emission_factors WHERE activity_type = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, activityType);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				float factor = rs.getFloat("factor_per_unit");
				return factor * distance;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	  public List<Activities> getAllActivities() {
	        List<Activities> activities = new ArrayList<>();
	        String query = "SELECT * FROM activities";

	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(query);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                int activityId = rs.getInt("activity_id");
	                int userId = rs.getInt("user_id");
	                String activityType = rs.getString("activity_type");
	                float distance = rs.getFloat("distance");
	                float emission = rs.getFloat("emission");

	                activities.add(new Activities(activityId, userId, activityType, distance, emission));
	            }

	        } catch (SQLException e) {
	            System.err.println("Error retrieving activities: " + e.getMessage());
	        }

	        return activities;
	    }
	  
	  public void updateActivity(int activityId, String activityType, float distance) {
		   
		    float emission = calculateEmission(activityType, distance);
		    String query = "UPDATE activities SET activity_type = ?, distance = ?, emission = ? WHERE activity_id = ?";

		    try (Connection connection = DatabaseConnection.getConnection();
		         PreparedStatement stmt = connection.prepareStatement(query)) {

		        stmt.setString(1, activityType);
		        stmt.setFloat(2, distance);
		        stmt.setFloat(3, emission);
		        stmt.setInt(4, activityId);

		        int rowsAffected = stmt.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("Activity updated successfully.");
		        } else {
		            System.out.println("Failed to update activity.");
		        }

		    } catch (SQLException e) {
		        System.err.println("Error updating activity: " + e.getMessage());
		    }
		}
	
}
