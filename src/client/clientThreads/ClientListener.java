package client.clientThreads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientListener implements Runnable {

   Socket socket;

   public ClientListener(Socket socket) {
      this.socket = socket;
   }

   @Override
   public void run() {
      try {
         // Listens for all messages comming thourgh socket
         // Same code as in ServerWorker
         while (!socket.isClosed()) {
            InputStreamReader input = new InputStreamReader(socket.getInputStream());
            String msg = new BufferedReader(input).readLine();
            if (msg == null) {
               System.out.println("Server disconnected");
               socket.close();
            }
            System.out.println(msg);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      System.out.println("Closing listener");
   }
}
