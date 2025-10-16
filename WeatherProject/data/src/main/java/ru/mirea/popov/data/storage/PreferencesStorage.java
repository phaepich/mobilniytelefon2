package ru.mirea.popov.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesStorage {
    private static final String PREFS = "weather_prefs";
    private static final String KEY_CITY = "last_city";
    private final SharedPreferences sharedPreferences;

    public PreferencesStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public void saveCity(String city) {
        sharedPreferences.edit().putString(KEY_CITY, city).apply();
    }

    public String getCity() {
        return sharedPreferences.getString(KEY_CITY, "Москва");
    }
}
