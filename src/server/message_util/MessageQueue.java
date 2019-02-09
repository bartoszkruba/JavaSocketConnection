package server.message_util;

import server.models.Message;

import java.net.Socket;
import java.util.LinkedList;

public class MessageQueue {
   private static MessageQueue ourInstance = new MessageQueue();

   public static MessageQueue getInstance() {
      return ourInstance;
   }

   private LinkedList<Message> messages = new LinkedList<Message>();

   private MessageQueue() {

   }

   public synchronized void addMessage(Socket receiver, String content) {
      messages.add(new Message(receiver, content));
   }

   public synchronized Message shiftMessage() {
      if (messages.size() > 0) {
         return messages.remove(0);
      } else {
         return null;
      }
   }
}
