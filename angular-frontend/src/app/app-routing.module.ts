import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OccasionComponent } from './occasion/occasion.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  { path: '', component: UserComponent},
  { path: 'occasions', component: OccasionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
