import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { TaskAllResponseDTO, TaskResponseToogleDTO } from './dtos/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl = 'http://127.0.0.1:8092/api/v1/tasks';

  constructor(
    private http: HttpClient,
    ) { 
    
  }

  /**
   * Obtengo información del bloque y las tareas
   */
  getTaksAndTitleBlock(userId: Number): Observable<TaskAllResponseDTO> {
    return this.http.get<TaskAllResponseDTO>(`${this.apiUrl}/${userId}/all`);
  }


  /**
   * Modifico el stado de la tarea, campo done
   */
  toogleDone(taskId: number, userId?: number, taskBlockId?: number): Observable<TaskResponseToogleDTO>{

    const data = {
        taskBlockId,
        userId
    }

    return this.http.patch<TaskResponseToogleDTO>(`${this.apiUrl}/${taskId}/toogle-done`, data);
  }

}