package server.serverThreads;

import server.ConnectionsManager;
import server.models.User;
import server.message_util.MessageQueueManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalTime;

public class ServerWorker implements Runnable {

   private final Socket socket;
   private final String adress;

   public ServerWorker(Socket socket) {
      this.socket = socket;
      this.adress = socket.getInetAddress().toString();
      ConnectionsManager.getInstance().addConnection(socket, new User("Unregistered"));
      System.out.println("New client connected: " + adress);
   }

   @Override
   public void run() {
      try {
         while (true) {
            InputStreamReader input = new InputStreamReader(socket.getInputStream());
            String msg = new BufferedReader(input).readLine();
            if (msg == null) {
               ConnectionsManager.getInstance().removeConnection(socket);
               System.out.println(adress + " disconnected");
               return;
            }
            String username = ConnectionsManager.getInstance().getConnectedUsers().get(socket).getName();
            msg = LocalTime.now().toString().substring(0, 5) + " | " + username + ": " + msg;
            MessageQueueManager.getInstance().sendToAll(msg);

            System.out.println(msg);
         }

      } catch (IOException e) {
         e.printStackTrace();
      }

   }
}
