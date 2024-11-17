package com.librarymanagement;

public class Borrower {
	private String name;
	private String mobile;
	private String membershipId;

	public Borrower(String name, String mobile, String membershipId) {
		this.name = name;
		this.mobile = mobile;
		this.membershipId = membershipId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}

	public String getMembershipId() {
		return membershipId;
	}

}