package ru.mirea.popov.retrofitapp.ui;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import ru.mirea.popov.retrofitapp.R;

public class TodoViewHolder extends RecyclerView.ViewHolder {
    TextView textViewTitle;
    CheckBox checkBoxCompleted;
    ImageView imageViewAvatar;

    public TodoViewHolder(View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        checkBoxCompleted = itemView.findViewById(R.id.checkBoxCompleted);
        imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
    }
}
