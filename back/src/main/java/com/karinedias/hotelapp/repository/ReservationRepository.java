package com.karinedias.hotelapp.repository;

import com.karinedias.hotelapp.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import java.sql.Date;


public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    Iterable<Reservation> findByClientId(int idClient);
    Iterable<Reservation> findByHotelId(int idHotel);
    Iterable<Reservation> findByHotelIdAndNumChambre(int idHotel, int roomNumber);
//    Iterable<Reservation> findByHotelIdAndNumChambreAndDateFinBefore(int idHotel, int roomNumber, Date dateFin);
}
