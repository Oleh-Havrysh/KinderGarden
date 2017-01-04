package ua.nure.dao;


import ua.nure.dao.model.Child;

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
public class ChildDAO {
    private static ChildDAO instance;

    public synchronized static ChildDAO get(){
        if(instance == null){
            instance = new ChildDAO();
        }
        return instance;
    }

    public List<Child> getChildren() {
        List<Child> children = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM children");
            while (rs.next()) {
                children.add(getChild(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement, rs);
        }
        return children;
    }

    public List<Child> getChildren(long groupId) {
        List<Child> children = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM children WHERE group_id=" + groupId);
            while (rs.next()) {
                children.add(getChild(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement, rs);
        }
        return children;
    }




    public Child getChild(long id) {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM children WHERE child_id=" + id);
            rs.next();
            return getChild(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement, rs);
        }
        return null;
    }

    private static Child getChild(ResultSet rs) throws SQLException {
        Child child = new Child(rs.getLong("child_id"), rs.getString("name"), rs.getString("surname"),
                rs.getDate("birth_date"), rs.getLong("group_id"),
                rs.getLong("parent_id"), rs.getString("notice"));
        return child;
    }
}
