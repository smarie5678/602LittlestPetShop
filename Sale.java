package project2;

import java.util.Date;

public class Sale extends Transaction {
	private double payAmt;
	
	Sale(Cashier cashier) {
		this.cashier = cashier;
		cashierID = cashier.getCashierID();
		receipt = new Receipt(true);
		date = new Date();
	}
	public void addSaleItem(int sku, int quantity) {
		Item toSell = inventory.findItem(sku);
		receipt.addItem(toSell, quantity);
		payAmt = payAmt + (toSell.getPrice()*quantity);
		//Display running total
		
	}
	public void voidSaleItem(int sku, int quantity) throws ItemNotFoundException{
		Item toVoid = inventory.findItem(sku);
		receipt.removeItem(toVoid, quantity);
		payAmt = payAmt - (toVoid.getPrice()*quantity);
		//Display running total
	}
	public void completeSale() {
		receipt.updateInventory();
		salesReport.addReceipt(receipt);
		salesReport.writeToFile(receipt);
		receipt.printReceipt(date, cashierID, payAmt);
	}
	public void makePayment(double payment) {
		if (payment > payAmt) {
			System.out.println("Change due: " + (payment - payAmt));
		}
		cashier.updateDrawerFromSale(payment);
	}
}
