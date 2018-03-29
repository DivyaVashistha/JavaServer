/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

/**
 *
 * @author Vikram
 */

import com.google.gson.Gson;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;


public class JavaApplication12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       String fromclient;

ServerSocket Server = new ServerSocket (5050);

System.out.println ("TCPServer Waiting for client on port 5000");

while(true) 
{
    Socket connected = Server.accept();
    System.out.println( " THE CLIENT"+" "+ connected.getInetAddress() +":"+connected.getPort()+" IS CONNECTED ");

    BufferedReader inFromClient = new BufferedReader(new InputStreamReader (connected.getInputStream()));

        fromclient = inFromClient.readLine();
        System.out.println( "RECIEVED:" + fromclient );
        connected.close();
        Gson g = new Gson();
        dataclass received = g.fromJson(fromclient, dataclass.class);
        System.out.println("Operations are "+received.operation);
        for(int j=0;j<received.operation.size();j++)
            System.out.println("Operation- "+received.operation.get(j));
        
        System.out.println("no of data items are "+received.data_count);
        System.out.println("data and its related operation :");
        for(int i=1;i<=received.data_count;i++){
        System.out.println("data- "+received.data.get(("d"+i).toString())+" operation- "+received.data.get(("op"+i).toString()));
        }

    }
}}

class dataclass{
public List operation = new ArrayList();
public JSONObject data = new JSONObject();
public int data_count;
}

