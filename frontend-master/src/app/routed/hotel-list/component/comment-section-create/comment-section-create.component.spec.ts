import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentSectionCreateComponent } from './comment-section-create.component';

describe('CommentSectionCreateComponent', () => {
  let component: CommentSectionCreateComponent;
  let fixture: ComponentFixture<CommentSectionCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommentSectionCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentSectionCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
