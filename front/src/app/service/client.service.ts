import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Client } from '../classes/client';
import { HttpOptions } from '../config';

@Injectable({
  providedIn: 'root' // utilisable depuis n'importe quel composant de l'app
})

export class ClientService {

  constructor(private http: HttpClient) { }

  loadPatients(search: String): Observable<client[]> {
    let searchCondition = ""
    if (search.length > 0) {
      searchCondition = "?search=" + search;
    }
    console.log("recherche des clients");
    return this.http.get<Client[]>(environment.baseUrl + "client" + searchCondition, HttpOptions);
  }

  addPatient(client: Client): Observable<Client> {
    return this.http.post<Client>(environment.baseUrl + "client", client, HttpOptions);
  }

  getPatient(id?: number): Observable<Client> {
    return this.http.get<Client>(environment.baseUrl + "client/" + id, HttpOptions);
  }

  editPatient(client: Client): Observable<Client> {
    return this.http.put<Client>(environment.baseUrl + "client/" + client.id, client, HttpOptions);
  }

  deletePatient(id?: number): Observable<any> {
    console.log("service delete client");

    return this.http.delete<client>(environment.baseUrl + "client/" + id, HttpOptions);
  }
}

