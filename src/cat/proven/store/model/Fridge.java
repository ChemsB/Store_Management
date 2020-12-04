
package cat.proven.store.model;

/**
 *
 * @author Chems
 */
public class Fridge extends Product{
    
    private int capacity;
    private boolean noFrost;

    /**
     * Full initializacion constructor
     * @param code
     * @param descritpion
     * @param price
     * @param stock
     * @param capacity
     * @param noFrost 
     */
    public Fridge(String code, String descritpion, double price, int stock, int capacity, boolean noFrost) {
        super(code, descritpion, price, stock);
        this.capacity = capacity;
        this.noFrost = noFrost;
    }
    
    /**
     * Constructor in case of Fridge
     * @param other 
     */
    public Fridge(Fridge other){
        
        super(other); // Invocamos el constructor de producto, y le pasamos un producto en este caso un tv
        this.capacity = other.capacity;
        this.noFrost = other.noFrost;
        
    }

    //Accessors
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isNoFrost() {
        return noFrost;
    }

    public void setNoFrost(boolean noFrost) {
        this.noFrost = noFrost;
    }
    
    
   
    /**
     * Display a Fridge
     */
   @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("Fridge{");
        sb.append(super.toString());
        sb.append("[capacity="); sb.append(capacity);  sb.append("]");
        
        if(noFrost){
            sb.append("[frost="); sb.append("YES]");
        }else{
            sb.append("[frost="); sb.append("NO]");
        }
        
        
        sb.append("}");
        return sb.toString();
    }
    
    
    
    
    
    
}
