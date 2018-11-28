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
    
    private Chat getChat(int id){
        return em.find(Chat.class, id);
    }
    
    private boolean isNewer(Chat chat){
        if(!chat.getLastMSG().equals(chat.getNewMSG()) && chat.getNewMSG() != null){ 
            chat.setLastMSG(chat.getNewMSG());
            chat.setNewMSG(null);
            return true;
        }
        return false;
    }
    
    private JSONObject toJSON(Message msg){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("sender", msg.getSender());
        jsonObj.put("msg", msg.getMsg());
        return jsonObj;
    }
    private JSONObject toJSON(Message msg, Chat chat){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("sender", msg.getSender());
        jsonObj.put("msg", msg.getMsg());
        jsonObj.put("chat", chat.getId());
        return jsonObj;
    }
        
    private String getNewMessage(int id){
        Chat chat = getChat(id);
        if(chat.getNewMSG() != null) 
            isNewer(chat);
        
        return toJSON(chat.getLastMSG()).toJSONString();
        
    }
    
    private String getHistory(int id){
        Chat chat = getChat(id);
        JSONArray jsonArr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        for(Message msg : chat.getHistory())
            jsonArr.add(toJSON(msg));
        jsonObj.put("results", jsonArr);
        return jsonObj.toString();
    }
    
    private Message createMSG(String msg, int userID, int chatID){
        Chat chat = em.find(Chat.class, chatID);
        Message newMSG = new Message(msg, em.find(User.class, userID),
                chat);
        chat.addHistory(newMSG);
        chat.setNewMSG(newMSG);
        isNewer(chat);
        return newMSG;
    }
    
    private Message fromJSON(JSONObject jsonMSG){
        String msg = jsonMSG.getAsString("msg");
        User user = em.find(User.class, jsonMSG.get("sender"));
        Chat chat = em.find(Chat.class, jsonMSG.get("chat"));
        Message newMSG = new Message(msg, user, chat);
        return new Message (msg, user, chat);
    }

}
