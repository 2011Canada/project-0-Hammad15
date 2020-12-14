package com.revature.models;

public class Application {
	
	private int application_id;
	
	private String username;
	
	private int startingBalance;
	
	private int creditScore;
	
	private int yearlySalary;
	
	private String applicationStatus;

	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Application(int application_id, String username, int startingBalance, int creditScore, int yearlySalary,
			String applicationStatus) {
		super();
		this.application_id = application_id;
		this.username = username;
		this.startingBalance = startingBalance;
		this.creditScore = creditScore;
		this.yearlySalary = yearlySalary;
		this.applicationStatus = applicationStatus;
	}

	public int getApplication_id() {
		return application_id;
	}

	public void setApplication_id(int application_id) {
		this.application_id = application_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getStartingBalance() {
		return startingBalance;
	}

	public void setStartingBalance(int startingBalance) {
		this.startingBalance = startingBalance;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	public int getYearlySalary() {
		return yearlySalary;
	}

	public void setYearlySalary(int yearlySalary) {
		this.yearlySalary = yearlySalary;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	@Override
	public String toString() {
		return "Application [application_id=" + application_id + ", username=" + username + ", startingBalance="
				+ startingBalance + ", creditScore=" + creditScore + ", yearlySalary=" + yearlySalary
				+ ", applicationStatus=" + applicationStatus + "]\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationStatus == null) ? 0 : applicationStatus.hashCode());
		result = prime * result + application_id;
		result = prime * result + creditScore;
		result = prime * result + startingBalance;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + yearlySalary;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		if (applicationStatus == null) {
			if (other.applicationStatus != null)
				return false;
		} else if (!applicationStatus.equals(other.applicationStatus))
			return false;
		if (application_id != other.application_id)
			return false;
		if (creditScore != other.creditScore)
			return false;
		if (startingBalance != other.startingBalance)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (yearlySalary != other.yearlySalary)
			return false;
		return true;
	}
	
}
