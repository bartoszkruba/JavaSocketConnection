package server;

import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConnectionsMenager {
   private static ConnectionsMenager ourInstance = new ConnectionsMenager();

   public static ConnectionsMenager getInstance() {
      return ourInstance;
   }

   private Map<Socket, User> connectedUsers = new HashMap<>();

   private ConnectionsMenager() {

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
