package com.conveniencestore.conveniencestore.services;

import com.conveniencestore.conveniencestore.domain.users.User;
import com.conveniencestore.conveniencestore.domain.users.UserDTO;
import com.conveniencestore.conveniencestore.domain.users.UserRoles;
import com.conveniencestore.conveniencestore.protobuf.UserDto;
import com.conveniencestore.conveniencestore.protobuf.UserRolesOuterClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class SecurityServiceTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetUsers() throws Exception{
        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testPostUsers() throws Exception{
        UserDto.UserDTO.newBuilder().setUsername("u1").setEmail("u1@email.com")
                .setPassword("ajsdklasjdk").setRole(UserRolesOuterClass.UserRoles.EMPLOYEE).build();
        User user = new User(UserDto.UserDTO.newBuilder().setUsername("u1").setEmail("u1@email.com")
                .setPassword("ajsdklasjdk").setRole(UserRolesOuterClass.UserRoles.EMPLOYEE).build());
        String json = this.mapper.writeValueAsString(user);
        mockMvc.perform(post("/users")
                .content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
