package ru.mirea.popov.mireaproject;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class PracticeWorker extends Worker {

    public static final String TAG = "PracticeWorker";

    public PracticeWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "Фоновая задача на 5 секунд");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            return Result.failure();
        }
        Log.d(TAG, "Фоновая задача завершена");
        return Result.success();
    }
}
