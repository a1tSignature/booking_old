import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {HotelListPage} from './routed/hotel-list/pages/hotel-list/hotel-list.page';

import {HotelListDetailPage} from './routed/hotel-list/pages/hotel-list-detail/hotel-list-detail.page';

import {MatProgressBarModule} from '@angular/material/progress-bar';
import {HotelsModule} from './features/hotels/hotels.module';
import {HotelListModule} from './routed/hotel-list/hotel-list.module';
import {MatListModule} from '@angular/material/list';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatCardModule} from '@angular/material/card';
import {MatChipsModule} from '@angular/material/chips';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {HotelAddPage} from './routed/hotel-list/pages/hotel-add/hotel-add.page';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {NgxDropzoneModule} from 'ngx-dropzone';
import {ArrayLengthValidatorDirective} from './features/forms/validators/array-length-validator.directive';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {CoreModule} from './core/core.module';
import {CurrentUserModule} from './features/current-user/current-user.module';
import {AuthModule} from './routed/auth/auth.module';
import {HotelAccessGuard} from './core/guards/hotel-access.guard';
import {BarRatingModule} from 'ngx-bar-rating';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {RoomsModule} from './features/rooms/rooms.module';
import { PasswordValidatorDirective } from './features/forms/validators/password-validator.directive';
import {OverlayModule} from '@angular/cdk/overlay';
import {MatExpansionModule} from '@angular/material/expansion';
import {CdkAccordionModule} from '@angular/cdk/accordion';

import {ChatModule} from './routed/chat/chat.module';
import {MatTooltipModule} from '@angular/material/tooltip';


@NgModule({
  declarations: [AppComponent, HotelListPage, HotelListDetailPage, HotelAddPage, ArrayLengthValidatorDirective, PasswordValidatorDirective],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MatToolbarModule,
        MatProgressBarModule,
        HotelsModule,
        HotelListModule,
        MatListModule,
        MatPaginatorModule,
        MatCardModule,
        MatChipsModule,
        MatIconModule,
        MatButtonModule,
        MatFormFieldModule,
        ReactiveFormsModule,
        MatInputModule,
        NgxDropzoneModule,
        MatDatepickerModule,
        CoreModule,
        CurrentUserModule,
        FormsModule,
        AuthModule,
        BarRatingModule,
        MatProgressSpinnerModule,
        MatExpansionModule,
        CdkAccordionModule,
        RoomsModule,
        MatProgressSpinnerModule,
        OverlayModule,
        MatTooltipModule,
       ChatModule
    ],

  providers: [HotelAccessGuard],
  exports: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
