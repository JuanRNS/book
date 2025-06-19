import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalEditContactComponent } from './modal-edit-contact.component';

describe('ModalEditContactComponent', () => {
  let component: ModalEditContactComponent;
  let fixture: ComponentFixture<ModalEditContactComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalEditContactComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalEditContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
