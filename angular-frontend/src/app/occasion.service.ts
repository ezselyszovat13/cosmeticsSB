import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Occasion } from './occasion';

@Injectable({
  providedIn: 'root'
})
export class OccasionService {
  private baseUrl = "http://localhost:8080/api/occasions";

  constructor(private http: HttpClient) { }

  getOccasions(): Observable<Occasion[]>{
    return this.http.get<Occasion[]>(`${this.baseUrl}`);
  }
}
