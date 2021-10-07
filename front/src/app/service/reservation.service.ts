import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Reservation } from '../classes/reservation';
import { HttpOptions } from '../config';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  loadReservations() : Observable<Reservation[]> {
    return this.http.get<Reservation[]>(environment.apiEndpoint + "resa", HttpOptions);
  }

  addReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(environment.apiEndpoint + "resa", reservation, HttpOptions);
  }

  getReservation(id?: number): Observable<Reservation> {
    return this.http.get<Reservation>(environment.apiEndpoint + "resa/" + id, HttpOptions);
  }

  editReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.put<Reservation>(environment.apiEndpoint + "resa/" + reservation.id, reservation, HttpOptions);
  }

  deleteReservation(id?: number): Observable<any> {
    return this.http.delete<Reservation>(environment.apiEndpoint + "resa/" + id, HttpOptions);
  }
}
