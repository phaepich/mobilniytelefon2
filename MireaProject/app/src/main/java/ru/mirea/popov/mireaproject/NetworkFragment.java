package ru.mirea.popov.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.gson.GsonBuilder;

import java.util.Objects;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mirea.popov.mireaproject.IpInfo;
import ru.mirea.popov.mireaproject.IpInfoApi;

public class NetworkFragment extends Fragment {

    private TextView ipText, cityText, regionText, countryText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_network, container, false);
        ipText = view.findViewById(R.id.ipText);
        cityText = view.findViewById(R.id.cityText);
        regionText = view.findViewById(R.id.regionText);
        countryText = view.findViewById(R.id.countryText);
        loadIpInfo();
        return view;
    }

    private void loadIpInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ipinfo.io/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();

        IpInfoApi api = retrofit.create(IpInfoApi.class);

        api.getIpInfo().enqueue(new Callback<IpInfo>() {
            @Override
            public void onResponse(Call<IpInfo> call, Response<IpInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    IpInfo info = response.body();
                    ipText.setText("IP: " + info.ip);
                    cityText.setText("City: " + info.city);
                    regionText.setText("Region: " + info.region);
                    countryText.setText("Country: " + info.country);
                }
            }

            @Override
            public void onFailure(Call<IpInfo> call, Throwable t) {
                ipText.setText("Ошибка загрузки: " + t.getMessage());
            }
        });
    }
}
