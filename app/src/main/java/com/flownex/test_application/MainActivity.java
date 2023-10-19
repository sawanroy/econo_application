package com.flownex.test_application;

import androidx.appcompat.app.AppCompatActivity;
import com.flownex.libecono.SerialPort;
import com.flownex.libecono.serialport.SerialHelper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button Button1, Button2, Button3 , Button4;
    String dev = "/dev/ttymxc1";
    int baud = 115200;
    int stop = 1;
    int data = 8;
    int parity = 0;
    int flowcon = 0;
    int flag = 0;
    private SerialPort mserial = new SerialPort();
//    public OutputStream mOutputstream = new ;
//    public InputStream mInputstream;
    public SerialHelper mserialhelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button1 = findViewById(R.id.open_b);
        Button2 = findViewById(R.id.Read_b);
        Button3 = findViewById(R.id.Write_b);
        Button4 = findViewById(R.id.close_b);


        Button1.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View view) {
                try {
                    mserialhelp.setPort(dev);
                    mserialhelp.setBaudRate(baud);
                    mserialhelp.setStopBits(stop);
                    mserialhelp.setDataBits(data);
                    mserialhelp.setParity(parity);
                    mserialhelp.setFlowCon(flowcon);

                    mserialhelp.open();
//                    mserial.
//                    this.mserial =new SerialPort(dev,baud,stop,data,parity,flowcon,flag);
//                    this.mInputstream = this.mserial.getInputStream();
//                    this.mOutputstream = this.mserial.getOutputStream();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Read.class);
//               intent.putExtra(this.mInputstream = this.mserial.getInputStream());


            }
        });

        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Write.class));
            }
        });

        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}