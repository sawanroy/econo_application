package com.flownex.test_application;

import androidx.appcompat.app.AppCompatActivity;
import com.flownex.libecono.SerialPort;
import com.flownex.libecono.serialport.SerialHelper;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button Button1, Button2, Button3 , Button4;
    String dev = "/dev/ttymxc2";
    int baud = 115200;
    int stop = 1;
    int data = 8;
    int parity = 0;
    int flowcon = 0;
    int flag = 0;

    public FileDescriptor devfd;
    public SerialPort mserial = new SerialPort();
//    public OutputStream mOutputstream = new ;
//    public InputStream mInputstream;
//    public SerialHelper mserialhelp;
String bytesToHex(byte[] in) {
    final StringBuilder builder = new StringBuilder();

    for (byte b : in) {
        builder.append(String.format("%02X", b));
    }
    return builder.toString();
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button1 = findViewById(R.id.open_b);
        Button2 = findViewById(R.id.Read_b);
        Button3 = findViewById(R.id.Write_b);
        Button4 = findViewById(R.id.close_b);
        TextView outputTextView = findViewById(R.id.read1);



        Button1.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View view) {
                try {
                    devfd = mserial.rs485_open(dev,baud,stop,data,parity,flowcon,flag);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Log.d("opened fd = ", devfd.toString());


//                    this.mInputstream = this.mserial.getInputStream();
//                    this.mOutputstream = this.mserial.getOutputStream();

            }
        });

        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button2", "Button2 Clicked");
                byte[] buf = new byte[10];
                int Timeout = 10000;
                // Assuming 'devfd' is a valid FileDescriptor object
                if(devfd != null) {
                int rcv = mserial.Rs485_read(devfd, buf, buf.length, Timeout);
                if (rcv < 0) {
                    outputTextView.setText("READ ERROR");
                } else {
                    outputTextView.setText("READ SUCCESS");
                    outputTextView.setText("READ DATA :" + bytesToHex(buf));
                    System.out.println("buffer " + bytesToHex(buf));
                }
            } else {
                outputTextView.setText("PORT NOT OPEN");
            }
            }
        });





        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "Sawan";
                byte[] RS485_buf=str.getBytes();//{(byte) 0xABCB};//str.getBytes();
                if(devfd != null) {
                    int rcv = mserial.RS485_Write(devfd,RS485_buf,RS485_buf.length);
                    System.out.println("da"+rcv);
                    if (rcv < 0) {
                        outputTextView.setText("WRITE ERROR");
                    } else {
                        outputTextView.setText("WRITE SUCCESSFULL");
                    }
                }else{
                    outputTextView.setText("PORT NOT OPEN");
                }
            }
        });

        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (devfd != null) {
                    mserial.close();

                    outputTextView.setText("RS485 PORT CLOSED");
                }
            }

        });


    }
}