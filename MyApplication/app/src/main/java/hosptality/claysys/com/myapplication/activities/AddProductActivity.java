package hosptality.claysys.com.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;
import com.nguyenhoanglam.imagepicker.activity.ImagePicker;
import com.nguyenhoanglam.imagepicker.activity.ImagePickerActivity;
import com.nguyenhoanglam.imagepicker.model.Image;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hosptality.claysys.com.myapplication.R;
import hosptality.claysys.com.myapplication.adapters.SpinnerAdapterCatogories;
import hosptality.claysys.com.myapplication.adapters.SpinnerAdapterFoodType;
import hosptality.claysys.com.myapplication.backend_connection.FirbaseFileService;
import hosptality.claysys.com.myapplication.backend_connection.FirebaseService;
import hosptality.claysys.com.myapplication.models.CategoriesModel;
import hosptality.claysys.com.myapplication.models.ProductModel;
import hosptality.claysys.com.myapplication.models.ProductTypeModel;
import hosptality.claysys.com.myapplication.utils.Utils;

/**
 * Created by muhammed on 3/9/2017.
 */

public class AddProductActivity extends ContainerActivity {

    private static final int REQUEST_CODE_PICKER = 1025;
    @BindView(R.id.productNameeditText)
    EditText mProNameEditText;

    @BindView(R.id.productDescripeditText)
    EditText mProDescriptionEditText;

    @BindView(R.id.imagePickerFrameLayout)
    FrameLayout mImagePicker;

    @BindView(R.id.productQuantitytEditText)
    EditText mProQuantitiyEditText;

    @BindView(R.id.productPriceEditText)
    EditText mProPriceEditText;

    @BindView(R.id.productCatgSpinner)
    Spinner mProCatogoriesSpinner;

    @BindView(R.id.productTypeSpinner)
    Spinner mProTypeSpinner;

    @BindView(R.id.productImageView)
    ImageView imageView;

    @BindView(R.id.submitButton)
    Button mSubmitButton;

    private ArrayList<Image> images = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_activity);
        ButterKnife.bind(this);

        loadValues();
    }

    private void loadValues() {
        FirebaseService.getInstance().getCatList(this);
        FirebaseService.getInstance().getProTypeList(this);
    }

    public void addToCategoriesArray(ArrayList<CategoriesModel> value) {
        SpinnerAdapterCatogories spinnerAdapter = new SpinnerAdapterCatogories(this, value);
        mProCatogoriesSpinner.setAdapter(spinnerAdapter);
    }

    public void addToProTypeArray(ArrayList<ProductTypeModel> value) {
        SpinnerAdapterFoodType spinnerAdapter = new SpinnerAdapterFoodType(this, value);
        mProTypeSpinner.setAdapter(spinnerAdapter);
    }

    @OnClick(R.id.submitButton)
    public void submitValues() {

        final String saltString = Utils.getSaltString();

        UploadTask uploadTask = FirbaseFileService.getInstance().uploadFile(this, saltString, images.get(0));
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ProductModel productModel = new ProductModel();
                productModel.setImages(images);
                productModel.setCatogoryid(((CategoriesModel) mProCatogoriesSpinner.getSelectedItem()).getId());
                productModel.setComment(mProDescriptionEditText.getText().toString());
                productModel.setCount(Integer.parseInt(mProQuantitiyEditText.getText().toString()));
                productModel.setName(mProNameEditText.getText().toString());
                productModel.setId(saltString);
                productModel.setPrice(mProPriceEditText.getText().toString());
                productModel.setType(((ProductTypeModel) mProTypeSpinner.getSelectedItem()).getId());
                FirebaseService.getInstance().insert(productModel, AddProductActivity.this);
            }
        });

//


    }

    @OnClick(R.id.imagePickerFrameLayout)
    public void pickImage() {
        ImagePicker.create(this)
                .folderMode(true) // folder mode (false by default)
                .folderTitle("Folder") // folder selection title
                .imageTitle("Tap to select") // image selection title
                .single()  // multi mode (default mode)
                .limit(10) // max images can be selected (999 by default)
                .showCamera(true) // show camera or not (true by default)
                .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                .origin(images) // original selected images, used in multi mode
                .start(REQUEST_CODE_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICKER && resultCode == RESULT_OK && data != null) {
            images = data.getParcelableArrayListExtra(ImagePickerActivity.INTENT_EXTRA_SELECTED_IMAGES);
            Glide.with(this)
                    .load(images.get(0).getPath())
                    .placeholder(R.drawable.ic_add_a_photo_blackdp)
                    .error(R.drawable.ic_add_a_photo_blackdp)
                    .dontAnimate()
                    .into(imageView);
        }
    }

    public void submissionSuccessful() {
        Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
