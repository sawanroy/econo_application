package com.flownex.test_application;

import androidx.appcompat.app.AppCompatActivity;
import com.flownex.libecono.SerialPort;

import android.os.Bundle;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Read extends AppCompatActivity {
    private InputStream mInputStream;

    public SerialPort mserial = new SerialPort();
    int primitiveIntValue;
    FileDescriptor readFD;
    int size = 1024;
    String output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        TextView outputTextView = findViewById(R.id.read1);
        String devfd = (String) getIntent().getSerializableExtra("file_descriptor");
        try {
            FileInputStream fileInputStream = new FileInputStream(devfd);
            readFD = fileInputStream.getFD();

            // Now you have the FileDescriptor for the opened file.
            // You can use this fileDescriptor to read data.

            // Don't forget to close the FileInputStream when done.
            fileInputStream.close();
        } catch (Exception e) {
            // Handle any exceptions that might occur (e.g., file not found, permission issues).
        }
//s


//        try {
//            // Define a buffer to read the data
//            byte[] buffer = new byte[1024]; // Adjust the buffer size as needed
//
//            // Read data from the UART connection
//
//
//            if (bytesRead > 0) {
//                // Trim the buffer to the actual data length
//                byte[] receivedData = Arrays.copyOf(buffer, bytesRead);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}