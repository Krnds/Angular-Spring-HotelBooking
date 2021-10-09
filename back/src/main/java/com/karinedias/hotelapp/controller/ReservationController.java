package com.karinedias.hotelapp.controller;

import com.karinedias.hotelapp.entity.Reservation;
import com.karinedias.hotelapp.exceptions.InvalidEntityException;
import com.karinedias.hotelapp.service.ReservationService;
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
@RequestMapping("/api/resa")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    //TODO: uniquement si terme(s) de recherche
//    @GetMapping(path="", produces="application/json")
//    public Iterable<Reservation> getAll(HttpServletRequest request) {
//        System.out.println("Valeur recherchée : " + request.getParameter("search"));
//        return reservationService.findAll(request.getParameter("search"));
//    }

    @GetMapping(path = "", produces = "application/json")
    public Iterable<Reservation> getAll() {
        return reservationService.findAll();
    }

    //TODO: remplacer if/else par switch case ?
    @GetMapping(path = "/search", produces = "application/json")
    public Iterable<Reservation> getReservationsByClientOrHotel(HttpServletRequest request) {
        if  (request.getParameter("client") != null) {
            return reservationService.findByClientId(Integer.parseInt(request.getParameter("client")));
        } else if (request.getParameter("hotel") != null){
            return reservationService.findByHotelId(Integer.parseInt(request.getParameter("hotel")));
        } else {
            return reservationService.findAll();
        }
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Reservation> get(@PathVariable(value = "id") int id) {
        try {
            Reservation reservationFound = reservationService.findById(id);
            return ResponseEntity.ok().body(reservationFound);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }


    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Reservation> add(@RequestBody Reservation reservation) {
        try {
            Reservation newReservation = reservationService.add(reservation.getClient(), reservation.getHotel()
                    , reservation.getDateDebut(), reservation.getDateFin(), reservation.getNumChambre());

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newReservation.getId())
                    .toUri();
            return ResponseEntity.created(uri)
                    .body(newReservation);

        } catch (Exception | InvalidEntityException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Reservation> edit(@RequestBody Reservation reservation, @PathVariable("id") int id) {
        try {
            Reservation updatedReservation = reservationService.update(id,
                    reservation.getClient(), reservation.getHotel(), reservation.getDateDebut(), reservation.getDateFin(), reservation.getNumChambre());
            return ResponseEntity.ok()
                    .body(updatedReservation);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        try {
            reservationService.delete(id);
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
