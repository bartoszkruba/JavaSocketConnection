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
         Message msg = MessageQueue.getInstance().shiftMessage();
         if (msg != null) {
            try {
               PrintWriter output = new PrintWriter(msg.getReceiver().getOutputStream());
               output.println(msg.getContent());
               output.flush();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
         try {
            Thread.sleep(SLEEP_MS);
         } catch (InterruptedException e) {
         }


         try {
            Thread.sleep(SLEEP_MS);
         } catch (InterruptedException e) {

         }
      }
   }
}
