package com.karinedias.hotelapp.service;

import com.karinedias.hotelapp.entity.Client;
import com.karinedias.hotelapp.entity.Hotel;
import com.karinedias.hotelapp.entity.Reservation;
import com.karinedias.hotelapp.exceptions.InvalidEntityException;
import com.karinedias.hotelapp.repository.DateFinReservation;
import com.karinedias.hotelapp.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
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

    public Iterable<Reservation> findByHotelId(int id) {
        if (id == 0) {
            return reservationRepo.findAll();
        } else {
            System.out.println("Id de l'hôtel à rechercher = " + id);
            List<Reservation> resaTrouvees = reservationRepo.findById(id).stream().toList();
            for (Reservation r : resaTrouvees) {
                System.out.println(r.toString());
            }
            return reservationRepo.findByHotelId(id);
        }
    }

    private boolean isReservationCorrect(Client client, Hotel hotel, Date debut, Date fin, int numChambre) {
        return client != null && hotel != null && fin.after(debut) && numChambre >= 0;
    }

    //TODO: faire une méthode qui check qu'il n'y a pas une autre réservation de la même chambre en cours avec la date souhaitée
    private boolean isRoomNumberFree(int idHotel, int roomNumber, Date dateFin) {

        List<Reservation> reservationTrouvees = new ArrayList<>();
        reservationRepo.findByHotelIdAndNumChambreAndDateFinBefore(idHotel, roomNumber, dateFin).forEach(reservationTrouvees::add);

        //TODO: utiliser méthode qui renvoie DateFinReservation
//        List<DateFinReservation> datesFinChambre = reservationRepo.findByHotelIdAndNumChambre(idHotel, roomNumber);
//        for (DateFinReservation d: datesFinChambre) {
//            Date a = d.getDateFin();
//            datesFinReservationsChambre.add(a);
//        }
//        datesFinReservationsChambre = reservationRepo.findByHotelIdAndNumChambre(idHotel, roomNumber).forEach().map(dates -> dates.getDateFin()).collect(Collectors.toList());
//    stream().map(dates -> dates.getDateFin()).collect(Collectors.toList());
//                forEach(datesFinReservationsChambre::add);

//        for (Date finResa : datesFinReservationsChambre) {
//            if (finResa.compareTo(dateFin) > 0) {
//                System.out.println(finResa.toDate() + " est après la date de fin de résa voulue...");
//                return false;
//            }
//        }


        if (reservationTrouvees.size() != 0) {
            return false;
        } else {
            return true;
        }
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
        if (isRoomNumberFree(hotel.getId(), numChambre, fin)) {
            throw new InvalidEntityException("Réservation invalide : une autre réservation pour cette chambre existe dejà pour ces dates.");
        }
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
