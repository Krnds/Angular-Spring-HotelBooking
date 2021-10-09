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

  reservations: Array<Reservation> = [];
  @ViewChild('closebutton') closebuttonelement: any;
  success: boolean = false;
  error: boolean = false;
  errMessage : string = "";
  @Input() todayDate:Date = new Date();

  constructor(private reservationService : ReservationService, private clientService : ClientService, private hotelService : HotelService,
    private datePipe : DatePipe) { }

  ngOnInit(): void {
    this.transformDate();
    console.log(this.transformDate());
    this.loadClients();
    this.loadHotels();
    this.loadReservations();
  }

  transformDate() : string | null {
    return this.datePipe.transform(this.todayDate, 'dd-MM-YYYY');
  }

  loadReservations(): void {
    this.reservationService.loadReservations().subscribe(
      data => {
        this.reservations = data;
        console.log(data);
      }
    );
  }

  loadClients(): void {
    console.log("in load clients");
    this.clientService.loadClients().subscribe(data => {
      this.clients = data;
      console.log(data);
    })
  }

  loadReservationsByClient(id?: number) : void {
    this.reservationService.loadReservationsByClient(id).subscribe(data => {
      this.reservations = data;
      console.log(data);
    })
  }

  loadHotels(): void {
    console.log("in load hotels");
    this.hotelService.loadHotels().subscribe(data => {
      this.hotels = data;
      console.log(data);
    })
  }

  loadReservationsByHotel(id?: number) : void {
    this.reservationService.loadReservationsByHotel(id).subscribe(data => {
      this.reservations = data;
      console.log(data);
    })
  }

  editReservation(id?: number): void {
    this.reservationService.getReservation(id).subscribe(data => {
      this.newReservation = data;
      this.loadReservations();
      // this.success = true;
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
      },
      error => {
      this.error = true;
      this.errMessage = error.error.message;
      })
    }

  }

  checkHotel( h1 : Hotel , h2 : Hotel ): boolean{
    return h1 != undefined && h2 != undefined && h1.id == h2.id;
  }

}
