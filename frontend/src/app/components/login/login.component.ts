import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  successMessage: string = '';
  errorMessage: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}
  
  onSubmit() {
    if(!this.campoVacio()){
      this.authService.login(this.email, this.password)
          .subscribe({
            next: (response) => {
              this.successMessage = 'Inicio de sesión exitoso!';
              this.errorMessage = '';
              this.email = '';
              this.password = '';
              
              // Redirigir después de 3 segundos
              setTimeout(() => {
                this.router.navigate(['/taskblock/add']);
              }, 3000);
            },
            error: (error) => {
              this.errorMessage = 'Error con el Inicio de sesión';
              this.successMessage = '';
            }
          });
    }else{
      this.errorMessage = 'Complete todos los campos';
      this.successMessage = '';
    }
  }

  campoVacio(){
    return this.email == '' || this.password == '';
  }
}
