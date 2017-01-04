package ua.nure.dao;


import ua.nure.dao.model.Announcement;

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
public class AnnouncementDAO {
    private static AnnouncementDAO instance;

    public synchronized static AnnouncementDAO get() {
        if (instance == null) {
            instance = new AnnouncementDAO();
        }
        return instance;
    }

    private static Announcement getAnnouncement(ResultSet rs) throws SQLException {
        return new Announcement(rs.getLong("announcement_id"), rs.getString("title"), rs.getString("content"), rs.getDate("creation_date"), rs.getDate("expires"));
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
}
