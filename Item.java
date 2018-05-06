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
    private double skew;
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
        this.skew = upc;
        description = descr;
        this.quantity = quantity;
        this.price = price;
        
        
    }
    
    public void updateQuantity(int amt)
    {
        this.quantity = amt;
    }
    
    public double getPrice()
    {
          return price;
    }
    
    public double getSkew()
    {
        return skew;
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
     {
        String result;
        result = skew + "\n";
        result += description + "\n";
        
        return result;
     }
    
    
}
