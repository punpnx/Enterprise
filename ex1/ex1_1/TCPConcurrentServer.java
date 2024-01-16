package ex1.ex1_1;

import java.io.*; 
import java.net.*; 
import java.util.*;

public class TCPConcurrentServer { 
    public static void main(String argv[])  { 
        ServerSocket welcomeSocket = null;
        try {
            welcomeSocket = new ServerSocket(1667);
            System.out.println("Waiting for client connection at port number 1667");
        } catch (IOException e) {
            System.out.println("Cannot create a welcome socket");
            System.exit(1);
        }
        while(true) {
            try {	 
                Socket connectionSocket = welcomeSocket.accept(); 
                EchoThread echoThread = new EchoThread(connectionSocket);
                echoThread.start();
            } catch (IOException e) {
                System.out.println("Cannot create this connection");
            }
        }
    } 
} 
