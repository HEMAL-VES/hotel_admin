package hosptality.claysys.com.myapplication.backend_connection;

import android.net.Uri;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nguyenhoanglam.imagepicker.model.Image;

import java.io.File;

import hosptality.claysys.com.myapplication.activities.AddProductActivity;

/**
 * Created by muhammed on 3/9/2017.
 */

public class FirbaseFileService implements FirbaseConstant {
    private static FirbaseFileService mFirbaseFileService;
    private StorageReference storageReference;
    private FirebaseStorage storage;

    private FirbaseFileService() {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    public static FirbaseFileService getInstance() {
        return mFirbaseFileService == null ? mFirbaseFileService = new FirbaseFileService() : mFirbaseFileService;
    }

    public UploadTask uploadFile(AddProductActivity activity, String id, Image image) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        StorageReference storageReference = this.storageReference.child(FirbaseConstant.STORAGE_MAIN).child(id).child(image.getName());

        Uri file = Uri.fromFile(new File(image.getPath()));
        // Create the file metadata
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentType("image/jpeg")
                .build();
        UploadTask uploadTask = storageReference.putFile(file, metadata);

        return uploadTask;


    }
}
