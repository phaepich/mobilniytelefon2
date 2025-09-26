package ru.mirea.popov.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import ru.mirea.popov.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonThread.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                int numberThread = (int) (Math.random() * 10);
                long endTime = System.currentTimeMillis() + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                runOnUiThread(() -> {
                    Handler handler = new Handler(Looper.getMainLooper());

                    handler.post(() -> binding.textResult.append("1) handler.post выполнен\n"));

                    handler.postDelayed(() -> binding.textResult.append("2) handler.postDelayed через 2 сек\n"), 2000);

                    runOnUiThread(() -> binding.textResult.append("3) runOnUiThread выполнен\n"));

                });
            });
            thread.start();
        });
    }
}
