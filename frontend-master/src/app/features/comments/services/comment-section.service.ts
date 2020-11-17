import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {ListModel} from '../../api/models/containers.model';
import {CommentModel} from '../models/comment.model';
import {environment} from '../../../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class CommentSectionService {


  constructor(private readonly http: HttpClient
  ) {
  }

  getItems(
    hotelId: number
  ): Observable<ListModel<CommentModel>> {
    return this.http
      .get<ListModel<CommentModel>>(
        `${environment.api}/comments/hotel/${hotelId}`
      );
  }

  getByUser(username: string): Observable<ListModel<CommentModel>> {
    return this.http
      .get<ListModel<CommentModel>>(
        `${environment.api}/comments/user/${username}`
      );
  }

  addItem(
    comment: CommentModel
  ): Observable<any> {
    return this.http.post(`${environment.api}/comments`, comment);

  }
}
