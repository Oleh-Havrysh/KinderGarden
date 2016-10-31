package ua.nure.havrysh;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.havrysh.model.Child;

import java.util.Calendar;

/**
 * Created by Oleg on 29.10.2016.
 */
@RestController
@RequestMapping("/api")
public class Controller {
    @RequestMapping("/children")
    public Child get()
    {
        return new Child("Nastya", Calendar.getInstance());
    }
}
