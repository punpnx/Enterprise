package ex1.ex1_1;

import java.io.*; 
import java.net.*;
import java.util.*; 

class TCPClient { 
    public static void main(String argv[]) throws Exception { 
        String sentence; 
        String modifiedSentence;
        Scanner inFromUser = null;
        Socket clientSocket = null;
        DataOutputStream outToServer = null;
        BufferedReader inFromServer = null;
        try { 
            inFromUser = new Scanner(System.in);
            clientSocket = new Socket("localhost", 1667); 
            outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while(true) {
                modifiedSentence = inFromServer.readLine();
                System.out.println(modifiedSentence);
                sentence = inFromUser.nextLine(); 
                outToServer.writeBytes(sentence + '\n');
                if (sentence.equals("")) break;
            }
        } catch (IOException e) {
            System.out.println("Error occurred: Closing the connection");
        } finally {
            try {
                if (inFromServer != null)
                    inFromServer.close();
                if (outToServer != null)
                    outToServer.close();
                if (clientSocket != null)
                    clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    } 
}



