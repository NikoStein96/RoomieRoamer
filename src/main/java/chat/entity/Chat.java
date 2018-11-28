/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.entity;

import entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Tarllark
 */
@Entity
@Table(name="chat")
public class Chat implements Serializable
{
private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "chat_id")
    final private Integer id;
    @OneToMany
    @Basic(optional = false)
    private List<Message> history = new ArrayList();
    @OneToMany
    @Basic(optional = false)
    private final User user1;
    @OneToMany
    @Basic(optional = false)
    private final User user2;
    private Message lastMSG;
    private Message newMSG;

    public Chat(Integer id, User owner, User partner)
    {
        this.id = id;
        this.user1 = owner;
        this.user2 = partner;
    }

    public List<Message> getHistory()
    {
        return history;
    }

    public void addHistory(Message msg)
    {
        this.history.add(msg);
    }

    public Message getLastMSG()
    {
        return lastMSG;
    }

    public void setLastMSG(Message lastMSG)
    {
        this.lastMSG = lastMSG;
    }

    public Message getNewMSG()
    {
        return newMSG;
    }

    public void setNewMSG(Message newMSG)
    {
        this.newMSG = newMSG;
    }

    public Integer getId()
    {
        return id;
    }
    
    
}
