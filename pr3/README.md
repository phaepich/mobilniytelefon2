# Практика 3

# Про фильмы

Создан класс `MainViewModel`, который инкапсулирует бизнес-логику и управляет состоянием UI через `LiveData`.
Для корректной инициализации модели реализован класс `ViewModelFactory`, который создаёт ViewModel с передачей зависимостей.

![image.png](readme_images/image.png)

![image.png](readme_images/image%201.png)

Активность `MainActivity` теперь не взаимодействует напрямую с use-case, а наблюдает за изменениями LiveData, автоматически обновляя интерфейс.
После поворота экрана данные сохраняются, что подтверждает корректную работу ViewModel.

![image.png](readme_images/image%202.png)

При повороте экрана в логкэте следующая картина:

![image.png](readme_images/image%203.png)

Таким образом, проект стал соответствовать принципам MVVM и рекомендациям Android Jetpack.

---

# Контрольное

Создан новый класс `WeatherViewModel`, реализующий логику архитектурного паттерна **MVVM**.
В нём размещена бизнес-логика работы с погодными данными.
Использованы `GetWeatherUseCase` и `SaveFavoriteCityUseCase` из слоя `domain` для получения и сохранения данных.
Добавлены объекты `MutableLiveData` и `MediatorLiveData` для хранения текущей погоды и статуса загрузки.
В `ViewModel` реализовано объединение данных из сети и локальной базы Room, а также асинхронное обновление с помощью `ExecutorService`.

Благодаря LiveData, `Fragment` теперь получает обновления автоматически, без ручного обновления интерфейса.

![image.png](readme_images/image%204.png)

Создан класс `WeatherViewModelFactory`, обеспечивающий корректное создание экземпляра `WeatherViewModel` с передачей всех зависимостей.
Во время инициализации создаются объекты `WeatherRepositoryImpl`, `PreferencesStorage` и `WeatherApi`, которые передаются в конструктор `WeatherViewModel`.Фабрика позволяет внедрять зависимости без нарушения принципов инверсии управления и переиспользовать ViewModel в нескольких фрагментах (например, “Погода” и “История”).

![image.png](readme_images/image%205.png)

Главный экран приложения, переработанный под **MVVM + LiveData**. 
Теперь `Fragment` не взаимодействует напрямую с репозиторием — он получает данные только через `WeatherViewModel`.
В интерфейсе отображается город, температура, описание погоды и время последнего обновления.
При нажатии на кнопку “Обновить” вызывается метод `vm.loadWeather()`, а UI автоматически обновляется при изменении LiveData.
Также добавлена кнопка “История”, открывающая экран истории запросов (`HistoryFragment`).

Фоновая загрузка сопровождается отображением `ProgressBar`, управляемым через `MutableLiveData<Boolean>`.

![image.png](readme_images/image%206.png)

### WeatherRepositoryImpl.java:

Реализация репозитория была дополнена методом `getAllWeatherLive()`, возвращающим `LiveData<List<WeatherEntity>>`.
Теперь репозиторий может предоставлять потоки данных из базы Room, которые автоматически обновляют экран истории при изменении данных.
Кроме того, репозиторий сохраняет каждое успешное обновление погоды в таблицу `weather_history`.
Таким образом, класс теперь объединяет три источника данных — API, Room и SharedPreferences.

