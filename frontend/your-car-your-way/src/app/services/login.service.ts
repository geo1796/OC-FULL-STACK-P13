import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginRequest } from '../payload/login-request';
import { LoginResponse } from '../payload/login-response';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  public loggedIn$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  
  set loginDetails(details: LoginResponse) {
    localStorage.setItem("access_token", details.token);
    localStorage.setItem("token_expiry", details.expiry);
    localStorage.setItem("roles", JSON.stringify(details.roles));
    localStorage.setItem("username", details.username);
    localStorage.setItem("userId", JSON.stringify(details.userId));
    this.loggedIn$.next(true);
  } 

  private url = 'http://localhost:8080/api/login';
 
  constructor(private http: HttpClient) { }

  public tryAutoLogin(): void {
    const token = localStorage.getItem("access_token");
    if (token === null) return;
    const expiry = Date.parse(localStorage.getItem("token_expiry")!);
    if (Date.now() > expiry) return;
    this.loggedIn$.next(true);
  }

  public logout(): void {
    localStorage.clear();
    this.loggedIn$.next(false);
  }

  public login(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.url, request);
  }
}
