package com.revature.models;

public class Transaction {
	
	private int transaction_id;
	
	private int accountNumber;
	
	private String transactionType;
	
	private int transactionAmount;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(int transaction_id, int accountNumber, String transactionType, int transactionAmount) {
		super();
		this.transaction_id = transaction_id;
		this.accountNumber = accountNumber;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", accountNumber=" + accountNumber
				+ ", transactionType=" + transactionType + ", transactionAmount=" + transactionAmount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + transactionAmount;
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		result = prime * result + transaction_id;
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
		Transaction other = (Transaction) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (transactionAmount != other.transactionAmount)
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		if (transaction_id != other.transaction_id)
			return false;
		return true;
	}

}
