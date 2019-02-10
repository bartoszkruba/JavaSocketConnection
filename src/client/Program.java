package client;

import client.clientThreads.ClientListener;
import client.clientThreads.ClientSender;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

class Program {

   private final int SERVER_PORT = 4999;
   private final String SERVER_ADRESS = "192.168.10.153";

   Program() {

      while (true) {
         runClient();
         try {
            Thread.sleep(2000);

         } catch (InterruptedException e) {
         }
      }

   }

   private void runClient() {
      Socket socket;
      LinkedList<String> msgQueue = new LinkedList<>();
      try {
         // Setting upp connection client and server
         socket = new Socket(SERVER_ADRESS, SERVER_PORT);
         System.out.println("Connected");
      } catch (IOException e) {
         System.out.println("Could not connect");
         return;
      }

      Scanner scanner = new Scanner(System.in);

      // Thread for listening for all incoming messages from server
      new Thread(new ClientListener(socket)).start();

      // Thread for sending new messages to the server
      new Thread(new ClientSender(msgQueue, socket)).start();

      while (!socket.isClosed()) {
         // Adding new message to queue
         msgQueue.push(scanner.nextLine());
      }
   }
}
