import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { TreatmentComponent } from './treatment/treatment.component';
import { OccasionComponent } from './occasion/occasion.component';
import { OccasionTreatmentComponent } from './occasiontreatment/occasiontreatment.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    TreatmentComponent,
    OccasionComponent,
    OccasionTreatmentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
