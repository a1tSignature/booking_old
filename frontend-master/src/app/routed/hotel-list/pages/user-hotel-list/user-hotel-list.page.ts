import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HotelListService} from '../../../../features/hotels/services/hotel-list.service';
import {HotelModel} from '../../../../features/hotels/models/hotel.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import {HotelEditComponent} from '../../component/hotel-edit/hotel-edit.component';
import {ImagesAddComponent} from '../../component/images-add/images-add.component';
import {RoomsEditComponent} from '../../component/rooms-edit/rooms-edit.component';
import {error} from 'selenium-webdriver';

@Component({
  selector: 'app-user-hotel-list',
  templateUrl: './user-hotel-list.page.html',
  styleUrls: ['./user-hotel-list.page.sass']
})
export class UserHotelListPage implements OnInit {
  items: HotelModel[] = [];
  loading: boolean = false;
  username: string;


  constructor(private readonly route: ActivatedRoute,
              private readonly hotelsService: HotelListService,
              private readonly _snackBar: MatSnackBar,
              public hotelDialog: MatDialog,
              public imageDialog: MatDialog,
              public roomsDialog: MatDialog


  ) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.username = params['user'];
    });
    this.getItems();
  }

  getItems(): void {
    this.loading = true;
    this.hotelsService.getUserHotels(this.username).subscribe((res) => {
        this.items = res.list;
        this.loading = false;
      }
    );
  }

  openHotelDialog(hotelId: number) {
    const dialogRef = this.hotelDialog.open(HotelEditComponent, {data: {hotelId: hotelId}});
    dialogRef.afterClosed().subscribe(res => {
      console.info(res);
      if (res) {
        this.getItems();
        this._snackBar.open('Информация об отеле обновлена', 'закрыть', {duration: 4000});
      }else  {
        this._snackBar.open('Информация об отеле не обновлена', 'закрыть', {duration: 4000});
      }
      }
    );
  }

  openImagesDialog(hotelId: number) {
    const dialogRef = this.imageDialog.open(ImagesAddComponent, {data: {hotelId: hotelId}});
    dialogRef.afterClosed().subscribe(res => {
        if (res) {
          this._snackBar.open('Фото добавлены', 'зкарыть', {duration: 4000});
        }else {
          this._snackBar.open('Фото не были добавлены', 'закрыть', {duration: 4000});
        }
      }
    );
  }

  openRoomsDialog(hotelId: number) {
    const dialogRef = this.imageDialog.open(RoomsEditComponent, {data: {hotelId: hotelId}});
    dialogRef.afterClosed().subscribe(res => {
        if (res) {
          this._snackBar.open('Комнаты добавлены', 'закрыть', {duration: 4000});
        }else {
          this._snackBar.open('Комнаты не были добавлены', 'закрыть', {duration: 4000});

        }
      }
    );
  }

  deleteItem(id: number): void {
    this.hotelsService.deleteHotel(id).subscribe((res) => {
        this.loading = true;
        this.getItems();
        this._snackBar.open('Отель удален', 'закрыть', {duration: 4000});

      },
      (error) => {
        this._snackBar.open('Ошибка при удалении отеля', 'закрыть', {duration: 4000});
      }
    );
  }

  //TODO:EDIT HOTEL PHOTOS
}
