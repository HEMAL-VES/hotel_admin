package hosptality.claysys.com.myapplication.models;

import com.google.firebase.database.IgnoreExtraProperties;
import com.nguyenhoanglam.imagepicker.model.Image;

/**
 * Created by muhammed on 3/10/2017.
 */
@IgnoreExtraProperties
public class ImageModel extends Image {
    public ImageModel(long id, String name, String path, boolean isSelected) {
        super(id, name, path, isSelected);
    }
}
