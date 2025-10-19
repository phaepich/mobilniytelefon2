package ru.mirea.popov.fragmentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TodoListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private List<Todo> todoList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            int number = args.getInt("student_number");
            Toast.makeText(getContext(), "Мой номер по списку: " + number, Toast.LENGTH_SHORT).show();

        }
        recyclerView = view.findViewById(R.id.recyclerViewTodos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        todoList = new ArrayList<>();
        todoList.add(new Todo("выучить Retrofit", true));
        todoList.add(new Todo("разобраться с Fragment", false));
        todoList.add(new Todo("возненавидеть разработку всю возможную", false));
        todoList.add(new Todo("че-то еще", true));
        todoList.add(new Todo("ну и сдать практику", false));

        adapter = new TodoAdapter(todoList);
        recyclerView.setAdapter(adapter);
    }

}
