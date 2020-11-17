import {Component, OnInit} from '@angular/core';
import {CurrentUserService} from '../../../../core/auth/current-user.service';
import {ChatService} from '../../../../features/chat/services/chat.service';
import {ChatsModel} from '../../../../features/chat/models/chats.model';
import {MatDialog} from '@angular/material/dialog';
import {HotelEditComponent} from '../../../hotel-list/component/hotel-edit/hotel-edit.component';
import {ChatDialogComponent} from '../../components/chat-dialog/chat-dialog.component';

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.page.html',
  styleUrls: ['./chat-list.page.sass']
})
export class ChatListPage implements OnInit {
  chats: ChatsModel[] = [];

  constructor(
    private readonly  userService: CurrentUserService,
    private readonly chatService: ChatService,
    public hotelDialog: MatDialog,

  ) {
  }

  ngOnInit(): void {
    this.chatService.getChats().subscribe((res) => {
      console.info(res);
      this.chats = res;
    });
  }
  openDialog(chatId:number):void{
  const dialogRef = this.hotelDialog.open(ChatDialogComponent, {data: {chatId: chatId}});

}

}
