import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
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
  submitted = false;
  showCongratulations: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router) {
  }

  onSubmit() {
    this.submitted = true;
    if (this.email.trim() === '' || this.password.trim() === '') {
      return;
    }else{
      this.authService.register(this.email, this.password)
          .subscribe({
            next: (response) => {
              this.showCongratulations = true; // Mostrar mensaje de felicitación
        
              // Redirigir después de 5 segundos
              setTimeout(() => {
                this.router.navigate(['/login']);
              }, 5000);
            },
            error: (error) => {
              this.errorMessage = 'Error, intente Luego';
              this.successMessage = '';
              setTimeout(() => {
                  this.errorMessage = '';
              }, 5000)
            }
          });
      }
    }

}
