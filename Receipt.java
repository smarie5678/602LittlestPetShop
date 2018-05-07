package project2;

import java.util.Date;

public class Receipt {
	private ReceiptNode first, last;
	private int size;
	private int receiptNum;
	private boolean isSale;

	Receipt(boolean sale) {
		receiptNum = (salesReport.getsize() + 1);
		first = new ReceiptNode();
		last = new ReceiptNode();
		first.setNext(last);
		last.setPrevious(first);
		size = 0;
		if (sale)
			isSale = true;
		else
			isSale = false;
}
	public int getSize() {
		return size;
	}
	public int getReceiptNum() {
		return receiptNum;
	}
	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}
	public void addItem(Item newItem, int quantity) {
		ReceiptNode newNode = new ReceiptNode(newItem, quantity);
		newNode.setNext(last);
		newNode.setPrevious(last.getPrevious());
		last.setPrevious(newNode);
		newNode.getPrevious().setNext(newNode);
		size++;
	}
	public Item removeItem (Item toRemove, int quantity) throws EmptyListException, ItemNotFoundException {
		if (isEmpty()) 
			throw new EmptyListException();
		else {
			ReceiptNode wanted = findItem(toRemove);
			if (wanted.getTransQuantity() > quantity) {
				wanted.setTransQuantity(wanted.getTransQuantity() - quantity);
				return wanted.getElement();
			}
			else if (wanted.getTransQuantity() < quantity) 
				throw new InsufficientQuantityException();
			else {
			last.setPrevious(last.getPrevious().getPrevious());
			last.getPrevious().setNext(last);
			size--;
			return wanted.getElement();
			}
		}
	}
	public ReceiptNode findItem (Item toFind) throws ItemNotFoundException {
		ReceiptNode toSearch = first.getNext();
		for (int nodeNum = 0; nodeNum <= size; nodeNum++) {
			Item possibleMatch = toSearch.getElement();
			if (possibleMatch.getSku() == toFind.getSku())
				return toSearch;
			else 
				toSearch = toSearch.getNext();
		}
		throw new ItemNotFoundException();	
	}
	public void updateInventory() {
		ReceiptNode toUpdate = first.getNext();
		for (int itemsUpdated = 0; itemsUpdated <= size; itemsUpdated++)
		{
			Item updating = toUpdate.getElement();
			if (isSale) {
			updating.updateQuantity(updating.getQuantity() - toUpdate.getTransQuantity());
			updating.checkReorder();
			}
			else
			updating.updateQuantity(updating.getQuantity() + toUpdate.getTransQuantity());
		}
	}
	public void printReceipt(Date date, String cashierID, double totalAmt) {
		if (isSale) {
		ReceiptNode toPrint = first;
		System.out.println("Store Name");
		System.out.println(date);
		System.out.println("Cashier ID: " + cashierID);
		System.out.println("Transaction Type: Sale \t Transaction Number:" + receiptNum);
		System.out.println("Name \t Quantity \t Price");
		for (int itemsPrinted = 0; itemsPrinted <= size; itemsPrinted++) {
			Item purchased = toPrint.getElement();
			System.out.println(purchased.getDescription() + "\t" + toPrint.getTransQuantity() + "\t" + (purchased.getPrice()*toPrint.getTransQuantity()));
			toPrint = toPrint.getNext();
		}
		System.out.println("Total: \t\t\t" + totalAmt) ;
		System.out.println("Thanks for shopping!!!!");
		}
		else {
			ReceiptNode toPrint = first;
			System.out.println("Store Name");
			System.out.println(date);
			System.out.println("Cashier ID: " + cashierID);
			System.out.println("Return #:" + transNum);
			System.out.println("Items Returned: ");
			System.out.println("Name \t Quantity \t Price");
			for (int itemsPrinted = 0; itemsPrinted <= size; itemsPrinted++) {
				Item purchased = toPrint.getElement();
				System.out.println(purchased.getName() + "\t" + toPrint.getTransQuantity() + "\t" + (purchased.getPrice()*toPrint.getTransQuantity()));
				toPrint = toPrint.getNext();
			}
			System.out.println("Value of Return: \t\t\t" + totalAmt) ;
			System.out.println("Thanks for shopping!!!!");
		}
	}
	public String toString() {
		String s;
		ReceiptNode toPrint = first;
		if (isSale) {
			s = "\n" + "Sale number: " + receiptNum + "\n" + "Items sold:" + "\n";
			for (int itemsPrinted = 0; itemsPrinted <= size; itemsPrinted++) {
				s = s + first.toString();
				toPrint = toPrint.getNext();
			}
			s = s + "\n";
		}
		else {
			s = "\n" + "Return number: " + receiptNum + "\n" + "Items returned:" + "\n";
			for (int itemsPrinted = 0; itemsPrinted <= size; itemsPrinted++) {
				s = s + first.toString();
				toPrint = toPrint.getNext();
			}
			s = s + "\n";
		}
		return s;
	}
}
