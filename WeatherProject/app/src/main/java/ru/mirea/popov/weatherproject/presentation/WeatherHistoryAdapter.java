package ru.mirea.popov.weatherproject.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.popov.data.db.WeatherEntity;
import ru.mirea.popov.weatherproject.R;

public class WeatherHistoryAdapter extends RecyclerView.Adapter<WeatherHistoryAdapter.ViewHolder> {
    private List<WeatherEntity> items = new ArrayList<>();

    public void setItems(List<WeatherEntity> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherEntity e = items.get(position);
        holder.city.setText(e.city);
        holder.temp.setText(String.format("%.1f°C", e.temperature));
        holder.date.setText(e.date);
        if (e.icon != null && !e.icon.isEmpty()) {
            String url = "https://openweathermap.org/img/wn/" + e.icon + "@2x.png";
            Picasso.get().load(url).into(holder.icon);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView city, temp, date;
        ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iconHistory);
            city = itemView.findViewById(R.id.textCityHistory);
            temp = itemView.findViewById(R.id.textTempHistory);
            date = itemView.findViewById(R.id.textDateHistory);
        }
    }
}
