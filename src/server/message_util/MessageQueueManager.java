package server.message_util;

import server.ConnectionsManager;

public class MessageQueueManager {
   private static MessageQueueManager ourInstance = new MessageQueueManager();

   public static MessageQueueManager getInstance() {
      return ourInstance;
   }

   private MessageQueueManager() {
   }

   public void sendToAll(String message) {
      ConnectionsManager.getInstance().getConnectedUsers()
              .entrySet()
              .forEach(e -> {
                 MessageQueue.getInstance().addMessage(e.getKey(), message);
              });
   }
}
