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

      //Getting ip address from socket
      this.adress = socket.getInetAddress().toString();

      //Adding socket connection to list with all connected users
      ConnectionsManager.getInstance().addConnection(socket, new User("Unregistered"));
      System.out.println("New client connected: " + adress);
   }

   @Override
   public void run() {
      try {
         while (true) {

            // Getting input stream from socket
            // all messages from client comes through input stream
            InputStreamReader input = new InputStreamReader(socket.getInputStream());

            // Reading message from input stream with buffered reader
            // This will freeze whole thread until next message arrives or connection is lost
            String msg = new BufferedReader(input).readLine();

            // If connection is lost buffered reader returns null value
            // it means that user disconnected
            if (msg == null) {

               // removing socket from userlist since its no longer connected
               ConnectionsManager.getInstance().removeConnection(socket);
               System.out.println(adress + " disconnected");

               // no user connected so there is no point in waiting for new messages
               // return will break the while-loop
               return;
            }

            // Getting username for this socket
            String username = ConnectionsManager.getInstance().getConnectedUsers().get(socket).getName();

            // Message will contain current time + username + actual message
            msg = LocalTime.now().toString().substring(0, 5) + " | " + username + ": " + msg;

            // adding new messages to messageQueue
            MessageQueueManager.getInstance().sendToAll(msg);

            // Printing message to the console - debugging purpose
            System.out.println(msg);
         }

      } catch (IOException e) {
         e.printStackTrace();
      }

   }
}
