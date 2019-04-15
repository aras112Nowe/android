package com.example.aras1.calculator;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.aras1.calculator.utils.AutoWiredXML;
import com.example.aras1.calculator.utils.DefaultKeyboardOnClickListener;
import com.example.aras1.calculator.utils.InjectOnClickListener;
import com.example.aras1.calculator.utils.KittyKit;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity
    {
    @AutoWiredXML
    private TextView result;
    private static String string;
    @InjectOnClickListener
    @AutoWiredXML
    private Button add, subtract, multiply, divide, pow2, powx, ln, sqrty, sin, cos, tg,log,per;
    @AutoWiredXML
    private Button dot, btnAC, btnC, btnEquals;
    @AutoWiredXML
    private ToggleButton btnSign;
    @AutoWiredXML
    @DefaultKeyboardOnClickListener
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    private static Status operationStatus;
    private ArrayList<Button> listOfKeyboardButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        init();

        btnEquals.setOnClickListener(v -> equalsFunction());
        btnAC.setOnClickListener(v ->
        {
        enableButtons(true);
        clearResult();
        });
        btnC.setOnClickListener(v -> cFunction());
        dot.setOnClickListener(v -> dotFunction());
        }

    private void init()
        {
        setContentView(R.layout.activity_main);
        KittyKit.initFieldByReflection(this);
        KittyKit.injectListener(this, getOnClickListener());
        listOfKeyboardButtons = KittyKit.setDefaultListener(this, "result");
        listOfKeyboardButtons.add(dot);
        checkCalculatorType();
        if (operationStatus == null)
            {
            operationStatus = new Status();
            }
        if (string != null)
            {
            result.setText(string);
            }
        if (operationStatus.getLastOperation() == OperationType.RESULT)
            {
            enableButtons(false);
            }
        }

    private void checkCalculatorType()
        {
        if (getIntent().getBooleanExtra("standard", true))
            {
            pow2.setVisibility(View.INVISIBLE);
            powx.setVisibility(View.INVISIBLE);
            ln.setVisibility(View.INVISIBLE);
            sqrty.setVisibility(View.INVISIBLE);
            sin.setVisibility(View.INVISIBLE);
            cos.setVisibility(View.INVISIBLE);
            tg.setVisibility(View.INVISIBLE);
            log.setVisibility(View.INVISIBLE);
            }
        }

    private void dotFunction()
        {
        if (!operationStatus.isDotInResult())
            {
            result.append(".");
            }
        operationStatus.setDotInResult(true);
        }

    private void cFunction()
        {
        clearResult();
        operationStatus.setLastOperation(OperationType.NULL);
        operationStatus.setResultNumber(0D);
        enableButtons(true);
        }

    private void equalsFunction()
        {
        if (last() != OperationType.RESULT && !tapedNumberIsEmpty() && last() != OperationType.NULL)
            {
            calculateResult();
            showResult();
            }
        }

    private void calculateResult()
        {
        operationStatus.setResultNumber(last().getResultFormOperation(operationStatus.getResultNumber(), getTypedNumber()));
        }

    @NonNull
    private View.OnClickListener getOnClickListener()
        {
        return (view) ->
        {
        OperationType currentOperation = getOperationType(view);

        if (currentOperation.isOneArg() && !tapedNumberIsEmpty())
            {
            saveResult();
            result.setText(new Integer(10).toString());
            operationStatus.setLastOperation(currentOperation);
            btnEquals.callOnClick();
            }

        else if (last() == OperationType.RESULT || last() == OperationType.NULL)
            {
            saveResult();
            operationStatus.setLastOperation(currentOperation);
            }
        else if (last() == currentOperation && !tapedNumberIsEmpty())
            {
            btnEquals.callOnClick();
            view.callOnClick();
            }
        else
            {
            operationStatus.setLastOperation(currentOperation);
            }
        };
        }

    @NonNull
    private OperationType getOperationType(View view)
        {
        String id = view.getResources().getResourceName(view.getId());
        id = id.split("/")[1].toUpperCase();
        return OperationType.valueOf(id);
        }

    private OperationType last()
        {
        return operationStatus.getLastOperation();
        }

    private void clearResult()
        {
        btnSign.setChecked(false);
        operationStatus.setDotInResult(false);
        result.setText("");
        }

    private double getTypedNumber()
        {
        int i = btnSign.isChecked() ? -1 : 1;
        //btnSign.setChecked(false);
        return !tapedNumberIsEmpty() ? Double.parseDouble(result.getText().toString()) * i : 0D;
        }

    private boolean tapedNumberIsEmpty()
        {
        return result.getText().length() == 0 || (result.length() == 1 && result.getText().toString().equals("."));
        }

    private void saveResult()
        {
        operationStatus.setResultNumber(getTypedNumber());
        clearResult();
        enableButtons(true);
        }

    private void showResult()
        {
        operationStatus.setLastOperation(OperationType.RESULT);
        clearResult();
        result.setText(operationStatus.getResultNumber().toString());
        enableButtons(false);
        }

    private void enableButtons(Boolean condition)
        {
        for (Button button : listOfKeyboardButtons)
            {
            button.setEnabled(condition);
            }
        }

        @Override
        protected void onStop()
        {
            super.onStop();
            if (!tapedNumberIsEmpty())
            {
                string = String.valueOf(abs(getTypedNumber()));
                operationStatus.setDotInResult(true);
            }
            else
            {
                string = null;
            }
        }
    }