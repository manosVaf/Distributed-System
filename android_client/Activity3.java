package com.example.apostolis.katanemhmena;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Timestamp;

public class Activity3 extends Activity{
    TextView name = null;
    TextView point = null;
    TextView url = null;

    Button pointButton = null;
    Button nameButton= null;
    Button urlButton = null;
    Button uploadButton =null;

    String setPoint;
    String setName;
    String setUrl;

    public void onCreate(Bundle bund) {
        super.onCreate(bund);
        setContentView(R.layout.activity3);

        pointButton = (Button) findViewById(R.id.point);
        nameButton = (Button) findViewById(R.id.name);
        urlButton = (Button) findViewById(R.id.url);
        uploadButton = (Button) findViewById(R.id.checkin);

        name = (TextView) findViewById(R.id.nametext);
        point = (TextView) findViewById(R.id.pointtext);
        url = (TextView) findViewById(R.id.urltext);

        pointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPoint();
                point.setTextColor(Color.WHITE);

            }
        });

        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setName();
                name.setTextColor(Color.WHITE);
            }
        });

        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUrl();
                url.setTextColor(Color.WHITE);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                new UploadCheckIn(setName,setPoint,setUrl).execute();
                Intent intent = new Intent(Activity3.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }
    private void setPoint(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Give a pair of Coordinates", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                setPoint = input.getText().toString().trim();
                point.setText(setPoint);
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


    private void setName(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Give the name", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                setName = input.getText().toString().trim();
                name.setText(setName);
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

    private void setUrl(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Give the url photo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                setUrl = input.getText().toString().trim();
               url.setText(setUrl);
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

