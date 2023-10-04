import { Message } from "./message";

export class Chat {
    constructor(public id: number, public subject: string, public date: number, public messages: Message[], public author: string){}

    static copy(c: Chat) {
        return new Chat(c.id, c.subject, c.date, c.messages.map(m => Message.copy(m)), c.author);
    }

    static copyList(l: Chat[]) {
        return l.map(c => Chat.copy(c));
    }
}