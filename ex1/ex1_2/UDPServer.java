package ex1.ex1_2;

import java.io.*; 
import java.net.*; 
import java.util.*;
import java.text.SimpleDateFormat;

class UDPServer { 
    public static void main(String args[]) throws Exception { 
        DatagramSocket serverSocket = new DatagramSocket(1667); 
        byte[] receiveData = new byte[1024]; 
        byte[] sendData  = new byte[1024];
        while(true) { 
            System.out.println("Waiting for client connection at port number 1667");
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
            serverSocket.receive(receivePacket);
            InetAddress IPAddress = receivePacket.getAddress(); 
            int port = receivePacket.getPort(); 

            String currentDateAndTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
            sendData = currentDateAndTime.getBytes(); 
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); 
            serverSocket.send(sendPacket); 
        } 
    } 
} 

