package ru.mirea.popov.fragmentmanagerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CountryDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_country_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView textViewName = view.findViewById(R.id.textViewCountryName);
        TextView textViewDesc = view.findViewById(R.id.textViewCountryDesc);

        if (getArguments() != null) {
            String name = getArguments().getString("name");
            String desc = getArguments().getString("desc");

            textViewName.setText(name);
            textViewDesc.setText(desc);
        }
    }
}
