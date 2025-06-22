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
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { debounceTime, distinctUntilChanged } from 'rxjs';

@Component({
  selector: 'app-contatos',
  standalone: true,
  imports: [MatInputModule, MatIconModule, MatButtonModule, MatPaginatorModule, ReactiveFormsModule],
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
  public listContactsOrigin: IResponseContact[] = [];
  public listContacts: IResponseContact[] = [];
  public search = new FormControl('');

  public page = 0;
  public pageSize = 5;
  public totalElements = 0;
  public letterSelected: string = 'Sem Letra Selecionada';

  private readonly _dialog = inject(MatDialog);

  constructor(private readonly _service: ApiService) {}

  ngOnInit() {
    this.searchContacts();
    this.search.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged()
    ).subscribe((value) => {
        this.filterContacts(value);
    });
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
    }).afterClosed().subscribe((result) => {
      if(result) {
        this.searchContacts();
      }
      console.log(result);
    });
  }

  public searchContacts() {
    this._service.searchContacts(this.page, this.pageSize).subscribe((response) => {
      this.listContacts = response.content;
      this.listContactsOrigin = response.content;
      this.totalElements = response.totalElements;
    });
  }
  public changePage(event: PageEvent) {
    this.page = event.pageIndex;
    this.pageSize = event.pageSize;
    this.searchContacts();
  }

  public editContact(contact: IResponseContact) {
   const dialogRef =  this._dialog.open(ModalEditContactComponent,{
      width: '400px',
      height: '600px',
      autoFocus: false,
      data: contact
    })
    dialogRef.afterClosed().subscribe((result) => {
      if(result !== 'Nenhum campo foi alterado') {
        this.searchContacts();
      }
      console.log(result);
    });
  }

  public filterContactsByLetter(letter: string) {
    this.letterSelected = letter || 'Sem Letra Selecionada';
    this.listContacts = this.listContactsOrigin.filter((contact) => {
      return contact.name.toLowerCase().startsWith(letter.toLowerCase());
    });
  }

  private filterContacts(filter: string | null) {
    
    if(filter !== null && filter !== '') {
      this.listContacts = this.listContactsOrigin.filter((contact) => {
        return contact.name.toLowerCase().includes(filter.toLowerCase());
      });
    }else{
      this.listContacts = this.listContactsOrigin;
    }
  }
  
  
  public deleteContact(id: number) {
    this._service.deleteContact(id).subscribe(() => {
      this.searchContacts();
    });
  }
}
