package com.karinedias.hotelapp.repository;

import com.karinedias.hotelapp.entity.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

    Admin findByUsername(String username);
}
