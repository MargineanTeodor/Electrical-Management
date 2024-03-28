import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListDeviceNormalComponent } from './list-device-normal.component';

describe('ListDeviceNormalComponent', () => {
  let component: ListDeviceNormalComponent;
  let fixture: ComponentFixture<ListDeviceNormalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListDeviceNormalComponent]
    });
    fixture = TestBed.createComponent(ListDeviceNormalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
