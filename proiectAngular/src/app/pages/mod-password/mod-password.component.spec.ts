import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModPasswordComponent } from './mod-password.component';

describe('ModPasswordComponent', () => {
  let component: ModPasswordComponent;
  let fixture: ComponentFixture<ModPasswordComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModPasswordComponent]
    });
    fixture = TestBed.createComponent(ModPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
