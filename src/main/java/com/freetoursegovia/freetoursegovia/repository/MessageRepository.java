package com.freetoursegovia.freetoursegovia.repository;

import com.freetoursegovia.freetoursegovia.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findMessageByUser_Id(int id);
}
