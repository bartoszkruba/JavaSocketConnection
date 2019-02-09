package client;

import client.clientThreads.ClientListener;
import client.clientThreads.ClientSender;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class Program {

   private final int SERVER_PORT = 4999;
   private final String SERVER_ADRESS = "192.168.10.153";

   public Program() {

      Socket socket;

      try {
         socket = new Socket(SERVER_ADRESS, SERVER_PORT);
         System.out.println("Connected");
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println("Could not connect");
         return;
      }

      Scanner scanner = new Scanner(System.in);
      LinkedList<String> msgQeue = new LinkedList<>();

      new Thread(new ClientListener(socket)).start();
      new Thread(new ClientSender(msgQeue, socket)).start();

      while (true) {
         msgQeue.push(scanner.nextLine());
      }

   }
}
