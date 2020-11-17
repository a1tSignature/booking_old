import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {HotelListPage} from '../../pages/hotel-list/hotel-list.page';


interface FormValue{
  country: string;
  city: string;
  dateStart: string;
  dateEnd: string;
}


/** @title Date range picker forms integration */
@Component({
  selector: 'app-search-section',
  templateUrl: './search-section.component.html',
  styleUrls: ['./search-section.component.sass']
})

export class SearchSectionComponent implements OnInit{
  country = '';
  city = '';
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });
  minDate: Date;
  maxDate: Date;



  constructor(
    private readonly hotelListPage: HotelListPage
  ) {
    if (
      this.hotelListPage.lastParam.dateStart != undefined
      &&
      this.hotelListPage.lastParam.dateEnd != undefined
    ) {
      this.range = new FormGroup({
        start: new FormControl(new Date(hotelListPage.lastParam.dateStart)),
        end: new FormControl(new Date(hotelListPage.lastParam.dateEnd))
      });
    }
    if (this.hotelListPage.lastParam.country != undefined) {
      this.country = this.hotelListPage.lastParam.country;
    }
    if (this.hotelListPage.lastParam.city != undefined) {
      this.city = this.hotelListPage.lastParam.city;
    }
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear, new Date().getMonth(), new Date().getDate());
    this.maxDate = new Date(currentYear + 1, 11, 31);
  }



  ngOnInit(): void {}

  handleSearchClick(value: FormValue): void {
    if (!(this.range.value.start == undefined ||
      this.range.value.end == undefined)) {
      value.dateStart = this.range.value.start.toDateString();
      value.dateEnd = this.range.value.end.toDateString();
    }
    this.hotelListPage.page = 0;
    this.hotelListPage.param$.next(value);
    this.hotelListPage.updateQueryParams(
      {
        page: this.hotelListPage.page,
        pageSize: this.hotelListPage.pageSize,
        country: this.hotelListPage.lastParam.country,
        city: this.hotelListPage.lastParam.city,
        dateStart: this.hotelListPage.lastParam.dateStart,
        dateEnd: this.hotelListPage.lastParam.dateEnd
      });
  }
}
