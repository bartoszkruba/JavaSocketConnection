package client;

import server.ConnectionsMenager;

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
         while (true) {
            InputStreamReader input = new InputStreamReader(socket.getInputStream());
            String msg = new BufferedReader(input).readLine();
            if (msg == null) {
               ConnectionsMenager.getInstance().removeConnection(socket);
               System.out.println("Server disconnected");
               return;
            }
            System.out.println(msg);
         }

      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
