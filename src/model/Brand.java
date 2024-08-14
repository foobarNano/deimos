package model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Brand
{
    @DatabaseField(generatedId = true)
    long id;
    @DatabaseField(canBeNull = false)
    String name;
    @DatabaseField(canBeNull = false)
    Type type;
    @ForeignCollectionField(eager = false)
    ForeignCollection<Product> products;

    enum Type
    {
        Federal,
        Union,
        Unaffiliated
    }
}
