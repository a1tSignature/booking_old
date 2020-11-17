import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'hotels',
    loadChildren: () =>
      import(
        './routed/hotel-list/hotel-list-routing.module'
        ).then((m) => m.HotelListRoutingModule)
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./routed/auth/auth-routing.module').then(
        (m) => m.AuthRoutingModule
      )
  },
  {
    path: 'chats',
    loadChildren: () =>
      import('./routed/chat/chat-routing.module').then(
        (m) => m.ChatRoutingModule
      )
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
