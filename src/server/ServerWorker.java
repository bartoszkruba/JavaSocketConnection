package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerWorker implements Runnable {

   private final Socket socket;
   private final String adress;

   public ServerWorker(Socket socket) {
      this.socket = socket;
      this.adress = socket.getInetAddress().toString();
      ConnectionsMenager.getInstance().addConnection(socket, new User("Unregistered"));
      System.out.println("New client connected: " + adress);
   }

   @Override
   public void run() {
      try {
         while (true) {
            InputStreamReader input = new InputStreamReader(socket.getInputStream());
            String msg = new BufferedReader(input).readLine();
            if (msg == null) {
               break;
            }
            System.out.println(msg);
         }

      } catch (IOException e) {
         e.printStackTrace();
      }
      System.out.println(adress + " disconnected");
   }
}
