import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpOptions } from '../config';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  authenticate(admin: any) {
    return this.http.post<any>(environment.apiEndpoint + "login", admin, HttpOptions);
  }

  logout(admin: any) {
    return this.http.get(environment.apiEndpoint + "logout", HttpOptions);
  }
}