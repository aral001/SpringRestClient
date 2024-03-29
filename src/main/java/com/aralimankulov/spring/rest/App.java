package com.aralimankulov.spring.rest;

import com.aralimankulov.spring.rest.configuration.MyConfig;
import com.aralimankulov.spring.rest.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class App {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        ResponseEntity<List<User>> responseEntity = communication.getAllUsers();
        System.out.println(responseEntity);

        List<String> cookies = responseEntity.getHeaders().get("Set-Cookie");
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.COOKIE, cookies);

        User user = new User("James", "Brown", (byte) 22);
        user.setId(3L);
        String code = "";
        code += communication.addUser(user, headers);

        user.setName("Thomas");
        user.setLastName("Shelby");
        code += communication.editUser(user, headers);

        code += communication.deleteUser(3L, headers);
        System.out.println(code);


    }
}
