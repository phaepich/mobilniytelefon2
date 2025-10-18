package ru.mirea.popov.listviewapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String[][] books = {
            {"Фёдор Достоевский", "Братья Карамазовы"},
            {"Лев Толстой", "Война и мир"},
            {"Александр Пушкин", "Евгений Онегин"},
            {"Джордж Оруэлл", "1984"},
            {"Фрэнсис Фицджеральд", "Великий Гэтсби"},
            {"Чарльз Диккенс", "Большие надежды"},
            {"Гомер", "Одиссея"},
            {"Рэй Брэдбери", "451 градус по Фаренгейту"},
            {"Габриэль Гарсиа Маркес", "Сто лет одиночества"},
            {"Харуки Мураками", "Норвежский лес"},
            {"Джон Толкин", "Властелин колец"},
            {"Джоан Роулинг", "Гарри Поттер и философский камень"},
            {"Антуан де Сент-Экзюпери", "Маленький принц"},
            {"Артур Конан Дойл", "Собака Баскервилей"},
            {"Михаил Булгаков", "Мастер и Маргарита"},
            {"Джек Лондон", "Мартин Иден"},
            {"Айн Рэнд", "Атлант расправил плечи"},
            {"Стивен Кинг", "Зелёная миля"},
            {"Эрих Мария Ремарк", "Три товарища"},
            {"Жюль Верн", "Таинственный остров"},
            {"Альбер Камю", "Чума"},
            {"Николай Гоголь", "Мёртвые души"},
            {"Франц Кафка", "Процесс"},
            {"Александр Дюма", "Граф Монте-Кристо"},
            {"Герберт Уэллс", "Машина времени"},
            {"Эрнест Хемингуэй", "Старик и море"},
            {"Джордж Мартин", "Игра престолов"},
            {"Оскар Уайльд", "Портрет Дориана Грея"},
            {"Агата Кристи", "Десять негритят"},
            {"Роберт Льюис Стивенсон", "Остров сокровищ"},
            {"Нил Гейман", "Американские боги"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.book_list_view);

        ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                books) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                text1.setText(books[position][0]);
                text2.setText(books[position][1]);
                return view;
            }
        };

        listView.setAdapter(adapter);
    }
}
