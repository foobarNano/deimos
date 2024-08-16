package model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Ingredient    // For normalization purposes
{
    @DatabaseField(generatedId = true)
    public long id;
    @DatabaseField
    String name;

    @ForeignCollectionField(eager = false)
    ForeignCollection<Drug> drugs;
}
