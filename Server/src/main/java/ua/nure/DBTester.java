package ua.nure;

import ua.nure.dao.MarkDAO;
import ua.nure.dao.model.Mark;

import java.time.Instant;
import java.sql.Date;

/**
 * Created by Oleg on 08.12.2016.
 */
public class DBTester {
    public  void main(String[] args) {
        //System.out.println(MarkDAO.get().getMarks(1));
        System.out.println(MarkDAO.get().insert(new Mark(0,  Date.valueOf("2016-08-12"), "com", 2, 2, 2, 1)));
    }
}
