import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserHotelListPage } from './user-hotel-list.page';

describe('UserHotelListPage', () => {
  let component: UserHotelListPage;
  let fixture: ComponentFixture<UserHotelListPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserHotelListPage ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserHotelListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