```java
package ru.mirea.popov.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import org.json.JSONObject;
import java.time.LocalDate;
import java.util.List;

import ru.mirea.popov.data.db.AppDatabase;
import ru.mirea.popov.data.db.WeatherEntity;
import ru.mirea.popov.data.network.WeatherApi;
import ru.mirea.popov.data.storage.PreferencesStorage;
import ru.mirea.popov.domain.models.WeatherInfo;
import ru.mirea.popov.domain.repository.WeatherRepository;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final WeatherApi api;
    private final PreferencesStorage prefs;
    private final AppDatabase db;

    public WeatherRepositoryImpl(Context context, WeatherApi api, PreferencesStorage prefs) {
        this.api = api;
        this.prefs = prefs;
        this.db = Room.databaseBuilder(context, AppDatabase.class, "weather_db").build();
    }

    public LiveData<List<WeatherEntity>> getAllWeatherLive() {
        return db.weatherDao().getAllWeatherLive();
    }

    @Override
    public WeatherInfo getWeather(String city) {
        JSONObject json = api.getWeatherData(city);
        if (json == null) return new WeatherInfo(city, 0, "ошибка сети");
        try {
            JSONObject current = json.getJSONObject("current_weather");
            double temp = current.getDouble("temperature");
            WeatherInfo info = new WeatherInfo(city, temp, "погода получена");
            db.weatherDao().insert(new WeatherEntity(city, temp, info.getDescription(), LocalDate.now().toString()));
            return info;
        } catch (Exception e) {
            return new WeatherInfo(city, 0, "ошибка парсинга");
        }
    }

    @Override
    public void saveFavoriteCity(String city) {
        prefs.saveCity(city);
    }

    public List<WeatherEntity> getLastWeather() {
        return db.weatherDao().getLastWeather();
    }
}

```

### RoomWeatherDao.java:
Этот метод позволяет получать историю запросов в виде **реактивного потока LiveData**, который автоматически уведомляет ViewModel об изменениях в таблице. Также сохранён прежний метод `getLastWeather()` для выборки последних записей без наблюдения.

```java
package ru.mirea.popov.data.storage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import ru.mirea.popov.data.db.WeatherEntity;

@Dao
public interface RoomWeatherDao {
    @Insert
    void insert(WeatherEntity weather);

    @Query("SELECT * FROM weather_history ORDER BY id DESC LIMIT 20")
    List<WeatherEntity> getLastWeather();

    @Query("SELECT * FROM weather_history ORDER BY id DESC")
    androidx.lifecycle.LiveData<List<WeatherEntity>> getAllWeatherLive();
}

```

### HistoryFragament.java:

Создан новый фрагмент `HistoryFragment`, который отображает историю запросов погоды.

Фрагмент подписывается на `LiveData<List<WeatherEntity>>`, предоставляемую `WeatherViewModel`, и выводит список через `RecyclerView`.

Таким образом, экран истории автоматически обновляется при каждом новом запросе погоды, без необходимости перезапуска фрагмента.

Используется общий экземпляр ViewModel, что позволяет делить данные между основным экраном и историей.

```java
package ru.mirea.popov.weatherproject.presentation;

import android.os.Bundle;
import android.view.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ru.mirea.popov.data.db.WeatherEntity;
import ru.mirea.popov.weatherproject.R;

public class HistoryFragment extends Fragment {
    private WeatherViewModel vm;
    private WeatherHistoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new WeatherHistoryAdapter();
        recyclerView.setAdapter(adapter);

        vm = new ViewModelProvider(requireActivity(), new WeatherViewModelFactory(requireContext()))
                .get(WeatherViewModel.class);

        vm.getWeatherHistory().observe(getViewLifecycleOwner(), this::updateList);

        return view;
    }

    private void updateList(List<WeatherEntity> list) {
        adapter.setItems(list);
    }
}

```

### WeatherHistoryAdapter.java:

Создан адаптер `WeatherHistoryAdapter` для отображения списка запросов погоды.
Адаптер получает список `WeatherEntity` и отображает в каждом элементе город, температуру и дату.
При обновлении LiveData в ViewModel адаптер получает новые данные через метод `setItems()` и вызывает `notifyDataSetChanged()`, перерисовывая список.Это обеспечивает плавную и автоматическую синхронизацию экрана истории с данными Room.

```java
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

```

В результате выполнения контрольного задания приложение *WeatherProject* было полностью переработано под архитектурный шаблон **MVVM (Model–View–ViewModel)** с использованием **LiveData**. Все данные теперь обрабатываются реактивно, UI обновляется автоматически.

---

На этом выполнение практической работы 3 закончено.