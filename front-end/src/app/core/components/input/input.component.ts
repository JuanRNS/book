import { CommonModule } from '@angular/common';
import { Component, input } from '@angular/core';
import { FormGroup, NG_VALUE_ACCESSOR, ReactiveFormsModule } from '@angular/forms';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-input',
  standalone: true,
  providers: [
        { 
          provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, 
          useValue: { appearance: 'outline' } 
        },
        {
          provide: NG_VALUE_ACCESSOR,
          useExisting: InputComponent,
          multi: true
        }
  ],
  imports: [MatInputModule, ReactiveFormsModule, CommonModule],
  templateUrl: './input.component.html',
  styleUrl: './input.component.scss'
})
export class InputComponent {

  public label = input.required<string>();
  public type = input<string>('text');
  public placeHolder = input<string>('');
  public form = input.required<FormGroup>();
  public controlName = input.required<string>();
  public color = input<string>('light');

  public requiredControl():boolean {
    return this.form().get(this.controlName())?.hasError('required') ?? false;
  }

}
