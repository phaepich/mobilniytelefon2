package ru.mirea.popov.employeedb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "EmployeeDB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();

        Employee hero = new Employee();
        hero.name = "Captain Java";
        hero.salary = 5432;
        employeeDao.insert(hero);

        List<Employee> heroes = employeeDao.getAll();
        for (Employee e : heroes) {
            Log.d(TAG, e.id + ": " + e.name + " – $" + e.salary);
        }

        Employee one = employeeDao.getById(1);
        if (one != null) {
            one.salary = 123456;
            employeeDao.update(one);
            Log.d(TAG, "Updated: " + one.name + " – $" + one.salary);
        }
        TextView textViewOutput = findViewById(R.id.textViewOutput);
        StringBuilder sb = new StringBuilder();
        for (Employee e : heroes) {
            sb.append(e.id).append(": ").append(e.name).append(" – $").append(e.salary).append("\n");
        }
        textViewOutput.setText(sb.toString());

    }

}
