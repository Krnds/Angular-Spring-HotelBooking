import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Client } from '../classes/client';
import { Hotel } from '../classes/hotel';
import { Reservation } from '../classes/reservation';
import { ClientService } from '../service/client.service';
import { HotelService } from '../service/hotel.service';
import { ReservationService } from '../service/reservation.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  newReservation: Reservation = new Reservation();
  clients: Array<Client> = [];
  hotels: Array<Hotel> = [];

  //TODO: test
  newReservationSearch: Reservation = new Reservation();

  reservations: Array<Reservation> = [];
  @ViewChild('closebutton') closebuttonelement: any;
  success: boolean = false;
  error: boolean = false;
  errMessage: string = "";

  constructor(private reservationService: ReservationService, private clientService: ClientService, private hotelService: HotelService,
    private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.loadClients();
    this.loadHotels();
    this.loadReservations();
  }

  private getTodayDate(): string | null {
    const todayDate: Date = new Date();
    return this.datePipe.transform(todayDate, 'yyyy-MM-dd');
  }

  private getTomorrowDate(): string | null {
    const tomorrowDate = new Date(new Date().setDate(new Date().getDate() + 1));
    return this.datePipe.transform(tomorrowDate, 'yyyy-MM-dd');
  }

  loadReservations(): void {
    this.reservationService.loadReservations().subscribe(
      data => {
        this.reservations = data;
      }
    );
  }

  loadClients(): void {
    this.clientService.loadClients().subscribe(data => {
      this.clients = data;
    })
  }

  loadReservationsByClient(id?: number): void {
    this.reservationService.loadReservationsByClient(id).subscribe(data => {
      this.reservations = data;
    })
  }

  loadHotels(): void {
    this.hotelService.loadHotels().subscribe(data => {
      this.hotels = data;
    })
  }

  loadReservationsByHotel(id?: number): void {
    this.reservationService.loadReservationsByHotel(id).subscribe(data => {
      this.reservations = data;
    })
  }

  editReservation(id?: number): void {
    this.reservationService.getReservation(id).subscribe(data => {
      this.newReservation = data;
      this.loadReservations();
    })
  }

  deleteReservation(id?: number): void {
    if (confirm("Voulez-vous vraiment supprimer cette réservation ?")) {
      this.reservationService.deleteReservation(id).subscribe(data => {
        this.loadReservations();
        this.success = true;
      },
        error => {
          this.success = false;
          this.error = true;
        });
    }
  }

  submitForm(): void {
    if (this.newReservation.id == undefined) {
      this.reservationService.addReservation(this.newReservation).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadReservations();
        this.success = true;
        this.resetForm();
      },
        error => {
          this.error = true;
          this.errMessage = error.error.message;
        })
    } else {
      this.reservationService.editReservation(this.newReservation).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadReservations();
        this.success = true;
        this.resetForm();
      },
        error => {
          this.error = true;
          this.errMessage = error.error.message;
        })
    }

  }

  private resetForm(): void {
    this.success = false;
    this.error = false;
    this.newReservation = new Reservation();

  }

  checkHotel(h1: Hotel, h2: Hotel): boolean {
    return h1 != undefined && h2 != undefined && h1.id == h2.id;
  }

  checkClient(c1: Client, c2: Client): boolean {
    return c1 != undefined && c2 != undefined && c1.id == c2.id;
  }

}
