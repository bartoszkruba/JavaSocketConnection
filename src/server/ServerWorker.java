package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerWorker implements Runnable {

   private final Socket socket;

   public ServerWorker(Socket socket) {
      this.socket = socket;
      System.out.println("Connected to new client: " + socket.getInetAddress());
   }

   @Override
   public void run() {
      try {
         InputStreamReader input = new InputStreamReader(socket.getInputStream());
         while (socket.getInputStream().read() != -1) {
            String msg = new BufferedReader(input).readLine();
            System.out.println(msg);
         }
         System.out.println("Connection lost");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
