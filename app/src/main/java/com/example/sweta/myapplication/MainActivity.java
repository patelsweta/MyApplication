package com.example.sweta.myapplication;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	    private TextView tvcalcedis;
	    private Boolean userIsInTheMiddleOfTypingANumber = false;
	    private Calculator calculator;
	    private static final String DIGITS = "0123456789.";
	 
	    DecimalFormat df = new DecimalFormat("@###########");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        calculator = new Calculator();
        tvcalcedis = (TextView) findViewById(R.id.textView1);
 
        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);
 
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
 
        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonToggleSign).setOnClickListener(this);
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);
        findViewById(R.id.buttonClearMemory).setOnClickListener(this);
        findViewById(R.id.buttonAddToMemory).setOnClickListener(this);
        findViewById(R.id.buttonSubtractFromMemory).setOnClickListener(this);
        findViewById(R.id.buttonRecallMemory).setOnClickListener(this);
 
        // The following buttons only exist in layout-land (Landscape mode) and require extra attention.
        // The messier option is to place the buttons in the regular layout too and set android:visibility="invisible".
        if (findViewById(R.id.buttonSquareRoot) != null) {
            findViewById(R.id.buttonSquareRoot).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonSquared) != null) {
            findViewById(R.id.buttonSquared).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonInvert) != null) {
            findViewById(R.id.buttonInvert).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonSine) != null) {
            findViewById(R.id.buttonSine).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonCosine) != null) {
            findViewById(R.id.buttonCosine).setOnClickListener(this);
        }
        if (findViewById(R.id.buttonTangent) != null) {
            findViewById(R.id.buttonTangent).setOnClickListener(this);
        }
    }
 
    @Override
    public void onClick(View v) {
 
        String buttonPressed = ((Button) v).getText().toString();
 
        if (DIGITS.contains(buttonPressed)) {
 
            // digit was pressed
            if (userIsInTheMiddleOfTypingANumber) {
 
                if (buttonPressed.equals(".") && tvcalcedis.getText().toString().contains(".")) {
                    // ERROR PREVENTION
                    // Eliminate entering multiple decimals
                } else {
                	tvcalcedis.append(buttonPressed);
                }
 
            } else {
 
                if (buttonPressed.equals(".")) {
                    // ERROR PREVENTION
                    // This will avoid error if only the decimal is hit before an operator, by placing a leading zero
                    // before the decimal
                	tvcalcedis.setText(0 + buttonPressed);
                } else {
                	tvcalcedis.setText(buttonPressed);
                }
 
                userIsInTheMiddleOfTypingANumber = true;
            }
 
        } else {
            // operation was pressed
            if (userIsInTheMiddleOfTypingANumber) {
 
            	calculator.setOperand(Double.parseDouble(tvcalcedis.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }
 
            calculator.performOperation(buttonPressed);
            tvcalcedis.setText(df.format(calculator.getResult()));
 
        }
 
    }
 
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save variables on screen orientation change
        outState.putDouble("OPERAND", calculator.getResult());
        outState.putDouble("MEMORY", calculator.getMemory());
    }
 
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore variables on screen orientation change
        calculator.setOperand(savedInstanceState.getDouble("OPERAND"));
        calculator.setMemory(savedInstanceState.getDouble("MEMORY"));
        tvcalcedis.setText(df.format(calculator.getResult()));
    }
	}		

	

