import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HotelListRoutingModule} from './hotel-list-routing.module';
import {CommentSectionCreateComponent} from './component/comment-section-create/comment-section-create.component';
import {CommentSectionComponent} from './component/comment-section/comment-section.component';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {BarRatingModule} from 'ngx-bar-rating';
import {PictureGalleryComponent} from './component/picture-gallery/picture-gallery.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {SliderCarouselModule} from 'slider-carousel';
import {HttpClientModule} from '@angular/common/http';
import {MatTabsModule} from '@angular/material/tabs';
import {MatTableModule} from '@angular/material/table';
import {SearchSectionComponent} from './component/search-section/search-section.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatNativeDateModule} from '@angular/material/core';
import { UserHotelListPage } from './pages/user-hotel-list/user-hotel-list.page';
import {MatListModule} from '@angular/material/list';
import {HotelsModule} from '../../features/hotels/hotels.module';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { HotelEditComponent } from './component/hotel-edit/hotel-edit.component';
import {MatChipsModule} from '@angular/material/chips';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { ImagesAddComponent } from './component/images-add/images-add.component';
import {NgxDropzoneModule} from 'ngx-dropzone';
import { RoomsEditComponent } from './component/rooms-edit/rooms-edit.component';
import {MatExpansionModule} from '@angular/material/expansion';
import {DateChangeSectionComponent} from './component/date-change-section/date-change-section.component';
import {HotelListBookbyPage} from './pages/hotel-list-bookby/hotel-list-bookby.page';
import {CoreModule} from '../../core/core.module';


@NgModule({
  declarations: [
    CommentSectionCreateComponent, CommentSectionComponent,
    PictureGalleryComponent, SearchSectionComponent,
    UserHotelListPage, HotelEditComponent,
    ImagesAddComponent, RoomsEditComponent,
    DateChangeSectionComponent, HotelListBookbyPage
  ],
  exports: [
    PictureGalleryComponent,
    CommentSectionCreateComponent,
    CommentSectionComponent,
    SearchSectionComponent,
    DateChangeSectionComponent
  ],
  imports: [
    CommonModule,
    HotelListRoutingModule,
    BarRatingModule,
    MatDialogModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    FormsModule,
    MatButtonModule,
    MatInputModule,
    SliderCarouselModule,
    BrowserAnimationsModule,
    SliderCarouselModule,
    BarRatingModule,
    ReactiveFormsModule,
    MatIconModule,
    BarRatingModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    HttpClientModule,
    MatButtonModule,
    MatTabsModule,
    MatTableModule,
    MatDialogModule,
    MatCardModule,
    MatDatepickerModule,
    MatToolbarModule,
    MatNativeDateModule,
    MatListModule,
    HotelsModule,
    MatProgressBarModule,
    MatChipsModule,
    MatProgressSpinnerModule,
    NgxDropzoneModule,
    MatExpansionModule,
    CoreModule,


  ]
})
export class HotelListModule {
}
