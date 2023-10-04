import { AfterViewChecked, Component, ElementRef, OnDestroy, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Chat } from 'src/app/model/chat';
import { Message } from 'src/app/model/message';
import { MessageRequest } from 'src/app/payload/message-request';
import { ChatWebsocketService } from 'src/app/services/chat-websocket.service';
import { ChatService } from 'src/app/services/chat.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy, AfterViewChecked {
  @ViewChildren('messagesContainer') messagesContainer!: QueryList<ElementRef>;
  loading: boolean = true;
  onError: boolean = false;
  routeSub!: Subscription;
  chatSub?: Subscription;
  onMessageSub?: Subscription;
  chat!: Chat;
  private chatWsService!: ChatWebsocketService;

  msgForm = this.fb.group({
    content: ''
  });

  constructor(private chatService: ChatService, private loginService: LoginService,
    private route: ActivatedRoute, private router: Router, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.routeSub = this.route.paramMap.subscribe({
      next: params => {
        if (!params.has('id')) return;
        if (this.chatSub !== undefined) this.chatSub.unsubscribe();
        const chatId = +params.get('id')!;
        this.chatSub = this.chatService.getChat(chatId).subscribe({
          next: data => {
            this.chat = Chat.copy(data);
            this.chatWsService = new ChatWebsocketService(chatId);
            this.loading = false;
            if (this.onMessageSub !== undefined) this.onMessageSub.unsubscribe();
            this.onMessageSub = this.chatWsService.onMessage().subscribe({
              next: (data: Message) => {
                this.chat.messages.push(Message.copy(data));
              }
            });
          }, error: _ => {
            this.loading = false;
            this.onError = true;
          }
        });
      }
    }
    );
  }

  ngOnDestroy(): void {
    this.routeSub.unsubscribe();
    if (this.chatSub !== undefined) this.chatSub.unsubscribe();
    if (this.onMessageSub !== undefined) this.onMessageSub.unsubscribe();
  }

  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  scrollToBottom(): void {
    if (this.messagesContainer) {
      this.messagesContainer.forEach(e => e.nativeElement.scrollTop = e.nativeElement.scrollHeight)
    }
  }

  sendMessage(): void {
    this.chatWsService.sendMessage(new MessageRequest(this.loginService.userId, this.msgForm.value.content!));
    this.msgForm.reset();
  }

  goBack(): void {
    this.router.navigateByUrl('chat-list');
  }
}
