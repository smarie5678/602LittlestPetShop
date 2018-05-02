package project2;

public class Sale extends Transaction {
	private double payAmt;
	
	
	public void addSaleItem(int sku, int quantity) {
		Item item = inventory.findItem(sku);
		receipt.addItem(item, quantity);
		payAmt = payAmt + (item.getPrice()*quantity);
		
	}
	public void voidSaleItem(int sku, int quantity) throws ItemNotFoundException{
		Item toVoid = inventory.findItem(sku);
		receipt.removeItem(toVoid);
		payAmt = payAmt - (toVoid.getPrice()*quantity);
	}
	public void completeSale() {
		receipt.updateInventory();
		salesReport.add(receipt); // Not sure what class will be holding the receipts.
		printReceipt(date, cashierID, transNum);
	}
	public void makePayment(double payment) {
		payAmt = payAmt - payment;
		Cahsier.updateDrawer(payment);
		completeSale();
	}
}
