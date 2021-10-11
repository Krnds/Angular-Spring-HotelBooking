import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  error: boolean = false;
  admin = {
    username: "",
    password: ""
  };

  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit(): void {
  }

  authenticate() {
    this.adminService.authenticate(this.admin).subscribe(data => {
      if (data.id > 0) {
        sessionStorage.setItem("connectedUser", data);
        sessionStorage.setItem('loggedUser', data.username);
        this.router.navigate(['welcome']);
      } else {
        this.error = true;
      }
    },
      error => {
        this.error = true;
      });
  }

  logout() {
    this.adminService.logout(this.admin).subscribe(data => {
      sessionStorage.clear();
      this.router.navigate(['login'])
    });
  }

}
