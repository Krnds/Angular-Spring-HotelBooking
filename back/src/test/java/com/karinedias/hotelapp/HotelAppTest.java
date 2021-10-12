package com.karinedias.hotelapp;

import com.karinedias.hotelapp.entity.Client;
import com.karinedias.hotelapp.entity.Hotel;
import com.karinedias.hotelapp.entity.Reservation;
import com.karinedias.hotelapp.exceptions.InvalidEntityException;
import com.karinedias.hotelapp.repository.ReservationRepository;
import com.karinedias.hotelapp.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

class HotelAppTest {

    private ReservationService service;
    private ReservationRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ReservationRepository.class);
        MockitoAnnotations.openMocks(this);
        service = new ReservationService(repository);
    }

    @Test
    void shouldDetectOverlapWhenAddingNewReservation() throws ParseException {
        // Given
        var hotelId = 1;
        var numChambre = 100;
        Reservation wantedReservation = buildReservation(hotelId, numChambre, "2021-10-09", "2021-10-20");
        Reservation existingReservation = buildReservation(hotelId, numChambre, "2021-10-08", "2021-10-15");

        given(repository.findByHotelIdAndNumChambre(anyInt(), anyInt()))
                .willReturn(Collections.singletonList(existingReservation));

        // When/Then
        assertThatThrownBy(() -> service.add(wantedReservation)).isInstanceOf(InvalidEntityException.class);
    }

    @Test
    void shouldNotDetectFalseOverlapWhenAddingNewReservation() throws ParseException {
        // Given
        var hotelId = 5;
        var numChambre = 92;
        Reservation wantedReservation = buildReservation(hotelId, numChambre, "2021-10-09", "2021-10-20");
        Reservation existingReservation = buildReservation(hotelId, numChambre, "2021-10-05", "2021-10-08");

        given(repository.findByHotelIdAndNumChambre(anyInt(), anyInt()))
                .willReturn(Collections.singletonList(existingReservation));


        // When/Then
        assertThatCode(() -> service.add(wantedReservation)).doesNotThrowAnyException();
    }

    private java.sql.Date parseDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(date);
        return new java.sql.Date(parsed.getTime());
    }

    private Reservation buildReservation(int hotelId, int numChambre, String dateDebut, String dateFin)
            throws ParseException {
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        Reservation reservation = new Reservation();
        reservation.setNumChambre(numChambre);
        reservation.setDateDebut(parseDate(dateDebut));
        reservation.setDateFin(parseDate(dateFin));
        reservation.setHotel(hotel);
        reservation.setClient(new Client());
        return reservation;
    }

}
