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


  //TODO: add loadClients with search bar
  // loadClients(search: String): Observable<Client[]> {
  //   let searchCondition = ""
  //   if (search.length > 0) {
  //     searchCondition = "?search=" + search;
  //   }
  //   console.log("recherche des clients");
  //   return this.http.get<Client[]>(environment.apiEndpoint + "client" + searchCondition, HttpOptions);
  // }

  loadClients() : Observable<Client[]> {
    return this.http.get<Client[]>(environment.apiEndpoint + "client", HttpOptions);
  }

  addClient(client: Client): Observable<Client> {
    return this.http.post<Client>(environment.apiEndpoint + "client", client, HttpOptions);
  }

  getClient(id?: number): Observable<Client> {
    return this.http.get<Client>(environment.apiEndpoint + "client/" + id, HttpOptions);
  }

  editClient(client: Client): Observable<Client> {
    return this.http.put<Client>(environment.apiEndpoint + "client/" + client.id, client, HttpOptions);
  }

  deleteClient(id?: number): Observable<any> {
    console.log("service delete client");

    return this.http.delete<Client>(environment.apiEndpoint + "client/" + id, HttpOptions);
  }
}

