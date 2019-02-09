package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerWorker implements Runnable {

   private final Socket socket;

   public ServerWorker(Socket socket) {
      this.socket = socket;
   }

   @Override
   public void run() {
      try {
         ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
         String msg = (String) input.readObject();
         System.out.println(msg);
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }
}
