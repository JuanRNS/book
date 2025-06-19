import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ModalCreateContactComponent } from '../../../core/components/modais/modal-create-contact/modal-create-contact.component';
import { IResponseContact } from '../../../core/interfaces/contact.interface';
import { ApiService } from '../../../core/services/api.service';
import { ModalEditContactComponent } from '../../../core/components/modais/modal-edit-contact/modal-edit-contact.component';

@Component({
  selector: 'app-contatos',
  standalone: true,
  imports: [MatInputModule, MatIconModule, MatButtonModule],
  providers: [
    {
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS,
      useValue: {
        appearance: 'outline',
        floatLabel: 'always',
      },
    },
  ],
  templateUrl: './contatos.component.html',
  styleUrl: './contatos.component.scss',
})
export class ContatosComponent {
  public listContacts: IResponseContact[] = [];

  private readonly _dialog = inject(MatDialog);

  constructor(private readonly _service: ApiService) {}

  ngOnInit() {
    this.searchContacts();
  }

  public alphabet: string[] = [
    'A',
    'B',
    'C',
    'D',
    'E',
    'F',
    'G',
    'H',
    'I',
    'J',
    'K',
    'L',
    'M',
    'N',
    'O',
    'P',
    'Q',
    'R',
    'S',
    'T',
    'U',
    'V',
    'W',
    'X',
    'Y',
    'Z',
  ];

  public addContact() {
    this._dialog.open(ModalCreateContactComponent, {
      width: '400px',
      height: '600px',
      autoFocus: false,
    });
  }

  public searchContacts() {
    this._service.searchContacts().subscribe((response) => {
      console.log(response);
      this.listContacts = response;
    });
  }

  public editContact(contact: IResponseContact) {
    this._dialog.open(ModalEditContactComponent,{
      width: '400px',
      height: '600px',
      autoFocus: false,
      data: contact
    })
  }
  
  public deleteContact(id: number) {
    this._service.deleteContact(id).subscribe(() => {
      this.searchContacts();
    });
  }
}
