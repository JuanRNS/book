import { Component, input } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, ReactiveFormsModule } from '@angular/forms';
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
  imports: [MatInputModule, ReactiveFormsModule],
  templateUrl: './input.component.html',
  styleUrl: './input.component.scss'
})
export class InputComponent implements ControlValueAccessor{

  public label = input<string>('');
  public type = input<string>('text');
  public placeHolder = input<string>('');
  public required = input<boolean>(false);  

  public value: any;
  public onChange: any = (value: any) => {};
  public onTouched: any = () => {};

   writeValue(value: any): void {
    this.value = value;
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  onInput(event: any) {
    this.value = event.target.value;
    this.onChange(this.value);
  }

}
