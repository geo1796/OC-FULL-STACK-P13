export class Message {
    constructor(public content: string, public date: number, public senderId: number, public sender: string){}
    static copy(m: Message) {
        return new Message(m.content, m.date, m.senderId, m.sender);
    }
}