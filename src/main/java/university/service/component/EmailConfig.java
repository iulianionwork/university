package university.service.component;

import org.springframework.stereotype.Component;

@Component
public class EmailConfig {

    public String emailConstruct(String firstName, String lastName, long id, String domain){
        String cleanName = (firstName + "." +lastName).toLowerCase().replaceAll("[^a-z.]","");
        return cleanName + id + domain;
    }
}
