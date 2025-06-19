import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalCreateContactComponent } from './modal-create-contact.component';

describe('ModalCreateContactComponent', () => {
  let component: ModalCreateContactComponent;
  let fixture: ComponentFixture<ModalCreateContactComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalCreateContactComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalCreateContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
