package com.karinedias.hotelapp.service;

import com.karinedias.hotelapp.entity.Client;
import com.karinedias.hotelapp.entity.Hotel;
import com.karinedias.hotelapp.entity.Reservation;
import com.karinedias.hotelapp.exceptions.InvalidEntityException;
import com.karinedias.hotelapp.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.List;

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

    //TODO: essayer de chercher une réservation selon le client
    public Iterable<Reservation> findByClientId(int id) {
        if (id == 0) {
            return reservationRepo.findAll();
        } else {
            System.out.println("Id du client à rechercher = " + id);
            List<Reservation> resaTrouvees = reservationRepo.findById(id).stream().toList();
            for (Reservation r : resaTrouvees) {
                System.out.println(r.toString());
            }
            return reservationRepo.findByClientId(id);
        }
    }

    private boolean isReservationCorrect(Client client, Hotel hotel, Date debut, Date fin, int numChambre) {
        return client != null && hotel != null && fin.after(debut) && numChambre >= 0;
    }

    //TODO: faire une méthode qui check qu'il n'y a pas une autre réservation de la même chambre en cours avec la date souhaitée
    private boolean isRoomNumberFree(int roomNumber, Client client) {
        return true;
    }

    public Reservation add(Client client, Hotel hotel, Date debut, Date fin, int numChambre) throws InvalidEntityException {
        if (!isReservationCorrect(client, hotel, debut, fin, numChambre)) {
            throw new InvalidEntityException("Invalid Reservation, please check all fields.");
        }
        Reservation newReservation = new Reservation();
        newReservation.setClient(client);
        newReservation.setHotel(hotel);
        newReservation.setDateDebut(debut);
        newReservation.setDateFin(fin);
        newReservation.setNumChambre(numChambre);
        reservationRepo.save(newReservation);
        return newReservation;
    }

    public Reservation update(int id, Client client, Hotel hotel, Date dateDebut, Date dateFin, int numChambre) {
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
