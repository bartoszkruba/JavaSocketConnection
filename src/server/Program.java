package server;

import server.serverThreads.NetworkServer;
import server.serverThreads.ServerSender;

public class Program {

   public Program() {

      // Starting new thread for sending all messages
      new Thread(new ServerSender()).start();

      // Starting new thread for receiving all new connections
      new NetworkServer().run();
   }
}
