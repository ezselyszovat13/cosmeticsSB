import { Component, ErrorHandler, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/user/user';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user = new User();
  msg = "";

  constructor(private regService: RegistrationService, private router: Router) { }

  ngOnInit(): void {
  }

  registerUser() {
    this.regService.registerUserFromRemote(this.user).subscribe(
      data => {
        this.router.navigate(['/login']);
      },
      error => {
        console.log(error);
        this.msg = "A megadott e-mail cím már használatban van!";
      }
    )
  }


}
