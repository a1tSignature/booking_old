import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {HotelDetailModel, HotelModel} from '../../../../features/hotels/models/hotel.model';
import {HotelListService} from '../../../../features/hotels/services/hotel-list.service';
import {RoomService} from '../../../../features/rooms/services/room.service';
import {Subject, Subscription} from 'rxjs';
import {RoomModel} from '../../../../features/rooms/models/room.model';
import {ChatService} from '../../../../features/chat/services/chat.service';
import {ChatDialogComponent} from '../../../chat/components/chat-dialog/chat-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';

import {CurrentUserService} from '../../../../core/auth/current-user.service';
import {Role} from '../../../../core/auth/role.model';


interface SearchParamValue {
  hotelId: number;
  dateStart: string;
  dateEnd: string;
}


@Component({
  selector: 'app-hotel-list-detail',
  templateUrl: './hotel-list-detail.page.html',
  styleUrls: ['./hotel-list-detail.page.sass']
})
export class HotelListDetailPage implements OnInit, OnDestroy {
  hotel: HotelDetailModel;
  rooms: RoomModel[] = [];

  id: number;
  param$ = new Subject<SearchParamValue>();
  // tslint:disable-next-line:new-parens
  lastParam: SearchParamValue = new class implements SearchParamValue {
    dateEnd: string;
    dateStart: string;
    hotelId: number;
  };
  private paramSub = Subscription.EMPTY;
  loading = false;

  private querySub: Subscription;
  private hotelsServiceSub: Subscription;


  user$ = this.currentUserService.user$;
  Role = Role;


  constructor(
    private router: Router,
    private readonly route: ActivatedRoute,
    private readonly hotelsService: HotelListService,
    private readonly roomService: RoomService,
    private readonly  chatService: ChatService,
    private readonly dialogRef: MatDialog,
    private  readonly _snackBar: MatSnackBar,
    readonly  currentUserService: CurrentUserService

  ) {
    this.querySub = route.queryParams.subscribe(
      // tslint:disable-next-line:no-any
      (queryParam: any) => {
        this.lastParam.dateStart = queryParam.dateStart;
        this.lastParam.dateEnd = queryParam.dateEnd;
      }
    );
  }

  updateQueryParams(queryParams: Params): void {
    this.router.navigate(
      [],
      {
        relativeTo: this.route,
        queryParams,
      });
  }


  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    // tslint:disable-next-line:radix
    this.id = parseInt(id);
    this.getInfo(id);

    this.paramSub = this.param$.subscribe((param) => {
      // TODO delete
      this.lastParam = param;
      this.refreshItems(param);
    });
    // tslint:disable-next-line:radix
    this.lastParam.hotelId = parseInt(id);
    this.param$.next(this.lastParam);
  }


  ngOnDestroy(): void {
    this.paramSub.unsubscribe();
    this.querySub.unsubscribe();
    this.hotelsServiceSub.unsubscribe();
  }


  private getInfo(id: string | null | undefined): void {
    if (id == undefined) {
      this.hotel = undefined;
      return;
    }
    this.hotel = undefined;
    this.hotelsServiceSub = this.hotelsService.getHotelDetails(id).subscribe((res) => {
      this.hotel = res;
    });
  }

  canShow(): boolean {
    return this.hotel != undefined;
  }

  refreshItems(param: SearchParamValue): void {
    this.loading = true;
    this.roomService
      .getRooms(param)
      .subscribe((res) => {
          this.rooms = res;
          this.loading = false;
        }
      );
  }


  startChat(): void {
    this.chatService.createChat(this.hotel.creatorId).subscribe(res => {
      const dialogRef = this.dialogRef.open(ChatDialogComponent, {data: {chatId: res.chatId}});
    },
    error => {
      this._snackBar.open('Не удалось открыть чат', 'закрыть', {duration: 4000});
    });
  }


  handleBookClick(roomId: number): void {
    this.roomService.bookRoom(
      this.lastParam,
      roomId,
      this.user$.getValue().getUsername()
    ).subscribe(
      res=>{
        window.open(
          '/hotels/bookby/' + this.user$.getValue().getUsername());
        window.location.reload();
      },
      errorя=>{
        this._snackBar.open('Не удалось забронировать комнату', 'закрыть', {duration: 4000});
      }
    );


  }


}

