package server.serverThreads;
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
         // Creating new ServerSocket and setting upp queue.
         // Max 100 users can await for connection at same time
         serverSocket = new ServerSocket(SERVER_PORT, QEUE_SIZE);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void run() {

      // Creating a threadpool for storing all serverworkers
      ExecutorService threadPool = Executors.newCachedThreadPool();

      System.out.println("Listening on " + SERVER_PORT);
      while (true) {
         try {
            // ServerSocket is waiting for new connection
            // it will freeze whole thread until new connection arrives
            // when connections is established it will return a socket
            Socket socket = serverSocket.accept();

            // Each ServerWorker is a new Thread.
            // It will listen for new messages coming from socket and process it
            ServerWorker worker = new ServerWorker(socket);

            // Adding serverworker to threadpool and staring it
            threadPool.submit(worker);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}
