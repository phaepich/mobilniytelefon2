package ru.mirea.popov.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {
    private EditText editBook, editQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        editBook = findViewById(R.id.editBook);
        editQuote = findViewById(R.id.editQuote);

        TextView devBook = findViewById(R.id.textViewDevBook);
        TextView devQuote = findViewById(R.id.textViewDevQuote);

        Intent intent = getIntent();
        String devBookStr = intent.getStringExtra(MainActivity.BOOK_NAME_KEY);
        String devQuoteStr = intent.getStringExtra(MainActivity.QUOTES_KEY);

        devBook.setText("Любимая книга разработчика: " + devBookStr);
        devQuote.setText("Цитата: " + devQuoteStr);

        Button button = findViewById(R.id.buttonSendBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Название вашей любимой книги: " + editBook.getText().toString()
                        + "\nЦитата: " + editQuote.getText().toString();
                Intent data = new Intent();
                data.putExtra(MainActivity.USER_MESSAGE, text);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }
}