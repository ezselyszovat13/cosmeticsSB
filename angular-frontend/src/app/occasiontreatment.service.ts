import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { OccasionTreatment } from './occasiontreatment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OccasionTreatmentService {
  private baseUrl = "http://localhost:8080/api/occasiontreatments";

  constructor(private http: HttpClient) { }

  getOccasionTreatments(): Observable<OccasionTreatment[]>{
    return this.http.get<OccasionTreatment[]>(`${this.baseUrl}`);
  }
}
