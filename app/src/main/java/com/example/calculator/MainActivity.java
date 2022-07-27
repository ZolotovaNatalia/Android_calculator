package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnAC, btnDel, btnPlus, btnMinus, btnDivide, btnMulti, btnEquals, btnDot;

    private TextView textViewResult, textViewHistory;

    private String number = null;
    private double firstNumber = 0;
    private double lastNumber = 0;
    private String status = "";
    private boolean operator = false;

    private DecimalFormat myFormat = new DecimalFormat("######.######");

    private String history, currentResult;

    private boolean dot = true;

    private boolean btnACcontrol = true;

    private boolean btnEqualsControl = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHistory = findViewById(R.id.textViewHistory);
        textViewHistory.setMovementMethod(new ScrollingMovementMethod());
        textViewResult = findViewById(R.id.textViewResult);

        findButtons();
        defineNumberButtons();
        defineOperatorButtons();
    }

    private void defineNumberButtons() {
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("9");
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dot){
                    if(number == null) {
                        number = "0.";
                    } else {
                        number = number + ".";
                    }
                }
                textViewResult.setText(number);
                dot = false;
            }
        });
    }

    private void defineOperatorButtons() {
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printHistoryForOperators("+");

                if (operator) {
                    if (status.equals("multiplication")) {
                        multiply();
                    } else if (status.equals("division")) {
                        divide();
                    } else if (status.equals("subtraction")) {
                        minus();
                    } else plus();
                }
                status = "sum";
                operator = false;
                number = null;
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printHistoryForOperators("/");
                if (operator) {
                    if (status.equals("subtraction")) {
                        minus();
                    } else if (status.equals("multiplication")) {
                        multiply();
                    } else if (status.equals("sum")) {
                        plus();
                    } else divide();
                }
                status = "division";
                operator = false;
                number = null;
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printHistoryForOperators("*");
                if (operator) {
                    if (status.equals("subtraction")) {
                        minus();
                    } else if (status.equals("division")) {
                        divide();
                    } else if (status.equals("sum")) {
                        plus();
                    } else multiply();
                }
                status = "multiplication";
                operator = false;
                number = null;
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printHistoryForOperators("-");
                if (operator) {
                    if (status.equals("multiplication")) {
                        multiply();
                    } else if (status.equals("division")) {
                        divide();
                    } else if (status.equals("sum")) {
                        plus();
                    } else minus();
                }
                status = "subtraction";
                operator = false;
                number = null;
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator) {
                    if (status.equals("multiplication")) {
                        multiply();
                    } else if (status.equals("division")) {
                        divide();
                    } else if (status.equals("sum")) {
                        plus();
                    } else if(status.equals("subtraction")){
                        minus();
                    }else {
                        firstNumber = Double.parseDouble(textViewResult.getText().toString());
                    }
                }
                operator = false;
                btnEqualsControl = true;

                history = textViewHistory.getText().toString() + "\n" + myFormat.format(firstNumber) + "\n";
                textViewHistory.setText(history);
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator = false;
                number = null;
                status = "";
                textViewResult.setText("0");
                textViewHistory.setText("");
                firstNumber = 0;
                lastNumber = 0;
                dot = true;
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnACcontrol) {
                    textViewResult.setText("0");
                }else {
                    number = number.substring(0, number.length() - 1);

                    if(number.length() == 0) {
                        btnDel.setClickable(false);
                    } else if (number.contains(".")){
                        dot = false;
                    } else {
                        dot = true;
                    }
                    textViewResult.setText(number);
                }
            }
        });

    }

    private void printHistoryForOperators(String operatorSign) {
        history = textViewHistory.getText().toString();
        textViewHistory.setText(history + operatorSign);
    }

    private void numberClick(String view) {
        if (number == null) {
            number = view;
        } else if(btnEqualsControl){
            firstNumber = 0;
            lastNumber = 0;
            number = view;
        } else {
            number = number + view;
        }
        textViewResult.setText(number);
        operator = true;
        btnACcontrol = false;
        btnDel.setClickable(true);
        btnEqualsControl = false;

        printHistoryForNumbers();
    }

    private void printHistoryForNumbers() {
        history = textViewHistory.getText().toString();
        currentResult = textViewResult.getText().toString();
        textViewHistory.setText(history + currentResult);
    }

    private void findButtons() {
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDel);
        btnDivide = findViewById(R.id.btnDiv);
        btnMinus = findViewById(R.id.btnMinus);
        btnMulti = findViewById(R.id.btnMult);
        btnEquals = findViewById(R.id.btnEquals);
        btnDot = findViewById(R.id.btnDot);
        btnPlus = findViewById(R.id.btnPlus);
    }

    private void plus() {
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber = firstNumber + lastNumber;
        textViewResult.setText(myFormat.format(firstNumber));
        dot = true;

    }

    private void minus() {
        if (firstNumber == 0) {
            firstNumber = Double.parseDouble(textViewResult.getText().toString());
        } else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber - lastNumber;
        }
        textViewResult.setText(myFormat.format(firstNumber));
        dot = true;
    }

    private void multiply() {
        if (firstNumber == 0) {
            firstNumber = 1;
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * lastNumber;
        } else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * lastNumber;
        }
        textViewResult.setText(myFormat.format(firstNumber));
        dot = true;
    }

    private void divide() {
        if (firstNumber == 0) {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = lastNumber / firstNumber;
        } else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber / lastNumber;
        }
        textViewResult.setText(myFormat.format(firstNumber));
        dot = true;
    }
}