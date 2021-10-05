package com.karinedias.hotelapp.service;

import com.karinedias.hotelapp.entity.Client;
import com.karinedias.hotelapp.entity.Hotel;
import com.karinedias.hotelapp.entity.Reservation;
import com.karinedias.hotelapp.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;

@Service
public class ReservationService {

    ReservationRepository reservationRepo;

    public ReservationService(ReservationRepository reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public Iterable<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    public Reservation findById(int id) {
        return reservationRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found with ID " + id));
    }

    public void add(Reservation client) {
        reservationRepo.save(client);
    }

    public Reservation update(int id, Client client, Hotel hotel, Timestamp dateDebut, Timestamp dateFin, int numChambre) {
        Reservation modifiedReservation = reservationRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found with ID " + id));
        modifiedReservation.setClient(client);
        modifiedReservation.setHotel(hotel);
        modifiedReservation.setDateDebut(dateDebut);
        modifiedReservation.setDateFin(dateFin);
        modifiedReservation.setNumChambre(numChambre);
        return reservationRepo.save(modifiedReservation);
    }

    public void delete(int id) {
        reservationRepo.deleteById(id);
    }
}
