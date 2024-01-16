package ex1.ex1_1;

import java.io.*; 
import java.net.*; 
import java.util.*;

public class EchoThread extends Thread {
    private Socket connectionSocket;

    public EchoThread(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    public void run() {
        BufferedReader inFromClient = null;
        DataOutputStream outToClient = null;
        try {
            inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            int sum = 0;
            for (int i = 0; i < 2; i++) {
                outToClient.writeBytes("Enter number " + (i + 1) + " (to end just press enter): " + '\n');
                String clientSentence = inFromClient.readLine();
                if (clientSentence.equals("")) {
                    return;
                }
                sum += Integer.parseInt(clientSentence);
            }
            outToClient.writeBytes("The result is " + sum + '\n');
        } catch (IOException e) {
            System.out.println("Error occurred: Closing this connection");
        } finally {
            try {
                if (inFromClient != null)
                    inFromClient.close();
                if (outToClient != null)
                    outToClient.close();
                if (connectionSocket != null)
                    connectionSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

