package cat.proven.store.model;

import java.util.Objects;

/**
 * ADT for product.
 * @author matí
 */
public class Product {
    private String code; 
    private String descritpion;
    private double price;
    private int stock;

    /**
     * Default constructor
     */
    public Product() {
        
    }
    
    /**
     * Full initializacion constructor 
     * @param code
     * @param descritpion
     * @param price
     * @param stock 
     */
    
    public Product(String code, String descritpion, double price, int stock) {
        this.code = code;
        this.descritpion = descritpion;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructor with only code
     * @param code 
     */
    
    public Product(String code) {
        this.code = code;
    }
    
    
    /**
     * copy constructor
     * @param other the product to copy
     */
    public Product(Product other) {
       this.code = other.code;
       this.descritpion = other.descritpion;
       this.price = other.price;
       this.stock = other.stock;
    }

    
    // Accessors
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.code);
        return hash;
    }

    
    //Check if one object is same an other object
    @Override
    public boolean equals(Object obj) {
        boolean b;
        
        if(obj == null){ //null object
            b = false;
        }else{
            if(this == obj){ //same object
                b = true;
            }else{
                if (obj instanceof Product){ // obj is a product
                    Product other = (Product) obj;
                    b=(this.code.equals(other.code));
                }else{
                    b = false;
                }
            }
        }
        return b;
    }
    
    //Display a porduct attributes
    @Override
    public String toString() {
       
        StringBuilder sb = new StringBuilder();
        sb.append("Product{ ");
        sb.append("[Code="); sb.append(code); sb.append("]");
        sb.append("[Description="); sb.append(descritpion); sb.append("]");
        sb.append("[Price="); sb.append(price); sb.append("]");
        sb.append("[Stock="); sb.append(stock); sb.append("]");
        sb.append(" }");
        return sb.toString();
    }
    
    
    
}
