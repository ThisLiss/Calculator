package com.example.calculator;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class calculator_fragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText current;
    private TextView res;
    boolean operationClick = false;
    long result;
    int whatOp = 0;
    ViewGroup root;
    Long sTime;
    TextView tvTime;

    public calculator_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments()!= null) {
            root = (ViewGroup) inflater.inflate(R.layout.fragment_calculator_fragment, null);
            forButtons();

            Bundle bundle = this.getArguments();
            sTime = bundle.getLong("time");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss");
            tvTime.setText(dateFormat.format(new Date(sTime)));
        }
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }

    private void forButtons(){

        tvTime = root.findViewById(R.id.tvTime);

        res = root.findViewById(R.id.tvRes);
        current = root.findViewById(R.id.etCurrentNum);

        Button button1 = root.findViewById(R.id.btn1);
        Button button2 = root.findViewById(R.id.btn2);
        Button button3 = root.findViewById(R.id.btn3);
        Button button4 = root.findViewById(R.id.btn4);
        Button button5 = root.findViewById(R.id.btn5);
        Button button6 = root.findViewById(R.id.btn6);
        Button button7 = root.findViewById(R.id.btn7);
        Button button8 = root.findViewById(R.id.btn8);
        Button button9 = root.findViewById(R.id.btn9);
        Button button0 = root.findViewById(R.id.btn0);
        Button buttonEqual = root.findViewById(R.id.btnEqual);

        Button buttonClear = root.findViewById(R.id.btnBack);
        Button buttonPlus = root.findViewById(R.id.btnPlus);
        Button buttonMin = root.findViewById(R.id.btnMinus);
        Button buttonDiv = root.findViewById(R.id.btnDiv);
        Button buttonMulti = root.findViewById(R.id.btnMul);
        Button buttonPer = root.findViewById(R.id.btnPer);
        Button buttonPow = root.findViewById(R.id.btnPow);


        Button buttonClose = root.findViewById(R.id.btnClose);

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button button = v.findViewById(v.getId());
                switch (v.getId()){
                    case R.id.btn1:
                    case R.id.btn2:
                    case R.id.btn3:
                    case R.id.btn4:
                    case R.id.btn5:
                    case R.id.btn6:
                    case R.id.btn7:
                    case R.id.btn8:
                    case R.id.btn9:
                    case R.id.btn0:
                        current.setText(String.format("%s%s", current.getText().toString(), button.getText()));
                        current.setSelection(current.getText().length());
                        break;
                    case R.id.btnBack:
                        if(current.getText().length()!=0) {
                            current.setText(current.getText().delete(current.getText().length() - 1, current.getText().length()));
                            current.setSelection(current.getText().length());
                        }
                        break;
                    case R.id.btnPlus:
                        if((current.getText().length()!=0) && !operationClick) {
                            forOper(button);
                            whatOp = 1;
                        }
                        break;
                    case R.id.btnMinus:
                        if((current.getText().length()!=0) && !operationClick) {
                            forOper(button);
                            whatOp = 2;
                        }
                        break;
                    case R.id.btnDiv:
                        if((current.getText().length()!=0) && !operationClick) {
                            forOper(button);
                            whatOp = 3;
                        }
                        break;
                    case R.id.btnMul:
                        if((current.getText().length()!=0) && !operationClick) {
                            forOper(button);
                            whatOp = 4;
                        }
                        break;
                    case R.id.btnPer:
                        if((current.getText().length()!=0) && !operationClick) {
                            result = Long.parseLong(current.getText().toString());
                            res.setText(result*0.01+"");
                            current.setText("");
                            current.setSelection(current.getText().length());
                        }
                        break;
                    case R.id.btnPow:
                        if((current.getText().length()!=0) && !operationClick) {
                            forOper(button);
                            whatOp = 5;
                        }
                        break;
                    case R.id.btnEqual:
                        if((current.getText().length()!=0) && operationClick) {
                            long secres;
                            double secresd = 0.0;
                            String was = res.getText().toString();
                            switch (whatOp){
                                case 1:
                                    secres = Long.parseLong(current.getText().toString());
                                    result = result + secres;
                                    res.setText(was + secres + " " + button.getText().toString() + " " + result);
                                    break;
                                case 2:
                                    secres = Long.parseLong(current.getText().toString());
                                    result = result - secres;
                                    res.setText(was + secres + " " + button.getText().toString() + " " + result);
                                    break;
                                case 3:
                                    secresd = Long.parseLong(current.getText().toString());
                                    res.setText(was + secresd + " " + button.getText().toString() + " " + result/secresd);
                                    break;
                                case 4:
                                    secres = Long.parseLong(current.getText().toString());
                                    res.setText(was + secres + " " + button.getText().toString() + " " + result*secres);
                                    break;
                                case 5:
                                    secres = Long.parseLong(current.getText().toString());
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

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction();
            }
        });
    }

    private void forOper(Button button){
        try {
            result = Long.parseLong(current.getText().toString());
            res.setText(String.format("%d %s ", result, button.getText().toString()));
        }
        catch (NumberFormatException e){
            Toast toast = Toast.makeText(getContext(),
                    "Ваше число слишком большое, калькулятор не справляется. Пожалуйста, напишите число поменьше С:", Toast.LENGTH_LONG);
            toast.show();
        }
        current.setText("");
        current.setSelection(current.getText().length());
        operationClick = true;
    }

}
