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

public class RegisterActivity extends AppCompatActivity {

    private EditText etFullname, etEmail, etPassword;
    private Button btnRegister;
    private TextView tvLogin;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        preferenceManager = new PreferenceManager(this);

        etFullname = findViewById(R.id.etFullname);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        // Animation
        findViewById(R.id.layoutHeaderRegister).setAlpha(0f);
        findViewById(R.id.layoutHeaderRegister).animate().alpha(1f).setDuration(1000).start();

        btnRegister.setOnClickListener(v -> {
            String fullname = etFullname.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (fullname.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User();
            user.setFullname(fullname);
            user.setEmail(email);
            user.setPassword(password);

            registerUser(user);
        });

        tvLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void registerUser(User user) {
        RetrofitClient.getApiService().register(user).enqueue(new retrofit2.Callback<com.example.readquizapp.model.ApiResponse<User>>() {
            @Override
            public void onResponse(Call<com.example.readquizapp.model.ApiResponse<User>> call, Response<com.example.readquizapp.model.ApiResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    User registeredUser = response.body().getData();
                    preferenceManager.setLogin(true, registeredUser.getEmail(), registeredUser.getFullname());
                    
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                } else {
                    String errorMsg = "Registration failed";
                    try {
                        if (response.errorBody() != null) {
                            // Cố gắng parse lỗi từ ApiResponse trong errorBody
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
                    Toast.makeText(RegisterActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.readquizapp.model.ApiResponse<User>> call, Throwable t) {
                android.util.Log.e("API_ERROR", "Network error: " + t.getMessage());
                Toast.makeText(RegisterActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
