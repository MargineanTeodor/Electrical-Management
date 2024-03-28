import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListdevicesComponent } from './listdevices.component';

describe('ListdevicesComponent', () => {
  let component: ListdevicesComponent;
  let fixture: ComponentFixture<ListdevicesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListdevicesComponent]
    });
    fixture = TestBed.createComponent(ListdevicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
