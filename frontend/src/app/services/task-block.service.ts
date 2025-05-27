import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { AuthRequestDTO, AuthResponseDTO } from './dtos/auth';
import { TaskBlockResponseDTO } from './dtos/taskblock';

@Injectable({
  providedIn: 'root'
})
export class TaskBlockService {
  private apiUrl = 'http://127.0.0.1:8092/api/v1/block';

  constructor(private http: HttpClient) { }

  /**
   * Registro del usuario
   */
  add(title: String, userId: Number): Observable<TaskBlockResponseDTO> {

    const data = {
      title,
      userId
    };

    return this.http.post<TaskBlockResponseDTO>(`${this.apiUrl}`,data);
  }


}