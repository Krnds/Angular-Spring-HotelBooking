import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  isUserConnected(): boolean {
    if (sessionStorage.getItem("connectedUser")) {
      return true;
    } else {
      return false;
    }

  }

}
