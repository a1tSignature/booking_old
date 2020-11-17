import {Component, Input, OnInit} from '@angular/core';
import {HotelListService} from '../../../../features/hotels/services/hotel-list.service';
import {ImageModel} from '../../../../features/api/models/image.model';

@Component({
  selector: 'app-picture-gallery',
  templateUrl: './picture-gallery.component.html',
  styleUrls: ['./picture-gallery.component.sass']
})
export class PictureGalleryComponent implements OnInit {
  @Input()
  hotelId: number;
  loading = true;
  folderPath = 'assets/images/';

  constructor(private readonly hotelService: HotelListService) {

  }

  images: ImageModel[] = [];

  ngOnInit(): void {
    this.hotelService.getHotelImages(this.hotelId).subscribe(
      (res) => {
        this.loading = false;
        this.images = res;
        this.images.forEach((i) => i.lg = (this.folderPath + i.lg));
      }
    );
  }


}
