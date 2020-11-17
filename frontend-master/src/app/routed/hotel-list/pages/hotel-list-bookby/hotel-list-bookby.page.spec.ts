import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelListBookbyPage } from './user-hotel-list.page';

describe('UserHotelListPage', () => {
  let component: HotelListBookbyPage;
  let fixture: ComponentFixture<HotelListBookbyPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelListBookbyPage ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelListBookbyPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
