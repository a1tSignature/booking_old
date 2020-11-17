import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HotelListDetailPage} from './pages/hotel-list-detail/hotel-list-detail.page';
import {HotelListPage} from './pages/hotel-list/hotel-list.page';
import {HotelAddPage} from './pages/hotel-add/hotel-add.page';
import {HotelAccessGuard} from '../../core/guards/hotel-access.guard';
import {UserHotelListPage} from './pages/user-hotel-list/user-hotel-list.page';
import {SearchSectionComponent} from './component/search-section/search-section.component';
import {HotelListBookbyPage} from "./pages/hotel-list-bookby/hotel-list-bookby.page";

const routes: Routes = [

    {
      path: '',
      component: HotelListPage
    },
    {
      path: 'createdby/:user',
      component: UserHotelListPage,
      canActivate: [HotelAccessGuard]
    },
    {
      path: 'add',
      component: HotelAddPage,
      canActivate: [HotelAccessGuard],
    },
    {
      path: ':id',
      component: HotelListDetailPage
    },
    {
      path: 'bookby/:user',
      component: HotelListBookbyPage
    }

  ]
;

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HotelListRoutingModule {
}
