package ru.mirea.popov.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    public static final String TAG = "MyWorker";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "Задача началась");

        try {
            Thread.sleep(10000); // 10 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Задача завершена");
        return Result.success();
    }
}
