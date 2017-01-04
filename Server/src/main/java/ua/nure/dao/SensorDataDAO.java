package ua.nure.dao;

import ua.nure.dao.model.SensorData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Oleg on 06.12.2016.
 */
public class SensorDataDAO {
    private static SensorDataDAO instance;

    public synchronized static SensorDataDAO get() {
        if (instance == null) {
            instance = new SensorDataDAO();
        }
        return instance;
    }

    private static final String SQL_SAVE_SENSOR_DATA = "insert into sensor_data values(default, ?, ?)";

    public boolean save(SensorData data) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = DAOUtils.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SAVE_SENSOR_DATA);
            int k = 1;
            preparedStatement.setLong(k++, data.getChild_id());
            preparedStatement.setLong(k++, data.getActivity());
            preparedStatement.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(connection, preparedStatement, null);
        }
        return result;
    }


    private SensorData getSensorData(ResultSet rs) throws SQLException {
        return new SensorData(rs.getLong("data_id"), rs.getLong("child_id"), rs.getInt("activity"));
    }
}
