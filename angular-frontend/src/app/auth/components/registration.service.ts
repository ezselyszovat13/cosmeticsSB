import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/user/user';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private loginUrl = "http://localhost:8080/api/loginuser";
  private registerUrl = "http://localhost:8080/api/registeruser";

  constructor(private http: HttpClient) { }

  public loginUserFromRemote(user: User) : Observable<any>{
    console.log(user);
    return this.http.post(this.loginUrl,user);
  }

  public registerUserFromRemote(user: User) : Observable<any>{
    console.log(user);
    return this.http.post(this.registerUrl,user);
  }
}
