package ua.nure.dao;

/**
 * Created by Oleg on 05.12.2016.
 */
public class MessageDAO {
    private static MessageDAO instance;

    public synchronized static MessageDAO get(){
        if(instance == null){
            instance = new MessageDAO();
        }
        return instance;
    }
}
