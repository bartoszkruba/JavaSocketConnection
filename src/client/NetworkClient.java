package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NetworkClient implements Runnable {
   private final int SERVER_PORT = 9999;
   private final String SERVER_ADRESS = "192.168.10.154";
   private final int SLEEP_MS = 100;

   private Socket socket;
   List<String> msgQeue;

   public NetworkClient(List<String> msgQeue) {

      this.msgQeue = msgQeue;

      try {
         socket = new Socket(SERVER_ADRESS, SERVER_PORT);
         System.out.println("Connected");
      } catch (IOException e) {
         System.out.println("Could not connect");
         e.printStackTrace();
      }
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
