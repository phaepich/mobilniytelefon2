package ru.mirea.popov.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ProfileFragment extends Fragment {

    private EditText editName, editAge, editEmail;
    private SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        editName = root.findViewById(R.id.editName);
        editAge = root.findViewById(R.id.editAge);
        editEmail = root.findViewById(R.id.editEmail);
        Button buttonSave = root.findViewById(R.id.buttonSaveProfile);

        preferences = requireActivity().getSharedPreferences("profile_prefs", Context.MODE_PRIVATE);

        editName.setText(preferences.getString("name", ""));
        editAge.setText(String.valueOf(preferences.getInt("age", 0)));
        editEmail.setText(preferences.getString("email", ""));

        buttonSave.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", editName.getText().toString());
            editor.putInt("age", Integer.parseInt(editAge.getText().toString()));
            editor.putString("email", editEmail.getText().toString());
            editor.apply();
        });

        return root;
    }
}
