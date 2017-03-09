package hosptality.claysys.com.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;

import hosptality.claysys.com.myapplication.R;

/**
 * Created by muhammed on 3/8/2017.
 */

public class MainActivity extends ContainerActivity implements View.OnClickListener {
    private CardView mAddProductButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setUI();

    }

    private void setUI() {
        mAddProductButton = (CardView) findViewById(R.id.addProductButon);
        mAddProductButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addProductButon:
                startActivity(new Intent(this, AddProductActivity.class));
                break;
            default:
                break;
        }
    }
}
