package ua.nure.easygo;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Oleg on 29.10.2016.
 */
@RestController
@RequestMapping("/api")
public class Controller {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String save()
    {

        return "OK";
    }
}
