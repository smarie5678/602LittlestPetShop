package project2;

import java.util.Date;

public class Receipt {
	private ReceiptNode first, last;
	private int size;

	Receipt() {
		first = new ReceiptNode();
		last = new ReceiptNode();
		first.setNext(last);
		last.setPrevious(first);
		size = 0;
}
	public int getSize() {
		return size;
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
	public Item removeItem (Item toRemove) throws EmptyListException, ItemNotFoundException {
		if (isEmpty()) 
			throw new EmptyListException();
		else {
			ReceiptNode wanted = findItem(toRemove);
			last.setPrevious(last.getPrevious().getPrevious());
			last.getPrevious().setNext(last);
			size--;
			return wanted.getElement();
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
			updating.updateQuantity(updating.getQuantity() - toUpdate.getTransQuantity());
			updating.checkReorder();
		}
	}
	public void printReceipt(Date date, int cashierID, int transNum) {
		ReceiptNode toPrint = first;
		System.out.println("Store Name");
		System.out.println(date);
		System.out.println("Cashier ID: " + cashierID);
		System.out.println(transNum);
		System.out.println("Name \t Quantity \t Price");
		for (int itemsPrinted = 0; itemsPrinted <= size; itemsPrinted++) {
			Item purchased = toPrint.getElement();
			System.out.println(purchased.getName() + "\t" + toPrint.getTransQuantity() + "\t" + purchased.getPrice());
		}
		System.out.println("Thanks for shopping!!!!");
	}
}
