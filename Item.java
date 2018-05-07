/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package possystem;
import java.util.*;

/**
 *
 * @author russelndip
 */
public class Item {
    private double price;
    private double sku;
    private String supplier;
    private String description;
    private int quantity;
    private int threshold;
    private boolean reorder;
    private Date lastOrderDate;
    Item left;
    Item right;
           
    public Item( double upc, String descr, int quantity, double price)
    {
        this.sku = upc;
        description = descr;
        this.quantity = quantity;
        this.price = price;
        
        
    }
   
    public boolean checkReorder()
    {
        return reorder;
    }
    
    public void updateQuantity(int amt)
    {
        this.quantity = amt;
    }
    
    public double getPrice()
    {
          return price;
    }
    public String getDescription()
            {
                return description;
                
            }
    public int getQuantity()
    {
        return quantity;
    }
    public double getSku()
    {
        return sku;
    }
    public void changePrice(double newPrice)
    {
        this.price = newPrice;
    }
     public void setThreshhold(int threshold)
     {
         this.threshold = threshold;
 
     }
     
     public String toString()
     {String reorderQ;
         if (reorder == true)
         {
              reorderQ ="yes";
         }
         else
             reorderQ = "no";
         
        String result;
        result = "Skew # \t description \t supplier \t quantity \t Marked for ReOrder \t last Re-Order Date";
        result = sku + "\t";
        result += description + "\t";
        result += price + "\t";
        result += supplier + "\t";
        result += quantity + "\t";
        result += reorderQ+ "\t";
        result += lastOrderDate;
        return result;
     }
    
    
}
