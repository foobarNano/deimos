package model;

import java.sql.Timestamp;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Order
{
    @DatabaseField(generatedId = true)
    public long id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    public Client client;
    @DatabaseField
    public Timestamp placed;
    @DatabaseField
    public Timestamp confirmed;
    @DatabaseField
    public Timestamp completed;

    @ForeignCollectionField(eager = true)
    public ForeignCollection<ProductInOrder> productsOrdered;

    public double getValue()
    {
        double sum = 0d;

        for (ProductInOrder p : productsOrdered) { sum += p.product.value; }

        return sum;
    }
}
