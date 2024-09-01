package com.insurance.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GeneralUtilities {
    public static void checkPolicyExist(int policyId) {
		try {
            Connection connection = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM Policy WHERE policy_id = ?";
			PreparedStatement checkStatement = connection.prepareStatement(sql);
	        checkStatement.setInt(1, policyId);
	        ResultSet resultSet = checkStatement.executeQuery();
	        if (!resultSet.next()) {
	        	System.out.println("Policy with ID " + policyId + " does not exist.");
	        	return;
	        }
		} catch (Exception e) {
			System.out.println("Some unexpected error occured");
		}
	}

	public static void checkCustomerExist(int customerId) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM Customer WHERE customer_id = ?";
			PreparedStatement checkStatement = connection.prepareStatement(sql);
	        checkStatement.setInt(1, customerId);
	        ResultSet resultSet = checkStatement.executeQuery();
	        if (!resultSet.next()) {
	        	System.out.println("Customer with ID " + customerId + " does not exist.");
	        	return;
	        }
		} catch (Exception e) {
			System.out.println("Some unexpected error occured");
		}
	}
}
