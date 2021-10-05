import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
console.log("in canactivate method in guard");
console.log(sessionStorage.getItem("connectedUser")?.toString);


    // sessionStorage stocke les données pour une connexion
    if (sessionStorage.getItem("connectedUser"))
      return true;

      // if user not connected, redirect them to the login route
    this.router.navigate(['login']);
    return false;
  }

  isConnected() : Boolean {
    if( sessionStorage.getItem( "connectedUser" ) )
      return true;
    else
      return false;
  }

}