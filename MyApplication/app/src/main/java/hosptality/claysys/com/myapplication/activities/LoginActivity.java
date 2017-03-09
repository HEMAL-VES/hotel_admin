package hosptality.claysys.com.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import hosptality.claysys.com.myapplication.R;

public class LoginActivity extends ContainerActivity implements View.OnClickListener {
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);

        setUI();
    }

    private void setUI() {
        firebaseAuth = FirebaseAuth.getInstance();
        mUsernameEditText = (EditText) findViewById(R.id.usernameeditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);
        mLoginButton = (Button) findViewById(R.id.loginbutton);

        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginbutton:
                firebaseAuth.signInWithEmailAndPassword(mUsernameEditText.getText().toString(), mPasswordEditText.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}
