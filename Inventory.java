/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

/**
 *
 * @author russelndip
 */
public class Inventory implements Reports {
    //create a inventory binary tree by creating and writing to a file.
    public Inventory()
    {
       
        
        
               
  
    }
    //adds items i
    public void addItem(double upc, String descr, int quantity, double price)
    {
         Item root= null;
        Item parent;
        Item current;
        
        if(root == null)
            root = new Item(upc, descr, quantity, price);
        else
        {
            current = root;
            while (current !=null)
                if (upc < current.getSkew())
                {
                    parent = current;
                    current = current.left;
                }
            
                else if (upc > current.getSkew())
                {
                    parent = current;
                    current = current.right;
                
            
            if (upc < parent.getSkew())
                parent.left = new Item(upc, descr, quantity, price);
            else
                parent.right = new Item(upc, descr, quantity, price);
        }
    }
    }
    
    public void removeItem(double upc)
    {
        
    }
    
    public void printInventoryReport()
    {
        
    }
    public void printRegisterReport(){};
    
    public void printCashierReport(){};
}
