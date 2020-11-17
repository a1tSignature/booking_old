import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HotelLocationPipe} from './pipes/hotel-location-pipe';

@NgModule({
  declarations: [HotelLocationPipe],
  imports: [
    CommonModule
  ],
  exports: [HotelLocationPipe]
})
export class HotelsModule {
}
