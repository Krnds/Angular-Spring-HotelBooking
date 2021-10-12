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
import java.util.stream.StreamSupport;

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

    public Iterable<Reservation> findByClientId(int id) {
        if (id == 0) {
            return reservationRepo.findAll();
        } else {
            return reservationRepo.findByClientId(id);
        }
    }

    public Iterable<Reservation> findByHotelId(int id) {
        if (id == 0) {
            return reservationRepo.findAll();
        } else {
            return reservationRepo.findByHotelId(id);
        }
    }

    private boolean isReservationCorrect(Reservation reservation) {
        return reservation.getClient() != null && reservation.getHotel() != null &&
                reservation.getDateFin().after(reservation.getDateDebut()) && reservation.getNumChambre() >= 0;
    }

    private boolean isReservationUnavailable(Reservation wantedReservation) {
        return StreamSupport.stream(reservationRepo.findByHotelIdAndNumChambre(
                                wantedReservation.getHotel().getId(),
                                wantedReservation.getNumChambre())
                        .spliterator(), false)
                .anyMatch(reservation -> isOverlap(wantedReservation, reservation));
    }

    private boolean isOverlap(Reservation wantedReservation, Reservation otherReservation) {
        return wantedReservation.getDateDebut().before(otherReservation.getDateFin()) &&
                wantedReservation.getDateFin().after(otherReservation.getDateDebut());
    }

    public Reservation add(Reservation reservation) throws InvalidEntityException {
        if (!isReservationCorrect(reservation)) {
            throw new InvalidEntityException("Réservation invalide, veuillez vérifiez tous les champs.");
        }
        if (isReservationUnavailable(reservation)) {
            throw new InvalidEntityException("Réservation invalide : une autre réservation pour cette chambre existe dejà pour ces dates.");
        }
        reservationRepo.save(reservation);
        return reservation;
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
