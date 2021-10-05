package com.karinedias.hotelapp.repository;

import com.karinedias.hotelapp.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
}
