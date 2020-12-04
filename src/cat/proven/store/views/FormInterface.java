package cat.proven.store.views;

import cat.proven.store.model.Product;

/**
 *
 * @author Chems
 */
public interface FormInterface {
    
    /**
     * reads product data from user
     * @return product or null in case of error
     */
    Product input();
    
    
}
