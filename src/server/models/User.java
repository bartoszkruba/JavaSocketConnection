package server.models;

public class User {

   // At the moment user contains only name
   private String name;


   public User(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
