import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from '../services/login.service';

export const authGuard: CanActivateFn = () => {
    const router = inject(Router);

    const loginService = inject(LoginService);
    if (!loginService.loggedIn$.value) {
        return router.parseUrl("/login");
    }

    return true;
}