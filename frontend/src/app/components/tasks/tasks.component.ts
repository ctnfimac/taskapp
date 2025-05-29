import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterModule } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';
import { TaskResponseDTO } from '../../services/dtos/task';

@Component({
  selector: 'app-tasks',
  standalone: true,
  imports: [CommonModule, FormsModule, 
        RouterModule, MatIconModule,
        MatListModule, MatDividerModule,
        MatCheckboxModule],
  templateUrl: './tasks.component.html',
  styleUrl: './tasks.component.scss'
})
export class TasksComponent implements OnInit{
    tasks: TaskResponseDTO[] = [];
    blockId?: number;
    blockTitle: string = '';
    userId?: number;
  
    constructor(
      private route: ActivatedRoute,
      private authService: AuthService,
      private userService: UserService
    ) {
      this.userId = this.authService.getCurrentUser()?.id;
    }
  
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.blockId = params['blockId'];
      this.blockTitle = params['blockTitle'];
      if (this.blockId) {
        this.loadTasks();
      }
    });
  }

  private loadTasks() {
    if (this.blockId) {
      this.userService.getAllTasksByUser(this.blockId, this.userId)
      .subscribe({
        next: (response) => {
          this.tasks = response;
        },
        error: (error) => {
          console.error('Error al cargar las tareas');
        }
      });
    }
  }
  
}
