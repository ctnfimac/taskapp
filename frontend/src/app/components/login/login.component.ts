import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { TaskBlockService } from '../../services/task-block.service';

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
  submitted = false;

  constructor(
    private authService: AuthService,
    private taskBlockService: TaskBlockService,
    private router: Router
  ) {}
  
  onSubmit() {
    this.submitted = true;
    if (this.email.trim() === '' || this.password.trim() === '') {
      return;
    }else{
      this.authService.login(this.email, this.password)
          .subscribe({
            next: (response) => {
              this.successMessage = 'Inicio de sesión exitoso!';
              this.errorMessage = '';
              //this.email = '';
              //this.password = '';
              
              this.userHasBlockActive(response.id)
            },
            error: (error) => {
              this.errorMessage = 'Credenciales incorrectas';
              this.successMessage = '';
              setTimeout(() => {
                this.errorMessage = ''
              }, 3000);
            }
          });
    }
  }
  userHasBlockActive(userId: number){
    this.taskBlockService.hasBlockActive(userId)
      .subscribe({
        next: (response) => {
          let url = response.blockActive == true ? 'tasks' : '/taskblock/add';
          setTimeout(() => {     
            this.router.navigate([url]);
          }, 1500);
        },
        error: (error) => {
          console.log('Error en userHasBlockActive')
        }
    })

  }
}
