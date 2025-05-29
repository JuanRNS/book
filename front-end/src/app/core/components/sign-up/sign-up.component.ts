import { Component } from '@angular/core';
import { FormComponent } from '../form/form.component';
import { MatInputModule } from '@angular/material/input';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { Router } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-sign-up',
  standalone: true,
    providers: [
      { 
        provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, 
        useValue: { appearance: 'outline' } 
      }
    ],
  imports: [FormComponent, MatInputModule, ReactiveFormsModule],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss'
})
export class SignupComponent {

  public form: FormGroup;

  constructor(private readonly _router: Router){
    this.form = new FormGroup({
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl('')
    });
  }

  public signUpClick(){
    this._router.navigate(['/login']);
  }

  public onSubmitSignUp(){
    console.log("aqui !!!");
  }
}
