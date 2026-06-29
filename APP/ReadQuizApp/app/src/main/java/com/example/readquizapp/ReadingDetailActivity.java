package com.example.readquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readquizapp.api.RetrofitClient;
import com.example.readquizapp.model.Reading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadingDetailActivity extends AppCompatActivity {

    private TextView tvTitle, tvSummary, tvContent;
    private Button btnEdit, btnDelete;
    private Integer readingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_detail);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> finish());
        }

        tvTitle = findViewById(R.id.tvTitleDetail);
        tvSummary = findViewById(R.id.tvSummaryDetail);
        tvContent = findViewById(R.id.tvContentDetail);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        readingId = getIntent().getIntExtra("READING_ID", -1);

        if (readingId != -1) {
            fetchReadingDetails(readingId);
        }

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(ReadingDetailActivity.this, AddEditReadingActivity.class);
            intent.putExtra("READING_ID", readingId);
            startActivity(intent);
        });

        btnDelete.setOnClickListener(v -> {
            deleteReading(readingId);
        });
    }

    private void fetchReadingDetails(Integer id) {
        RetrofitClient.getApiService().getReadingById(id).enqueue(new retrofit2.Callback<com.example.readquizapp.model.ApiResponse<Reading>>() {
            @Override
            public void onResponse(Call<com.example.readquizapp.model.ApiResponse<Reading>> call, Response<com.example.readquizapp.model.ApiResponse<Reading>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Reading reading = response.body().getData();
                    tvTitle.setText(reading.getTitle());
                    tvSummary.setText(reading.getSummary());
                    tvContent.setText(reading.getContent());
                }
            }

            @Override
            public void onFailure(Call<com.example.readquizapp.model.ApiResponse<Reading>> call, Throwable t) {
                Toast.makeText(ReadingDetailActivity.this, "Error fetching details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteReading(Integer id) {
        RetrofitClient.getApiService().deleteReading(id).enqueue(new retrofit2.Callback<com.example.readquizapp.model.ApiResponse<Void>>() {
            @Override
            public void onResponse(Call<com.example.readquizapp.model.ApiResponse<Void>> call, Response<com.example.readquizapp.model.ApiResponse<Void>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Toast.makeText(ReadingDetailActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<com.example.readquizapp.model.ApiResponse<Void>> call, Throwable t) {
                Toast.makeText(ReadingDetailActivity.this, "Error deleting", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
