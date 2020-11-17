import { Pipe, PipeTransform } from '@angular/core';
import { LocationModel } from '../models/location.model';

// @ts-ignore
@Pipe({
  name: 'hotelLocationPipe'
})
export class HotelLocationPipe implements PipeTransform {
  transform(location?: LocationModel): string | undefined {
    if (
      location.country == undefined &&
      location.city == undefined &&
      location.street == undefined &&
      location.house == undefined
    ) {
      return undefined;
    } else if (
      location.city == undefined &&
      location.country == undefined &&
      location.street == undefined
    ) {
      return location.house;
    } else if (
      location.country == undefined &&
      location.city == undefined
    ) {
      return ` ${location.street} ${location.house}`;
    } else if (location.country == undefined) {
      return `${location.city} ${location.street} ${location.house} `;
    }
    return `${location.country} ${location.city} ${location.street} ${location.house}`;
  }
}
