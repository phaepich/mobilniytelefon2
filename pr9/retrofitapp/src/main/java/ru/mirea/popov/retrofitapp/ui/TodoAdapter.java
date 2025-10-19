package ru.mirea.popov.retrofitapp.ui;

import android.content.Context;
import android.util.Log;
import android.widget.CompoundButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mirea.popov.retrofitapp.R;
import ru.mirea.popov.retrofitapp.data.ApiService;
import ru.mirea.popov.retrofitapp.data.Todo;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {
    private List<Todo> todos;
    private Context context;
    private ApiService apiService;

    public TodoAdapter(Context context, List<Todo> todos) {
        this.context = context;
        this.todos = todos;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.textViewTitle.setText(todo.getTitle());
        holder.checkBoxCompleted.setOnCheckedChangeListener(null);
        holder.checkBoxCompleted.setChecked(todo.getCompleted());

        String imageUrl = "https://picsum.photos/200?random=" + todo.getId();
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .resize(100, 100)
                .centerCrop()
                .into(holder.imageViewAvatar);

        holder.checkBoxCompleted.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            todo.setCompleted(isChecked);
            Call<Todo> call = apiService.updateTodo(todo.getId(), todo);
            call.enqueue(new Callback<Todo>() {
                @Override
                public void onResponse(Call<Todo> call, Response<Todo> response) {
                    if (response.isSuccessful()) {
                        Log.d("RetrofitApp", "Todo updated: " + todo.getTitle());
                    } else {
                        Log.e("RetrofitApp", "Update failed: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Todo> call, Throwable t) {
                    Log.e("RetrofitApp", "Error: " + t.getMessage());
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }
}
