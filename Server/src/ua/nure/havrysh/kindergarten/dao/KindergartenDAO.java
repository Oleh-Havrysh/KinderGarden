package ua.nure.havrysh.kindergarten.dao;

import ua.nure.havrysh.kindergarten.dao.model.Announcement;
import ua.nure.havrysh.kindergarten.dao.model.Child;
import ua.nure.havrysh.kindergarten.dao.model.Group;
import ua.nure.havrysh.kindergarten.dao.model.Human;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KindergartenDAO {


    private static KindergartenDAO instance;

    public synchronized static KindergartenDAO getInstance() {
        if (instance == null) {
            instance = new KindergartenDAO();
        }
        return instance;
    }

    private static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kindergarten_schema", "root", "wipole");
        return connection;
    }

    private static Announcement getAnnouncement(ResultSet rs) throws SQLException {
        return new Announcement(rs.getLong("announcement_id"), rs.getString("title"), rs.getString("content"), rs.getDate("creation_date"), rs.getDate("expires"));
    }

    private static Group getGroup(ResultSet rs) throws SQLException {
        return new Group(rs.getLong("group_id"), rs.getString("name"), rs.getLong("teacher_id"));
    }

    private static Child getChild(ResultSet rs) throws SQLException {
        Child child = new Child(rs.getLong("child_id"), rs.getString("name"), rs.getString("surname"),
                rs.getDate("birth_date"), rs.getLong("group_id"),
                rs.getLong("parent_id"), rs.getString("notice"));
        return child;
    }

    private static Human getHuman(ResultSet rs) throws SQLException {
        Human human = new Human(rs.getLong("human_id"), rs.getString("name"),
                rs.getString("surname"), rs.getString("address"), rs.getString("phone"),
                rs.getString("email"), rs.getLong("role"), rs.getString("login"), rs.getString("password"));
        return human;
    }

    private static void close(Connection connection, Statement statement, ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    public List<Human> getHumans() {
        List<Human> humans = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM humans");
            while (rs.next()) {
                humans.add(getHuman(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement, rs);
        }
        return humans;
    }

    public List<Announcement> getAnnouncements() {
        List<Announcement> announcements = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM announcements");
            while (rs.next()) {
                announcements.add(getAnnouncement(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement, rs);
        }
        return announcements;
    }

    public Announcement getAnnouncement(long id) {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM announcements WHERE announcement_id=" + id);
            rs.next();
            return getAnnouncement(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, statement, rs);
        }
        return null;
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

    public Human getHuman(long id) {

            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            try {
                connection = getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM humans WHERE human_id=" + id);
                rs.next();
                return getHuman(rs);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close(connection, statement, rs);
            }
            return null;

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
}
