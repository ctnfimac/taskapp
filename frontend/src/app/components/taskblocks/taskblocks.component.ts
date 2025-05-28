import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';
import { TaskBlockAllResponseDTO } from '../../services/dtos/taskblock';


@Component({
  selector: 'app-taskblocks',
  standalone: true,
  imports: [CommonModule, FormsModule, 
      RouterModule, MatIconModule,
      MatListModule, MatDividerModule,
      MatCheckboxModule],
  templateUrl: './taskblocks.component.html',
  styleUrl: './taskblocks.component.scss'
})
export class TaskblocksComponent {
  tarea: string = '';
  userId?: number;
  taskBlocks: TaskBlockAllResponseDTO[] = [];

  constructor(
      private authService: AuthService, 
      private userService: UserService
      ) {
      this.userId = this.authService.getCurrentUser()?.id;
    }
  

  ngOnInit() {
    if(this.userId != null) {
      this.userService.getAllTaskBlockByUser(this.userId)
        .subscribe({
          next: (response) => {
            this.taskBlocks = response;
          },
          error: (error) => {
            console.error('Error al obtener las el bloque de tareas:', error);
          }
        });
    }
  }

  detailTaskBlock(blockId: number) {
    console.log('detalle del bloque de tarea con id:', blockId);
    // Aquí agrego la lógica
  }
}
