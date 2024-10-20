package io.github.bergdeveloper.todolist_api.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Role {
    ADMIN("admin"),
    USER("user");

    private String role;

    Role(String role){
        this.role = role;
    }
}
