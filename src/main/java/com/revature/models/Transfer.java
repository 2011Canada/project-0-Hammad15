package com.revature.models;

public class Transfer {
	
	private int accountNumber;
	
	private int transferAmount;
	
	private int recAccountNumber;
	
	private String transferStatus;

	public Transfer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transfer(int accountNumber, int transferAmount, int recAccountNumber, String transferStatus) {
		super();
		this.accountNumber = accountNumber;
		this.transferAmount = transferAmount;
		this.recAccountNumber = recAccountNumber;
		this.transferStatus = transferStatus;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(int transferAmount) {
		this.transferAmount = transferAmount;
	}

	public int getRecAccountNumber() {
		return recAccountNumber;
	}

	public void setRecAccountNumber(int recAccountNumber) {
		this.recAccountNumber = recAccountNumber;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	@Override
	public String toString() {
		return "From Account Number = " + accountNumber + " | Transfer Amount = " + transferAmount + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + recAccountNumber;
		result = prime * result + transferAmount;
		result = prime * result + ((transferStatus == null) ? 0 : transferStatus.hashCode());
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
		Transfer other = (Transfer) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (recAccountNumber != other.recAccountNumber)
			return false;
		if (transferAmount != other.transferAmount)
			return false;
		if (transferStatus == null) {
			if (other.transferStatus != null)
				return false;
		} else if (!transferStatus.equals(other.transferStatus))
			return false;
		return true;
	}
	
}
