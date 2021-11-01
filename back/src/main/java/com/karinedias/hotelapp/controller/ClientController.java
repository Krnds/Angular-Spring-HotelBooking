package com.karinedias.hotelapp.controller;

import com.karinedias.hotelapp.entity.Client;
import com.karinedias.hotelapp.exceptions.InvalidEntityException;
import com.karinedias.hotelapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(path = "", produces = "application/json")
    public Iterable<Client> getAll(HttpServletRequest request) {
        return clientService.findAll();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Client> get(@PathVariable(value = "id") int id) {
        try {
            Client clientFound = clientService.findById(id);
            return ResponseEntity.ok().body(clientFound);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }


    @PostMapping(produces = "application/json")
    public ResponseEntity<Client> add(@RequestBody Client client) {
        try {
            Client newClient = clientService.add(client);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newClient.getId())
                    .toUri();
            return ResponseEntity.created(uri)
                    .body(newClient);

        } catch (Exception | InvalidEntityException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Client> edit(@RequestBody Client client, @PathVariable("id") int id) {
        try {
            Client updatedPatient = clientService.update(id,
                    client.getNomComplet(), client.getTelephone(), client.getEmail(), client.getAdresse());
            return ResponseEntity.ok()
                    .body(updatedPatient);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        try {
            clientService.delete(id);
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
