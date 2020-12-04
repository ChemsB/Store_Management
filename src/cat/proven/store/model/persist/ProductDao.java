/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.store.model.persist;

import java.sql.ResultSet;

import cat.proven.store.model.Product;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chems
 */
public class ProductDao {

    private final String QUERY_SELECT_WHERE_CODE = "select * from products where code=?";
    private final String QUERY_SELECT_ALL = "select * from products";
    private final String QUERY_INSERT_INTO = "insert into products values (?,?,?,?)";
    private final String QUERY_DELETE = "delete from products where code=?";
    private final String QUERY_SELECT_STOCK = "select * from products where stock<?";
    private final String QUERY_MODIFY = "update products set code=?, description=?, price=?, stock=? where code = ?";

    /**
     * selects product by code
     *
     * @param code the code to search
     * @return product with the given code or null in case of error or otherwise
     */
    public Product selectWhereCode(String code) {

        
        Product found = null;
        try {

            Connection connection = DbConnect.getConnection();

            if (connection != null) {

                PreparedStatement ps = connection.prepareStatement(QUERY_SELECT_WHERE_CODE);
                ps.setString(1, code);
                ResultSet rs = ps.executeQuery();

                rs.next();

                found = resultSetToProduct(rs);

            } else {
                found = null;

            }

        } catch (SQLException ex) {
            //Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            found = null;
        }

        return found;

    }

    /**
     * List all products from database
     *
     * @return list with products or null in case of error or otherwise
     */
    public List<Product> selectAll() {

        List<Product> found = new ArrayList<>();
        try {

            Connection connection = DbConnect.getConnection();

            if (connection != null) {

                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(QUERY_SELECT_ALL);

                while (rs.next()) {
                    Product p = resultSetToProduct(rs);
                    found.add(p);

                }
                return found;

            } else {
                found = null;
            }

        } catch (SQLException ex) {
            found = null;
            //Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return found;
    }

    /**
     * Insert new product
     *
     * @param product to add
     * @return 0 in case of error or otherwise
     */
    public int insertData(Product product) {

        int res = 0;
        try {

            Connection connection = DbConnect.getConnection();

            if (connection != null) {

                PreparedStatement ps = connection.prepareStatement(QUERY_INSERT_INTO);
                ps.setString(1, product.getCode());
                ps.setString(2, product.getDescritpion());
                ps.setDouble(3, product.getPrice());
                ps.setInt(4, product.getStock());
                ps.execute();
                res = 1;

            }

        } catch (SQLException ex) {
            //Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            res = 0;
        }

        return res;

    }

    /**
     * List a products with low stock input for user
     * @param stock to compare
     * @return list with porducts
     */
    public List<Product> listWithLowStock(int stock) {

        List<Product> found = new ArrayList<>();

        try {

            Connection connection = DbConnect.getConnection();

            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(QUERY_SELECT_STOCK);
                ps.setInt(1, stock);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Product p = resultSetToProduct(rs);
                    found.add(p);

                }

            }

        } catch (SQLException ex) {
            found = null;
            //Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return found;

    }
    
    
    public boolean modifyProduct (Product oldProduct, Product newProduct){
        
        boolean res = false;
        
        try {            
            Connection connection = DbConnect.getConnection();
            
            if(connection!=null){
                
                PreparedStatement ps = connection.prepareStatement(QUERY_MODIFY);
                ps.setString(1, newProduct.getCode());
                ps.setString(2, newProduct.getDescritpion());
                ps.setDouble(3, newProduct.getPrice());
                ps.setInt(4, newProduct.getStock());
                ps.setString(5, oldProduct.getCode());
                ps.execute();
                res=true;
                
            }
            
            
        } catch (SQLException ex) {
            res=false;
            //Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
    

    public boolean deleteData(Product p) {

        boolean res = false;
        try {

            Connection connection = DbConnect.getConnection();

            if (connection != null) {

                PreparedStatement ps = connection.prepareStatement(QUERY_DELETE);
                ps.setString(1, p.getCode());
                ps.execute();
                res = true;

            }

        } catch (SQLException ex) {
            //Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            res = false;
        }

        return res;
    }

    /**
     * COnverts data from current row of resulset into a product
     *
     * @param rs the result to extract data from
     * @return product with extracted data
     * @throws SQLException in case of error
     */
    private Product resultSetToProduct(ResultSet rs) throws SQLException {

        String code = rs.getString("code");
        String description = rs.getString("description");
        double price = rs.getDouble("price");
        int stock = rs.getInt("stock");
        Product p = new Product(code, description, price, stock);
        return p;

    }

}
