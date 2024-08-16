package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import model.Brand;
import model.Client;
import model.Designation;
import model.Drug;
import model.Employee;
import model.Equipment;
import model.Grade;
import model.Ingredient;
import model.Order;
import model.Person;
import model.Product;
import model.ProductInOrder;
import model.ProductInWarehouse;
import model.Supplement;
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
    private List<Drug> drugs = null;
    private List<Ingredient> ingredients = null;
    private List<Supplement> supplements = null;
    private List<Designation> designations = null;
    private List<Equipment> equipments = null;
    private List<Order> orders = null;
    private List<ProductInOrder> productsInOrders = null;
    private List<Person> people = null;
    private List<Client> clients = null;
    private List<Grade> grades = null;
    private List<Employee> employees = null;

    public void pullDB()
    {
        try
        {
            JdbcPooledConnectionSource source = new JdbcPooledConnectionSource(URL, UN, PW);

            TableUtils.createTableIfNotExists(source, Brand.class);
            TableUtils.createTableIfNotExists(source, Product.class);
            TableUtils.createTableIfNotExists(source, Warehouse.class);
            TableUtils.createTableIfNotExists(source, ProductInWarehouse.class);
            TableUtils.createTableIfNotExists(source, Ingredient.class);
            TableUtils.createTableIfNotExists(source, Drug.class);
            TableUtils.createTableIfNotExists(source, Designation.class);
            TableUtils.createTableIfNotExists(source, Supplement.class);
            TableUtils.createTableIfNotExists(source, Equipment.class);
            TableUtils.createTableIfNotExists(source, Order.class);
            TableUtils.createTableIfNotExists(source, ProductInOrder.class);
            TableUtils.createTableIfNotExists(source, Person.class);
            TableUtils.createTableIfNotExists(source, Client.class);
            TableUtils.createTableIfNotExists(source, Grade.class);
            TableUtils.createTableIfNotExists(source, Employee.class);

            Dao<Brand, Long> brandDao = DaoManager.createDao(source, Brand.class);
            Dao<Product, Long> productDao = DaoManager.createDao(source, Product.class);
            Dao<Warehouse, Long> warehouseDao = DaoManager.createDao(source, Warehouse.class);
            Dao<ProductInWarehouse, Long> productInWarehouseDao = DaoManager.createDao(source, ProductInWarehouse.class);
            Dao<Ingredient, Long> ingredientDao = DaoManager.createDao(source, Ingredient.class);
            Dao<Drug, Long> drugDao = DaoManager.createDao(source, Drug.class);
            Dao<Designation, Long> designationDao = DaoManager.createDao(source, Designation.class);
            Dao<Supplement, Long> supplementDao = DaoManager.createDao(source, Supplement.class);
            Dao<Equipment, Long> equipmentDao = DaoManager.createDao(source, Equipment.class);
            Dao<Order, Long> orderDao = DaoManager.createDao(source, Order.class);
            Dao<ProductInOrder, Long> productInOrderDao = DaoManager.createDao(source, ProductInOrder.class);
            Dao<Person, Long> personDao = DaoManager.createDao(source, Person.class);
            Dao<Client, Long> clientDao = DaoManager.createDao(source, Client.class);
            Dao<Grade, Long> gradeDao = DaoManager.createDao(source, Grade.class);
            Dao<Employee, Long> employeeDao = DaoManager.createDao(source, Employee.class);


            brands = brandDao.queryForAll();
            products = productDao.queryForAll();
            warehouses = warehouseDao.queryForAll();
            productsInWarehouses = productInWarehouseDao.queryForAll();
            ingredients = ingredientDao.queryForAll();
            drugs = drugDao.queryForAll();
            designations = designationDao.queryForAll();
            supplements = supplementDao.queryForAll();
            equipments = equipmentDao.queryForAll();
            orders = orderDao.queryForAll();
            productsInOrders = productInOrderDao.queryForAll();
            people = personDao.queryForAll();
            clients = clientDao.queryForAll();
            grades = gradeDao.queryForAll();
            employees = employeeDao.queryForAll();

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
    
    public int pushIngredient(Ingredient ingredient)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Ingredient, Long> ingredientDao = DaoManager.createDao(destination, Ingredient.class);

            if (ingredient.id <= 0)
            {
                ingredientDao.create(ingredient);
                ingredients.add(ingredient);
            }
            else
            {
                ingredientDao.update(ingredient);
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

    public int popIngredient(Ingredient ingredient)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Ingredient, Long> ingredientDao = DaoManager.createDao(destination, Ingredient.class);

            ingredientDao.delete(ingredient);

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public int pushDrug(Drug drug)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Drug, Long> drugDao = DaoManager.createDao(destination, Drug.class);

            if (drug.id <= 0)
            {
                drugDao.create(drug);
                drugs.add(drug);
            }
            else
            {
                drugDao.update(drug);
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

    public int popDrug(Drug drug)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Drug, Long> drugDao = DaoManager.createDao(destination, Drug.class);

            drugDao.delete(drug);

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public int pushDesignation(Designation designation)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Designation, Long> designationDao = DaoManager.createDao(destination, Designation.class);

            if (designation.id <= 0)
            {
                designationDao.create(designation);
                designations.add(designation);
            }
            else
            {
                designationDao.update(designation);
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

    public int popDesignation(Designation designation)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Designation, Long> designationDao = DaoManager.createDao(destination, Designation.class);

            designationDao.delete(designation);

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public int pushSupplement(Supplement supplement)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Supplement, Long> supplementDao = DaoManager.createDao(destination, Supplement.class);

            if (supplement.id <= 0)
            {
                supplementDao.create(supplement);
                supplements.add(supplement);
            }
            else
            {
                supplementDao.update(supplement);
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

    public int popSupplement(Supplement supplement)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Supplement, Long> supplementDao = DaoManager.createDao(destination, Supplement.class);

            supplementDao.delete(supplement);

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public int pushEquipment(Equipment equipment)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Equipment, Long> equipmentDao = DaoManager.createDao(destination, Equipment.class);

            if (equipment.id <= 0)
            {
                equipmentDao.create(equipment);
                equipments.add(equipment);
            }
            else
            {
                equipmentDao.update(equipment);
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

    public int popEquipment(Equipment equipment)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Equipment, Long> equipmentDao = DaoManager.createDao(destination, Equipment.class);

            equipmentDao.delete(equipment);

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public int pushOrder(Order order)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Order, Long> orderDao = DaoManager.createDao(destination, Order.class);

            if (order.id <= 0)
            {
                orderDao.create(order);
                orders.add(order);
            }
            else
            {
                orderDao.update(order);
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

    public int popOrder(Order order)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<Order, Long> orderDao = DaoManager.createDao(destination, Order.class);

            orderDao.delete(order);

            destination.close();
            return 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    public int pushProductInOrder(ProductInOrder pio)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<ProductInOrder, Long> pioDao = DaoManager.createDao(destination, ProductInOrder.class);

            Map<String, Object> fieldValues = new HashMap<>();
            fieldValues.put("product_id", pio.product.id);
            fieldValues.put("order_id", pio.order.id);

            if (pioDao.queryForFieldValues(fieldValues).isEmpty())
            {
                pioDao.create(pio);
                productsInOrders.add(pio);
            }
            else
            {
                pioDao.update(pio);
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

    public int popProductInOrder(ProductInOrder pio)
    {
        try
        {
            JdbcPooledConnectionSource destination = new JdbcPooledConnectionSource(URL, UN, PW);
            Dao<ProductInOrder, Long> pioDao = DaoManager.createDao(destination, ProductInOrder.class);

            pioDao.delete(pio);

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
    public List<Drug> getDrugs() { return drugs; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public List<Supplement> getSupplements() { return supplements; }
    public List<Designation> getDesignations() { return designations; }
    public List<Equipment> getEquipments() { return equipments; }
    public List<Order> getOrders() { return orders; }
    public List<ProductInOrder> getProductsInOrders() { return productsInOrders; }
    public List<Person> getPeople() { return people; }
    public List<Client> getClients() { return clients; }
    public List<Grade> getGrades() { return grades; }
    public List<Employee> getEmployees() { return employees; }
}
