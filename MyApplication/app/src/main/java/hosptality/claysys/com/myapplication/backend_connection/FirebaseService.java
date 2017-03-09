package hosptality.claysys.com.myapplication.backend_connection;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hosptality.claysys.com.myapplication.activities.AddProductActivity;
import hosptality.claysys.com.myapplication.models.CategoriesModel;
import hosptality.claysys.com.myapplication.models.ProductModel;
import hosptality.claysys.com.myapplication.models.ProductTypeModel;

/**
 * Created by muhammed on 3/9/2017.
 */

public class FirebaseService implements FirbaseConstant {


    private static FirebaseService mFirbase;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    private FirebaseService() {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReferenceFromUrl(MAIN_URL);
    }

    public static FirebaseService getInstance() {
        return mFirbase == null ? mFirbase = new FirebaseService() : mFirbase;
    }


    public void getCatList(final AddProductActivity activity) {
        databaseReference.child(CATEGORIES).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<CategoriesModel>> arrayListGenericTypeIndicator = new GenericTypeIndicator<ArrayList<CategoriesModel>>() {
                };
                ArrayList<CategoriesModel> value = dataSnapshot.getValue(arrayListGenericTypeIndicator);
                activity.addToCategoriesArray(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getProTypeList(final AddProductActivity activity) {
        databaseReference.child(PRO_TYPE).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<ProductTypeModel>> arrayListGenericTypeIndicator = new GenericTypeIndicator<ArrayList<ProductTypeModel>>() {
                };
                ArrayList<ProductTypeModel> value = dataSnapshot.getValue(arrayListGenericTypeIndicator);
                activity.addToProTypeArray(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void insert(ProductModel productModel) {
        if (databaseReference.child(FOOD_ITEMS).child(productModel.getId()).setValue(productModel).isSuccessful()) {
            Log.d("TAG", "insert: " + "succes");
        }
    }
}
