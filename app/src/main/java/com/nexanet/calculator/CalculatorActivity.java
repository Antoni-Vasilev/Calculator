package com.nexanet.calculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private StringBuilder task = new StringBuilder("0");

    private Button btn0, btn00, btnDot, btnEqual, btn1, btn2, btn3, btnPlus, btn4, btn5, btn6, btnMinus,
            btn7, btn8, btn9, btnMultiply, btnLeftBracket, btnRightBracket, btnPercent, btnDivide, btnSin, btnCos, btnTan, btnCoTan,
            btnClear;
    private ImageButton btnHistory, btnOpenMenu, btnRemoveChar;
    private TextView txtResult, txtTask;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        context = this;

        init();
        onLoad();
    }

    private void init() {
        btn0 = findViewById(R.id.btn0);
        btn00 = findViewById(R.id.btn00);
        btnDot = findViewById(R.id.btnDot);
        btnEqual = findViewById(R.id.btnEqual);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btnPlus = findViewById(R.id.btnPlus);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btnMinus = findViewById(R.id.btnMinus);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnLeftBracket = findViewById(R.id.btnLeftBracket);
        btnRightBracket = findViewById(R.id.btnRightBracket);
        btnPercent = findViewById(R.id.btnPercent);
        btnDivide = findViewById(R.id.btnDivide);
        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTg);
        btnCoTan = findViewById(R.id.btnCoTg);
        btnClear = findViewById(R.id.btnClear);
        btnRemoveChar = findViewById(R.id.btnRemoveChar);
        btnHistory = findViewById(R.id.btnHistory);
        btnOpenMenu = findViewById(R.id.btnOpenMenu);
        txtResult = findViewById(R.id.txtResult);
        txtTask = findViewById(R.id.txtTask);
    }

    private void onLoad() {
        btn0.setOnClickListener(it -> addTask("0"));
        btn00.setOnClickListener(it -> addTask("00"));
        btnDot.setOnClickListener(it -> addTask("."));
        btnEqual.setOnClickListener(it -> {
            if (!task.toString().contains("=")) addTask("=");
        });
        btn1.setOnClickListener(it -> addTask("1"));
        btn2.setOnClickListener(it -> addTask("2"));
        btn3.setOnClickListener(it -> addTask("3"));
        btnPlus.setOnClickListener(it -> addTask("+"));
        btn4.setOnClickListener(it -> addTask("4"));
        btn5.setOnClickListener(it -> addTask("5"));
        btn6.setOnClickListener(it -> addTask("6"));
        btnMinus.setOnClickListener(it -> addTask("-"));
        btn7.setOnClickListener(it -> addTask("7"));
        btn8.setOnClickListener(it -> addTask("8"));
        btn9.setOnClickListener(it -> addTask("9"));
        btnMultiply.setOnClickListener(it -> addTask("*"));
        btnLeftBracket.setOnClickListener(it -> addTask("("));
        btnRightBracket.setOnClickListener(it -> addTask(")"));
        btnPercent.setOnClickListener(it -> addTask("%"));
        btnDivide.setOnClickListener(it -> addTask("/"));
        btnSin.setOnClickListener(it -> addTask("sin("));
        btnCos.setOnClickListener(it -> addTask("cos("));
        btnTan.setOnClickListener(it -> addTask("tg("));
        btnCoTan.setOnClickListener(it -> addTask("cotg("));
        btnClear.setOnClickListener(it -> clearTask());
        btnRemoveChar.setOnClickListener(it -> removeLastChar());
        btnOpenMenu.setOnClickListener(it -> {
            Intent intent = new Intent(this, CalculationsActivity.class);
            intent.putExtra("task", txtTask.getText());
            startActivity(intent);
        });
    }

    private void addTask(String part) {
        if (task.toString().equals("0") && !part.equals(".")) task = new StringBuilder();

        task.append(part);
        try {
            txtResult.setText(new Calculate().calc(task.toString()));
        } catch (Exception ignored) {
        }
        txtTask.setText(task.toString());

        try {
            if (Double.parseDouble(task.toString()) == 0) {
                task = new StringBuilder("0");
                txtTask.setText(task.toString());
            }
        } catch (Exception ignored) {
        }
    }

    private void addBracket() {
        try {
            new Calculate().calc(task.toString());
            addTask("(");
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Missing bracket")) addTask(")");
            else addTask("(");
        }
    }

    private void removeLastChar() {
        int removeChar = 1;
        String c = String.valueOf(task.charAt(task.length() - 1));
        try {
            if (c.equals("g") && task.charAt(task.length() - 2) == 't' && task.charAt(task.length() - 3) == 'o' && task.charAt(task.length() - 4) == 'c')
                removeChar = 4;
        } catch (Exception ignored) {
        }

        try {
            if (c.equals("g") && task.charAt(task.length() - 2) == 't' && removeChar != 4)
                removeChar = 2;
        } catch (Exception ignored) {
        }

        try {
            if (c.equals("n") && task.charAt(task.length() - 2) == 'i' && task.charAt(task.length() - 3) == 's')
                removeChar = 3;
        } catch (Exception ignored) {
        }

        try {
            if (c.equals("s") && task.charAt(task.length() - 2) == 'o' && task.charAt(task.length() - 3) == 'c')
                removeChar = 3;
        } catch (Exception ignored) {
        }

        task = new StringBuilder(task.substring(0, task.length() - removeChar));
        if (task.toString().isEmpty()) task = new StringBuilder("0");
        try {
            txtResult.setText(new Calculate().calc(task.toString()));
        } catch (Exception ignored) {
        }
        txtTask.setText(task.toString());
    }

    private void clearTask() {
        task = new StringBuilder("0");
        txtResult.setText(new Calculate().calc(task.toString()));
        txtTask.setText(task.toString());
    }
}