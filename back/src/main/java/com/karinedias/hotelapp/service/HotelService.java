package com.karinedias.hotelapp.service;

import com.karinedias.hotelapp.entity.Hotel;
import com.karinedias.hotelapp.exceptions.InvalidEntityException;
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

    private boolean isHotelCorrect(String nom, int etoiles, String adresse, String telephone, String email, String ville) {
        return nom.length() >= 2 && etoiles >= 1 && adresse.length() >= 5 && telephone.length() == 10 &&
                email.length() >= 5 && ville.length() >= 2;
    }

    public Hotel add(String nom, int etoiles, String adresse, String telephone, String email, String ville) throws InvalidEntityException {
        if (!isHotelCorrect(nom, etoiles, adresse, telephone, email, ville)) {
            throw new InvalidEntityException("Invalid hotel, please check all fields.");
        }
        Hotel newHotel = new Hotel();
        newHotel.setNom(nom);
        newHotel.setEtoiles(etoiles);
        newHotel.setAdresse(adresse);
        newHotel.setTelephone(telephone);
        newHotel.setEmail(email);
        newHotel.setVille(ville);
        hotelRepo.save(newHotel);
        return newHotel;
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
