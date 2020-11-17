import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {HotelListDetailPage} from '../../pages/hotel-list-detail/hotel-list-detail.page';
import {Params} from '@angular/router';




@Component({
  selector: 'app-date-change-section',
  templateUrl: './date-change-section.component.html',
  styleUrls: ['./date-change-section.component.sass']
})

export class DateChangeSectionComponent implements OnInit{
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });


  constructor(
    private readonly hotelListDetailPage: HotelListDetailPage
  ) {
    this.range = new FormGroup({
      start: new FormControl(new Date(hotelListDetailPage.lastParam.dateStart)),
      end: new FormControl(new Date(hotelListDetailPage.lastParam.dateEnd))
    });
  }


  ngOnInit(): void {}


  handleSearchClick(): void {
    if (!(this.range.value.start == undefined ||
      this.range.value.end == undefined)) {
      this.hotelListDetailPage.lastParam.dateStart =
        this.range.value.start.toDateString();
      this.hotelListDetailPage.lastParam.dateEnd =
        this.range.value.end.toDateString();
    }
    this.hotelListDetailPage.param$.next(this.hotelListDetailPage.lastParam);

    const queryParams: Params = {
      dateStart: this.hotelListDetailPage.lastParam.dateStart,
      dateEnd: this.hotelListDetailPage.lastParam.dateEnd
    };
    this.hotelListDetailPage.updateQueryParams(queryParams);
  }
}
