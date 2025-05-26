import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthRequestDTO, AuthResponseDTO } from './dtos/auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://127.0.0.1:8091/api/v1/auth';

  constructor(private http: HttpClient) { }

  /**
   * Registro del usuario
   */
  register(email: String, password: String): Observable<AuthResponseDTO> {
    const userData = {
      email,
      password
    };

    return this.http.post<AuthResponseDTO>(`${this.apiUrl}/register`,userData);
  }

  /**
   * Inicio de sesión del usuario
   */
  login(email: String, password: String): Observable<AuthResponseDTO> {
    const userData = {
      email,
      password
    };

    return this.http.post<AuthResponseDTO>(`${this.apiUrl}/login`,userData);
  }
}