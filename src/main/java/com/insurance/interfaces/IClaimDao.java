package com.insurance.interfaces;

import com.insurance.models.Claim;

public interface IClaimDao {
	public void submitClaim(int policyId, Claim claim);
	public void viewClaim(int claimId);
	public void updateClaim(int claimId, String status);
	public void deleteClaim(int claimId);
}
