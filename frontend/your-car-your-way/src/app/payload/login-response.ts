export class LoginResponse {
    constructor(public token: string, public expiry: string, public roles: string[], public username: string, public userId: number){}
}