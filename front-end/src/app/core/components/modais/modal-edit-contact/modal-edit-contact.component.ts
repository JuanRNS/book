import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { InputComponent } from '../../input/input.component';
import { IResponseContact } from '../../../interfaces/contact.interface';
import { ApiService } from '../../../services/api.service';

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

  constructor(@Inject (MAT_DIALOG_DATA) public data: IResponseContact, private readonly _dialog: MatDialogRef<ModalEditContactComponent>, private readonly _service: ApiService) {
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
    this.form.patchValue(this.data);
  }

  public onSubmit(){
    if(!this.form.dirty){
      this._dialog.close('Nenhum campo foi alterado');
      return;
    }
    if(this.form.invalid){
      this.form.markAllAsTouched();
      this.form.markAsDirty();
      this.form.updateValueAndValidity();
      return;
    }

    this._service.updateContact(this.form.value, this.data.id).subscribe(res => {
      this._dialog.close('Contato atualizado com sucesso');
    });
  }
}
