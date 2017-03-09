package hosptality.claysys.com.myapplication.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by muhammed on 3/9/2017.
 */
@IgnoreExtraProperties
public class ProductModel {

    private int catogoryid;
    private String comment;
    private int count;
    private String id;
    private String name;
    private String price;
    private int type;
    private ArrayList<String> images;

    public ProductModel() {
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public int getCatogoryid() {
        return catogoryid;
    }

    public void setCatogoryid(int catogoryid) {
        this.catogoryid = catogoryid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
