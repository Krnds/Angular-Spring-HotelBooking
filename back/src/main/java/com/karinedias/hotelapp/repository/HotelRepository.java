package com.karinedias.hotelapp.repository;

import com.karinedias.hotelapp.entity.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {
}
