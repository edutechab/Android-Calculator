package com.example.kenapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import static android.view.View.*;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView display1, display2;
    private String currentNum = "", operatorToExecute = "", previousOperator = "";
    private double num1 = Double.POSITIVE_INFINITY, num2 = Double.POSITIVE_INFINITY;    //initialize nums as infinite to do checks
    private boolean consecutiveEqual = false;

    public static ArrayList<String> calculationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideNavBar();           //calls function that hides the upper and lower navigations bar for a cleanear look

        final Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAdd, btnSubtract,
                btnMultiply, btnDivide, btnEquals, btnSquareRoot, btnSquare, btnDivideByX,
                btnPercent, btnPlusMinus, btnDot, btnClearEntry, btnClearAll, btnBackspace, btnHistory;

        display1 = findViewById(R.id.displayCalculationAnswer);
        display2 = findViewById(R.id.displayCalculation);
        btn0 = findViewById(R.id.num0);
        btn1 = findViewById(R.id.num1);
        btn2 = findViewById(R.id.num2);
        btn3 = findViewById(R.id.num3);
        btn4 = findViewById(R.id.num4);
        btn5 = findViewById(R.id.num5);
        btn6 = findViewById(R.id.num6);
        btn7 = findViewById(R.id.num7);
        btn8 = findViewById(R.id.num8);
        btn9 = findViewById(R.id.num9);
        btnEquals = findViewById(R.id.equals);
        btnAdd = findViewById(R.id.addition);
        btnSubtract = findViewById(R.id.subtraction);
        btnMultiply = findViewById(R.id.multiplication);
        btnDivide = findViewById(R.id.division);
        btnSquareRoot = findViewById(R.id.squareRoot);
        btnSquare = findViewById(R.id.square);
        btnDivideByX = findViewById(R.id.divideByX);
        btnPercent = findViewById(R.id.percent);
        btnPlusMinus = findViewById(R.id.plusMinus);
        btnDot = findViewById(R.id.dot);
        btnClearEntry = findViewById(R.id.clearEntry);
        btnClearAll = findViewById(R.id.clearAll);
        btnBackspace = findViewById(R.id.backspace);
        btnHistory = findViewById(R.id.history);

        //-----------------------------------------------------------------------------------------
        //--------------    ON CLICK LISTENERS FOR "0-9", "+/-" & "."   && history    -------------
        //-----------------------------------------------------------------------------------------

        // "0"
        btn0.setOnClickListener(v -> {
            if(Objects.equals(operatorToExecute, "=")){             //if num 0-9 is selected after selecting
                numAfterEqualPressed("0");          //"=". reset everything
                return;
            }

            if(currentNum.length() > 0) {           //if num already exists, append 0
                currentNum += "0";
                display1.setText(currentNum);
            }
            else                                    //else, set as 0 (to prevent something like "0000..." from displaying)
                display1.setText("0");              //(to prevent something like "0000..." from displaying)
        });

        // "1"
        btn1.setOnClickListener(v -> {
            if ("=".equals(operatorToExecute)) {
                numAfterEqualPressed("1");
                return;
            }

            currentNum += "1";                      //append to (string) currentNum
            display1.setText(currentNum);           //set main display to currentNum
        });

        // "2"
        btn2.setOnClickListener(v -> {
            if(Objects.equals(operatorToExecute, "=")){
                numAfterEqualPressed("2");
                return;
            }

            currentNum += "2";
            display1.setText(currentNum);

        });

        // "3"
        btn3.setOnClickListener(v -> {
            if(Objects.equals(operatorToExecute, "=")){
                numAfterEqualPressed("3");
                return;
            }

            currentNum += "3";
            display1.setText(currentNum);

        });

        // "4"
        btn4.setOnClickListener(v -> {
            if(Objects.equals(operatorToExecute, "=")){
                numAfterEqualPressed("4");
                return;
            }

            currentNum += "4";
            display1.setText(currentNum);
        });

        // "5"
        btn5.setOnClickListener(v -> {
            if(Objects.equals(operatorToExecute, "=")){
                numAfterEqualPressed("5");
                return;
            }

            currentNum += "5";
            display1.setText(currentNum);

        });

        // "6"
        btn6.setOnClickListener(v -> {
            if(Objects.equals(operatorToExecute, "=")){
                numAfterEqualPressed("6");
                return;
            }

            currentNum += "6";
            display1.setText(currentNum);

        });

        // "7"
        btn7.setOnClickListener(v -> {
            if(Objects.equals(operatorToExecute, "=")){
                numAfterEqualPressed("7");
                return;
            }

            currentNum += "7";
            display1.setText(currentNum);

        });

        // "8"
        btn8.setOnClickListener(v -> {
            if(Objects.equals(operatorToExecute, "=")){
                numAfterEqualPressed("8");
                return;
            }

            currentNum += "8";
            display1.setText(currentNum);
        });

        // "9"
        btn9.setOnClickListener(v -> {
            if(Objects.equals(operatorToExecute, "=")){
                numAfterEqualPressed("9");
                return;
            }

            currentNum += "9";
            display1.setText(currentNum);
        });

        // "."
        btnDot.setOnClickListener(v -> {
            if(Objects.equals(operatorToExecute, "=")){
                numAfterEqualPressed(".");

                return;
            }

            if(!currentNum.contains(".")){              //if num does NOT contain "."
                if(currentNum.length() == 0) currentNum = "0";  //if nothing has been entered yet
                //i.e) users select "." before a num
                currentNum+= ".";                        //set currentNum to 0, then append "."
            }

            display1.setText(currentNum);
        });

        // "+/-"
        btnPlusMinus.setOnClickListener(v -> {
            if (currentNum.length() == 0) return;       //if no input yet, return

            if(currentNum.contains("-")){               //if num is negative, make positive
                currentNum = currentNum.substring(1);
            }
            else {
                currentNum.length();     //if NOT negative & user has entered an input
                currentNum = "-" + currentNum;
            }

            display1.setText(currentNum);
        });


        //open pop-up of calculation history
        btnHistory.setOnClickListener(v -> openHistory());

        //-----------------------------------------------------------------------------------------
        //----------  ON CLICK LISTENERS FOR "backspace", "clearEntry" & "clearAll  ---------------
        //-----------------------------------------------------------------------------------------

        btnBackspace.setOnClickListener(v -> {
            if(currentNum.length() > 0) {               //if there is a char to backspace
                currentNum = currentNum.substring(0, currentNum.length() - 1);
                display1.setText(currentNum);

                if(currentNum.length() == 0){       //if all chars have been deleted, display 0
                    display1.setText("0");
                }
            }
        });

        btnClearEntry.setOnClickListener(v -> {
            currentNum = "";                            //ONLY clear current entry/display
            display1.setText("0");
        });

        btnClearAll.setOnClickListener(v -> {
            currentNum = "";                            //clear and reset everything
            display1.setText("0");
            display2.setText("");
            num1 = Double.POSITIVE_INFINITY;
            num2 = Double.POSITIVE_INFINITY;
            consecutiveEqual = false;
            operatorToExecute = "";
            previousOperator = "";
        });

        //-----------------------------------------------------------------------------------------
        //--------     ON CLICK LISTENERS FOR "SQUARE ROOT", "SQUARE", "1/X" & "%"    -------------
        //-----------------------------------------------------------------------------------------

        //SQUARE ROOT
        btnSquareRoot.setOnClickListener(v -> {
            //the try-catch is for the case that
            // currentNum is NOT parse-able to a double(i.e "-", ".", "-.")
            try {
                if(currentNum.length() == 0) currentNum = "0";        //if no user-input, default calc to 0
                currentNum = String.valueOf(display1.getText());    //if user just selected "=",
                // takes what's on display1 as currentNum
                double num1 = Double.parseDouble(currentNum);

                display2.setText("Sqrt(" + isInt(num1) + ") ");      //calculate, display & save
                String tmp = "Sqrt(" + isInt(num1) + ") = ";
                num1 = Math.sqrt(num1);
                currentNum = String.valueOf(num1);
                display1.setText(isInt(num1));
                tmp +=isInt(num1);
                calculationList.add(0, tmp);
            } catch (NumberFormatException nfe){}

        });

        //SQUARE
        btnSquare.setOnClickListener(v -> {
            try {
                if(currentNum.length() == 0) currentNum = "0";
                currentNum = String.valueOf(display1.getText());
                double num1 = Double.parseDouble(currentNum);

                display2.setText("Sqr(" + isInt(num1) + ") ");
                String tmp = "Sqr(" + isInt(num1) + ") = ";
                num1 = Math.pow(num1, 2);
                currentNum = String.valueOf(num1);
                display1.setText(isInt(num1));
                tmp +=isInt(num1);
                calculationList.add(0, tmp);
            } catch (NumberFormatException nfe){}
        });

        //1/ CURRENT NUM
        btnDivideByX.setOnClickListener(v -> {
            try {
                if(currentNum.length() == 0) currentNum = "0";
                currentNum = String.valueOf(display1.getText());
                double num1 = Double.parseDouble(currentNum);

                display2.setText("(1/" + isInt(num1) + ") ");
                String tmp = "1/(" + isInt(num1) + ") = ";
                num1 = 1/num1;
                currentNum = String.valueOf(num1);
                display1.setText(isInt(num1));
                tmp +=isInt(num1);
                calculationList.add(0, tmp);
            } catch (NumberFormatException nfe){}
        });

        //CURRENTNUM / 100
        btnPercent.setOnClickListener(v -> {
            try {
                if(currentNum.length() == 0) currentNum = "0";
                currentNum = String.valueOf(display1.getText());
                double num1 = Double.parseDouble(currentNum);

                display2.setText("(" + isInt(num1) + ")/100 ");
                String tmp = "(" + isInt(num1) + ")/100 = ";
                num1 = num1/100;
                currentNum = String.valueOf(num1);
                display1.setText(isInt(num1));
                tmp +=isInt(num1);
                calculationList.add(0, tmp);
            } catch (NumberFormatException nfe){}
        });

        //-----------------------------------------------------------------------------------------
        //--------     ON CLICK LISTENERS FOR ALL THE OPERATORS "+", "-", "x", "/"    -------------
        //-----------------------------------------------------------------------------------------

        btnEquals.setOnClickListener(v -> {
            if(!Objects.equals(previousOperator, "=") && Objects.equals(operatorToExecute, "=")) {
                operatorToExecute = previousOperator;             //if equal is selected consecutively
                consecutiveEqual = true;                        //repeat same operation
            }
            compute("=");
        });

        //add
        btnAdd.setOnClickListener(v -> {
            if(consecutiveEqual) consecutiveEqual = false;
            compute("+");

        });

        //subtract
        btnSubtract.setOnClickListener(v -> {
            if(consecutiveEqual) consecutiveEqual = false;

            compute("-");
        });

        //multiply
        btnMultiply.setOnClickListener(v -> {
            if(consecutiveEqual) consecutiveEqual = false;
            compute("x");
        });

        //divide
        btnDivide.setOnClickListener(v -> {
            if(consecutiveEqual) consecutiveEqual = false;
            compute("/");
        });
    }

    public void compute(String nextOperator){
        try {
            if(currentNum.length() == 0) currentNum = "0";      //if no user input, default to 0

            if(num1 == Double.POSITIVE_INFINITY ){          //if only one number has been entered
                currentNum = String.valueOf(display1.getText());    //i.e "5 +"
                num1 = Double.parseDouble(currentNum);
                display2.append(isInt(num1) + " " + nextOperator + " ");
                currentNum = "";
                operatorToExecute = nextOperator;
            }
            else {
                DecimalFormat f = new DecimalFormat("0000000E00");

                if(consecutiveEqual) {                      //if equal selected consecutively
                    currentNum = String.valueOf(num2);      //repeat same operation w/ previous total
                    String tmp = isInt(num1) + " " + operatorToExecute + " " + isInt(num2) + " " + nextOperator + " ";

                    display2.setText(tmp);
                }
                else {
                    currentNum = String.valueOf(display1.getText());

                    if(operatorToExecute.equals("=")){                //if equals selected, print the total to secondary display
                        String tmp = isInt(num1) + " " + nextOperator + " ";    //followed by NEXT Operator to execute
                        display2.setText(tmp);
                    }
                    else {
                        if (String.valueOf(display2.getText()).length() > 20) {       //if display 2 is greater than allotted length
                            String tmp = isInt(num1) + " " + operatorToExecute + " ";   //print the total to display 2 followed by
                            display2.setText(tmp);                                  //the CURRENT operator to execute
                        }

                        if (currentNum.length() >= 6) {              //if currentNum >=6, format it
                            String tmp = "... " + operatorToExecute + " "
                                    + f.format(num1) + " " + nextOperator + " ";
                            display2.setText(tmp);
                        }
                        else display2.append(isInt(Double.parseDouble(currentNum)) //else print as normal
                                + " " + nextOperator + " ");
                    }
                }

                num2 = Double.parseDouble(currentNum);
                String equation = String.valueOf(display2.getText());

                switch (operatorToExecute) {
                    case "+":
                        num1 += num2;
                        break;
                    case "-":
                        num1 -= num2;
                        break;
                    case "x":
                        num1 *= num2;
                        break;
                    case "/":
                        num1 /= num2;
                        break;
                }

                if(!Objects.equals(nextOperator, "=")){        //formatting current equation for calculation history
                    equation = equation.substring(0, equation.length()-2);
                    equation += "= " + isInt(num1);
                }
                else{
                    equation += isInt(num1);
                }

                if(!Objects.equals(operatorToExecute, "=")) calculationList.add(0,equation);

                if(num1 > Integer.MAX_VALUE || currentNum.length() > 9)     //if the total from calculation
                    display1.setText(f.format(num1));       //exceeds max value of an int OR
                else                                                        //length is > 9, format it
                    display1.setText(isInt(num1));

                previousOperator = operatorToExecute;
                operatorToExecute = nextOperator;
                currentNum = "";
            }
        } catch (NumberFormatException nfe){}
    }

    public void numAfterEqualPressed(String num){       //if user selects a num 0-9 after selecting
        if(num.equals(".")) num = "0.";                 //"=" , clears everything. starts new
        display1.setText(num);
        display2.setText("");
        num1 = Double.POSITIVE_INFINITY;
        num2 = Double.POSITIVE_INFINITY;
        operatorToExecute = "";
    }

    public String isInt(double x){      //if num can be written as an int, returns num in Int form
        if(x%1 == 0 && x < Integer.MAX_VALUE) return String.valueOf((int)x);
        else return String.valueOf(x);
    }

    private void openHistory(){         //opens pop-up of calculations history
        Intent historyWindow = new Intent(MainActivity.this, History.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("calculationsList", calculationList);
        historyWindow.putExtras(bundle);
        startActivity(historyWindow);
    }

    @Override           //if app is left, then re-opened, hide upper/lower navbar
    protected void onResume() {
        super.onResume();
        hideNavBar();
    }

    private void hideNavBar(){      //hides upper/lower navbar
        this.getWindow().getDecorView().setSystemUiVisibility(
                SYSTEM_UI_FLAG_FULLSCREEN |
                        SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
}