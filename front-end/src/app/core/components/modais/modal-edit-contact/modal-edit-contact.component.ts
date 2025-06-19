import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { InputComponent } from '../../input/input.component';
import { IResponseContact } from '../../../interfaces/contact.interface';

@Component({
  selector: 'app-modal-edit-contact',
  standalone: true,
  imports: [
     MatDialogModule,
     MatButtonModule,
     MatIconModule,
     MatInputModule,
     InputComponent,
     MatCheckboxModule,
     ReactiveFormsModule,
  ],
  templateUrl: './modal-edit-contact.component.html',
  styleUrl: './modal-edit-contact.component.scss'
})
export class ModalEditContactComponent {
  public form: FormGroup;

  constructor(@Inject (MAT_DIALOG_DATA) public data: IResponseContact) {
    this.form = new FormGroup({
          name: new FormControl<string>('', [Validators.required, Validators.minLength(3)]),
          email: new FormControl<string>('', [
            Validators.required,
            Validators.email,
          ]),
          phone: new FormControl<string>('', [Validators.required]),
          address: new FormControl<string>(''),
          favorite: new FormControl<boolean>(false),
          description: new FormControl<string>(''),
        });
  }

  ngOnInit() {
    this.addValuesForm(this.data);
  }

  private addValuesForm(contact: IResponseContact) {
    this.form.get('name')?.setValue(contact.name);
    this.form.get('email')?.setValue(contact.email);
    this.form.get('phone')?.setValue(contact.phone);
    this.form.get('address')?.setValue(contact.address);
    this.form.get('favorite')?.setValue(contact.favorite);
    this.form.get('description')?.setValue(contact.description);
  }

  public onSubmit(){}
}
