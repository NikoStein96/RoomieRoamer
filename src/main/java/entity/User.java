package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_name", length = 25)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_pass")
    private String userPass;
    @Basic(optional = false)
    @Column(name = "user_desc", length = 255)
    private String desc;
    @Basic(optional = false)
    @Column(name = "user_picture", length = 255)
    private String picRef;
    @JoinTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
        @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    @ManyToMany
    private List<Role> roleList = new ArrayList();

    public List<String> getRolesAsStrings()
    {
        if (roleList.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList();
        for (Role role : roleList) {
            rolesAsStrings.add(role.getRoleName());
        }
        return rolesAsStrings;
    }

    public User()
    {
    }

    //TODO Change when password is hashed
    public boolean verifyPassword(String pw)
    {
        return BCrypt.checkpw(pw, userPass);
//        return(pw.equals(userPass));
    }

    public User(String userName, String userPass)
    {
        this.userName = userName;
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(userPass, salt);
        this.userPass = hash;
    }

    public User(String userName, String userPass, String desc, String picRef)
    {
        this.userName = userName;
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(userPass, salt);
        this.userPass = hash;
        this.desc = desc;
        this.picRef = picRef;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPass()
    {
        return this.userPass;
    }

    public void setUserPass(String userPass)
    {
        this.userPass = userPass;
    }

    public List<Role> getRoleList()
    {
        return roleList;
    }

    public void setRoleList(List<Role> roleList)
    {
        this.roleList = roleList;
    }

    public void addRole(Role userRole)
    {
        roleList.add(userRole);
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getPicRef()
    {
        return picRef;
    }

    public void setPicRef(String picRef)
    {
        this.picRef = picRef;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

}
