import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chat } from '../model/chat';
import { NewChat } from '../payload/new-chat';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private url = 'http://localhost:8080/api/chats';
 
  constructor(private http: HttpClient) { }

  startChat(c: NewChat): Observable<Chat> {
    return this.http.post<Chat>(this.url, c);
  }

  getChat(id: number): Observable<Chat> {
    return this.http.get<Chat>(this.url + '/' + id);
  }
}
