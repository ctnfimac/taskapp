import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
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

  constructor(
    private authService: AuthService,
    private router: Router) {
  }

  onSubmit() {
    this.authService.register(this.email, this.password)
        .subscribe({
          next: (response) => {
            this.successMessage = '¡Registro exitoso!';
            this.errorMessage = '';
            this.email = '';
            this.password = '';
            // Redirigir después de 5 segundos
              setTimeout(() => {
                this.router.navigate(['/login']);
              }, 5000);
          },
          error: (error) => {
            this.errorMessage = 'Error, intente Luego';
            this.successMessage = '';
          }
        });
  }

}
