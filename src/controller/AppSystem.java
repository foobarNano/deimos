package controller;

import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import model.Brand;
import model.Product;

public class AppSystem
{
    private List<Brand> brandsList = null;
    private List<Product> productsList = null;

    public void pullDB()
    {
        try
        {
            JdbcPooledConnectionSource source = new JdbcPooledConnectionSource("jdbc:mysql://localhost:3306/deimosdb?useSSL=false&serverTimezone=UTC", "default", "");

            // TableUtils.dropTable(source, Product.class, true);

            TableUtils.createTableIfNotExists(source, Brand.class);
            TableUtils.createTableIfNotExists(source, Product.class);
            // TableUtils.createTableIfNotExists(source, Order.class);

            Dao<Brand, Long> brandDao = DaoManager.createDao(source, Brand.class);
            Dao<Product, Long> productDao = DaoManager.createDao(source, Product.class);
            // Dao<Order, Long> orderDao = DaoManager.createDao(source, Order.class);

            brandsList = brandDao.queryForAll();
            productsList = productDao.queryForAll();

            source.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public List<Brand> getBrandsList() { return brandsList; }
    public List<Product> getProductsList() { return productsList; }
}
