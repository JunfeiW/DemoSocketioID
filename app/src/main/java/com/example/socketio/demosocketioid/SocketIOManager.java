package com.example.socketio.demosocketioid;

import android.util.Log;
import java.net.URISyntaxException;


import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class SocketIOManager {
    private static final String TAG = SocketIOManager.class.getSimpleName();

    private static final String SOCKET_IO_SERVER = "http://chat.socket.io/";

    private static SocketIOManager mInstance;
    //Added @null
    private Socket mSocket=null;


    public Socket getSocketInstance(){
        // To ensure the SAME instance of socket can be called from any activity/ fragment in the app
        //IF this is not done as is, the methods will not be attached properly to the socket
        if (mSocket==null){
            try {
                mSocket = IO.socket(SOCKET_IO_SERVER);
                initializeSocket();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return mSocket;
    }

    private void initializeSocket(){
        /**
         * In case you decide to add socket methods in different class, this can be ignored
         */
        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "EVENT connect");

                String id = mSocket.connect().id();
                Log.d(TAG, "call: " + id);
            }
        }).on(Socket.EVENT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (args != null) {
                    Log.e(TAG, "Event error: " + args[0].toString());
                }
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.e("TAG", "Event disconnect, Socket is disconnected");
            }
        });

        mSocket.connect();
    }


//    private SocketIOManager() {
//        try {
//            mSocket = IO.socket(SOCKET_IO_SERVER);
//        } catch (URISyntaxException e) {
//            Log.e(TAG, "URL is not correct");
//        }
//    }
//
//    public synchronized static SocketIOManager getInstance() {
//        if (mInstance != null) {
//            return mInstance;
//        }
//
//        mInstance =  new SocketIOManager();
//        return mInstance;
//    }

    public void start() {
        Log.d(TAG, "start socket...");
        if (mSocket.connected()) {
            return;
        }

        mSocket.off();

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "EVENT connect");

                String id = mSocket.connect().id();
                Log.d(TAG, "call: " + id);
            }
        }).on(Socket.EVENT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (args != null) {
                    Log.e(TAG, "Event error: " + args[0].toString());
                }
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.e("TAG", "Event disconnect, Socket is disconnected");
            }
        });

        mSocket.connect();
    }


    public void stop() {
        Log.d(TAG, "stop socket...");

        if (mSocket != null) {
            mSocket.disconnect();
        }
    }
}
