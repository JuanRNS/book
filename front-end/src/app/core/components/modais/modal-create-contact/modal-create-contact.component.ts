import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { InputComponent } from '../../input/input.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-modal-create-contact',
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
  templateUrl: './modal-create-contact.component.html',
  styleUrl: './modal-create-contact.component.scss',
})
export class ModalCreateContactComponent {
  public form: FormGroup;

  constructor(private readonly _service: ApiService) {
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

  public onSubmit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      this.form.updateValueAndValidity();
      this.form.markAsDirty();
      return;
    }

    this._service
      .createContact(this.form.value)
      .subscribe((res) => console.log(res));
  }
}
