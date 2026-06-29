package com.example.readquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readquizapp.api.RetrofitClient;
import com.example.readquizapp.model.User;
import com.example.readquizapp.utils.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        preferenceManager = new PreferenceManager(this);
        if (preferenceManager.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        // Advanced Animation for "Wow" effect
        findViewById(R.id.layoutHeader).setTranslationY(-300f);
        findViewById(R.id.layoutHeader).animate().translationY(0f).alpha(1f).setDuration(800).start();

        // Pre-fill test account
        etEmail.setText("android_test_20260629121125@readquiz.local");
        etPassword.setText("123456");

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            loginUser(user);
        });

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        });
    }

    private void loginUser(User user) {
        RetrofitClient.getApiService().login(user).enqueue(new retrofit2.Callback<com.example.readquizapp.model.ApiResponse<User>>() {
            @Override
            public void onResponse(Call<com.example.readquizapp.model.ApiResponse<User>> call, Response<com.example.readquizapp.model.ApiResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    User loggedInUser = response.body().getData();
                    preferenceManager.setLogin(true, loggedInUser.getEmail(), loggedInUser.getFullname());

                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    String errorMsg = "Invalid credentials";
                    try {
                        if (response.errorBody() != null) {
                            String errorJson = response.errorBody().string();
                            com.google.gson.Gson gson = new com.google.gson.Gson();
                            com.example.readquizapp.model.ApiResponse apiResponse = gson.fromJson(errorJson, com.example.readquizapp.model.ApiResponse.class);
                            if (apiResponse != null && apiResponse.getMessage() != null) {
                                errorMsg = apiResponse.getMessage();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.readquizapp.model.ApiResponse<User>> call, Throwable t) {
                android.util.Log.e("API_ERROR", "Network error: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
