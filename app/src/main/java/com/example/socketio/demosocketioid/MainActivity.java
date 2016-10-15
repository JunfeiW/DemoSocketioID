package com.example.socketio.demosocketioid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SocketIOManager ioManager = SocketIOManager.getInstance();
        ioManager.start();
    }
}
