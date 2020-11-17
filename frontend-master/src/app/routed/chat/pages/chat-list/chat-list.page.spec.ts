import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatListPage } from './chat-list.page';

describe('ChatListPage', () => {
  let component: ChatListPage;
  let fixture: ComponentFixture<ChatListPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChatListPage ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChatListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
