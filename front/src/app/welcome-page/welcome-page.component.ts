import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.css']
})
export class WelcomePageComponent implements OnInit {

  userDisplayName : string | null = "";

  constructor() { }

  ngOnInit(): void {
    if (sessionStorage.getItem("loggedUser") != null) {
      this.userDisplayName = sessionStorage.getItem("loggedUser");
    }

  }

}
