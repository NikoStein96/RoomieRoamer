package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import exceptions.AuthenticationException;
import java.util.List;

/**
 *
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    //Default EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static final UserFacade instance = new UserFacade();
    
    public UserFacade(){}
    
    public static UserFacade getInstance(){
        return instance;
    }
    public UserDTO getUserDTO(Integer id)
    {
        EntityManager em = emf.createEntityManager();
        
        try
        {
            em.getTransaction().begin();
            User u = em.find(User.class, id);
            UserDTO udto = new UserDTO(u);
            return udto;

        }
        finally
        {
            em.close();
        }    
    }
    
        public List<User> getUsers()
    {
        EntityManager em = emf.createEntityManager();

        List<User> users;
        
        
        
            em.getTransaction().begin();
            users = em.createQuery("Select p from User p").getResultList();
            //em.getTransaction().commit();
            em.close();
            return users;
            
    }
        
        public List<User> getUsersByRoleAdmin() {
            EntityManager em = emf.createEntityManager();
           
            List<User> users = null;
            
            try
        {
            em.getTransaction().begin();
            users = em.createQuery("Select p from User p WHERE p.roleList = :Admin").getResultList();
            em.getTransaction().commit();
            return users;
        }
        finally
        {
            em.close();
        }
        }
        
        public List<User> getUsersByRoleUser() {
            EntityManager em = emf.createEntityManager();
           
            List<User> users = null;
            
            try
        {
            em.getTransaction().begin();
            users = em.createQuery("Select p from User p WHERE p.roleList = :User").getResultList();
            em.getTransaction().commit();
            return users;
        }
        finally
        {
            em.close();
        }
        }
        
        public User addUser(User u)
    {
        EntityManager em = emf.createEntityManager();
       
        try
        {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            return u;
        }
        finally
        {
            em.close();
        }
    }
    
    public User editUser(User user)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            User u = em.find(User.class, user.getId());
            if(u != null)
            {
                u = user;
                em.merge(u);
                em.getTransaction().commit();
                return u;
            }
        }
        finally
        {
            em.close();
        }  
        
        return null;
    }
    
    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

}
