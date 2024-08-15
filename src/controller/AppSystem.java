package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import model.Brand;
import model.Product;
import model.ProductInWarehouse;
import model.Warehouse;

public class AppSystem
{
    // Rozwiązanie tymczasowe
    private final String URL = "jdbc:mysql://localhost:3306/deimosdb?useSSL=false&serverTimezone=UTC";
    private final String UN = "default";
    private final String PW = "";

    private List<Brand> brands = null;
    private List<Product> products = null;
    private List<Warehouse> warehouses = null;
    private List<ProductInWarehouse> productsInWarehouses = null;

    public void pullDB()
    {
        try
        {
            JdbcPooledConnectionSource source = new JdbcPooledConnectionSource(URL, UN, PW);

            TableUtils.createTableIfNotExists(source, Brand.class);
            TableUtils.createTableIfNotExists(source, Product.class);
            TableUtils.createTableIfNotExists(source, Warehouse.class);
            TableUtils.createTableIfNotExists(source, ProductInWarehouse.class);

            Dao<Brand, Long> brandDao = DaoManager.createDao(source, Brand.class);
            Dao<Product, Long> productDao = DaoManager.createDao(source, Product.class);
            Dao<Warehouse, Long> warehouseDao = DaoManager.createDao(source, Warehouse.class);
            Dao<ProductInWarehouse, Long> productInWarehouseDao = DaoManager.createDao(source, ProductInWarehouse.class);

            brands = brandDao.queryForAll();
            products = productDao.queryForAll();
            warehouses = warehouseDao.queryForAll();
            productsInWarehouses = productInWarehouseDao.queryForAll();

            source.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }   
    }

    public int pushBrand(Brand brand)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Brand, Long> brandDao = DaoManager.createDao(destination, Brand.class);

            if (brand.id <= 0)
            {
                brandDao.create(brand);
                brands.add(brand);
            }
            else
            {
                brandDao.update(brand);
            }

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }
    
    public int popBrand(Brand brand)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Brand, Long> brandDao = DaoManager.createDao(destination, Brand.class);

            brandDao.delete(brand);

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public int pushProduct(Product product)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Product, Long> productDao = DaoManager.createDao(destination, Product.class);

            if (product.id <= 0)
            {
                productDao.create(product);
                products.add(product);
            }
            else
            {
                productDao.update(product);
            }

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public int popProduct(Product product)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Product, Long> productDao = DaoManager.createDao(destination, Product.class);

            productDao.delete(product);

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public int pushWarehouse(Warehouse warehouse)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Warehouse, Long> warehouseDao = DaoManager.createDao(destination, Warehouse.class);

            if (warehouse.id <= 0)
            {
                warehouseDao.create(warehouse);
                warehouses.add(warehouse);
            }
            else
            {
                warehouseDao.update(warehouse);
            }

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public int popWarehouse(Warehouse warehouse)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Warehouse, Long> warehouseDao = DaoManager.createDao(destination, Warehouse.class);

            warehouseDao.delete(warehouse);

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public int pushProductInWarehouse(ProductInWarehouse piw)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<ProductInWarehouse, Long> piwDao = DaoManager.createDao(destination, ProductInWarehouse.class);

            Map<String, Object> fieldValues = new HashMap<>();
            fieldValues.put("product_id", piw.product.id);
            fieldValues.put("warehouse_id", piw.warehouse.id);

            if (piwDao.queryForFieldValues(fieldValues).isEmpty())
            {
                piwDao.create(piw);
                productsInWarehouses.add(piw);
            }
            else
            {
                piwDao.update(piw);
            }

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public int popProductInWarehouse(ProductInWarehouse piw)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<ProductInWarehouse, Long> piwDao = DaoManager.createDao(destination, ProductInWarehouse.class);

            piwDao.delete(piw);

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }
    
    public List<Brand> getBrands() { return brands; }
    public List<Product> getProducts() { return products; }
    public List<Warehouse> getWarehouses() { return warehouses; }
    public List<ProductInWarehouse> getProductsInWarehouses() { return productsInWarehouses; }
}
