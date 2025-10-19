package ru.mirea.popov.fragmentresultapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SenderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sender, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText editTextMessage = view.findViewById(R.id.editTextMessage);
        Button buttonSend = view.findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString();

            Bundle result = new Bundle();
            result.putString("message_key", message);
            getParentFragmentManager().setFragmentResult("request_key", result);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, ReceiverFragment.class, null)
                    .addToBackStack(null)
                    .commit();
        });
    }
}
