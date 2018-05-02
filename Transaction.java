package project2;

import java.util.Date;

public class Transaction {
	protected int transNum, regNum, cashierID; 
	protected Date date;

	protected Receipt receipt;
	protected int receiptLength;
	
	Transaction(int number, Cashier cashier, Register register) {
		transNum = number;
		cashierID = cashier.getID();
		regNum = register.getNum();
		receipt = new Receipt();
		date = new Date();
	}
}
