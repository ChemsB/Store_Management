package cat.proven.store.model;

import cat.proven.store.model.persist.DbConnect;
import java.util.ArrayList;
import cat.proven.store.model.persist.ProductDao;
import java.util.List;

/**
 *
 * @author Chems
 */
public class Model {

    private ProductDao productDao;

    public Model() {
        productDao = new ProductDao();

    }

    /**
     * recives all products from the data store
     *
     * @return list of all products or null in case of error
     */
    public List<Product> listAllProducts() {
        List<Product> res = productDao.selectAll();
        return res;
    }

    /**
     * recives product with the given code from the data source
     *
     * @param code the code to search
     * @return product with the given code or null if not found or in case of
     * error
     */
    public Product findProductByCode(String code) {

        Product result = null;
        result=productDao.selectWhereCode(code);
        return result;
    }

    /**
     * adds a product to the data source avoids adding null products or products
     * with null code else avoids adding when list is full and when another
     * product with the same code exists in the list
     *
     * @param product the product to add
     * @return true if successfully added, false otherwise
     */
    public boolean addProduct(Product product) {

        boolean res = false;
        
        int rows = productDao.insertData(product);
        
        if(rows>0){
            res=true;
        }

        return res;
    }

    /**
     * Find products with stock lower than the given value
     *
     * @param stock the threshold value to search products
     * @return list of porducts lower than stock or null in case of error
     */
    public List<Product> findProductWithLowStock(int stock) {

        List<Product> listLower = new ArrayList<>(); //create other list

        listLower=productDao.listWithLowStock(stock);
        
        return listLower; //Return new list
    }

    /**
     * Update data from a product
     *
     * @param oldProduct product to update
     * @param newProduct data form a new porduct
     * @return true if success modified or false in case of error o otherwise
     * check if the new code exists in other porduct return false
     *
     */
    public boolean modifyProduct(Product oldProduct, Product newProduct) {

        boolean res = true;
        
        res=productDao.modifyProduct(oldProduct, newProduct);
        
        return res;

    }

    /**
     * remove one product from products
     *
     * @param productToRemove product to remove
     * @return true if has removed or false in case of error
     */
    public boolean removeProduct(Product prod){

        boolean res = false;

        res = productDao.deleteData(prod);
        
        return res;
    }
}
