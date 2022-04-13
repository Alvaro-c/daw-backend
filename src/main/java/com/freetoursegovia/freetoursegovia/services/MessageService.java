package com.freetoursegovia.freetoursegovia.services;

import com.freetoursegovia.freetoursegovia.model.Message;
import com.freetoursegovia.freetoursegovia.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public List<Message> findAllMessages(){
        return messageRepository.findAll();
    }

    public Message saveMessage(Message message){
        return messageRepository.save(message);
    }

    public Message findMessageById(int id) { return messageRepository.findById(id).orElseThrow(); }

    public List<Message> findMessageByUserId(int id) { return messageRepository.findMessageByUser_Id(id); }

    public void deleteMessage(int id) { messageRepository.deleteById(id);}


}
