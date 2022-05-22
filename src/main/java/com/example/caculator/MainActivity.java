package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_0;

    private Button button_clear;
    private Button button_delete;
    private Button button_add;
    private Button button_subtract;
    private Button button_divide;
    private Button button_multi;
    private Button button_assignment;
    private Button button_percent;

    private TextView mTextView;
    private EditText mEditText;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_1 = (Button)findViewById(R.id.button_1);
        button_2 = (Button)findViewById(R.id.button_2);
        button_3 = (Button)findViewById(R.id.button_3);
        button_4 = (Button)findViewById(R.id.button_4);
        button_5 = (Button)findViewById(R.id.button_5);
        button_6 = (Button)findViewById(R.id.button_6);
        button_7 = (Button)findViewById(R.id.button_7);
        button_8 = (Button)findViewById(R.id.button_8);
        button_9 = (Button)findViewById(R.id.button_9);
        button_0 = (Button)findViewById(R.id.button_0);

        button_add = (Button)findViewById(R.id.button_add);
        button_subtract = (Button)findViewById(R.id.button_subtract);
        button_divide = (Button)findViewById(R.id.button_divide);
        button_multi = (Button)findViewById(R.id.button_multi);
        button_assignment = (Button)findViewById(R.id.button_assignment);
        button_percent = (Button)findViewById(R.id.button_percent);

        button_delete = (Button)findViewById(R.id.button_delete);
        button_clear = (Button)findViewById(R.id.button_clear);

        mEditText = (EditText)findViewById(R.id.edittext);
        mTextView = (TextView)findViewById(R.id.textview);

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

        button_add.setOnClickListener(this);
        button_subtract.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_multi.setOnClickListener(this);
        button_assignment.setOnClickListener(this);
        button_percent.setOnClickListener(this);

        button_delete.setOnClickListener(this);
        button_clear.setOnClickListener(this);
}
    boolean judge = false;
    @Override
    public void onClick(View v){
        if (judge){
            judge = false;
            mEditText.setText(null);}
        Editable startStr = mEditText.getText();
        switch(v.getId()){
            case R.id.button_0:
                mEditText.setText(startStr+"0");
                break;
            case R.id.button_1:
                mEditText.setText(startStr+"1");
                break;
            case R.id.button_2:
                mEditText.setText(startStr+"2");
                break;
            case R.id.button_3:
                mEditText.setText(startStr+"3");
                break;
            case R.id.button_4:
                mEditText.setText(startStr+"4");
                break;
            case R.id.button_5:
                mEditText.setText(startStr+"5");
                break;
            case R.id.button_6:
                mEditText.setText(startStr+"6");
                break;
            case R.id.button_7:
                mEditText.setText(startStr+"7");
                break;
            case R.id.button_8:
                mEditText.setText(startStr+"8");
                break;
            case R.id.button_9:
                Log.e("Test","Click 9");
                mEditText.setText(startStr+"9");
                break;
            case R.id.button_add:
                mEditText.setText(startStr+"+");
                break;
            case R.id.button_subtract:
                mEditText.setText(startStr+"-");
                break;
            case R.id.button_divide:
                mEditText.setText(startStr+"÷");
                break;
            case R.id.button_multi:
                mEditText.setText(startStr+"×");
                break;
            case R.id.button_percent:
                mEditText.setText(startStr+"%");
                break;
            case R.id.button_assignment:
                try{
                    double result = evaluateExpression(startStr.toString());
                    mTextView.setText(String.format("%.2f",result));
                }catch (Exception ex){
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
                judge = !judge;
                break;
            case R.id.button_clear:
                mEditText.setText(null);
                break;
            case R.id.button_delete:
                if (startStr.length() < 1)
                    break;
                mEditText.setText(startStr.toString().substring(0,startStr.length()-1));
                break;
            default:
                break;
        }
    }
    //
    //计算一个字符串表达式
    public static double evaluateExpression(String expression){

        //用来存放操作数和操作符
        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        //插入空格
        expression = insertBlanks(expression);

        String[] tokens = expression.split(" ");

        for (String token: tokens){
            if (token.length() == 0)
                continue;
            else if (token.charAt(0) == '+' || token.charAt(0) == '-'){
                while(!operatorStack.isEmpty() && (
                        operatorStack.peek() == '+' ||
                                operandStack.peek() == '-' ||
                                operandStack.peek() == '×' || operandStack.peek() == '÷' ||
                                operandStack.peek() == '*' || operandStack.peek() == '/'
                )){
                    processAnOperator(operandStack,operatorStack);
                }

                operatorStack.push(token.charAt(0));
            }
            else if (token.charAt(0) == '×' || token.charAt(0) == '÷' ||
                    token.charAt(0) == '*' || token.charAt(0) == '/'){
                while(!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '×' || operatorStack.peek() == '*' ||
                                operatorStack.peek() == '÷' || operatorStack.peek() == '/')){
                    processAnOperator(operandStack,operatorStack);
                }
                operatorStack.push(token.charAt(0));
            }
            else if (token.trim().charAt(0) == '('){
                operatorStack.push('(');
            }
            else if (token.trim().charAt(0) == ')'){
                while (operatorStack.peek() != ')'){
                    processAnOperator(operandStack,operatorStack);
                }
                operatorStack.pop();
            }
            else{
                operandStack.push(Double.parseDouble(token));
            }
        }
        while(!operatorStack.isEmpty()){
            processAnOperator(operandStack,operatorStack);
        }

        return operandStack.pop();
    }

    //从操作数栈和操作符栈中取出相应的数据进行一次运算
    public static void processAnOperator(Stack<Double> operandStack, Stack<Character> operatorStack){

        char op = operatorStack.pop();
        double op1 = operandStack.pop();
        double op2 = operandStack.pop();

        if (op == '+')
            operandStack.push(op1 + op2);
        else if (op == '-')
            operandStack.push(op2 - op1);
        else if (op == '×' || op == '*')
            operandStack.push(op2 * op1);
        else if (op == '÷' || op == '/')
            operandStack.push(op2 / op1);

    }

    //给字符串插入空格，以便拆分字符串
    public static String insertBlanks(String s){

        String result ="";

        for (int i = 0; i< s.length(); i++){
            if (s.charAt(i) == '(' || s.charAt(i) == ')'
                    || s.charAt(i) == '+' || s.charAt(i) == '-'
                    || s.charAt(i) == '×' || s.charAt(i) == '÷'
                    || s.charAt(i) == '*' || s.charAt(i) == '/')
                result += " " + s.charAt(i) +" ";
            else
                result += s.charAt(i);
        }

        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.item_continue:
                Toast.makeText(this,"You click the continue item",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_exit:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}