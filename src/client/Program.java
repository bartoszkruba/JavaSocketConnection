package client;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Program {

   public Program() {

      Scanner scanner = new Scanner(System.in);
      LinkedList<String> msgQeue = new LinkedList<>();

      new Thread(new NetworkClient(msgQeue)).start();

      while (true) {
         msgQeue.push(scanner.nextLine());
      }

   }
}
