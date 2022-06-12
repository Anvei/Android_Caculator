package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caculator.util.EvaluateExpression;


//主活动
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_pi;

    private Button button_clear;
    private Button button_delete;
    private Button button_add;
    private Button button_subtract;
    private Button button_divide;
    private Button button_multi;
    private Button button_assignment;
    private Button button_percent;
    private Button button_dot;

    private TextView text_result;
    private TextView text_expression;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_0 = (Button)findViewById(R.id.button_0);
        button_1 = (Button)findViewById(R.id.button_1);
        button_2 = (Button)findViewById(R.id.button_2);
        button_3 = (Button)findViewById(R.id.button_3);
        button_4 = (Button)findViewById(R.id.button_4);
        button_5 = (Button)findViewById(R.id.button_5);
        button_6 = (Button)findViewById(R.id.button_6);
        button_7 = (Button)findViewById(R.id.button_7);
        button_8 = (Button)findViewById(R.id.button_8);
        button_9 = (Button)findViewById(R.id.button_9);
        button_pi = (Button) findViewById(R.id.button_pi);

        button_add = (Button)findViewById(R.id.button_add);
        button_subtract = (Button)findViewById(R.id.button_subtract);
        button_divide = (Button)findViewById(R.id.button_divide);
        button_multi = (Button)findViewById(R.id.button_multi);
        button_assignment = (Button)findViewById(R.id.button_assignment);
        button_percent = (Button)findViewById(R.id.button_percent);
        button_dot = (Button) findViewById(R.id.button_dot);

        button_delete = (Button)findViewById(R.id.button_delete);
        button_clear = (Button)findViewById(R.id.button_clear);

        text_expression = (TextView) findViewById(R.id.expression);
        text_result = (TextView)findViewById(R.id.result);

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_pi.setOnClickListener(this);

        button_add.setOnClickListener(this);
        button_subtract.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_multi.setOnClickListener(this);
        button_assignment.setOnClickListener(this);
        button_percent.setOnClickListener(this);
        button_dot.setOnClickListener(this);


        button_delete.setOnClickListener(this);
        button_clear.setOnClickListener(this);
}
    boolean judge = false;
    @Override
    public void onClick(View v) {
        if (judge) {
            judge = false;
            text_expression.setText(null);
        }
        CharSequence startStr = text_expression.getText();
        switch (v.getId()) {
            case R.id.button_0:
                text_expression.setText(startStr + "0");
                break;
            case R.id.button_1:
                text_expression.setText(startStr + "1");
                break;
            case R.id.button_2:
                text_expression.setText(startStr + "2");
                break;
            case R.id.button_3:
                text_expression.setText(startStr + "3");
                break;
            case R.id.button_4:
                text_expression.setText(startStr + "4");
                break;
            case R.id.button_5:
                text_expression.setText(startStr + "5");
                break;
            case R.id.button_6:
                text_expression.setText(startStr + "6");
                break;
            case R.id.button_7:
                text_expression.setText(startStr + "7");
                break;
            case R.id.button_8:
                text_expression.setText(startStr + "8");
                break;
            case R.id.button_9:
                mEditText.setText(startStr + "9");
                break;
            case R.id.button_pi:
                text_expression.setText(startStr + "π");
                break;
            case R.id.button_add:
                text_expression.setText(startStr + "+");
                break;
            case R.id.button_subtract:
                text_expression.setText(startStr + "-");
                break;
            case R.id.button_divide:
                text_expression.setText(startStr + "÷");
                break;
            case R.id.button_multi:
                text_expression.setText(startStr + "×");
                break;
            case R.id.button_percent:
                text_expression.setText(startStr + "%");
                break;
            case R.id.button_dot:
                text_expression.setText(startStr + ".");
                break;
            case R.id.button_assignment:
                try {
                    double result = EvaluateExpression.eval(startStr.toString());
                    text_result.setText(String.format("%,.5f", result));
                } catch (Exception ex) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
                judge = !judge;
                break;
            case R.id.button_clear:
                text_result.setText(null);
                break;
            case R.id.button_delete:
                if (startStr.length() < 1)
                    break;
                text_expression.setText(startStr.toString().substring(0, startStr.length() - 1));
                break;
            default:
                break;
        }
    }
}