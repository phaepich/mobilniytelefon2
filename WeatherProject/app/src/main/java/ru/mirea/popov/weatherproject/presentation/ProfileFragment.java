package ru.mirea.popov.weatherproject.presentation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.mirea.popov.data.storage.PreferencesStorage;
import ru.mirea.popov.weatherproject.R;

public class ProfileFragment extends Fragment {

    private TextView textEmail, textAuthHeader;
    private EditText editDisplayName;
    private Button buttonSave, buttonLogout;
    private ImageView imageAvatar;

    private PreferencesStorage prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        textEmail = v.findViewById(R.id.textEmail);
        textAuthHeader = v.findViewById(R.id.textAuthHeader);
        editDisplayName = v.findViewById(R.id.editDisplayName);
        buttonSave = v.findViewById(R.id.buttonSaveProfile);
        buttonLogout = v.findViewById(R.id.buttonLogout);
        imageAvatar = v.findViewById(R.id.imageAvatar);

        prefs = new PreferencesStorage(requireContext());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user != null ? user.getEmail() : "гость";
        textEmail.setText("email: " + email);
        textAuthHeader.setText(user != null ? "профиль" : "гостевой режим");

        String savedName = prefs.getString("display_name", "");
        if (savedName != null) editDisplayName.setText(savedName);

        buttonSave.setOnClickListener(view -> {
            String name = editDisplayName.getText().toString();
            prefs.putString("display_name", name);
        });

        buttonLogout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            textEmail.setText("email: гость");
            textAuthHeader.setText("гостевой режим");
        });

        return v;
    }
}
