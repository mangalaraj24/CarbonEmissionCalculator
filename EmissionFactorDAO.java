package CarbonEmissionCalculator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmissionFactorDAO {

	public void addEmissionFactor(String activityType, float factorPerUnit) throws SQLException {
		String add = "INSERT INTO emission_factors (activity_type, factor_per_unit) VALUES (?, ?)";
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(add);
			pstmt.setString(1, activityType);
			pstmt.setFloat(2, factorPerUnit);
			pstmt.executeUpdate();
			System.out.println("Emission factor added successfully.");
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
