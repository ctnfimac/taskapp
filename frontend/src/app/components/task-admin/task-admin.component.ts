import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import {MatButton} from '@angular/material/button';
import {MatTooltip} from '@angular/material/tooltip';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { AuthService } from '../../services/auth.service';
import { TaskService } from '../../services/task.service';
import { TaskResponseDTO } from '../../services/dtos/task';


@Component({
  selector: 'app-task-admin',
  standalone: true,
  imports: [CommonModule, FormsModule, 
    RouterModule, MatButton, 
    MatTooltip, MatIconModule,
    MatListModule, MatDividerModule,
    MatCheckboxModule
  ],
  templateUrl: './task-admin.component.html',
  styleUrl: './task-admin.component.scss'
})
export class TaskAdminComponent {
  tarea: string = '';
  titleBlock: string = '';
  tasks: TaskResponseDTO[] = [];
  userId?: number;
  taskBlockId?: number;
  title: string = '';

  constructor(
    private route: ActivatedRoute, 
    private authService: AuthService, 
    private taskService: TaskService,
    ) {
    this.userId = this.authService.getCurrentUser()?.id;
  }

  ngOnInit() {
    
    this.route.queryParams.subscribe(params => {
      this.title = params['title'] || 'Título por defecto';
    });

    if(this.userId != null) {
      this.taskService.getTaksAndTitleBlock(this.userId)
        .subscribe({
          next: (response) => {
            this.taskBlockId = response.idTaskBlock;
            this.titleBlock = response.titleBlock;
            this.tasks = response.listTasks;
          },
          error: (error) => {
            console.error('Error al obtener las tareas:', error);
          }
        });
    }
  }

  onSubmit() {
    console.log('Email:', this.tarea);
    // TODO: Aquí agrego la lógica
  }

  deleteTask(taskId: number) {
    console.log('Eliminando tarea con ID:', taskId);
    // TODO: Aquí implementaremos la lógica para eliminar la tarea
  }

  toggleDone(taskId: number) {
    console.log('Cambiando estado de tarea con ID:' + taskId + ', userId: ' + this.userId + ', blockId: ' + this.taskBlockId);
    this.taskService.toogleDone(taskId, this.userId, this.taskBlockId)
      .subscribe({
          next: (response) => {
            console.log('Respuesta del servidor:', response);
            // Actualizar la lista de tareas después de cambiar el estado
            if (this.userId) this.refreshTaskList(this.userId)
          },
          error: (error) => {
            console.error('Error en toggleDone:', error);
          }
        })
  }

  refreshTaskList(userId: number){
    this.taskService.getTaksAndTitleBlock(userId)
        .subscribe({
          next: (response) => {
            this.tasks = response.listTasks;
          },
          error: (error) => {
            console.error('Error al actualizar las tareas:', error);
          }
    });
  }
}
