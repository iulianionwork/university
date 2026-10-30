package university.service.component;

import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class StudentCodeGenerator {

    public String generate(long studentId){
        return "STU-" + Year.now().getValue() + "-" + studentId;
    }
}
