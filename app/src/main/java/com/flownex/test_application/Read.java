package com.flownex.test_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Read extends AppCompatActivity {
    private InputStream mInputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        try {
            // Define a buffer to read the data
            byte[] buffer = new byte[1024]; // Adjust the buffer size as needed

            // Read data from the UART connection
            int bytesRead = mInputStream.read(buffer);

            if (bytesRead > 0) {
                // Trim the buffer to the actual data length
                byte[] receivedData = Arrays.copyOf(buffer, bytesRead);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}