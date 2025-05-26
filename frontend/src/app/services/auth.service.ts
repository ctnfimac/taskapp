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

  register(email: String, password: String): Observable<AuthResponseDTO> {
    console.log('entro acá')
    const userData = {
      email,
      password
    };

    return this.http.post<AuthResponseDTO>(`${this.apiUrl}/register`,userData);
  }
}