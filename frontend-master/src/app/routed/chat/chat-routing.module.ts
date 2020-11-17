import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ChatListPage} from './pages/chat-list/chat-list.page';

const routes: Routes = [
  {path: 'myChats', component: ChatListPage},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChatRoutingModule {

}
