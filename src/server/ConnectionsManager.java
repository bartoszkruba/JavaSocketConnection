package server;

import server.models.User;

import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConnectionsManager {

   private static ConnectionsManager ourInstance = new ConnectionsManager();

   public static ConnectionsManager getInstance() {
      return ourInstance;
   }

   // All current connected users are stored in a hash map
   private Map<Socket, User> connectedUsers = new HashMap<>();

   private ConnectionsManager() {

   }

   // adding new connection to hashmap
   // return true if connection was successfully added
   public synchronized boolean addConnection(Socket socket, User user) {
      if (!connectedUsers.containsKey(socket)) {
         connectedUsers.put(socket, user);
         return true;
      }
      return false;
   }

   // removing connection from hashmap
   // returns true if connection was succesfully removed
   public synchronized boolean removeConnection(Socket socket) {
      return connectedUsers.remove(socket) != null;
   }

   public synchronized Map<Socket, User> getConnectedUsers() {
      return Collections.unmodifiableMap(connectedUsers);
   }
}
