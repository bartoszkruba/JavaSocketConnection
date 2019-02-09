package server.serverThreads;

import server.serverThreads.ServerWorker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

      ExecutorService threadPool = Executors.newCachedThreadPool();

      System.out.println("Listening on " + SERVER_PORT);
      while (true) {
         try {
            Socket socket = serverSocket.accept();
            ServerWorker worker = new ServerWorker(socket);
            threadPool.submit(worker);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}
