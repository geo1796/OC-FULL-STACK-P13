import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NewChat } from 'src/app/payload/new-chat';
import { ChatService } from 'src/app/services/chat.service';

@Component({
  selector: 'app-chat-form',
  templateUrl: './chat-form.component.html',
  styleUrls: ['./chat-form.component.css']
})
export class ChatFormComponent {

  chatForm = this.fb.group({
    subject: ['', Validators.required]
  });

  constructor(private chatService: ChatService, private fb: FormBuilder, private router: Router){}

  onSubmit(): void {
    this.chatService.startChat(new NewChat(this.chatForm.value.subject!)).subscribe({
      next: data => {
        this.router.navigateByUrl('chat/' + data.id);
      }
    })
  }

  goBack(): void {
    this.router.navigateByUrl('');
  }
}
