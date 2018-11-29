/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.resources;

import chat.entity.Chat;
import chat.entity.Message;
import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


/**
 *
 * @author Tarllark
 */
public class ChatFacade
{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();
    
    public Chat getChat(int id){
        return em.find(Chat.class, id);
    }
    
    public boolean isNewer(Chat chat){
        if(!chat.getLastMSG().equals(chat.getNewMSG()) && chat.getNewMSG() != null){ 
            chat.setLastMSG(chat.getNewMSG());
            chat.setNewMSG(null);
            return true;
        }
        return false;
    }
    
    public JSONObject toJSON(Message msg){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("sender", msg.getSender());
        jsonObj.put("msg", msg.getMsg());
        return jsonObj;
    }
    public JSONObject toJSON(Message msg, Chat chat){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("sender", msg.getSender());
        jsonObj.put("msg", msg.getMsg());
        jsonObj.put("chat", chat.getId());
        return jsonObj;
    }
        
    public String getNewMessage(int id){
        Chat chat = getChat(id);
        if(chat.getNewMSG() != null) 
            isNewer(chat);
        
        return toJSON(chat.getLastMSG()).toJSONString();
        
    }
    
    public String getHistory(int id){
        Chat chat = getChat(id);
        JSONArray jsonArr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        for(Message msg : chat.getHistory())
            jsonArr.add(toJSON(msg));
        jsonObj.put("results", jsonArr);
        return jsonObj.toString();
    }
    
    public Message createMSG(String msg, int userID, int chatID){
        Chat chat = em.find(Chat.class, chatID);
        Message newMSG = new Message(msg, em.find(User.class, userID),
                chat);
        chat.addHistory(newMSG);
        chat.setNewMSG(newMSG);
        isNewer(chat);
        return newMSG;
    }
    public Message createMSG(String msg, User user, Chat chat){
        Message newMSG = new Message(msg, user,
                chat);
        chat.addHistory(newMSG);
        chat.setNewMSG(newMSG);
        isNewer(chat);
        return newMSG;
    }
    public Message createMSG(JSONObject jsonMSG){
        String msg = jsonMSG.getAsString("msg");
        User user = em.find(User.class, jsonMSG.get("sender"));
        Chat chat = em.find(Chat.class, jsonMSG.get("chat"));
        return createMSG(msg, user, chat);
    }
    
    public Message fromJSON(JSONObject jsonMSG){
        String msg = jsonMSG.getAsString("msg");
        User user = em.find(User.class, jsonMSG.get("sender"));
        Chat chat = em.find(Chat.class, jsonMSG.get("chat"));
        Message newMSG = new Message(msg, user, chat);
        return new Message (msg, user, chat);
    }

    public boolean receive(int id){
        Chat chat = getChat(id);
        Message newest = chat.getHistory().get(chat.getHistory().size()-1);
        if(newest == chat.getLastMSG()) chat.setNewMSG(newest);
        return chat.getNewMSG() != null;
    }
    
}
