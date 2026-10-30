package university.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import university.config.SecurityConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@Import(SecurityConfig.class)
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(roles = "ADMIN")
    void createStudentAsAdmin() throws Exception {
        mockMvc.perform(post("/api/admin/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "firstName": "Ion",
                            "lastName": "Antonescu",
                            "password": "student"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Ion"));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void createStudentAsAdminWithBlankContent() throws Exception {
        mockMvc.perform(post("/api/admin/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "firstName": "",
                            "lastName": "",
                            "password": ""
                        }
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void getProfessorsAsStudents() throws Exception {
        mockMvc.perform(get("/api/admin/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }
}
