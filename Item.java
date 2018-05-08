/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package possystem;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

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
    private int reorderAmt;
    private boolean reorder;
    LocalDate lastOrderDate;
    LocalDate endDate= lastOrderDate.plusYears(1);
    Item left;
    Item right;
           
    public Item( double upc, String descr, int quantity, double price)
    {
        this.sku = upc;
        description = descr;
        this.quantity = quantity;
        this.price = price;
    }
   public Item(double upc, String descr, int quantity, double price, String supplier,int threshold, boolean reorder)
   {
       this.sku = upc;
       this.description = descr;
       this.quantity = quantity;
       this.price = price;
       this.supplier = supplier;
       this.threshold = threshold;
       this.reorder = reorder;
     
       
   }
    public boolean checkReorder()
    {
        return reorder;
    }
    
    public void updateQuantity(int amt)
    {
        if(quantity < threshold)
        
        {
          
            
          for (LocalDate Date = new LocalDate(); Date.isBefore(endDate); Date = Date.plusDays(1))
          {
          Date = lastOrderDate;
            reorder =true;
            reorderAmt = 50;
           quantity = amt;
            
        }
        }
        else{
            quantity = amt;
        }
    }
    
    public double getPrice()
    {
          return price;
    }
    public String getDescription()
            {
                return description;
                
            }
    public void setSupplier(String newsupplier)
    {
       for (LocalDate Date = new LocalDate(); Date.isBefore(endDate); Date = Date.plusDays(1))
          {
              this.supplier = newsupplier;
          }
        
    }
    
    public int getThreshold()
    {
        return threshold;
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
    
    
