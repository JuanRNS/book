import { Component } from '@angular/core';
import { FormComponent } from '../form/form.component';
import { MatInputModule } from '@angular/material/input';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../../features/services/user.service';
import { IUserRequestRegister } from '../../interfaces/user.interface';
import { InputComponent } from "../input/input.component";

@Component({
  selector: 'app-sign-up',
  standalone: true,
    providers: [
      { 
        provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, 
        useValue: { appearance: 'outline' } 
      }
    ],
  imports: [FormComponent, MatInputModule, InputComponent],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss'
})
export class SignupComponent {

  public form: FormGroup;

  constructor(private readonly _router: Router, private readonly _service: UserService){
    this.form = new FormGroup({
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl(''),
      phoneNumber: new FormControl('')
    });
  }

  public signUpClick(){
    this._router.navigate(['/login']);
  }

  public onSubmitSignUp(){
    if(this.form.invalid){
      this.form.markAllAsTouched();
    }
    if(this.form.value.password !== this.form.value.confirmPassword){
      alert('As senhas não são iguais');
    }
    const form: IUserRequestRegister = {
      name: this.form.value.name,
      email: this.form.value.email,
      password: this.form.value.password,
      phoneNumber: this.form.value.phoneNumber
    }
    this._service.register(form).subscribe(res => {
      this._router.navigate(['/login']);
    });

  }
}
