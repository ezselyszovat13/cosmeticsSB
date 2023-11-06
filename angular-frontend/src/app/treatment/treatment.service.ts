import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Treatment } from './treatment';

@Injectable({
  providedIn: 'root'
})
export class TreatmentService {
  private baseUrl = "http://localhost:8080/api/treatments";

  constructor(private http: HttpClient) { }

  getTreatments(): Observable<Treatment[]>{
    return this.http.get<Treatment[]>(`${this.baseUrl}`);
  }

}
