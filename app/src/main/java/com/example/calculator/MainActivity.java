package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText current;
    private TextView res;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonEqual;
    private Button buttonClear;
    private Button buttonPlus;
    private Button buttonMin;
    private Button buttonDiv;
    private Button buttonMulti;
    private Button buttonPer;
    private Button buttonPow;
    boolean operationClick = false;
    int result;
    int whatOp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forButtons();
    }

    private void forButtons(){

        res = findViewById(R.id.res);
        current = findViewById(R.id.currentNum);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button0 = findViewById(R.id.button0);
        buttonEqual = findViewById(R.id.buttonEqual);

        buttonClear = findViewById(R.id.buttonBack);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMin = findViewById(R.id.buttonMinus);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonMulti = findViewById(R.id.buttonMul);
        buttonPer = findViewById(R.id.buttonPer);
        buttonPow = findViewById(R.id.buttonPow);


        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button button = v.findViewById(v.getId());
                switch (v.getId()){
                    case R.id.button1:
                    case R.id.button2:
                    case R.id.button3:
                    case R.id.button4:
                    case R.id.button5:
                    case R.id.button6:
                    case R.id.button7:
                    case R.id.button8:
                    case R.id.button9:
                    case R.id.button0:
                        current.setText(String.format("%s%s", current.getText().toString(), button.getText()));
                        current.setSelection(current.getText().length());
                        break;
                    case R.id.buttonBack:
                        if(current.getText().length()!=0) {
                            current.setText(current.getText().delete(current.getText().length() - 1, current.getText().length()));
                            current.setSelection(current.getText().length());
                        }
                        break;
                    case R.id.buttonPlus:
                        if((current.getText().length()!=0) && !operationClick) {
                            forOper(button);
                            whatOp = 1;
                        }
                        break;
                    case R.id.buttonMinus:
                        if((current.getText().length()!=0) && !operationClick) {
                            forOper(button);
                            whatOp = 2;
                        }
                        break;
                    case R.id.buttonDiv:
                        if((current.getText().length()!=0) && !operationClick) {
                            forOper(button);
                            whatOp = 3;
                        }
                        break;
                    case R.id.buttonMul:
                        if((current.getText().length()!=0) && !operationClick) {
                            forOper(button);
                            whatOp = 4;
                        }
                        break;
                    case R.id.buttonPer:
                        if((current.getText().length()!=0) && !operationClick) {
                            result = Integer.parseInt(current.getText().toString());
                            res.setText(result*0.01+"");
                            current.setText("");
                            current.setSelection(current.getText().length());
                        }
                        break;
                    case R.id.buttonPow:
                        if((current.getText().length()!=0) && !operationClick) {
                            forOper(button);
                            whatOp = 5;
                        }
                        break;
                    case R.id.buttonEqual:
                        if((current.getText().length()!=0) && operationClick) {
                            int secres;
                            double secresd = 0.0;
                            String was = res.getText().toString();
                            switch (whatOp){
                                case 1:
                                    secres = Integer.parseInt(current.getText().toString());
                                    result = result + secres;
                                    res.setText(was + secres + " " + button.getText().toString() + " " + result);
                                    break;
                                case 2:
                                    secres = Integer.parseInt(current.getText().toString());
                                    result = result - secres;
                                    res.setText(was + secres + " " + button.getText().toString() + " " + result);
                                    break;
                                case 3:
                                    secresd = Integer.parseInt(current.getText().toString());
                                    res.setText(was + secresd + " " + button.getText().toString() + " " + result/secresd);
                                    break;
                                case 4:
                                    secres = Integer.parseInt(current.getText().toString());
                                    res.setText(was + secres + " " + button.getText().toString() + " " + result*secres);
                                    break;
                                case 5:
                                    secres = Integer.parseInt(current.getText().toString());
                                    res.setText(was + secres + " " + button.getText().toString() + " " + Math.pow(result,secres));
                                    break;
                            }
                            current.setText("");
                            current.setSelection(current.getText().length());
                            operationClick = false;
                        }
                        break;
                }
            }
        };

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        button8.setOnClickListener(onClickListener);
        button9.setOnClickListener(onClickListener);
        button0.setOnClickListener(onClickListener);
        buttonEqual.setOnClickListener(onClickListener);
        buttonClear.setOnClickListener(onClickListener);
        buttonPlus.setOnClickListener(onClickListener);
        buttonMin.setOnClickListener(onClickListener);
        buttonDiv.setOnClickListener(onClickListener);
        buttonMulti.setOnClickListener(onClickListener);
        buttonPer.setOnClickListener(onClickListener);
        buttonPow.setOnClickListener(onClickListener);

        buttonClear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                res.setText("");
                current.setText("");
                operationClick = false;
                return false;
            }
        });
    }

    private void forOper(Button button){
        result = Integer.parseInt(current.getText().toString());
        res.setText(String.format("%d %s ", result, button.getText().toString()));
        current.setText("");
        current.setSelection(current.getText().length());
        operationClick = true;
    }
}
