
package possystem;

public class ReceiptListNode {
	 private ReceiptListNode next;
	   private ReceiptListNode previous;
	   private Receipt receipt;

	   public ReceiptListNode()
	   {
		  next = null;
		  previous = null;
	      receipt = null;
	   }
	   public ReceiptListNode(Receipt newReceipt) {
		   next = null;
		   previous = null;
		   receipt = newReceipt;
	   }
	   public Receipt getReceipt() {
		   return receipt;
	   }
	   public void setReceipt(Receipt newReceipt) {
		   receipt = newReceipt;
	   }
	   public ReceiptListNode getNext() {
		   return next;
	   }
	   public void setNext(ReceiptListNode newNext) {
		   next = newNext;
	   }
	   public ReceiptListNode getPrevious() {
		   return previous;
	   }
	   public void setPrevious(ReceiptListNode newPrevious) {
		   next = newPrevious;
	   }
}
