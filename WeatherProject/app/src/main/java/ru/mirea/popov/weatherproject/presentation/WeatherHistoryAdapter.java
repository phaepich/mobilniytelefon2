package ru.mirea.popov.weatherproject.presentation;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
        WeatherEntity entity = items.get(position);
        holder.city.setText(entity.city);
        holder.temp.setText(String.format("%.1f°C", entity.temperature));
        holder.date.setText(entity.date);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView city, temp, date;
        ViewHolder(View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.textCityHistory);
            temp = itemView.findViewById(R.id.textTempHistory);
            date = itemView.findViewById(R.id.textDateHistory);
        }
    }
}
