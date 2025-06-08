import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import {MatButton, MatButtonModule} from '@angular/material/button';
import {MatTooltip} from '@angular/material/tooltip';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { AuthService } from '../../services/auth.service';
import { TaskService } from '../../services/task.service';
import { TaskResponseDTO } from '../../services/dtos/task';
import { TaskBlockService } from '../../services/task-block.service';
import {
  MatDialog,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogModule,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';



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
  taskTitle: string = '';
  titleBlock: string = '';
  tasks: TaskResponseDTO[] = [];
  userId?: number;
  taskBlockId?: number;
  // title: string = '';
  // Propiedad para controlar el estado del botón de finalizar bloque de tareas
  allTasksCompleted: boolean = false;

  showCongratulations: boolean = false;

  readonly dialog = inject(MatDialog);

  constructor(
    private route: ActivatedRoute, 
    private authService: AuthService, 
    private taskService: TaskService,
    private taskBlockService: TaskBlockService,
    private router: Router
    ) {
    this.userId = this.authService.getCurrentUser()?.id;
  }

  ngOnInit() {
    if(this.userId != null) {
      this.taskService.getTaksAndTitleBlock(this.userId)
        .subscribe({
          next: (response) => {
            this.taskBlockId = response.idTaskBlock;
            this.titleBlock = response.titleBlock;
            this.tasks = response.listTasks;
            this.checkAllTasksCompleted();
          },
          error: (error) => {
            console.error('Error al obtener las tareas:', error);
          }
        });
    }
  }

  addTask(){
    this.taskBlockService.addTask(this.userId, this.taskBlockId, this.taskTitle)
      .subscribe({
            next: (response) => {
              console.info('Creando una nueva tarea.');
              // Actualizar la lista de tareas después de cambiar el estado
              if (this.userId) this.refreshTaskList(this.userId)
              this.taskTitle = '';
            },
            error: (error) => {
              console.error('Error agregando una nueva tarea:');
            }
      })
  }

  deleteTask(taskId: number) {
    this.taskBlockService.deleteTask(this.taskBlockId, taskId)
      .subscribe({
            next: (response) => {
              console.info('Eliminando una tarea.');
              if (this.userId) this.refreshTaskList(this.userId)
            },
            error: (error) => {
              console.error('Error eliminando una tarea:');
            }
      })
  }

  toggleDone(taskId: number) {
    this.taskService.toogleDone(taskId, this.userId, this.taskBlockId)
      .subscribe({
          next: (response) => {
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
            this.checkAllTasksCompleted(); 
          },
          error: (error) => {
            console.error('Error al actualizar las tareas:', error);
          }
    });
  }

  finishBlock(){
    this.taskBlockService.finishTaskBlock(this.taskBlockId, this.userId)
      .subscribe({
            next: (response) => {
              console.info('Finalizando bloque de tarea');
              this.showCongratulations = true; // Mostrar mensaje de felicitación
        
              // Redirigir después de 5 segundos
              setTimeout(() => {
                this.router.navigate(['/taskblock/add']);
              }, 5000);
              
            },
            error: (error) => {
              console.error('Error Finalizando el bloque de tarea:');
            }
      })
  }

  // Método para verificar si todas las tareas están completadas
  checkAllTasksCompleted(): void {
    this.allTasksCompleted = this.tasks.length > 0 && this.tasks.every(task => task.done);
  }


  /*cancelBlock(){
    console.log('cancelando bloque')
    this.taskBlockService.deleteTaskBlock(this.taskBlockId, this.userId)
      .subscribe({
            next: (response) => {
              console.info('Eliminando bloque de tarea');
              console.log(response)
              setTimeout(() => {
                this.router.navigate(['/taskblock/add']);
              }, 5000);
              //if (response.status) this.refreshTaskList(this.userId)
            },
            error: (error) => {
              console.error('Error eliminando un bloque de tarea');
            }
      })
  }*/

  openDialog(enterAnimationDuration: string, exitAnimationDuration: string): void {
    const dialogRef = this.dialog.open(DialogAnimationsExampleDialog, {
      width: '250px',
      enterAnimationDuration,
      exitAnimationDuration,
    });
    dialogRef.componentInstance.taskBlockId = this.taskBlockId;
    dialogRef.componentInstance.userId = this.userId;
  }

}


@Component({
  standalone: true,
  selector: 'dialog-animations-example-dialog',
  templateUrl: 'dialog-animations-example-dialog.html',
  imports: [MatDialogModule, MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DialogAnimationsExampleDialog {
  readonly dialogRef = inject(MatDialogRef<DialogAnimationsExampleDialog>);
  userId?: number;
  taskBlockId?: number;

  constructor(
    private taskBlockService: TaskBlockService,
    private router: Router
  ) {
  }

  cancelBlock() {
    this.taskBlockService.deleteTaskBlock(this.taskBlockId, this.userId)
      .subscribe({
        next: (response) => {
          console.info('Eliminando bloque de tarea');
          this.dialogRef.close();
          setTimeout(() => {
            this.router.navigate(['/taskblock/add']);
          }, 2000);
        },
        error: (error) => {
          console.error('Error eliminando un bloque de tarea');
        }
      });
  }
}