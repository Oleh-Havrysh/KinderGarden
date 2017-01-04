package ua.nure.dao.model;

/**
 * Created by Oleg on 06.12.2016.
 */
public class SensorData {
    private long data_id;
    private long child_id;
    private int activity;

    public SensorData(long data_id, long child_id, int activity) {
        this.data_id = data_id;
        this.child_id = child_id;
        this.activity = activity;
    }

    public SensorData() {
    }

    public long getData_id() {
        return data_id;
    }

    public void setData_id(long data_id) {
        this.data_id = data_id;
    }

    public long getChild_id() {
        return child_id;
    }

    public void setChild_id(long child_id) {
        this.child_id = child_id;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }
}
