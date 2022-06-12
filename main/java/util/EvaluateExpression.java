package com.example.caculator.util;

import android.text.TextUtils;
import android.util.Log;

import java.util.Stack;

public class EvaluateExpression {

    //计算一个字符串表达式
    public static double eval(String expression) throws Exception {

        //用来存放操作数和操作符
        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        //插入空格
        expression = insertBlanks(expression);

        String[] tokens = expression.split(" ");

        for (String token: tokens){

            //1.处理空字符串
            if (token.length() == 0)
                continue;

            //2.处理加减符号
            else if (token.charAt(0) == '+' || token.charAt(0) == '-'){
                while(!operatorStack.isEmpty() && (
                        operatorStack.peek() == '+' || operandStack.peek() == '-' ||
                                operandStack.peek() == '×' || operandStack.peek() == '÷'
                )){
                    processAnOperator(operandStack,operatorStack);
                }

                operatorStack.push(token.charAt(0));
            }

            //3.处理乘除符号
            else if (token.charAt(0) == '×' || token.charAt(0) == '÷' ||
                    token.charAt(0) == '*' || token.charAt(0) == '/'){
                while(!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '×' || operatorStack.peek() == '÷')){
                    processAnOperator(operandStack,operatorStack);
                }

                operatorStack.push(token.charAt(0));
            }

            //4.处理左括号
            else if (token.trim().charAt(0) == '('){
                operatorStack.push('(');
            }

            //5.处理右括号
            else if (token.trim().charAt(0) == ')'){
                while (operatorStack.peek() != '('){
                    processAnOperator(operandStack,operatorStack);
                }
                //移除一个左括号
                operatorStack.pop();
            }

            //6.处理百分号
            else if (token.charAt(0) == '%'){
                operandStack.push(operandStack.pop() * 0.01);
            }

            //7.处理操作数
            else{
                //如果操作数包含π，就执行以下操作
                if(token.contains("π")){
                    //π在最后一个位置
                    if(token.charAt(token.length() - 1) == 'π'){
                        if(token.length() == 1){
                            operandStack.push(Math.PI);
                        }else{
                            operandStack.push(Double.parseDouble(token.substring(0, token.length() - 1)) * Math.PI);
                        }
                    }
                    //如果π不在最后一个位置,就抛出异常给调用函数处理
                    else{
                        throw new Exception();
                    }
                }

                else{
                    if(token.indexOf('.') != token.lastIndexOf('.')){
                        throw new Exception();
                    }else{
                        operandStack.push(Double.parseDouble(token));
                    }
                }
            }
        }

        while(!operatorStack.isEmpty()){
            processAnOperator(operandStack,operatorStack);
        }

        return operandStack.pop();
    }

    //从操作数栈和操作符栈中取出相应的数据进行一次运算
    public static void processAnOperator(Stack<Double> operandStack, Stack<Character> operatorStack){

        //从操作数栈顶取出两个数,再从操作符栈顶取出一位操作符,进行一次运算
        char op = operatorStack.pop();
        double op1 = operandStack.pop();
        double op2 = operandStack.pop();

        //运算完后，再将计算结果压入栈中
        if (op == '+')
            operandStack.push(op2 + op1);
        else if (op == '-')
            operandStack.push(op2 - op1);
        else if (op == '×' || op == '*')
            operandStack.push(op2 * op1);
        else if (op == '÷' || op == '/')
            operandStack.push(op2 / op1);

    }

    //给每个操作符两端各插入一个空格，以便拆分字符串
    public static String insertBlanks(String s){

        String result ="";

        for (int i = 0; i< s.length(); i++){
            if (s.charAt(i) == '(' || s.charAt(i) == ')'
                    || s.charAt(i) == '+' || s.charAt(i) == '-'
                    || s.charAt(i) == '×' || s.charAt(i) == '÷'
                    || s.charAt(i) == '%')
                result += " " + s.charAt(i) +" ";
            else
                result += s.charAt(i);
        }

        return result;
    }
}