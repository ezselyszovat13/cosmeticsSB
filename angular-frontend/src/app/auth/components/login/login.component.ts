import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/user/user';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user = new User();
  msg = "";

  constructor(private regService: RegistrationService, private router: Router) { }

  ngOnInit(): void {
  }

  loginUser() {
    this.regService.loginUserFromRemote(this.user).subscribe(
      data => {
        this.router.navigate(['/']);
      },
      error => {
        this.msg = "A megadott felhasználónév és jelszó nem megfelelő.";
      }
    )
  }

}
