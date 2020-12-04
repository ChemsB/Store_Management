package cat.proven.store.model;

/**
 *
 * @author Chems
 */
public class Tv extends Product {
    
    private int inches;

   /**
    * Full initializacion constructor
    * @param code
    * @param descritpion
    * @param price
    * @param stock
    * @param inches 
    */
    public Tv(String code, String descritpion, double price, int stock, int inches) {
        super(code, descritpion, price, stock);
        this.inches = inches;
    }
    
    /**
     * Constructor in case of TV
     */
    public Tv(Tv other){
        
        super(other);
        this.inches=other.inches;
        
    }

    
    //Accessors
   
    public int getInches() {
        return inches;
    }

    public void setInches(int inches) {
        this.inches = inches;
    }
    
    
    
    /**
     * Display a TV
     * @return to string for diplay TV
     */
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("Tv{");
        sb.append(super.toString());
        sb.append("[inches="); sb.append(inches);  sb.append("]");
        sb.append("}");
        return sb.toString();
    }

}
