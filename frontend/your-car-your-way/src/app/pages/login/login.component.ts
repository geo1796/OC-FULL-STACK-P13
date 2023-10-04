import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { LoginRequest } from 'src/app/payload/login-request';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {
  public onError: boolean = false;
  public loading: boolean = false;
  public loggedIn: boolean;
  private submitSub?: Subscription;
  private loggedInSub!: Subscription;

  public loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  constructor(private loginService: LoginService, private fb: FormBuilder, private router: Router) {
    this.loggedIn = loginService.loggedIn$.value;
  }

  ngOnInit(): void {
    this.loggedInSub = this.loginService.loggedIn$.subscribe({ 
      next: loggedIn => {
        this.loggedIn = loggedIn;
      }
    });
  }

  ngOnDestroy(): void {
    if (this.submitSub !== undefined) this.submitSub.unsubscribe();
    this.loggedInSub.unsubscribe();
  }
  
  public onSubmit(): void {
    if (this.loading) return;
    this.loading = true;
    this.onError = false;
    const request = new LoginRequest(this.loginForm.value.username!, this.loginForm.value.password!); 
    if (this.submitSub !== undefined) this.submitSub.unsubscribe();
    this.submitSub = this.loginService.login(request).subscribe({
      next: data => {
        this.loginService.loginDetails = data;
        this.loading = false;
        this.router.navigateByUrl('');
      }, error: _ => {
        this.loading = false;
        this.onError = true;
      }
    });
  }
}
