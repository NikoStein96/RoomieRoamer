/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin
 */
public class UserDTO
{
    private Integer id;
    private String userName;
    private String userPass;
    private String desc;
    private String picRef;
    private List<RoleDTO> roleList = new ArrayList();
    private List<InterestDTO> interests = new ArrayList();
    private List<UserDTO> matches = new ArrayList();
    private List<UserDTO> ignored = new ArrayList();
    private List<UserDTO> liked = new ArrayList();
    
    public UserDTO(User user) {
        System.out.println(user);
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.desc = user.getDesc();
        this.picRef = user.getPicRef();
        for(Role r : user.getRoleList()){
            this.roleList.add(new RoleDTO(r));
        
        }
        for(Interest i : user.getInterests()) {
            this.interests.add(new InterestDTO(i));
        }
        for(User um : user.getMatches()) {
            this.matches.add(new UserDTO(um));
        }
        for(User ui : user.getIgnored()) {
            this.ignored.add(new UserDTO(ui));
        }
        for(User ul : user.getLiked()) {
            this.liked.add(new UserDTO(ul));
        }
    }


    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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
        return userPass;
    }

    public void setUserPass(String userPass)
    {
        this.userPass = userPass;
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

    public List<RoleDTO> getRoleList()
    {
        return roleList;
    }

    public void setRoleList(List<RoleDTO> roleList)
    {
        this.roleList = roleList;
    }

    public List<InterestDTO> getInterests()
    {
        return interests;
    }

    public void setInterests(List<InterestDTO> interests)
    {
        this.interests = interests;
    }
    
                    
}
