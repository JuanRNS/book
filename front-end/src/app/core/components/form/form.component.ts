import { Component, EventEmitter, input, Output,} from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [MatButtonModule, ReactiveFormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.scss',
})
export class FormComponent {
  public signinOrSignup = input<string>('');
  public formTitle = input<string>('');
  public buttonForm = input<string>('');
  public form = input.required<FormGroup>();
  @Output()
  public signinOrSignupButton = new EventEmitter<void>();
  @Output()
  public formSubmit = new EventEmitter<void>();

  constructor() {}

  public onSigninOrSignupClick() {
    this.signinOrSignupButton.emit();
  }

  public onSubmit() {
    this.formSubmit.emit();
  }
}

