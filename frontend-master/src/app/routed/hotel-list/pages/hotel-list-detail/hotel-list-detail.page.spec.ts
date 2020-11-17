import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelListDetailPage } from './hotel-list-detail.page';

describe('HotelListDetailPage', () => {
  let component: HotelListDetailPage;
  let fixture: ComponentFixture<HotelListDetailPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelListDetailPage ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelListDetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
