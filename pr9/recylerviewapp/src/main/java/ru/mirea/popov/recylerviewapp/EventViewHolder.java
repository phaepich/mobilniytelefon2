package ru.mirea.popov.recylerviewapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView titleView;
    TextView descriptionView;

    public EventViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageViewEvent);
        titleView = itemView.findViewById(R.id.textViewTitle);
        descriptionView = itemView.findViewById(R.id.textViewDescription);
    }
}
