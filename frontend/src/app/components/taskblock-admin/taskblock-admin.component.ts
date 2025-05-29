import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { TaskBlockService } from '../../services/task-block.service';

@Component({
  selector: 'app-taskblock-admin',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './taskblock-admin.component.html',
  styleUrl: './taskblock-admin.component.scss'
})
export class TaskblockAdminComponent {
  title: string = '';
  successMessage: string = '';
  errorMessage: string = '';
  userId: number = 0;
  

  constructor(
    private taskBlockservice: TaskBlockService,
    private authService: AuthService, 
    private router: Router
  ) {
    
    const currentUser = this.authService.getCurrentUser();

    if(currentUser?.id != null){
      this.userId = currentUser?.id;
    }
   
  }

  onSubmit() {
    if(!this.campoVacio()){
      this.taskBlockservice.add(this.title, this.userId)
          .subscribe({
            next: (response) => {
              this.successMessage = 'Nuevo bloque de tarea creado!';
              this.errorMessage = '';
              this.title = '';
              
              // Redirigir después de 3 segundos
              setTimeout(() => {
                this.router.navigate(['tasks']);
              }, 3000);
            },
            error: (error) => {
              this.errorMessage = 'Error agregando un nuevo bloque de tarea';
              this.successMessage = '';
            }
          });
    }else{
      this.errorMessage = 'El título es obligatorio';
      this.successMessage = '';
    }
  }

  campoVacio(){
    return this.title == '';
  }
}
