import {Component, Inject, OnInit} from '@angular/core';
import {MessageModel} from '../../../../features/chat/models/message.model';
import {ChatService} from '../../../../features/chat/services/chat.service';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {CdkScrollable, ScrollDispatcher} from '@angular/cdk/overlay';
import {Subscription} from 'rxjs';
import {error} from 'selenium-webdriver';

export class MessageImpl implements MessageModel {
  constructor(chatId: number, message: string) {
    this.chatId = chatId;
    this.message = message;
  }

  chatId: number;
  message: string;
  sentAt: string;
  sentBy: string;
}

@Component({
  selector: 'app-chat-dialog',
  templateUrl: './chat-dialog.component.html',
  styleUrls: ['./chat-dialog.component.sass']
})
export class ChatDialogComponent implements OnInit {
  messages: MessageModel[] = [];
  currentMessage="";
  chatId: number;
  error=false;



  constructor(
    private readonly chatService: ChatService, @Inject(MAT_DIALOG_DATA) public data,

  ) {
    this.chatId = this.data.chatId;
  }

  refreshChat(): void {
    this.chatService.getMessages(this.chatId).subscribe(res => {
        this.error=false;

        this.messages = res;
      this.error=false;
    },
    error=>{
      this.messages=[];
      this.currentMessage="Ошибка при загрузке чата";
      this.error =true;

    });
  }

  ngOnInit(): void {
    this.refreshChat();
  }

  sendMessage(): void {
    this.chatService.sendMessage(new MessageImpl(this.chatId,this.currentMessage)).subscribe(
      res=>{
        this.refreshChat();
     }
    )
  }


}
