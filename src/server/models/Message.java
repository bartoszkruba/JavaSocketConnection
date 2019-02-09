package server.models;

import java.net.Socket;

public class Message {

   private final Socket receiver;
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
