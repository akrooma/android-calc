package com.example.kristjan.mycalc;

import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 * Created by Kristjan on 08/03/2016.
 */
public class CalculatorEngine {

    private DecimalFormat df;
    private LinkedList<Double> stack;
    private String operand;

    private Double x;
    private Double y;

    public CalculatorEngine() {
        stack = new LinkedList<Double>();
        x = null;
        y = null;
        operand = null;

        df = new DecimalFormat("######.######");
    }

    public String addItem(String input, String operation, boolean anotherOperation){
        if(operand == null) {
            x = Double.parseDouble(input);
            operand = operation;
        } else if (anotherOperation) {
            operand = operation;
        } else {
            y = Double.parseDouble(input);
            return calculate(operation);
        }

        return df.format(x);
        //return df.format(stack.getFirst());
    }

    public void clearStack() {

    }

    private String calculate(String newOperation) {
        if (operand.equals("+")) {
            x = x + y;
        } else if (operand.equals("-")) {
            x = x - y;
        } else if (operand.equals("*")) {
            x = x * y;
        } else if (operand.equals("/")) {
            if (y == 0) {
                return "Error";
            } else {
                x = x / y;
            }
        }

        operand = newOperation;
        return df.format(x);
    }

    public void clearCalculator() {
        x = null;
        y = null;
        operand = null;
    }
}
