import { Component, Input } from '@angular/core';
import { format } from 'date-fns';
import { Message } from 'src/app/model/message';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent {
  @Input() message!: Message;
  constructor(private loginService: LoginService) { }

  isSelfMessage(): boolean {
    return this.message.senderId === this.loginService.userId;
  }

  getMessageDate(): string {
    return format(this.message.date, 'dd/MM/yyyy HH:mm');
  }
}
