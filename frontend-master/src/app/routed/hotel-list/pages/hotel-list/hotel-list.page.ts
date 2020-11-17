import {Component, Injectable, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {HotelModel} from '../../../../features/hotels/models/hotel.model';
import {HttpClient} from '@angular/common/http';
import {MatDialog} from '@angular/material/dialog';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {HotelListService} from '../../../../features/hotels/services/hotel-list.service';
import {Subject, Subscription} from 'rxjs';


interface SearchParamValue{
  country: string;
  city: string;
  dateStart: string;
  dateEnd: string;
}

@Injectable({
  providedIn: 'root'
})
@Component({
  templateUrl: './hotel-list.page.html',
  styleUrls: ['./hotel-list.page.sass']
})
export class HotelListPage implements OnInit, OnDestroy {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  items: HotelModel[] = [];
  page = 0;
  pageSize = 5;
  loading = false;
  total = 0;
  param$ = new Subject<SearchParamValue>();
  lastParam: SearchParamValue;
  folderPath = 'assets/images/';
  waysToImages: Map<number, string> = new Map<number, string>();
  private paramSub = Subscription.EMPTY;
  private querySub: Subscription;



  constructor(
    private router: Router,
    private readonly route: ActivatedRoute,
    private readonly http: HttpClient,
    private readonly dialog: MatDialog,
    private readonly hotelsService: HotelListService
  ) {
    // tslint:disable-next-line:new-parens
    this.lastParam = new class implements SearchParamValue {
      country = undefined;
      city = undefined;
      dateStart = undefined;
      dateEnd = undefined;
    };
    this.querySub = route.queryParams.subscribe(
      // tslint:disable-next-line:no-any
      (queryParam: any) => {
        this.lastParam.country = queryParam.country;
        this.lastParam.city = queryParam.city;
        this.lastParam.dateStart = queryParam.dateStart;
        this.lastParam.dateEnd = queryParam.dateEnd;
        if (queryParam.page != undefined) {
          this.page = queryParam.page;
        }
        if (queryParam.pageSize != undefined) {
          this.pageSize = queryParam.pageSize;
        }
      }
    );
  }


  updateQueryParams(queryParams: Params): void {
    this.router.navigate(
      [],
      {
        relativeTo: this.route,
        queryParams
      });
  }


  setImageSourceForHotel(hotelId: number): void {
    if (hotelId != undefined) {
      this.loading = true;
      this.hotelsService.getHotelImage(hotelId).subscribe(
        (res) => {
          if (res == undefined) {
            this.waysToImages.set(hotelId, this.folderPath + 'default.jpg');
          } else {
            this.waysToImages.set(hotelId, this.folderPath + res.lg);
          }
          this.loading = false;
        }
      );
    }
  }


  ngOnInit(): void {
    this.paramSub = this.param$.subscribe( (param) => {
      this.lastParam = param;
      this.refreshItems(param);
      }
    );
    this.param$.next(this.lastParam);
  }


  handlePageChange(event: PageEvent): void {
    this.page = event.pageIndex;
    this.pageSize = event.pageSize;
    this.updateQueryParams(
      {
        page: this.page,
        pageSize: this.pageSize,
        country: this.lastParam.country,
        city: this.lastParam.city,
        dateStart: this.lastParam.dateStart,
        dateEnd: this.lastParam.dateEnd
      });
    this.refreshItems(this.lastParam);
  }


  refreshItems(param: SearchParamValue): void {
    this.loading = true;
    this.hotelsService
      .getHotels(this.page, this.pageSize, param)
      .subscribe((res) => {
        this.items = res.list;
        this.total = res.amount;
        this.items.forEach( (i) => {
          this.setImageSourceForHotel(i.id);
        });
        this.loading = false;
      }
    );
  }


  ngOnDestroy(): void {
    this.paramSub.unsubscribe();
    this.querySub.unsubscribe();
  }

}
