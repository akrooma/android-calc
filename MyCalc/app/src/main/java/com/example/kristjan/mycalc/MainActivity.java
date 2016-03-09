package com.example.kristjan.mycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CalculatorEngine calculatorEngine = new CalculatorEngine();

    private boolean operandLastPressed = false;

    private TextView textViewEntryBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewEntryBox = (TextView) findViewById(R.id.textViewEntryBox);
    }



    public void buttonClicked(View view){
        Button button = (Button) view;
        int id = button.getId();

        // If the calc displays an error message, then any button acts as C
        if (textViewEntryBox.getText().toString().equals("Error") || id == R.id.buttonC) {
            textViewEntryBox.setText("0");
            operandLastPressed = false;
            calculatorEngine.clearCalculator();
            return;
        }

        if (id == R.id.buttonAdd || id == R.id.buttonSub || id == R.id.buttonMult || id == R.id.buttonDiv) {
            textViewEntryBox.setText(calculatorEngine.addItem(textViewEntryBox.getText().toString(),
                    button.getText().toString(), operandLastPressed));
            operandLastPressed = true;

        } else {
            if (operandLastPressed) {
                if (id == R.id.buttonDot) {
                    return;
                }

                textViewEntryBox.setText(button.getText().toString());
                operandLastPressed = false;
                return;
            }

            if (textViewEntryBox.getText().toString().equals("0")) {
                if (id == R.id.button0) {
                    return;
                } else if (id != R.id.buttonDot) {
                    textViewEntryBox.setText("");
                }
            } else if (textViewEntryBox.getText().toString().contains(".") && id == R.id.buttonDot) {
                return;
            }

            textViewEntryBox.append(button.getText().toString());
        }
    }
}
