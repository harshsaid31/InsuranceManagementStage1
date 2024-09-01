package com.insurance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.insurance.interfaces.IClaimDao;
import com.insurance.models.Claim;
import com.insurance.utils.GeneralUtilities;

public class ClaimDao implements IClaimDao {
	
	private Connection connection = null;
	
	public ClaimDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void submitClaim(Claim claim) {
		try {
			// check if policy exist's
			GeneralUtilities.checkPolicyExist(claim.getPolicyId());

			// check if customer exist's
			GeneralUtilities.checkCustomerExist(claim.getCustomerId());

			// submit claim
			String sql = "INSERT INTO Claim (policy_id, customer_id, claim_date, status) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, claim.getPolicyId());
			preparedStatement.setInt(2, claim.getCustomerId());
			preparedStatement.setString(3, claim.getClaimDate());
			preparedStatement.setString(4, claim.getStatus());

			int rowInserted = preparedStatement.executeUpdate();
			if (rowInserted > 0) {
				System.out.println("Claim Submitted Successfully");
			} 

		} catch (Exception e) {
			System.out.println("Some error occured while submitting claim");
		}
	}

	@Override
	public void viewClaim(int claimId) {
		try {
			String sql = "SELECT * FROM Claim WHERE claim_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, claimId);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                System.out.println("Claim ID: " + result.getInt("claim_id"));
                System.out.println("Policy ID: " + result.getInt("policy_id"));
                System.out.println("Customer ID: " + result.getInt("customer_id"));
                System.out.println("Claim Date: " + result.getString("claim_date"));
                System.out.println("Status: " + result.getString("status"));
            } else {
                System.out.println("Claim not found.");
            }
		} catch (Exception e) {
			System.out.println("Some error occured while viewing claim");
		}
	}

	@Override
	public void updateClaim(int claimId, String status) {

	}

	@Override
	public void deleteClaim(int claimId) {
	
	}

}
