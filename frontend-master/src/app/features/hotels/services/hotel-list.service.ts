import {Injectable} from '@angular/core';
import {HotelDetailModel, HotelModel} from '../models/hotel.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ListModel} from '../../api/models/containers.model';
import {ImageModel} from '../../api/models/image.model';
import {CreatedHotelModel} from '../models/created-hotel.model';
import {environment} from '../../../../environments/environment.prod';




interface SearchParamValue{
  country: string;
  city: string;
  dateStart: string;
  dateEnd: string;
}



@Injectable({
  providedIn: 'root'
})
export class HotelListService {


  constructor(private readonly http: HttpClient) {
  }


  getHotels(
    page: number,
    pageSize: number,
    param: SearchParamValue
  ): Observable<ListModel<HotelModel>> {
    if (param.country == undefined || param.city == undefined) {
      return this.getAnyHotels(page, pageSize);
    }
    if (param.dateStart == undefined || param.dateEnd == undefined) {
      return this.getHotelsByLocation(page, pageSize, param);
    }
    return this.getHotelsByLocationAndDate(page, pageSize, param);
  }

  getAnyHotels(
    page: number,
    pageSize: number
  ): Observable<ListModel<HotelModel>> {
    return this.http
      .get<ListModel<HotelModel>>(
        `${environment.api}/hotel`,
        {
          params: {
            offset: (pageSize * page).toString(10),
            quantity: pageSize.toString(10)
          }
        }
      );
  }

  getHotelsByLocation(
    page: number,
    pageSize: number,
    param: SearchParamValue
  ): Observable<ListModel<HotelModel>> {
    return this.http
      .get<ListModel<HotelModel>>(
        `${environment.api}/hotel/` + param.country + '/' + param.city,
        {
          params: {
            offset: (pageSize * page).toString(10),
            quantity: pageSize.toString(10)
          }
        }
      );

  }

  getHotelsByLocationAndDate(
    page: number,
    pageSize: number,
    param: SearchParamValue
  ): Observable<ListModel<HotelModel>> {
    return this.http
      .get<ListModel<HotelModel>>(
        `${environment.api}/hotel/` + param.country + '/' + param.city + '/byDate',
        {
          params: {
            arrivalDate: param.dateStart,
            dateOfDeparture: param.dateEnd,
            offset: (pageSize * page).toString(10),
            quantity: pageSize.toString(10)
          }
        }
      );

  }


  getHotelDetails(hotelId: string): Observable<HotelDetailModel> {
    return this.http.get<HotelDetailModel>(`${environment.api}/hotel/${hotelId}`);
  }


  getHotelImages(hotelId: number): Observable<ImageModel[]> {
    return this.http.get<ImageModel[]>(`${environment.api}/image/${hotelId}`);
  }

  getHotelImage(hotelId: number): Observable<ImageModel> {
    return this.http.get<ImageModel>(`${environment.api}/image/first/${hotelId}`);
  }


  addHotel(newHotel: CreatedHotelModel): Observable<any> {
    return this.http.post(`${environment.api}/hotel`, newHotel);
  }


  getUserHotels(username: string): Observable<ListModel<HotelModel>> {
    return this.http
      .get<ListModel<HotelModel>>(
        `${environment.api}/hotel/createdby/${username}`,
      );
  }


  deleteHotel(id: number): Observable<any> {
  return this.http.delete(`${environment.api}/hotel/${id}`);
  }


  updateHotel(updatedHotel: CreatedHotelModel, hotelId: number): Observable<any> {
    return this.http.put(`${environment.api}/hotel/${hotelId}`, updatedHotel);
  }

}
