package com.prolan.cars.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.prolan.cars.R;

public class LoginActivity extends AppCompatActivity{
    private EditText etUser, etPass ;
    private TextInputLayout til_user, til_pass ;
    private ProgressBar pbLoading ;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inflating views

        etUser = (EditText) findViewById(R.id.et_user);
        etPass = (EditText) findViewById(R.id.et_pass);
        til_pass = (TextInputLayout) findViewById(R.id.input_layout_et_pass);
        til_user = (TextInputLayout) findViewById(R.id.input_layout_et_user);
        pbLoading = (ProgressBar) findViewById(R.id.login_progress);
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pbLoading.setVisibility(View.GONE);
        til_pass.setErrorEnabled(false);
        til_user.setErrorEnabled(false);
        etPass.setText("");
    }

    public void login(View view)  {
        if(etUser.getText().toString().trim().isEmpty()){ //Validating if is empty the fields
            til_user.setError(getString(R.string.msg_empty));
            til_user.setErrorEnabled(true);
            til_pass.setErrorEnabled(false);
            etUser.requestFocus();
        } else if (etPass.getText().toString().trim().isEmpty()){
            til_user.setErrorEnabled(false);
            til_pass.setErrorEnabled(true);
            til_pass.setError(getString(R.string.msg_empty));
            etPass.requestFocus();
            //Requesting the next activity
        }else {
            intent = new Intent(this,MenuActivity.class);
            final Handler handler = new Handler();
            pbLoading.setVisibility(View.VISIBLE);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                }
            }, 1500);


        }

    }
}
