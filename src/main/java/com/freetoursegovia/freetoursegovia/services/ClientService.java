package com.freetoursegovia.freetoursegovia.services;

import com.freetoursegovia.freetoursegovia.model.Client;
import com.freetoursegovia.freetoursegovia.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to work with JPA Library and to be able to inject the dependency in other classes
 *
 */
@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Integer id){
        return clientRepository.findById(id);
    }

    public Client saveClient(Client client) { return clientRepository.save(client); }

    public Client editClient(Client newClient){

        Integer id = newClient.getClientId();

        if(clientRepository.findById(id).isPresent()){

            Client client = new Client();
            client.setClientId(newClient.getClientId());
            client.setName(newClient.getName());
            client.setSurname(newClient.getSurname());
            client.setEmail(newClient.getEmail());
            client.setPhone(newClient.getPhone());
            client.setAdults(newClient.getAdults());
            client.setChildren(newClient.getChildren());
            client.setComment(newClient.getComment());
            client.setVisitDate(newClient.getVisitDate());
            client.setTour(newClient.getTour());
            clientRepository.save(client);
            return client;
        }
        return newClient;
    }
}
