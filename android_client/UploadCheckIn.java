package com.example.apostolis.katanemhmena;


import android.os.AsyncTask;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UploadCheckIn extends AsyncTask<Void,Void,Void>{
    String name,point;
    String url;
    private Socket socket1;
    private ObjectOutputStream out;

    public UploadCheckIn(String name,String point,String url){
        this.name=name;
        this.point=point;
        this.url=url;
    }

    @Override
    protected Void doInBackground(Void... params){
        Info a =new Info(name,point,url,true);
        try {
            socket1 = new Socket("192.168.1.3", 4001);
            out = new ObjectOutputStream(socket1.getOutputStream());
            out.writeObject(a);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
