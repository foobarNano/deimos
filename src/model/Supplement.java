package model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.ForeignCollectionField;

public class Supplement extends Product
{
    @ForeignCollectionField(eager = false)
    ForeignCollection<Designation> designations;
}
