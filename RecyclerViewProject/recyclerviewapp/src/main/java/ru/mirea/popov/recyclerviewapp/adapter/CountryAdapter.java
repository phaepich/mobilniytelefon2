package ru.mirea.popov.recyclerviewapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.popov.recyclerviewapp.R;
import ru.mirea.popov.recyclerviewapp.model.Country;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private List<Country> countries = new ArrayList<>();
    private Context context;

    public void setItems(List<Country> newItems) {
        this.countries = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.country_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country item = countries.get(position);
        holder.textViewCountry.setText(item.getName());
        holder.textViewPopulation.setText("Население: " + item.getPopulation());

        int flagRes = context.getResources().getIdentifier(item.getFlag(), "drawable", context.getPackageName());
        if (flagRes != 0) {
            holder.imageViewFlag.setImageResource(flagRes);
        }
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewFlag;
        TextView textViewCountry;
        TextView textViewPopulation;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFlag = itemView.findViewById(R.id.imageViewFlag);
            textViewCountry = itemView.findViewById(R.id.textViewCountry);
            textViewPopulation = itemView.findViewById(R.id.textViewPopulation);
        }
    }
}
