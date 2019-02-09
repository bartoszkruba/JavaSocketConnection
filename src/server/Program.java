package server;

import server.serverThreads.NetworkServer;
import server.serverThreads.ServerSender;

public class Program {

   public Program() {
      new Thread(new ServerSender()).start();
      new NetworkServer().run();
   }
}
