import {RoomModel} from '../models/room.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../../environments/environment.prod';
import {Injectable} from '@angular/core';
import {RoomBookedInfoModel} from '../models/room-booked-info.model';


interface SearchParamValue {
  hotelId: number;
  dateStart: string;
  dateEnd: string;
}


@Injectable({
  providedIn: 'root'
})
export class RoomService {


  constructor(private readonly http: HttpClient) {
  }


  getRooms(
    param: SearchParamValue
  ): Observable<RoomModel[]> {
    if (param.dateStart == undefined || param.dateEnd == undefined) {
      return this.getAnyRooms(param);
    }
    return this.getRoomsByDate(param);
  }

  getAnyRooms(
    param: SearchParamValue
  ): Observable<RoomModel[]> {
    return this.http
      .get<RoomModel[]>(
        `${environment.api}/room/` + param.hotelId
      );
  }

  getRoomsByDate(
    param: SearchParamValue
  ): Observable<RoomModel[]> {
    return this.http
      .get<RoomModel[]>(
        `${environment.api}/room/` + param.hotelId + '/byDate',
        {
          params: {
            arrivalDate: param.dateStart,
            dateOfDeparture: param.dateEnd,
          }
        }
      );
  }


  // tslint:disable-next-line:no-any
  addRooms(rooms: RoomModel[], hotelId: number): Observable<any> {
    return this.http
      .post(
        `${environment.api}/room/` + hotelId + '/multiple', rooms);
  }


  // tslint:disable-next-line:no-any
  bookRoom(param: SearchParamValue, roomId: number, _userLogin: string): Observable<any> {
    return this.http
      .put(
        `${environment.api}/room/` + roomId + `/book`.toString(),
        {},
        {
          params: {
            userLogin : _userLogin,
            arrivalDate: param.dateStart,
            dateOfDeparture: param.dateEnd,
          }
        }
        );
  }


  getUserBookedRoomInfo(username: string): Observable<RoomBookedInfoModel[]> {
    return this.http
      .get<RoomBookedInfoModel[]>(`${environment.api}/room/booked/` + username);
  }
}
