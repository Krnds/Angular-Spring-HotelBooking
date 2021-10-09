package com.karinedias.hotelapp.service;

import com.karinedias.hotelapp.entity.Client;
import com.karinedias.hotelapp.exceptions.InvalidEntityException;
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
        return clientRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Le client n'a pas été trouvé avec l'ID " + id));
    }

    private boolean isClientCorrect(Client client) {
        return client.getNomComplet().length() >= 2 && client.getTelephone().length() >= 10 &&
                client.getEmail().length() >= 2 && client.getAdresse().length() >= 5;
    }

    public Client add(Client client) throws InvalidEntityException {
        if (!isClientCorrect(client)) {
            throw new InvalidEntityException("Client invalide, vérifiez les champs de la saisie.");
        }
        clientRepo.save(client);
        return client;
    }

    public Client update(int id, String nomComplet, String telephone, String email, String adresse) {
        Client modifiedClient = clientRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Le client n'a pas été trouvé avec l'ID " + id));
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
