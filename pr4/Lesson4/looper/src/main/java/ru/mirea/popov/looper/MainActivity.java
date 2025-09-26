package ru.mirea.popov.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import ru.mirea.popov.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HandlerThread handlerThread;
    private Handler backgroundHandler;
    private Handler mainHandler;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainHandler = new Handler(Looper.getMainLooper());

        handlerThread = new HandlerThread("MyHandlerThread");
        handlerThread.start();

        backgroundHandler = new Handler(handlerThread.getLooper());

        binding.buttonLooper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int age = Integer.parseInt(binding.editAge.getText().toString());
                String job = binding.editJob.getText().toString();

                backgroundHandler.post(() -> {
                    try {
                        Thread.sleep(age * 1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Log.d(TAG, "Ваш возраст: " + age + ". Ваша работа: " + job);

                    mainHandler.post(() ->
                            binding.textStatus.setText("Смотри в логи")
                    );
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quitSafely();
    }
}
