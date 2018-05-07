package possystem;

import java.util.Date;

public class Return extends Transaction {
	private double refundAmt;
	private Receipt oldReceipt;
	private Inventory inventory;
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
            try{
                
            
		Item toReturn = inventory.findItem(sku);
		oldReceipt.removeItem(toReturn, quantity);
		receipt.addItem(toReturn, quantity);
		refundAmt = refundAmt + (toReturn.getPrice()*quantity);}
            catch(EmptyListException |ItemNotFoundException|InsufficientQuantityException e)
            {
                e.printStackTrace();
                
            }
            
	}
	public void voidReturn(int sku, int quantity) {
		try{
                    Item toVoid = inventory.findItem(sku);
                
		receipt.removeItem(toVoid, quantity);
		oldReceipt.addItem(toVoid, quantity);
		refundAmt = refundAmt - (toVoid.getPrice()*quantity);
                }
                catch(EmptyListException |ItemNotFoundException|InsufficientQuantityException e)
            {
                e.printStackTrace();
                
            }
	}
	public void finalizeReturn() {
		cashier.updateDrawerFromReturn(refundAmt); 
		receipt.updateInventory();
		salesReport.add(receipt);
		salesReport.writeToFile(receipt);
		receipt.printReceipt(date, cashierID, refundAmt);
		
	}
}
