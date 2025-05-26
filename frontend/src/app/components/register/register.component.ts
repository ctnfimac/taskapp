import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  email: string = '';
  password: string = '';
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private fb: FormBuilder,) {
  }

  onSubmit() {
    console.log('hola')
    console.log(this.email)
    console.log(this.password)

    this.authService.register(this.email, this.password)
        .subscribe({
          next: (response) => {
            this.successMessage = '¡Registro exitoso!';
            this.errorMessage = '';
            this.email = '';
            this.password = '';
          },
          error: (error) => {
            this.errorMessage = 'Error, intente Luego';
            this.successMessage = '';
          }
        });
  }

}
