import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ChatsModel} from '../models/chats.model';
import {ListModel} from '../../api/models/containers.model';
import {CommentModel} from '../../comments/models/comment.model';
import {environment} from '../../../../environments/environment.prod';
import {Injectable} from '@angular/core';
import {MessageModel} from '../models/message.model';
import {ExistingChatModel} from '../models/existingChat.model';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private readonly http: HttpClient
  ) {
  }

  getChats(): Observable<ChatsModel[]> {
    return this.http
      .get<ChatsModel[]>(
        `${environment.api}/chat/mychats`
      );

  }

  getMessages(
    chatId: number
  ): Observable<MessageModel[]> {
    return this.http
      .get<MessageModel[]>(
        `${environment.api}/chat/messages/${chatId}`
      );
  }

  sendMessage(message: MessageModel): Observable<any> {
    return this.http.post(
      `${environment.api}/chat`, message
    );

  }

  createChat(landlordId: number): Observable<ExistingChatModel> {
    return this.http.post<ExistingChatModel>(
      `${environment.api}/chat/create`, {landlordId: landlordId}
    );
  }

}
