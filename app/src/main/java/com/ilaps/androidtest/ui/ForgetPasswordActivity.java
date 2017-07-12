package com.ilaps.androidtest.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.ilaps.androidtest.R;

/**
 * Created by Sarah on 7/12/17.
 */
public class ForgetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText emailInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        firebaseAuth = FirebaseAuth.getInstance();
        emailInput = (EditText) findViewById(R.id.email);
    }


    public void resetPassword(View v) {
        String email = emailInput.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), getString(R.string.error_enter_email), Toast.LENGTH_SHORT).show();
            return;
        }
        passwordResetEmail(email);
    }


    private void passwordResetEmail(String email) {
        final ProgressBar progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            Toast.makeText(ForgetPasswordActivity.this, getString(R.string.reset_password_success), Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(ForgetPasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


}
