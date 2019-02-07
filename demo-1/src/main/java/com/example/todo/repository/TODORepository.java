package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.todo.entity.TODO;
@RepositoryRestResource
public interface TODORepository extends JpaRepository<TODO, Long>{

}
