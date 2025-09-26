package ru.mirea.popov.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onClickShowDialog(View view) {
        new AlertDialogFragment().show(getSupportFragmentManager(), "mirea");
    }

    public void onOkClicked() {
        Toast.makeText(this, "выбрано движение дальше", Toast.LENGTH_SHORT).show();
    }

    public void onNeutralClicked() {
        Toast.makeText(this, "выбрана пауза", Toast.LENGTH_SHORT).show();
    }

    public void onCancelClicked() {
        Toast.makeText(this, "выбрал 'нет'", Toast.LENGTH_SHORT).show();
    }

    public void showTimeDialog(View view) {
        new TimeDialogFragment().show(getSupportFragmentManager(), "time");
    }

    public void showDateDialog(View view) {
        new DateDialogFragment().show(getSupportFragmentManager(), "date");
    }

    public void showProgressDialog(View view) {
        new ProgressDialogFragment().show(getSupportFragmentManager(), "progress");
    }

}