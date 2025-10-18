package ru.mirea.popov.recylerviewapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Event> events = new ArrayList<>();
        events.add(new Event("Основание Рима", "753 год до н.э. — легендарное основание города Ромула и Рема", R.drawable.rome));
        events.add(new Event("Открытие Америки", "1492 год — Христофор Колумб достиг берегов Нового Света", R.drawable.columbus));
        events.add(new Event("Французская революция", "1789 год — начало революции во Франции", R.drawable.france_revolution));
        events.add(new Event("Падение Берлинской стены", "1989 год — символ конца холодной войны", R.drawable.berlin_wall));
        events.add(new Event("Первый полёт человека в космос", "1961 год — Юрий Гагарин облетел Землю", R.drawable.gagarin));

        EventAdapter adapter = new EventAdapter(events);
        recyclerView.setAdapter(adapter);
    }
}
