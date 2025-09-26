package ru.mirea.popov.internalfilestorage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private EditText editTextDate, editTextDescription;
    private Button buttonSave;
    private static final String FILE_NAME = "history.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDate = findViewById(R.id.editTextDate);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonSave = findViewById(R.id.buttonSaveFile);

        buttonSave.setOnClickListener(v -> {
            String textToSave = editTextDate.getText().toString() + ": " +
                    editTextDescription.getText().toString();
            try {
                FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                fos.write(textToSave.getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
