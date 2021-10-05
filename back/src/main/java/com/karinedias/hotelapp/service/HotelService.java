package com.karinedias.hotelapp.service;

import com.karinedias.hotelapp.entity.Hotel;
import com.karinedias.hotelapp.repository.HotelRepository;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Service
public class HotelService {

    private HotelRepository hotelRepo;
    
    public HotelService(HotelRepository hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    public Iterable<Hotel> findAll() {
        return hotelRepo.findAll();
    }

    public Hotel findById(int id) {
        return hotelRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel not found with ID " + id));
    }

    public void add(Hotel client) {
        hotelRepo.save(client);
    }

    public Hotel update(int id, String nom, int etoiles, String adresse, String telephone, String email, String ville) {
        Hotel modifiedHotel = hotelRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel not found with ID " + id));
        modifiedHotel.setNom(nom);
        modifiedHotel.setEtoiles(etoiles);
        modifiedHotel.setAdresse(adresse);
        modifiedHotel.setTelephone(telephone);
        modifiedHotel.setEmail(email);
        modifiedHotel.setVille(ville);
        return hotelRepo.save(modifiedHotel);
    }

    public void delete(int id) {
        hotelRepo.deleteById(id);
    }
}
