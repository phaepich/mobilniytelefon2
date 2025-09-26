package ru.mirea.popov.mireaproject;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.FileOutputStream;

public class FileFragment extends Fragment {
    private static final String FILENAME = "user_note.txt";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_file, container, false);
        FloatingActionButton fab = root.findViewById(R.id.fabSaveText);
        TextView textSaved = root.findViewById(R.id.textSaved);

        fab.setOnClickListener(v -> {
            EditText input = new EditText(requireContext());
            new AlertDialog.Builder(requireContext())
                    .setTitle("Введите заметку")
                    .setView(input)
                    .setPositiveButton("Сохранить", (dialog, which) -> {
                        String text = input.getText().toString();
                        try (FileOutputStream fos = requireActivity().openFileOutput(FILENAME, Context.MODE_PRIVATE)) {
                            fos.write(text.getBytes());
                            textSaved.setText("Файл сохранён:\n" + text);
                        } catch (Exception e) {
                            textSaved.setText("Ошибка сохранения: " + e.getMessage());
                        }
                    })
                    .setNegativeButton("Отмена", null)
                    .show();
        });

        return root;
    }
}
