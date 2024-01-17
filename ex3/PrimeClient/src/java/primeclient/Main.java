/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package primeclient;

import java.io.IOException;
import java.util.Scanner;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Puntawan Buasung
 */
public class Main {
    @Resource(mappedName = "jms/SimpleJMSQueue")
    private static Queue queue;
    @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/SimpleJMSTopic")
    private static Topic topic;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Connection connection = null;
        TextListener listener = null;
        
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            listener = new TextListener();
            Queue tempDest = session.createTemporaryQueue();
            MessageConsumer responseConsumer = session.createConsumer(tempDest);
            responseConsumer.setMessageListener(listener);
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            message.setJMSReplyTo(tempDest);
            connection.start();
            
            String range = "";
            Scanner scanner = new Scanner(System.in);

            while(true) {
                System.out.println("Enter two numbers. Use ',' to seperate each number. To end the program press enter");
                range = scanner.nextLine();
                if (!range.equals("")) {
                    message.setText(range);
                    System.out.println("Sending message: " + message.getText());
                    producer.send(message);
                }
                else {
                    break;
                }
            }
        } catch (JMSException e) {
            System.err.println("Exception occurred: " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
    }
    
}
