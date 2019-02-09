package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkServer {
   private final int SERVER_PORT = 4999;
   private final int QEUE_SIZE = 100;

   private ServerSocket serverSocket;

   public NetworkServer() {
      try {
         serverSocket = new ServerSocket(SERVER_PORT, QEUE_SIZE);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void run() {
      System.out.println("Listening on " + SERVER_PORT);
      while (true) {
         try {
            Socket socket = serverSocket.accept();
            ServerWorker worker = new ServerWorker(socket);
            new Thread(worker).start();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}
