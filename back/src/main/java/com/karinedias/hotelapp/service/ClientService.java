package com.karinedias.hotelapp.service;

import com.karinedias.hotelapp.entity.Client;
import com.karinedias.hotelapp.repository.ClientRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ClientService {

    private final ClientRepository clientRepo;

    public ClientService(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Iterable<Client> findAll() {
        return clientRepo.findAll();
    }

    public Client findById(int id) {
        return clientRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with ID " + id));
    }

    public void add(Client client) {
        clientRepo.save(client);
    }

    public Client update(int id, String nomComplet, String telephone, String email, String adresse) {
        Client modifiedClient = clientRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with ID " + id));
        modifiedClient.setNomComplet(nomComplet);
        modifiedClient.setTelephone(telephone);
        modifiedClient.setEmail(email);
        modifiedClient.setAdresse(adresse);
        return clientRepo.save(modifiedClient);
    }

    public void delete(int id) {
        clientRepo.deleteById(id);
    }
}
