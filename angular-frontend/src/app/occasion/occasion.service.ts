import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Occasion } from './occasion';
import { Treatment } from '../treatment/treatment';

@Injectable({
  providedIn: 'root'
})
export class OccasionService {
  private baseUrl = "http://localhost:8080/api/occasions";
  private treatmentUrl = "http://localhost:8080/api/occasiontreatments/"

  constructor(private http: HttpClient) { }

  getOccasions(): Observable<Occasion[]>{
    return this.http.get<Occasion[]>(`${this.baseUrl}`);
  }

  getTreatmentsOfOccasion(id: number): Observable<Treatment[]>{
    return this.http.get<Treatment[]>(`${this.treatmentUrl}` + id)
  }
}
