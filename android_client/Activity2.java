package com.example.apostolis.katanemhmena;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Timestamp;


public class Activity2 extends Activity{

    String setDate1;
    String setDate2;
    String setPoint1;
    String setPoint2;

    Button point1button = null;
    Button point2button = null;
    Button date1button = null;
    Button date2button = null;
    Button sendcoordinates = null;
    TextView date1 = null;
    TextView date2 = null;
    TextView point2 = null;
    TextView point1 = null;
    public void onCreate(Bundle bund){
        super.onCreate(bund);
        setContentView(R.layout.activity2);


        point1button = (Button)findViewById(R.id.point1);
        point2button = (Button)findViewById(R.id.point2);
        date1button = (Button)findViewById(R.id.date1);
        date2button = (Button)findViewById(R.id.date2);
        sendcoordinates = (Button)findViewById(R.id.send);

        date1 = (TextView)findViewById(R.id.takedate1);
        date2 = (TextView)findViewById(R.id.takedate2);
        point1 = (TextView)findViewById(R.id.pointa);
        point2 = (TextView)findViewById(R.id.pointb);

        point1button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPoint1();
                point1.setTextColor(Color.WHITE);
                //Log.d("MyApplication", setPoint1);
            }
        });

        point2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPoint2();
                point2.setTextColor(Color.WHITE);
            }
        });

        date1button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate1();
                date1.setTextColor(Color.WHITE);
            }
        });

        date2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate2();
                date2.setTextColor(Color.WHITE);
            }
        });

        sendcoordinates.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Timestamp x1 = new Timestamp(65756);
                Timestamp x2 = new Timestamp(04355345);
                new SendCoordintates(setPoint1 + "," + setPoint2, x1 = Timestamp.valueOf(setDate1), x2 = Timestamp.valueOf(setDate2)).execute();
                Intent intent = new Intent(Activity2.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }


    private void setPoint1(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Give a pair of Coordinates", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                setPoint1 = input.getText().toString().trim();
                point1.setText(setPoint1);
            }
        });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });

        alert.show();
    }

    private void setPoint2(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Give a pair of Coordinates", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                setPoint2 = input.getText().toString().trim();
                point2.setText(setPoint2);
            }
        });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();

                    }
                });
        alert.show();
    }


    private void setDate1(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Give a Date", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                setDate1 = input.getText().toString().trim();
                date1.setText(setDate1);
            }
        });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
        alert.show();
    }

    private void setDate2(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Give a Date", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                setDate2 = input.getText().toString().trim();
                date2.setText(setDate2);
            }
        });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
        alert.show();
    }

}