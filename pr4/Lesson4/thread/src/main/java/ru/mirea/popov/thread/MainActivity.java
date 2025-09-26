package ru.mirea.popov.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import ru.mirea.popov.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        handler = new Handler(Looper.getMainLooper());

        binding.buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pairsStr = binding.editTotalPairs.getText().toString();
                String daysStr = binding.editStudyDays.getText().toString();

                if (TextUtils.isEmpty(pairsStr) || TextUtils.isEmpty(daysStr)) {
                    Toast.makeText(MainActivity.this, "Заполните оба поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                int totalPairs = Integer.parseInt(pairsStr);
                int studyDays = Integer.parseInt(daysStr);

                new Thread(() -> {
                    double result = (double) totalPairs / studyDays;

                    handler.post(() -> {
                        binding.textResult.setText("Среднее количество пар: " + String.format("%.2f", result));
                    });
                }).start();
            }
        });
    }
}
