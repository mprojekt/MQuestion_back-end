package m.mquestion.controllers;

import java.util.List;
import m.mquestion.repositories.TypeDao;
import m.mquestion.entities.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    
    @Autowired
    private TypeDao typeDao;
    
    @RequestMapping("/")
    public String message() { 
        String msg = "Hello:player";
        return msg;
    }
    
    @RequestMapping("/all")
    public List<Type> getAllTypes() { 
        return (List<Type>) this.typeDao.findAll();
    }

}
