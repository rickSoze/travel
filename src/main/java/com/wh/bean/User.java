package com.wh.bean;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
}
