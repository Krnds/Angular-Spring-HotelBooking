import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Hotel } from '../classes/hotel';
import { HttpOptions } from '../config';

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  constructor(private http: HttpClient) { }

  loadHotels() : Observable<Hotel[]> {
    return this.http.get<Hotel[]>(environment.apiEndpoint + "hotel", HttpOptions);
  }

  addHotel(hotel: Hotel): Observable<Hotel> {
    return this.http.post<Hotel>(environment.apiEndpoint + "hotel", hotel, HttpOptions);
  }

  getHotel(id?: number): Observable<Hotel> {
    return this.http.get<Hotel>(environment.apiEndpoint + "hotel/" + id, HttpOptions);
  }

  editHotel(hotel: Hotel): Observable<Hotel> {
    return this.http.put<Hotel>(environment.apiEndpoint + "hotel/" + hotel.id, hotel, HttpOptions);
  }

  deleteHotel(id?: number): Observable<any> {
    return this.http.delete<Hotel>(environment.apiEndpoint + "hotel/" + id, HttpOptions);
  }
}
