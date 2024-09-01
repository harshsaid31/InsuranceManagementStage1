package com.insurance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.insurance.interfaces.IClaimDao;
import com.insurance.models.Claim;
import com.insurance.utils.GeneralUtilities;

public class ClaimDao implements IClaimDao {
	
	private Connection connection = null;
	
	public ClaimDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void submitClaim(int policyId, Claim claim) {
		try {
			// check if policy exist's
			GeneralUtilities.checkPolicyExist(policyId);

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
			e.printStackTrace();
		}
	}

	@Override
	public void viewClaim(int claimId) {

	}

	@Override
	public void updateClaim(int claimId, String status) {

	}

	@Override
	public void deleteClaim(int claimId) {
	
	}

}
