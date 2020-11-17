import { TestBed } from '@angular/core/testing';

import { HotelAccessGuard } from './hotel-access.guard';

describe('HotelAccessGuard', () => {
  let guard: HotelAccessGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(HotelAccessGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
