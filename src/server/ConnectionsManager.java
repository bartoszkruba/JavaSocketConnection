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

   private Map<Socket, User> connectedUsers = new HashMap<>();

   private ConnectionsManager() {

   }

   public boolean addConnection(Socket socket, User user) {
      if (!connectedUsers.containsKey(socket)) {
         connectedUsers.put(socket, user);
         return true;
      }
      return false;
   }

   public boolean removeConnection(Socket socket) {
      return connectedUsers.remove(socket) != null;
   }

   public Map<Socket, User> getConnectedUsers() {
      return Collections.unmodifiableMap(connectedUsers);
   }
}
