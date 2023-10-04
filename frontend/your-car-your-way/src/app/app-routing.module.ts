import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { authGuard } from './guards/auth.guard';
import { ChatFormComponent } from './pages/chat-form/chat-form.component';
import { ChatComponent } from './pages/chat/chat.component';
import { ChatListComponent } from './pages/chat-list/chat-list.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [authGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'chat/:id',
    component: ChatComponent
  },
  {
    path: 'chat-form',
    component: ChatFormComponent
  },
  {
    path: 'chat-list',
    component: ChatListComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
