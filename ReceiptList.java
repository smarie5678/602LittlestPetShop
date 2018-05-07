package project2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReceiptList {
		private ReceiptListNode first, last;
		private int size;

		ReceiptList() {
			first = new ReceiptListNode();
			last = new ReceiptListNode();
			first.setNext(last);
			last.setPrevious(first);
			size = 0;
			try {
				initialize();
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			} catch (IOException e) {
				System.out.println("No More Data");
			} catch (ClassNotFoundException e ) {
				System.out.println("File contains invalid objects");
			}
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
		public void initialize() throws FileNotFoundException, IOException, ClassNotFoundException{
			boolean hasNext = true;
			FileInputStream fileIn = new FileInputStream("ReceiptFile.txt");
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			while (hasNext == true) {
				Receipt newReceipt = null;
				try {
				newReceipt = (Receipt) objIn.readObject();
				}
				catch (IOException e) {
					System.out.println("No More Data");
					hasNext = false;
					objIn.close();
					fileIn.close();
				}
				if (hasNext == true) {
				ReceiptListNode newNode = new ReceiptListNode(newReceipt);
				addReceipt(newNode);
				}
			}
		}
		public void addReceipt(ReceiptListNode newNode) {
			newNode.setNext(last);
			newNode.setPrevious(last.getPrevious());
			last.setPrevious(newNode);
			newNode.getPrevious().setNext(newNode);
			size++;
		}
		public Receipt removeReceipt (int receiptNum) throws EmptyListException, ItemNotFoundException {
			if (isEmpty()) 
				throw new EmptyListException();
			else {
				ReceiptListNode wanted = findReceipt(receiptNum);
				wanted.getPrevious().setNext(wanted.getNext());
				wanted.getNext().setPrevious(wanted.getPrevious());
				size--;
				return wanted.getReceipt();
			}
		}
		public ReceiptListNode findReceipt(int receiptNum) throws ItemNotFoundException{ 
			ReceiptListNode checking = first;
			while (checking != null) {
				if (checking.getReceipt().getReceiptNum() == receiptNum)
					return checking;
				else 
					checking = checking.getNext();
			}
			throw new ItemNotFoundException();
		}
		public void writeToFile(Receipt toWrite) throws FileNotFoundException, IOException{
			FileOutputStream fileOut = new FileOutputStream("ReceiptFile.txt");
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(toWrite);
			objOut.close();
			fileOut.close();			
		}
	}
