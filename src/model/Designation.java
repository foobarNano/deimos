package model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Designation
{
    @DatabaseField(generatedId = true)
    public long id;
    @DatabaseField(width = 64, canBeNull = false)
    public String short_description;
    @DatabaseField(width = 512, canBeNull = true)
    public String long_description;

    @ForeignCollectionField(eager = false)
    ForeignCollection<Supplement> supplements;
}
