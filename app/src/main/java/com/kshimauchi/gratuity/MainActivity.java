package com.kshimauchi.gratuity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    TextView percentTip;
    TextView tip;
    TextView total;
    EditText subtotal;
    Button calculate;
    BigDecimal totalAmount,tipAmount;
    double tipPercentage =0.15;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);  //sets the Design: Resource.layout.main.xml
            percentTip = (TextView)findViewById(R.id.tipPercent);
            tip = (TextView)findViewById(R.id.tip);
            total = (TextView)findViewById(R.id.total);
            subtotal = (EditText)findViewById(R.id.subtotal);
            calculate = (Button)findViewById(R.id.button);
            seekBar =(SeekBar)findViewById(R.id.seekbar);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekbar, int i, boolean b) {
                percentTip.setText("%" + String.valueOf(i));
                tipPercentage = i * .01;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        percentTip.setText("%" +seekBar.getProgress());

        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(subtotal.getText()!=null)
                {
                    BigDecimal mVal = new BigDecimal(Double.valueOf(subtotal.getText().toString()));

                    tipAmount = mVal.multiply(new BigDecimal(   tipPercentage   ));

                    totalAmount = mVal.add(  tipAmount   );

                    String tipAmountText = NumberFormat.getCurrencyInstance().format(tipAmount);

                    tip.setText(    tipAmountText   );

                    String totalAmountText =NumberFormat.getCurrencyInstance().format(totalAmount);
                    total.setText(  totalAmountText  );
                }
            }
        });
    }
}
