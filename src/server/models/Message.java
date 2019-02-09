package server.models;

import java.net.Socket;

public class Message {

   // receiver is socket to which message should be send
   private final Socket receiver;
   // content is the actual String that will be send
   private final String content;

   public Message(Socket receiver, String content) {
      this.receiver = receiver;
      this.content = content;
   }

   public Socket getReceiver() {
      return receiver;
   }

   public String getContent() {
      return content;
   }
}
