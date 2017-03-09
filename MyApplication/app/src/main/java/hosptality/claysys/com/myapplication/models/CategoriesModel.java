package hosptality.claysys.com.myapplication.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by muhammed on 3/9/2017.
 */

@IgnoreExtraProperties
public class CategoriesModel {
    private String description;
    private int id;
    private String name;

    public CategoriesModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
