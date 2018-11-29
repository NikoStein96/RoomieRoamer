package utils;

import chat.entity.*;
import entity.*;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    
    // throw new UnsupportedOperationException("REMOVE THIS LINE, WHEN YOU HAVE READ WARNING");
    
       
    em.getTransaction().begin();
   // Role userRole = new Role("user");
//    Role adminRole = new Role("admin");
//    Interest smoking = new Interest("smoking");
//    Interest soccer = new Interest("soccer");
//    Interest football = new Interest("football");
//    Interest swimming = new Interest("swimming");
//    Interest gaming = new Interest("gaming");
//    Interest music = new Interest("music");
//    Interest art = new Interest("art");
//    Budget low = new Budget("2000-3000");
//    Budget medium = new Budget("3000-4000");
//    Budget high = new Budget("4000-5000");
//    CleanLevel messy = new CleanLevel("Messy");
//    CleanLevel tidy = new CleanLevel("Tidy");
//    Reason money = new Reason("Money");
//    Reason friends = new Reason("Friends");
//    User user = new User("Charlie", "lol123", "Testing", "picRef");
   // user.addRole(userRole);
//    User admin = new User("admin", "test123");
//    admin.addRole(adminRole);
//    User both = new User("user_admin", "test123");
//    both.addRole(userRole);
//    both.addRole(adminRole);
//    em.persist(userRole);
//    em.persist(adminRole);
//    em.persist(smoking);
//    em.persist(soccer);
//    em.persist(football);
//    em.persist(swimming);
//    em.persist(gaming);
//    em.persist(music);
//    em.persist(art);
//    em.persist(user);
//    em.persist(low);
//    em.persist(medium);
//    em.persist(high);
//    em.persist(messy);
//    em.persist(tidy);
//    em.persist(money);
//    em.persist(friends);
//    em.persist(admin);
//    em.persist(both);
Message msg = new Message();
Chat chat = new Chat();

em.persist(msg);
em.persist(chat);
    em.getTransaction().commit();
//    System.out.println("PW: " + user.getUserPass());
//    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
//    User user = em.find(User.class, "user");
//    System.out.println("Testing user with password: " + user.verifyPassword("test123"));
//    System.out.println("Created TEST Users");
   em.close();
  }

}
