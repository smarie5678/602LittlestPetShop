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
public class Inventory{
    Item root= null;
        Item parent;
        Item current;
    //create a inventory binary tree by creating and writing to a file.
    public Inventory()
    {
       
       
      
    }
    //adds items i
    public void addItem (double upc, String descr, int quantity, double price)
    {
         
        
        if(root == null)
            root = new Item(upc, descr, quantity, price);
        else
        {
            current = root;
            while (current !=null)
                if (upc < current.getSku())
                {
                    parent = current;
                    current = current.left;
                }
            
                else if (upc > current.getSku())
                {
                    parent = current;
                    current = current.right;
                
            
            if (upc < parent.getSku())
                parent.left = new Item(upc, descr, quantity, price);
            else
                parent.right = new Item(upc, descr, quantity, price);
        }
    }
    }
    
    public void removeItem(double upc)
    {
        
    }
    
    public Item findItem(double upc){
        Item current = root;
        
        while(current.getSku() != upc)
            
        {
            if (upc < current.getSku())
            {
                current = current.left;
            }
            else {
                current = current.right;
            }
            if (current ==null)
            return null;
        }
         return current;
         
       
                   
                    
                    
    }
    
    public void printInventoryReport(Item root)
    {
        Item current= root;
        if ( current == null)
           
            printInventoryReport(current.left);
     
        System.out.print(current +"/n");
        printInventoryReport(current.right);
        
        
        
    }
    
}
