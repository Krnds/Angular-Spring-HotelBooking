import { Component, OnInit, ViewChild } from '@angular/core';
import { Reservation } from '../classes/reservation';
import { ReservationService } from '../service/reservation.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  newReservation: Reservation = new Reservation();

  reservations: Array<Reservation> = [];
  @ViewChild('closebutton') closebuttonelement: any;
  success: boolean = false;
  error: boolean = false;

  constructor(private reservationService : ReservationService) { }

  ngOnInit(): void {
    this.loadReservations();
  }

  loadReservations(): void {
    this.reservationService.loadReservations().subscribe(
      data => {
        this.reservations = data;
        console.log(data);
      }
    );
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
      })
    } else {
      this.reservationService.editReservation(this.newReservation).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadReservations();
        this.success = true;
      })
    }

  }

}
