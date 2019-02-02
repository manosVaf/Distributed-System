package com.example.apostolis.katanemhmena;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SendCoordintates extends AsyncTask<Void, Void, ArrayList<CheckIn>> {
    private String location;
    private Timestamp date1;
    private Timestamp date2;
    private Socket socket1;
    private Socket socket2;
    private Socket socket3;
    private Socket socket;
    private ServerSocket server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    StringTokenizer token;
    private final String mapper_ip1="192.168.1.3";
    private final String mapper_ip2="192.168.1.3";
    private final String mapper_ip3="192.168.1.3";
    Info a=null; //mapper1
    Info b=null; // mapper 2
    Info c=null; //mapper 3
    String ip_addr;
    ArrayList<Bitmap> bmp=new ArrayList<>();

    public SendCoordintates(String Location, Timestamp Date1, Timestamp Date2){
        location = Location;
        date1 = Date1;
        date2 = Date2;
    }

    @Override
    protected void onPostExecute(ArrayList<CheckIn> result) {
        ArrayList<Marker> finalmarkes = new ArrayList<Marker>();
        ArrayList<LatLng> points = new ArrayList<LatLng>();

        for (int i = 0; i < result.size(); i++) {
            points.add(new LatLng(result.get(i).getX(), result.get(i).getY()));
        }

        final MarkerOptions option = new MarkerOptions();

        for (int i = 0; i < points.size(); i++) {
            option.position(points.get(i));
            option.title(result.get(i).getPoiname());
            option.snippet(result.get(i).getFreq() + " people checked in here!!!");
            //bmp.get(1).createScaledBitmap(bmp.get(1),10,10,true);
            //option.icon(BitmapDescriptorFactory.fromBitmap(bmp.get(1)));
            //if(i<bmp.size())
            //option.icon(BitmapDescriptorFactory.fromBitmap(bmp.get(i)));
            finalmarkes.add(MapsActivity.getmMap().addMarker(option));
        }
    }

    @Override
    protected ArrayList<CheckIn> doInBackground(Void... params) {
        StringTokenizer token = new StringTokenizer(location, ",");
        double firstx = Double.parseDouble(token.nextToken().trim());
        double firsty = Double.parseDouble(token.nextToken().trim());
        double secondx = Double.parseDouble(token.nextToken().trim());
        double secondy = Double.parseDouble(token.nextToken().trim());
        double differencex = Math.abs((firstx - secondx))/3.0;

        try {

            Enumeration<NetworkInterface> e=NetworkInterface.getNetworkInterfaces();
           for(NetworkInterface interface1:Collections.list(e)){
               if(interface1.isLoopback()) continue;
               Enumeration<InetAddress> addr=interface1.getInetAddresses();
               for(InetAddress addre :Collections.list(addr)){
                   if(addre instanceof Inet4Address){
                       ip_addr=addre.getHostAddress();
                   }
               }
           }



            socket1 = new Socket(mapper_ip1, 4001);
            socket2 = new Socket(mapper_ip2, 4002);
            socket3 = new Socket(mapper_ip3, 4003);


            a = new Info(firstx, firsty, (firstx + differencex), secondy, date1, date2,ip_addr, 3);
            out = new ObjectOutputStream(socket1.getOutputStream());
            out.writeObject(a);
            out.flush();

            b = new Info(firstx + differencex, firsty, (firstx + (2 *differencex)), secondy, date1, date2,ip_addr, 3);
            out = new ObjectOutputStream(socket2.getOutputStream());
            out.writeObject(b);
            out.flush();

            c = new Info(firstx + (2 *differencex), firsty, (firstx + (3*differencex)), secondy, date1, date2,ip_addr, 3);
            out = new ObjectOutputStream(socket3.getOutputStream());
            out.writeObject(c);
            out.flush();

            server = new ServerSocket(4005);
            socket = server.accept();

            in = new ObjectInputStream(socket.getInputStream());

            @SuppressWarnings("unchecked")
            ArrayList<CheckIn> finalList = (ArrayList<CheckIn>)in.readObject();
            final ExecutorService service;
            final Future<ArrayList<Bitmap>> task;

            service = Executors.newFixedThreadPool(1);
            task    = service.submit(new ThreadPhoto(finalList));

            try{
                bmp=task.get();
            }catch(final InterruptedException ex) {
                ex.printStackTrace();
            } catch(final ExecutionException ex) {
                ex.printStackTrace();
            }

            service.shutdownNow();

            return finalList;

        } catch (IOException e) {
            System.err.println("Cannot connect to mappers!");
        } catch (ClassNotFoundException e) {
            System.err.println("Wrong Packet Received!!");
        }finally{
            try{
                if(out!=null) out.close();
                in.close();
                socket1.close();
                socket2.close();
                socket3.close();
                socket.close();
                server.close();
            }catch(IOException e){
                System.err.println("Cannot Close connections!");
            }
        }
        return null;
    }


}
