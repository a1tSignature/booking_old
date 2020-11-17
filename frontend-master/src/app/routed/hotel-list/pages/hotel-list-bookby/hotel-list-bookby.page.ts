import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RoomService} from '../../../../features/rooms/services/room.service';
import {RoomBookedInfoModel} from '../../../../features/rooms/models/room-booked-info.model';



@Component({
  selector: 'app-user-hotel-list',
  templateUrl: './hotel-list-bookby.page.html',
  styleUrls: ['./hotel-list-bookby.page.sass']
})
export class HotelListBookbyPage implements OnInit {
  items: RoomBookedInfoModel[] = [];
  loading = false;
  username: string;


  constructor(
    private readonly route: ActivatedRoute,
    private readonly roomService: RoomService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.username = params.user;
    });
    this.getItems();
  }

  getItems(): void {
    this.loading = true;
    this.roomService.getUserBookedRoomInfo(this.username).subscribe((res) => {
        this.items = res;
        this.loading = false;
      }
    );
  }

}
