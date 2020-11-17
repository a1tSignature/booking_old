import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChatListPage } from './pages/chat-list/chat-list.page';
import {MatCardModule} from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import {MatSidenavModule} from '@angular/material/sidenav';
import { ChatDialogComponent } from './components/chat-dialog/chat-dialog.component';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {MatPaginatorModule} from '@angular/material/paginator';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {OverlayModule} from '@angular/cdk/overlay';
import {A11yModule} from '@angular/cdk/a11y';
import {CdkTableModule} from '@angular/cdk/table';
import {MatIconModule} from '@angular/material/icon';



@NgModule({
  declarations: [ChatListPage, ChatDialogComponent],
  imports: [
    CommonModule,
    MatCardModule,
    MatListModule,
    MatSidenavModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    MatPaginatorModule,
    ScrollingModule,
    OverlayModule,
    A11yModule,
    CdkTableModule,
    MatIconModule
  ]
})
export class ChatModule { }
