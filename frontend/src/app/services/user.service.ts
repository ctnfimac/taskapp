import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { TaskAllResponseDTO, TaskResponseToogleDTO } from './dtos/task';
import { ListTaskAllResponseDTO, TaskBlockAllResponseDTO } from './dtos/taskblock';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://127.0.0.1:8091/api/v1/user';

  constructor(
    private http: HttpClient,
    ) { 
    
  }

  /**
   * Obtengo todos los bloques de tarea de un usuario
   */
  getAllTaskBlockByUser(userId: Number): Observable<TaskBlockAllResponseDTO[]> {
    return this.http.get<TaskBlockAllResponseDTO[]>(`${this.apiUrl}/${userId}/blocks`);
  }

}
