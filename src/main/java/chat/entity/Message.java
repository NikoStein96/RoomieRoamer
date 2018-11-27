/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.entity;

import entity.Budget;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tarllark
 */
@Entity
@Table(name="message")
public class Message implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "msg_id")
    final private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "msg", length = 20)
    final private String msg;

    public Message(Integer id, String msg)
    {
        this.id = id;
        this.msg = msg;
    }

    
    public Integer getId()
    {
        return id;
    }

    public String getMsg()
    {
        return msg;
    }

}
