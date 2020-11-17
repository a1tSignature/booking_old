import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelAddPage } from './hotel-add.page';

describe('HotelAddPage', () => {
  let component: HotelAddPage;
  let fixture: ComponentFixture<HotelAddPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelAddPage ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelAddPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
