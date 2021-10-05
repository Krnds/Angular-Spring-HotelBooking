package com.karinedias.hotelapp.repository;

import com.karinedias.hotelapp.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}
