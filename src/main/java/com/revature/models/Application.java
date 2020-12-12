package com.revature.models;

public class Application {
	
	private int application_id;
	
	private String username;
	
	private int startingBalance;
	
	private int creditScore;
	
	private double yearlySalary;

	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Application(int application_id, String username, int startingBalance, int creditScore, double yearlySalary) {
		super();
		this.application_id = application_id;
		this.username = username;
		this.startingBalance = startingBalance;
		this.creditScore = creditScore;
		this.yearlySalary = yearlySalary;
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

	public double getYearlySalary() {
		return yearlySalary;
	}

	public void setYearlySalary(double yearlySalary) {
		this.yearlySalary = yearlySalary;
	}

	@Override
	public String toString() {
		return "Application [application_id=" + application_id + ", username=" + username + ", startingBalance="
				+ startingBalance + ", creditScore=" + creditScore + ", yearlySalary=" + yearlySalary + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + application_id;
		result = prime * result + creditScore;
		result = prime * result + startingBalance;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		long temp;
		temp = Double.doubleToLongBits(yearlySalary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (Double.doubleToLongBits(yearlySalary) != Double.doubleToLongBits(other.yearlySalary))
			return false;
		return true;
	}
	
}
