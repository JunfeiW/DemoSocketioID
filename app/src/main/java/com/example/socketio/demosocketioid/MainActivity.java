package com.example.socketio.demosocketioid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.socket.client.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SocketIOManager ioManager = SocketIOManager.getInstance();
//        ioManager.start();

        Socket socket = new SocketIOManager().getSocketInstance();

        /**
         * Either you can place socket.connect() and the corresponding methods here or leave
         * it in the SocketIOManager class. Up to you. For complete persistance, use a service
         */
    }
}
