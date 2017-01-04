package ua.nure.dao;



import ua.nure.dao.model.Human;

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
public class HumanDAO {
    private static HumanDAO instance;

    public synchronized static HumanDAO get(){
        if(instance == null){
            instance = new HumanDAO();
        }
        return instance;
    }

    private static Human getHuman(ResultSet rs) throws SQLException {
        Human human = new Human(rs.getLong("human_id"), rs.getString("name"),
                rs.getString("surname"), rs.getString("address"), rs.getString("phone"),
                rs.getString("email"), rs.getLong("role"), rs.getString("login"), rs.getString("password"));
        return human;
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
}
