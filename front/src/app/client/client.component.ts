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
  errMessage: string = "";
  // search: String = "";

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.clientService.loadClients().subscribe(
      data => {
        this.clients = data;
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
    if (confirm("Voulez-vous vraiment supprimer ce client ? Cela va supprimer aussi les réservations associées.")) {
      this.clientService.deleteClient(id).subscribe(data => {
        this.loadClients();
        this.success = true;
      },
        error => {
          this.success = false;
          this.error = true;
          this.errMessage = error.error.message;
        });
    }
  }

  submitForm(): void {
    if (this.newClient.id == undefined) {
      this.clientService.addClient(this.newClient).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadClients();
        this.success = true;
        this.resetForm();
      },
        error => {
          this.error = true;
          this.errMessage = error.error.message;
        })
    } else {
      this.clientService.editClient(this.newClient).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadClients();
        this.success = true;
        this.resetForm();
      },
        error => {
          this.error = true;
          this.errMessage = error.error.message;
        })
    }

  }

  resetForm(): void {
    this.success = false;
    this.error = false;
    this.errMessage = "";
    this.newClient = new Client();
  }

}
