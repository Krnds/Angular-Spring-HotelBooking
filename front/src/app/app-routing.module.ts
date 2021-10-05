import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { ClientComponent } from './client/client.component';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main/main.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  //TODO: add /addedit/:id route if routerlink ?
  // { path: 'hotels/addedit/:id', component: RdvDetailsComponent },
  {
    path: '', component: MainComponent, canActivate: [AuthGuard], children:
      [
        { path: 'client', component: ClientComponent, canActivate: [AuthGuard] },
        // { path: 'ville', component: VilleComponent, canActivate: [AuthGuard] },
        // { path: 'rdv', component: RdvComponent, canActivate: [AuthGuard] }
      ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
