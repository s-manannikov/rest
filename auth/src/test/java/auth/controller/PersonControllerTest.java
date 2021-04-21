package auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import auth.AuthApplication;
import auth.model.Person;
import auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = AuthApplication.class)
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenFindAll() throws Exception {
        Person p1 = Person.of("login1", "pass1");
        Person p2 = Person.of("login2", "pass2");
        when(repository.findAll()).thenReturn(List.of(p1, p2));
        mockMvc.perform(get("/person/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].login").value("login1"))
                .andExpect(jsonPath("$[0].password").value("pass1"))
                .andExpect(jsonPath("$[1].login").value("login2"))
                .andExpect(jsonPath("$[1].password").value("pass2"));
    }

    @Test
    public void whenFindById() throws Exception {
        Person person = Person.of("login", "password");
        when(repository.findById(anyInt())).thenReturn(Optional.of(person));
        mockMvc.perform(get("/person/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("login"))
                .andExpect(jsonPath("$.password").value("password"));
    }

    @Test
    public void whenCreate() throws Exception {
        Person person = Person.of("login", "password");
        mockMvc.perform(
                post("/person/")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenUpdate() throws Exception {
        Person person = Person.of("login", "password");
        mockMvc.perform(
                put("/person/")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenDelete() throws Exception {
        mockMvc.perform(delete("/person/0")).andExpect(status().isOk());
        verify(repository).delete(any());
    }
}