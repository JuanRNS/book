import { Component } from '@angular/core';
import { FormComponent } from '../form/form.component';
import { MatInputModule } from '@angular/material/input';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  providers: [
    { 
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, 
      useValue: { appearance: 'outline' } 
    }
  ],
  imports: [FormComponent,MatInputModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.scss'
})
export class SiginComponent {

  public form: FormGroup;

  constructor(private readonly _router: Router){
    this.form = new FormGroup({
      email: new FormControl(''),
      password: new FormControl('')
    });
  }

  public signinClick(){
    this._router.navigate(['/register']);
  }

  public onSubmitSignIn(){
    console.log("aqui !!!");
  }
}
