package com.librarymanagement;

//Borrower Class
public class Borrower {
	private String name;
	private String contactDetails;
	private String membershipId;

	public Borrower(String name, String contactDetails, String membershipId) {
		this.name = name;
		this.contactDetails = contactDetails;
		this.membershipId = membershipId;
	}

	public String getName() {
		return name;
	}

	public String getMembershipId() {
		return membershipId;
	}

	@Override
	public String toString() {
		return "Borrower{" + "Name='" + name + '\'' + ", ContactDetails='" + contactDetails + '\'' + ", MembershipID='"
				+ membershipId + '\'' + '}';
	}
}
