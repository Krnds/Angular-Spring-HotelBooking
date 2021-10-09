package com.karinedias.hotelapp.controller;

import com.karinedias.hotelapp.entity.Hotel;
import com.karinedias.hotelapp.exceptions.InvalidEntityException;
import com.karinedias.hotelapp.service.HotelService;
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
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    //TODO: uniquement si terme(s) de recherche
//    @GetMapping(path="", produces="application/json")
//    public Iterable<Hotel> getAll(HttpServletRequest request) {
//        System.out.println("Valeur recherchée : " + request.getParameter("search"));
//        return hotelService.findAll(request.getParameter("search"));
//    }

    @GetMapping(path = "", produces = "application/json")
    public Iterable<Hotel> getAll(HttpServletRequest request) {
        return hotelService.findAll();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Hotel> get(@PathVariable(value = "id") int id) {
        try {
            Hotel hotelFound = hotelService.findById(id);
            return ResponseEntity.ok().body(hotelFound);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }


    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Hotel> add(@RequestBody Hotel hotel) {
        try {
            Hotel newClient = hotelService.add(hotel);

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
    public ResponseEntity<Hotel> edit(@RequestBody Hotel hotel, @PathVariable("id") int id) {
        try {
            Hotel updatedPatient = hotelService.update(id,
                    hotel.getNom(), hotel.getEtoiles(), hotel.getAdresse(), hotel.getTelephone(), hotel.getEmail(), hotel.getVille());
            return ResponseEntity.ok()
                    .body(updatedPatient);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        try {
            hotelService.delete(id);
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
