package com.company.calculatorex;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnDot,btnDel,btnPlus,btnMulti,btnDivide,btnMinus,btnEqual,btnAc;

    TextView textViewHistory,textViewResult;

    String number = null;

    double firstNumber =0;
    double lastNumber =0;

    String status =null;
    boolean oparator = false;

    DecimalFormat myFormatter = new DecimalFormat("######.######");

    String history,currentResult;

    boolean dot = true;
    boolean btnAcControl = true;
    boolean btnEqualControl = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);

        btnDel=findViewById(R.id.btnDel);
        btnAc=findViewById(R.id.btnAC);
        btnDot=findViewById(R.id.btnDot);
        btnEqual=findViewById(R.id.btnEqual);


        btnPlus=findViewById(R.id.btnPlus);
        btnMinus=findViewById(R.id.btnMinus);
        btnDivide=findViewById(R.id.btnDivide);
        btnMulti=findViewById(R.id.btnMulti);


        textViewHistory=findViewById(R.id.textViewHistory);
        textViewResult=findViewById(R.id.textViewResult);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("9");
            }
        });



        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dot=true;
                number=null;
                status=null;
                textViewResult.setText("0");
                textViewHistory.setText("");
                firstNumber=0;
                lastNumber=0;
                btnAcControl=true;

            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btnAcControl)
                {
                    textViewResult.setText("0");
                }
                else
                {
                    number = number.substring(0,number.length()-1);
                    if(number.length()==0)
                    {
                        btnDel.setClickable(false);
                    }
                    else if(number.contains("."))
                    {
                        dot=false;

                    }
                    else
                    {
                        dot=true;
                    }
                    textViewResult.setText(number);

                }

            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!btnEqualControl)
                {
                    history = textViewHistory.getText().toString();
                    currentResult=textViewResult.getText().toString();
                    textViewHistory.setText(history+currentResult);
                }


                if(oparator)
                {
                    if(status=="sum")
                    {
                        plus();
                    }
                    else if(status=="subtraction")
                    {
                        minus();
                    }
                    else if(status=="multiplication")
                    {
                        multiplication();
                    }
                    else if(status=="division")
                    {
                        divide();
                    }
                    else
                    {
                        firstNumber=Double.parseDouble(textViewResult.getText().toString());
                    }
                }
                oparator=false;
                dot=true;
                btnEqualControl=true;

            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dot)
                {

                    if(number==null)
                    {
                        number="0.";
                    }
                    else
                    {
                        number=number+".";
                    }

                }

                textViewResult.setText(number);
                dot = false;

            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btnEqualControl)
                {
                    history = textViewHistory.getText().toString();
                   // currentResult=textViewResult.getText().toString();
                    textViewHistory.setText(history+"+");
                }
                else
                {
                    history = textViewHistory.getText().toString();
                    currentResult=textViewResult.getText().toString();
                    textViewHistory.setText(history+currentResult+"+");
                }
                if(oparator)
                {
                    if(status=="multiplication")
                    {
                        multiplication();
                    }
                    else if(status=="division")
                    {
                        divide();
                    }
                    else if(status=="subtraction")
                    {
                        minus();
                    }
                    else
                    {
                        plus();
                    }
                }
                oparator=false;
                status="sum";
                number=null;
                dot=true;

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btnEqualControl)
                {
                    history = textViewHistory.getText().toString();
                    // currentResult=textViewResult.getText().toString();
                    textViewHistory.setText(history+"-");
                }
                else
                {
                    history = textViewHistory.getText().toString();
                    currentResult=textViewResult.getText().toString();
                    textViewHistory.setText(history+currentResult+"-");
                }
                if(oparator)
                {
                    if(status=="multiplication")
                    {
                        multiplication();
                    }
                    else if(status=="division")
                    {
                        divide();
                    }
                    else if(status=="sum")
                    {
                        plus();
                    }
                    else
                    {
                        minus();
                    }

                }

                oparator=false;
                status="subtraction";
                number=null;
                dot=true;

            }

        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btnEqualControl)
                {
                    history = textViewHistory.getText().toString();
                    // currentResult=textViewResult.getText().toString();
                    textViewHistory.setText(history+"*");
                }
                else
                {
                    history = textViewHistory.getText().toString();
                    currentResult=textViewResult.getText().toString();
                    textViewHistory.setText(history+currentResult+"*");
                }

                if(oparator)
                {
                    if(status=="sum")
                    {
                        plus();
                    }
                    else if(status=="subtraction")
                    {
                        minus();
                    }
                    else if(status=="division")
                    {
                        divide();
                    }
                    else
                    {
                        multiplication();
                    }
                }
                status="multiplication";
                oparator=false;
                number=null;
                dot=true;

            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btnEqualControl)
                {
                    history = textViewHistory.getText().toString();
                    // currentResult=textViewResult.getText().toString();
                    textViewHistory.setText(history+"/");
                }
                else
                {
                    history = textViewHistory.getText().toString();
                    currentResult=textViewResult.getText().toString();
                    textViewHistory.setText(history+currentResult+"/");
                }
                if(oparator)
                {
                    if(status=="sum")
                    {
                        plus();
                    }
                    else if(status=="subtraction")
                    {
                        minus();
                    }
                    else if(status=="multiplication")
                    {
                        multiplication();
                    }
                    else
                    {
                        divide();
                    }
                }
                status="division";
                oparator=false;
                number=null;
                dot=true;

            }
        });





    }

    public  void numberClick(String view)
    {
        if(number==null)
        {
            number = view;
        }
        else if(btnEqualControl)
        {
            firstNumber=0;
            lastNumber=0;
            textViewHistory.setText("");
            number=view;
        }
        else
        {
            number = number+view;
        }
        textViewResult.setText(number);
        oparator=true;
        btnAcControl=false;
        btnDel.setClickable(true);
        btnEqualControl=false;
    }
    public  void plus()
    {
        lastNumber= Double.parseDouble(textViewResult.getText().toString());
        firstNumber=firstNumber+lastNumber;
        textViewResult.setText(myFormatter.format(firstNumber));


    }

    public void minus()
    {
        if(firstNumber==0)
        {
            firstNumber=Double.parseDouble(textViewResult.getText().toString());
        }
        else
        {
            lastNumber=Double.parseDouble(textViewResult.getText().toString());
            firstNumber=firstNumber-lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
    }

    public void multiplication()
    {
        if(firstNumber==0)
        {
            firstNumber=1;
            lastNumber=Double.parseDouble(textViewResult.getText().toString());
            firstNumber=firstNumber * lastNumber;
        }
        else
        {
            lastNumber=Double.parseDouble(textViewResult.getText().toString());
            firstNumber=firstNumber*lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
    }

    public void divide()
    {
        if(firstNumber==0)
        {
            lastNumber=Double.parseDouble(textViewResult.getText().toString());
            firstNumber= lastNumber/1;
        }
        else
        {
            lastNumber=Double.parseDouble(textViewResult.getText().toString());
            firstNumber=firstNumber/lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
    }


}