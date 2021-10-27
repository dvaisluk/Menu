package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    protected final int EASY_MODE_MAX = 10;
    protected final int MEDIUM_MODE_MAX = 100;
    protected final int HARD_MODE_MAX = 1000;

    private int random;
    private int max;

    TextView info;
    EditText input;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = findViewById(R.id.info);
        input = findViewById(R.id.input);
        button = findViewById(R.id.button);

        startNewGame(MEDIUM_MODE_MAX);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String text = input.getText().toString();
                        if (text.equals("")) {
                            setTextRes(R.string.error);
                        } else {
                            int number = Integer.parseInt(text);
                            if (number > max) {
                                setTextRes(R.string.error);
                            } else if (number < random) {
                                setTextRes(R.string.ahead);
                            } else if (number > random) {
                                setTextRes(R.string.behind);
                            } else {
                                onFinish();
                            }
                            if (number != random) {
                                clearInput();
                            }
                        }
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.newGame) {
            startNewGame(max);
        }
        else if (item.getItemId() == R.id.easyMode) {
            startNewGame(EASY_MODE_MAX);
        }
        else if (item.getItemId() == R.id.mediumMode) {
            startNewGame(MEDIUM_MODE_MAX);
        }
        else if (item.getItemId() == R.id.hardMode) {
            startNewGame(HARD_MODE_MAX);
        }
        else if (item.getItemId() == R.id.quit) {
            this.finishAffinity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setRandom() {
        random = (int) (Math.random() * max);
    }

    private void setTextRes(int strResId) {
        info.setText(getResources().getString(strResId));
    }

    private void startNewGame(int max) {
        this.max = max;
        setRandom();
        info.setText(getTryToGuessMessage());
        input.setEnabled(true);
        button.setEnabled(true);
        clearInput();
    }

    private String getTryToGuessMessage() {
        return "Попробуйте угадать число (от 0 до " + max + ")";
    }

    private void onFinish() {
        setTextRes(R.string.hit);
        input.setEnabled(false);
        button.setEnabled(false);
    }

    private void clearInput() {
        input.getText().clear();
    }
}
