import { Component, OnInit } from '@angular/core';
// import { Client} from '../classes/'

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  newPatient: Client = new Client();

  clients: Array<Client> = [];
  // @ViewChild('closebutton') closebuttonelement: any;
  success: boolean = false;
  error: boolean = false;
  currentVille: string | undefined;
  search: String = "";

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.clientService.loadClients(this.search).subscribe(
      data => {
        this.clients = data;
        console.log(data);
      }
    );
  }


  editPatient(id?: number): void {
    this.clientService.getPatient(id).subscribe(data => {
      this.newPatient = data;
      this.currentVille = this.newPatient.ville?.nom;
      this.loadClients();
      this.success = true;
    })
  }

  deletePatient(id?: number): void {
    if (confirm("Voulez-vous vraiment supprimer ce client ?")) {
      this.clientService.deletePatient(id).subscribe(data => {
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
    if (this.newPatient.id == undefined) {
      this.clientService.addPatient(this.newPatient).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadClients();
        this.success = true;
      })
    } else {
      this.clientService.editPatient(this.newPatient).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadClients();
        this.success = true;
      })
    }

  }

  comparePatientCity(v1: Ville, v2: Ville): boolean {
    return v1 != undefined && v2 != undefined && v1.id == v2.id;
  }

}
