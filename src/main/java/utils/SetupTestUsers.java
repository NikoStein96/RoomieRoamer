package utils;

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
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    Interest smoking = new Interest("smoking");
    Interest soccer = new Interest("soccer");
    Interest football = new Interest("football");
    Interest swimming = new Interest("swimming");
    Interest gaming = new Interest("gaming");
    Interest music = new Interest("music");
    Interest art = new Interest("art");
    
//    User user = new User("user", "test123");
//    user.addRole(userRole);
//    User admin = new User("admin", "test123");
//    admin.addRole(adminRole);
//    User both = new User("user_admin", "test123");
//    both.addRole(userRole);
//    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(smoking);
    em.persist(soccer);
    em.persist(football);
    em.persist(swimming);
    em.persist(gaming);
    em.persist(music);
    em.persist(art);
//    em.persist(user);
//    em.persist(admin);
//    em.persist(both);
    em.getTransaction().commit();
//    System.out.println("PW: " + user.getUserPass());
//    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
//    User user = em.find(User.class, "user");
//    System.out.println("Testing user with password: " + user.verifyPassword("test123"));
//    System.out.println("Created TEST Users");
   
  }

}
