package org.finance.mybank.exception;

public class NotEnoughBalanceException extends RuntimeException {
	public NotEnoughBalanceException(Double balance, Double amount) {
		super(String.format("Not enough balance: %s, trying to transfer: %s", balance, amount));
	}
}
