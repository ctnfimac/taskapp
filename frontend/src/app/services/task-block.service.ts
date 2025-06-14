import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { HasBlockActiveResponseDTO, TaskBlockResponseDTO } from './dtos/taskblock';
import { TaskCreateResponseDTO } from './dtos/task';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TaskBlockService {
  private apiUrl = environment.taskBlockBaseUrl;
  //private apiUrl = 'http://127.0.0.1:8092/api/v1/block';

  constructor(private http: HttpClient) { }

  /**
   * Registro del usuario
   */
  add(title: String, userId: number): Observable<TaskBlockResponseDTO> {
    const data = {
      title,
      userId
    };

    return this.http.post<TaskBlockResponseDTO>(`${this.apiUrl}`,data);
  }

  /**
   * Agrego una tarea nueva al bloque de tareas
   */
  addTask(userId?: number, blockId?: number, title?: string): Observable<TaskCreateResponseDTO> {
     const data = {
        title,
        userId
    }
    return this.http.post<TaskCreateResponseDTO>(`${this.apiUrl}/${blockId}/task`, data);
  }


  /**
   * Elimino una tarea de un bloque de tareas
   */
  deleteTask(blockId?: number, taskId?: number): Observable<null> {
    return this.http.delete<null>(`${this.apiUrl}/${blockId}/task/${taskId}`);
  }
  

  /**
   * Finalizar bloque de tareas
   */
  finishTaskBlock(blockId?: number, userId?: number): Observable<null> {
    const data = {
      userId
    }
    return this.http.patch<null>(`${this.apiUrl}/${blockId}/finish`, data);
  }


  /**
   * verifico si el usuario tiene un bloque de tareas activo
   */
  hasBlockActive(userId?: number): Observable<HasBlockActiveResponseDTO> {
    return this.http.get<HasBlockActiveResponseDTO>(`${this.apiUrl}/has-block-active/${userId}`);
  }


  /**
   * Elimino un bloque de tareas y sus tareas relacionadas
   */
  deleteTaskBlock(blockId?: number, userId?: number): Observable<null> {
    return this.http.delete<null>(`${this.apiUrl}/${blockId}/${userId}`);
  }

}