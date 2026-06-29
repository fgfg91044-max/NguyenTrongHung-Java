package com.example.readquizapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readquizapp.api.RetrofitClient;
import com.example.readquizapp.model.Reading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditReadingActivity extends AppCompatActivity {

    private EditText etTitle, etSummary, etContent;
    private Button btnSave;
    private TextView tvHeader;
    private Integer readingId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_reading);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarAddEdit);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> finish());
        }

        etTitle = findViewById(R.id.etTitle);
        etSummary = findViewById(R.id.etSummary);
        etContent = findViewById(R.id.etContent);
        btnSave = findViewById(R.id.btnSave);
        tvHeader = findViewById(R.id.tvHeader);

        readingId = getIntent().getIntExtra("READING_ID", -1);

        if (readingId != -1) {
            tvHeader.setText("Edit Reading");
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Edit Reading");
            }
            fetchReadingDetails(readingId);
        }

        btnSave.setOnClickListener(v -> {
            saveReading();
        });
    }

    private void fetchReadingDetails(Integer id) {
        RetrofitClient.getApiService().getReadingById(id).enqueue(new retrofit2.Callback<com.example.readquizapp.model.ApiResponse<Reading>>() {
            @Override
            public void onResponse(Call<com.example.readquizapp.model.ApiResponse<Reading>> call, Response<com.example.readquizapp.model.ApiResponse<Reading>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Reading reading = response.body().getData();
                    etTitle.setText(reading.getTitle());
                    etSummary.setText(reading.getSummary());
                    etContent.setText(reading.getContent());
                }
            }

            @Override
            public void onFailure(Call<com.example.readquizapp.model.ApiResponse<Reading>> call, Throwable t) {
                Toast.makeText(AddEditReadingActivity.this, "Error fetching details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveReading() {
        String title = etTitle.getText().toString().trim();
        String summary = etSummary.getText().toString().trim();
        String content = etContent.getText().toString().trim();

        if (title.isEmpty() || summary.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Reading reading = new Reading();
        reading.setTitle(title);
        reading.setSummary(summary);
        reading.setContent(content);

        if (readingId == -1) {
            RetrofitClient.getApiService().createReading(reading).enqueue(new retrofit2.Callback<com.example.readquizapp.model.ApiResponse<Reading>>() {
                @Override
                public void onResponse(Call<com.example.readquizapp.model.ApiResponse<Reading>> call, Response<com.example.readquizapp.model.ApiResponse<Reading>> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                        Toast.makeText(AddEditReadingActivity.this, "Created successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<com.example.readquizapp.model.ApiResponse<Reading>> call, Throwable t) {
                    Toast.makeText(AddEditReadingActivity.this, "Error creating", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            RetrofitClient.getApiService().updateReading(readingId, reading).enqueue(new retrofit2.Callback<com.example.readquizapp.model.ApiResponse<Reading>>() {
                @Override
                public void onResponse(Call<com.example.readquizapp.model.ApiResponse<Reading>> call, Response<com.example.readquizapp.model.ApiResponse<Reading>> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                        Toast.makeText(AddEditReadingActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<com.example.readquizapp.model.ApiResponse<Reading>> call, Throwable t) {
                    Toast.makeText(AddEditReadingActivity.this, "Error updating", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
