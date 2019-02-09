package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientSender implements Runnable {
   private final int SLEEP_MS = 100;

   private Socket socket;
   List<String> msgQeue;

   public ClientSender(List<String> msgQeue, Socket socket) {
      this.socket = socket;
      this.msgQeue = msgQeue;
   }

   @Override
   public void run() {
      while (true) {
         if (msgQeue.size() > 0) {
            String msg = msgQeue.remove(0);

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
   }
}
