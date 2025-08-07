package dev.code.HelloWorld.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.code.HelloWorld.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
