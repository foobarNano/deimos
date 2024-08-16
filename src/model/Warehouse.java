package model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Warehouse
{
    @DatabaseField(generatedId = true)
    public long id;
    @DatabaseField
    public String location;
    @DatabaseField(canBeNull = false)
    public double sales_tax;

    @ForeignCollectionField(eager = false)
    public ForeignCollection<ProductInOrder> productsStored;
}
