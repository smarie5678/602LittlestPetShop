package project2;

public class ReceiptNode {
	private Item element;
	private ReceiptNode next;
	private ReceiptNode previous;
	private int transQuantity;
	ReceiptNode() {
		element = null;
		next = null;
		previous = null;
		transQuantity = 0;
	}
	ReceiptNode(Item item, int quantity) {
		element = item;
		next = null;
		previous = null;
		transQuantity = quantity;
	}
	public void setElement(Item newItem) {
		element = newItem;
	}
	public Item getElement() {
		return element;
	}
	public void setNext(ReceiptNode newNext) {
		next = newNext;
	}
	public ReceiptNode getNext() {
		return next;
	}
	public void setPrevious(ReceiptNode newPrevious) {
		previous = newPrevious;
	}
	public ReceiptNode getPrevious() {
		return previous;
	}
	public void setTransQuantity(int newQuantity) {
		transQuantity = newQuantity;
	}
	public int getTransQuantity() {
		return transQuantity;
	}
	
	public String toString() {
		String s = element.getDescription() + "\t" + element.getPrice() + "\t" + transQuantity + "\t" + "Total: " + "\t" + (element.getPrice()*transQuantity) + "\n";
		return s;
	}
	
}
