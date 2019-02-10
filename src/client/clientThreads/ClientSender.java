package client.clientThreads;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientSender implements Runnable {
   private final int SLEEP_MS = 100;

   private Socket socket;
   List<String> msgQueue;

   public ClientSender(List<String> msgQeue, Socket socket) {
      this.socket = socket;
      this.msgQueue = msgQeue;
   }

   @Override
   public void run() {

      while (!socket.isClosed()) {

         // Checking if there is some message waiting in the queue
         // Same code as in ServerSender but there is no middleware between list and sender
         if (msgQueue.size() > 0) {
            String msg = msgQueue.remove(0);

            try {
               PrintWriter output = new PrintWriter(socket.getOutputStream());
               output.println(msg);
               output.flush();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
         try {
            Thread.sleep(SLEEP_MS);
         } catch (InterruptedException e) {
         }
      }
      System.out.println("Closing sender");
   }
}
