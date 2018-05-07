package project2;

import java.util.Date;

public class Return extends Transaction {
	private double refundAmt;
	private Receipt oldReceipt;
	
	Return(Cashier cashier) {
		this.cashier = cashier;
		cashierID = cashier.getCashierID();
		receipt = new Receipt(false);
		date = new Date();
	}
	
	public Receipt findReceipt(int receiptNum) {
		oldReceipt = salesReport.findReceipt(receiptNum);
	}
	public void returnItem(int sku, int quantity) {
		Item toReturn = inventory.findItem(sku);
		oldReceipt.removeItem(toReturn, quantity);
		receipt.addItem(toReturn, quantity);
		refundAmt = refundAmt + (toReturn.getPrice()*quantity);
	}
	public void voidReturn(int sku, int quantity) {
		Item toVoid = inventory.findItem(sku);
		receipt.removeItem(toVoid, quantity);
		oldReceipt.addItem(toVoid, quantity);
		refundAmt = refundAmt - (toVoid.getPrice()*quantity);
	}
	public void finalizeReturn() {
		cashier.updateDrawerFromReturn(refundAmt); 
		receipt.updateInventory();
		salesReport.add(receipt);
		salesReport.writeToFile(receipt);
		receipt.printReceipt(date, cashierID, refundAmt);
		
	}
}
