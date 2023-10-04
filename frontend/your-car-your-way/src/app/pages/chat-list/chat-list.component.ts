import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { format } from 'date-fns';
import { Subscription } from 'rxjs';
import { Chat } from 'src/app/model/chat';
import { ChatService } from 'src/app/services/chat.service';

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styleUrls: ['./chat-list.component.css']
})
export class ChatListComponent implements OnInit, OnDestroy {
  public loading: boolean = true;
  private chatSub!: Subscription;
  public chats: Chat[] = [];
  public columns: string[] = ['author', 'subject', 'date', 'nav'];
  
  constructor(private chatService: ChatService, private router: Router){}

  ngOnInit(): void {
    this.chatSub = this.chatService.findAll().subscribe({
      next: data => {
        this.chats = Chat.copyList(data);
        this.loading = false;
      }
    })
  }

  ngOnDestroy(): void {
    this.chatSub.unsubscribe();
  }

  getChatDate(c: Chat): string {
    return format(c.date, 'dd/MM/yyyy HH:mm');
  }

  navToChat(c: Chat): void {
    this.router.navigateByUrl('chat/' + c.id);
  }

  goBack(): void {
    this.router.navigateByUrl('');
  }
}
