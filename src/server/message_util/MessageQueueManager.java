package server.message_util;

import server.ConnectionsManager;

public class MessageQueueManager {
   private static MessageQueueManager ourInstance = new MessageQueueManager();

   public static MessageQueueManager getInstance() {
      return ourInstance;
   }

   private MessageQueueManager() {
   }

   // Sending message to all current connected users with one method
   public void sendToAll(String message) {

      // Loops though all connected users
      ConnectionsManager.getInstance().getConnectedUsers()
              .entrySet()
              .forEach(e -> {
                 // places a new message in the queue for each user
                 MessageQueue.getInstance().addMessage(e.getKey(), message);
              });
   }
}
