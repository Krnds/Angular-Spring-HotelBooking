import { Component, OnInit, ViewChild } from '@angular/core';
import { Client } from '../classes/client';
import { ClientService } from '../service/client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  newClient: Client = new Client();

  clients: Array<Client> = [];
  @ViewChild('closebutton') closebuttonelement: any;
  success: boolean = false;
  error: boolean = false;
  // currentVille: string | undefined;
  // search: String = "";

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.loadClients();
  }

  //TODO: to implement when search bar
  // loadClients(): void {
  //   this.clientService.loadClients(this.search).subscribe(
  //     data => {
  //       this.clients = data;
  //       console.log(data);
  //     }
  //   );
  // }

    loadClients(): void {
    this.clientService.loadClients().subscribe(
      data => {
        this.clients = data;
        console.log(data);
      }
    );
  }

  editClient(id?: number): void {
    this.clientService.getClient(id).subscribe(data => {
      this.newClient = data;
      this.loadClients();
      this.success = true;
    })
  }

  deleteClient(id?: number): void {
    if (confirm("Voulez-vous vraiment supprimer ce client ?")) {
      this.clientService.deleteClient(id).subscribe(data => {
        this.loadClients();
        this.success = true;
      },
        error => {
          this.success = false;
          this.error = true;
        });
    }
  }

  submitForm(): void {
    if (this.newClient.id == undefined) {
      this.clientService.addClient(this.newClient).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadClients();
        this.success = true;
      })
    } else {
      this.clientService.editClient(this.newClient).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadClients();
        this.success = true;
      })
    }

  }

  //TODO: ???
  // comparePatientCity(v1: Ville, v2: Ville): boolean {
  //   return v1 != undefined && v2 != undefined && v1.id == v2.id;
  // }

}
