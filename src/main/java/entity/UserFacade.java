package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import exceptions.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TODO: Liste over potentielle personer du kan like Liste over LIKEDE personer
 * (én person har liked en anden og afventer svar) Liste over Matches (to
 * personer som har liked hinanden) Liste over disliked personer (personlig
 * dislike liste) Liste over blokerede personer Liste over samtaler
 *
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    //Default EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static final UserFacade instance = new UserFacade();
    

    public UserFacade(){}

    
    public static UserFacade getInstance() {
        return instance;
    }

    public UserDTO getUserDTO(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            User u = em.find(User.class, id);
            
            System.out.println(u);

            //System.out.println(u);
            UserDTO udto = new UserDTO(u);
            return udto;
            
        } finally {
            em.close();
        }        
    }
    
    public List<UserDTO> getUsers() {
        EntityManager em = emf.createEntityManager();
        
        List<UserDTO> usersDTO = new ArrayList();
        
        em.getTransaction().begin();
        List<User> users = em.createQuery("Select p from User p").getResultList();
        em.getTransaction().commit();
        for (User user : users) {
            UserDTO uDTO = new UserDTO(user);
            usersDTO.add(uDTO);
        }
        em.close();
        return usersDTO;
    }
    
    public List<User> getUsersByRoleAdmin() {
        EntityManager em = emf.createEntityManager();
        
        List<User> users = null;
        
        try {
            em.getTransaction().begin();
            users = em.createQuery("Select p from User p WHERE p.roleList = :Admin").getResultList();
            em.getTransaction().commit();
            return users;
        } finally {
            em.close();
        }
    }
    
    public List<User> getUsersByRoleUser() {
        EntityManager em = emf.createEntityManager();
        
        List<User> users = null;
        
        try {
            em.getTransaction().begin();
            users = em.createQuery("Select p from User p WHERE p.roleList = :User").getResultList();
            em.getTransaction().commit();
            return users;
        } finally {
            em.close();
        }
    }
    
    public User addUser(User u) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            return u;
        } finally {
            em.close();
        }
    }
    
    public User editUser(User user) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            User u = em.find(User.class, user.getId());
            if (u != null) {
                u = user;
                em.merge(u);
                em.getTransaction().commit();
                return u;
            }
        } finally {
            em.close();
        }        
        
        return null;
    }

    public User deleteUser(int id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            User u = em.find(User.class, id);
            em.remove(u);
            em.getTransaction().commit();
            return u;
        } finally {
            em.close();
        }
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

    public User getTest(){
        EntityManager em = emf.createEntityManager();
        User u = em.find(User.class, 1);
        return u;
    }
    
    public List<User> getPoma(User user)
    {
        EntityManager em = emf.createEntityManager();
        List<User> poma = new ArrayList();
        int max = 30;
        int iteration = 1;
        List<User> curSet = new ArrayList();
        while(!(poma.size() >= 20))
        {
           if(em.find(User.class, (max*iteration-29)) == null) break;
            //Create user set
            for(int i = (max*iteration-29); i <= max*iteration; i++)
            {
                User cur = em.find(User.class, i);
                if(user.getId()!= cur.getId() &&(!user.getIgnored().contains(cur) && 
                        (!user.getLiked().contains(cur) &&
                        !user.getMatches().contains(cur))))
                {
                    curSet.add(cur);
                }
            }
            
            //Create poma list
            for(User cur : curSet)
            {
                int count = 0;
                if(user.getQuestionnaire().getQuestionnaireArea().getCityInfoZip().equals(cur.getQuestionnaire().getQuestionnaireArea().getCityInfoZip())) count++;
                if(user.getQuestionnaire().getQuestionnaireBudget().getBudgetName().equals(cur.getQuestionnaire().getQuestionnaireBudget().getBudgetName())) count++;
                if(user.getQuestionnaire().getQuestionnaireClean().getCleanLevelName().equals(cur.getQuestionnaire().getQuestionnaireClean().getCleanLevelName())) count++;
                if(user.getQuestionnaire().getQuestionnaireReason().getReasonName().equals(cur.getQuestionnaire().getQuestionnaireReason().getReasonName())) count++;
                if(user.getQuestionnaire().isQuestionnaireMusic() == cur.getQuestionnaire().isQuestionnaireMusic()) count++;
                if(user.getQuestionnaire().isQuestionnaireParty() == cur.getQuestionnaire().isQuestionnaireParty()) count++;
                if(user.getQuestionnaire().isQuestionnairePet() == cur.getQuestionnaire().isQuestionnairePet()) count++;
                if(user.getQuestionnaire().isQuestionnaireSingle() == cur.getQuestionnaire().isQuestionnaireSingle()) count++;
                if(user.getQuestionnaire().isQuestionnaireSmoke() == cur.getQuestionnaire().isQuestionnaireSmoke()) count++;
                if(user.getQuestionnaire().isQuestionnaireSport() == cur.getQuestionnaire().isQuestionnaireSport()) count++;
                
                if(count >= 3) poma.add(cur);
                if(poma.size() >= 20) break;
            }
            iteration++;
        }
        
        return poma;
    }
    
    public String getPomaAsString()
    {
        EntityManager em = emf.createEntityManager();
        String res = "";
        for(User u : getPoma(em.find(User.class, 1)))
        {
            res += u.getUserName() + "\n";
        }
        return res;
    }
    
}
