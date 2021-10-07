import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { ClientComponent } from './client/client.component';
import { HotelComponent } from './hotel/hotel.component';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main/main.component';
import { ReservationComponent } from './reservation/reservation.component';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  //TODO: add /addedit/:id route if routerlink ?
  // { path: 'hotels/addedit/:id', component: RdvDetailsComponent },
  {
    path: '', component: MainComponent, canActivate: [AuthGuard], children:
      [
        { path: 'client', component: ClientComponent, canActivate: [AuthGuard] },
        { path: 'welcome', component: WelcomePageComponent, canActivate: [AuthGuard] },
        { path: 'hotel', component: HotelComponent, canActivate: [AuthGuard] },
        { path: 'reservation', component: ReservationComponent, canActivate: [AuthGuard] }
      ]
  },
  { path: '**', redirectTo: 'login' } //TODO: à voir
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
