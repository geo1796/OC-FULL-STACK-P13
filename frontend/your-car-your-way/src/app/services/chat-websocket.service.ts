import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { MessageRequest } from '../payload/message-request';

export class ChatWebsocketService {
  private socket$: WebSocketSubject<any>;

  constructor(chatId: number) {
    this.socket$ = webSocket('ws://localhost:8080/api/chat?id=' + chatId);
  }

  sendMessage(message: MessageRequest) {
    this.socket$.next({ message });
  }

  onMessage() {
    return this.socket$.asObservable();
  }
}
