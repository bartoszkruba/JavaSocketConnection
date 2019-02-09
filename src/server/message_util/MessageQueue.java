package server.message_util;

import server.models.Message;

import java.net.Socket;
import java.util.LinkedList;

public class MessageQueue {
   private static MessageQueue ourInstance = new MessageQueue();

   public static MessageQueue getInstance() {
      return ourInstance;
   }

   // All messages awaits in a linked list until they can be send
   private LinkedList<Message> messages = new LinkedList<>();

   private MessageQueue() {

   }


   // Adding new message to queue
   public synchronized void addMessage(Socket receiver, String content) {
      messages.add(new Message(receiver, content));
   }

   // Return the oldest awaitng message and removes it from queue
   public synchronized Message shiftMessage() {
      if (messages.size() > 0) {
         return messages.remove(0);
      } else {
         return null;
      }
   }
}
