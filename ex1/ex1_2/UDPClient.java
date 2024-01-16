package ex1.ex1_2;

import java.io.*; 
import java.net.*;
import java.util.*; 

class UDPClient { 
    public static void main(String args[]) throws Exception { 
        DatagramSocket clientSocket = new DatagramSocket(); 
        InetAddress IPAddress = InetAddress.getByName("localhost"); 
        byte[] sendData = new byte[1024]; 
        byte[] receiveData = new byte[1024]; 
        String sentence = "Requesting date and time";
        sendData = sentence.getBytes();  
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1667); 
        clientSocket.send(sendPacket); 
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
        clientSocket.receive(receivePacket); 
        String modifiedSentence = new String(receivePacket.getData()); 
        System.out.println("FROM SERVER: " + modifiedSentence.trim()); 
        clientSocket.close(); 
    } 
} 
