package com.example.cuocduakithu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBoxXanh;
    CheckBox checkBoxTim;
    CheckBox checkBoxDen;
    SeekBar seekBarXanh;
    SeekBar seekBarTim;
    SeekBar seekBarDen;
    Button buttonStart;
    TextView textResul;
    int sodiem =100;
    private static final String LOGTAG = "SeekBarDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intivity();
        addAction();
    }
    private void enableCheckbox(){
        checkBoxDen.setEnabled(true);
        checkBoxXanh.setEnabled(true);
        checkBoxTim.setEnabled(true);
    }
    private void disableCheckbox(){
        checkBoxDen.setEnabled(false);
        checkBoxXanh.setEnabled(false);
        checkBoxTim.setEnabled(false);
    }
    public void intivity(){
        checkBoxXanh = findViewById(R.id.checkBoxXanh);
        checkBoxTim  = findViewById(R.id.checkBoxTim);
        checkBoxDen  = findViewById(R.id.checkBoxDen);
        seekBarXanh  = findViewById(R.id.seekBarXanh);
        seekBarTim   = findViewById(R.id.seekBarTim);
        seekBarDen   = findViewById(R.id.seekBarDen);
        buttonStart  = findViewById(R.id.buttonStart);
        textResul    = findViewById(R.id.textViewResult);
    }
    public void addAction() {
        seekBarXanh.setEnabled(false);
        seekBarDen.setEnabled(false);
        seekBarTim.setEnabled(false);
        checkBoxXanh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBoxTim.setChecked(false);
                    checkBoxDen.setChecked(false);
                    Toast.makeText(MainActivity.this, "Ban da chon Xanh", Toast.LENGTH_SHORT).show();
                }
                //else Toast.makeText(MainActivity.this, "Ban bo chon Xanh", Toast.LENGTH_SHORT).show();
            }
        }
        );
        checkBoxTim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBoxDen.setChecked(false);
                    checkBoxXanh.setChecked(false);
                    Toast.makeText(MainActivity.this, "Ban da chon Tim", Toast.LENGTH_SHORT).show();
                }
                //else Toast.makeText(MainActivity.this, "Ban bo chon Tim", Toast.LENGTH_SHORT).show();
            }
        }
        );
        checkBoxDen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBoxXanh.setChecked(false);
                    checkBoxTim.setChecked(false);
                    Toast.makeText(MainActivity.this, "Ban da chon Den", Toast.LENGTH_SHORT).show();
                }
                //else Toast.makeText(MainActivity.this, "Ban bo chon Den", Toast.LENGTH_SHORT).show();
            }
        }
        );
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //textResul.setText("Progress:"+seekBarXanh.getProgress()+"/"+seekBarXanh.getMax());                        de xem tien do chay cua chin xanh
                seekBarXanh.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    int progress = 0;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                        progress = progressValue;
                        //textResul.setText("Progress"+progressValue+ "/" + seekBarXanh.getMax());
                        Log.i(LOGTAG,"Changing seekbar's progress");

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Log.i(LOGTAG,"Started tracking seekbar");
                        Toast.makeText(MainActivity.this, "Started tracking seekbar", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        //textResul.setText("Progress: " + progress + "/" + seekBarXanh.getMax());
                        Log.i(LOGTAG, "Stopped tracking seekbar");
                        Toast.makeText(MainActivity.this, "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
                    }
                });
                final CountDownTimer countDownTimer = new CountDownTimer(20000,250) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Random random = new Random();
                        int xanh = random.nextInt(6);
                        int tim  = random.nextInt(6);
                        int den  = random.nextInt(6);

                         if(seekBarXanh.getProgress()>=seekBarXanh.getMax()){
                             this.cancel();
                             buttonStart.setVisibility(View.VISIBLE);
                             //Toast.makeText(MainActivity.this, "Xanh WIN!", Toast.LENGTH_SHORT).show();
                             if(checkBoxXanh.isChecked()){
                                 Toast.makeText(MainActivity.this, "Xanh WIN!\nYou Win!", Toast.LENGTH_SHORT).show();
                                 sodiem+=10;
                                 //Toast.makeText(MainActivity.this, "You Win!", Toast.LENGTH_SHORT).show();
                             }
                             else {
                                 if(checkBoxTim.isChecked()){
                                     sodiem-=10;
                                     Toast.makeText(MainActivity.this, "Tim Win!\nYou lose!", Toast.LENGTH_SHORT).show();
                                 }
                                 else if (checkBoxDen.isChecked()){
                                     sodiem-=10;
                                     Toast.makeText(MainActivity.this, "Den Win!\nYou lose!", Toast.LENGTH_SHORT).show();
                                 }
                                 //sodiem-=10;
                                 //Toast.makeText(MainActivity.this, "You lose!", Toast.LENGTH_SHORT).show();
                             }
                             textResul.setText(sodiem+"");
                             enableCheckbox();
                         }
                         else if(seekBarTim.getProgress()>=seekBarTim.getMax()){
                             this.cancel();
                             buttonStart.setVisibility(View.VISIBLE);
                             //Toast.makeText(MainActivity.this, "Tim WIN!", Toast.LENGTH_SHORT).show();
                             if(checkBoxTim.isChecked()){
                                 Toast.makeText(MainActivity.this, "Tim WIN!\nYou Win!", Toast.LENGTH_SHORT).show();
                                 sodiem+=10;
                                 //Toast.makeText(MainActivity.this, "You Win!", Toast.LENGTH_SHORT).show();
                             }
                             else {
                                 if(checkBoxXanh.isChecked()){
                                     sodiem-=10;
                                     Toast.makeText(MainActivity.this, "Xanh Win!\nYou lose!", Toast.LENGTH_SHORT).show();
                                 }
                                 else if (checkBoxDen.isChecked()){
                                     sodiem-=10;
                                     Toast.makeText(MainActivity.this, "Den Win!\nYou lose!", Toast.LENGTH_SHORT).show();
                                 }
//                                 sodiem-=10;
//                                 Toast.makeText(MainActivity.this, "You lose!", Toast.LENGTH_SHORT).show();
                             }
                             textResul.setText(sodiem+"");
                             enableCheckbox();
                         }
                         else if(seekBarDen.getProgress()>=seekBarDen.getMax()){
                             this.cancel();
                             buttonStart.setVisibility(View.VISIBLE);
                             //Toast.makeText(MainActivity.this, "Den WIN!", Toast.LENGTH_SHORT).show();
                             if(checkBoxDen.isChecked()){
                                 Toast.makeText(MainActivity.this, "Den WIN!\nYou Win!", Toast.LENGTH_SHORT).show();
                                 sodiem+=10;
                                 //Toast.makeText(MainActivity.this, "You Win!", Toast.LENGTH_SHORT).show();
                             }
                             else {
                                 if(checkBoxXanh.isChecked()){
                                     sodiem-=10;
                                     Toast.makeText(MainActivity.this, "Xanh Win!\nYou lose!", Toast.LENGTH_SHORT).show();
                                 }
                                 else if (checkBoxTim.isChecked()){
                                     sodiem-=10;
                                     Toast.makeText(MainActivity.this, "Tim Win!\nYou lose!", Toast.LENGTH_SHORT).show();
                                 }
//                                 sodiem-=10;
//                                 Toast.makeText(MainActivity.this, "You lose!", Toast.LENGTH_SHORT).show();
                             }
                             textResul.setText(sodiem+"");
                             enableCheckbox();
                         }
                        seekBarXanh.setProgress(seekBarXanh.getProgress()+xanh);
                        seekBarTim.setProgress(seekBarTim.getProgress()+tim);
                        seekBarDen.setProgress(seekBarDen.getProgress()+den);
                    }

                    @Override
                    public void onFinish() {

                    }
                };
                buttonStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkBoxDen.isChecked()||checkBoxTim.isChecked()||checkBoxXanh.isChecked()) {
                               seekBarXanh.setProgress(0);
                               seekBarTim.setProgress(0);
                               seekBarDen.setProgress(0);
                               buttonStart.setVisibility(View.INVISIBLE);
                               countDownTimer.start();
                               disableCheckbox();
                        }
                        else Toast.makeText(MainActivity.this, "Vui long dat cuoc truoc khi Start ", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
