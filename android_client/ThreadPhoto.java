package com.example.apostolis.katanemhmena;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;


public class ThreadPhoto implements Callable<ArrayList<Bitmap>> {
    private ArrayList<CheckIn> result;
    private ArrayList<Bitmap> bmp=new ArrayList<Bitmap>();

    ThreadPhoto(ArrayList<CheckIn> result){
        this.result=result;
    }

    public ArrayList<Bitmap> call() throws IOException{

        URL url ;
       for(int i=0; i<result.size(); i++) {
           try {
               url = new URL(result.get(i).getUrls().get(0));
               bmp.add(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       return bmp;
    }
}
