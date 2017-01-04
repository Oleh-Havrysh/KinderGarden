package ua.nure.dao;



import ua.nure.dao.model.Group;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.dao.DAOUtils.close;
import static ua.nure.dao.DAOUtils.getConnection;


/**
 * Created by Oleg on 05.12.2016.
 */
public class GroupDAO {
    private static GroupDAO instance;

    public synchronized static GroupDAO get(){
        if(instance == null){
            instance = new GroupDAO();
        }
        return instance;
    }

    private static Group getGroup(ResultSet rs) throws SQLException {
        return new Group(rs.getLong("group_id"), rs.getString("name"), rs.getLong("teacher_id"));
    }




    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM groups");
            while (rs.next()) {
                groups.add(getGroup(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement, rs);
        }
        return groups;
    }

    public Group getGroup(long id) {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM groups WHERE group_id=" + id);
            rs.next();
            return getGroup(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement, rs);
        }
        return null;
    }
}
