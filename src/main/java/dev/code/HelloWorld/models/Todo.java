package dev.code.HelloWorld.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;


@Entity
@Data
public class Todo {
    public Todo() {
    }

    @Id
    @GeneratedValue
     Long id;
    @NotNull
     @NotBlank
            @Schema(name = "title",example = "Complete Spring Boot")
     String title;
    String Description;
     Boolean isCompleted;

}
