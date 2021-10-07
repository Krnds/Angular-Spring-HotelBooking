package com.karinedias.hotelapp.repository;

import com.karinedias.hotelapp.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

//    @Query("FROM reservation r WHERE r.client.id = : idClient")
    Iterable<Reservation> findByClientId(int idClient);
    Iterable<Reservation> findByHotelId(int idHotel);
    Iterable<Reservation> findByNumChambre(int numChambre);

}
