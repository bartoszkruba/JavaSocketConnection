package server.serverThreads;


import server.models.Message;
import server.message_util.MessageQueue;

import java.io.IOException;
import java.io.PrintWriter;

public class ServerSender implements Runnable {

   private final int SLEEP_MS = 100;

   public ServerSender() {

   }

   @Override
   public void run() {
      while (true) {

         // Getting the oldest message from messageQueue
         // Method return null if there is no messages waiting at the moment
         Message msg = MessageQueue.getInstance().shiftMessage();

         if (msg != null) {
            // There is new message awaiting to be send

            try {

               // getting output stream from socket
               // msg.getReceiver will return socket
               // All data needs to be sended through output stream
               PrintWriter output = new PrintWriter(msg.getReceiver().getOutputStream());

               // Setting up message
               output.println(msg.getContent());

               // Sending message
               output.flush();

            } catch (IOException e) {
               e.printStackTrace();
            }
         }

         // If there is no messages waiting to be send Thread will sleep for 100ms
         try {
            Thread.sleep(SLEEP_MS);
         } catch (InterruptedException e) {
         }

      }
   }
}
