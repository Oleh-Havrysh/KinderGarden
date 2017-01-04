package ua.nure.dao;

import ua.nure.dao.model.Mark;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oleg on 05.12.2016.
 */
public class MarkDAO {
    private static MarkDAO instance;

    public synchronized static MarkDAO get() {
        if (instance == null) {
            instance = new MarkDAO();
        }
        return instance;
    }

    public Mark insert(Mark mark) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = DAOUtils.getConnection();
            stmt = connection.prepareStatement("INSERT INTO marks VALUES (default, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            stmt.setDate(k++, mark.getDate());
            stmt.setString(k++, mark.getComment());
            stmt.setInt(k++, mark.getBehaviour());
            stmt.setInt(k++, mark.getSleeping());
            stmt.setInt(k++, mark.getEating());
            stmt.setLong(k++, mark.getChild_id());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                mark.setMark_id(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(connection, stmt, rs);
        }
        return mark;
    }

    public List<Mark> getMarks(long childId) {
        List<Mark> marks = new LinkedList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = DAOUtils.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM marks where child_id=?");
            stmt.setLong(1, childId);

            rs = stmt.executeQuery();

            while (rs.next()) {
                marks.add(getMark(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(connection, stmt, rs);
        }

        return marks;
    }

    public Mark getMark(long markId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
            connection = DAOUtils.getConnection();
            ps = connection.prepareStatement("select * from marks where mark_id=?");
            ps.setLong(1, markId);
            rs = ps.executeQuery();
            rs.next();
            return getMark(rs);

        } catch (SQLException e) {


        } finally {
            DAOUtils.close(connection, ps, rs);
        }
        return null;
    }

    private Mark getMark(ResultSet rs) throws SQLException {
        return new Mark(rs.getLong("mark_id"), rs.getDate("date"), rs.getString("comment"), rs.getInt("behaviour"), rs.getInt("sleeping"), rs.getInt("eating"), rs.getLong("child_id"));
    }


}
