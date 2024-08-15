package model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Brand
{

    @DatabaseField(generatedId = true)
    public long id;
    @DatabaseField(canBeNull = false, unique = true)
    public String name;
    @DatabaseField(canBeNull = false)
    public Type type;
    @ForeignCollectionField(eager = false)
    public ForeignCollection<Product> products;

    public enum Type
    {
        Federal,
        Union,
        Unaffiliated
    }
}
