import { Injectable, PLATFORM_ID, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap, BehaviorSubject } from 'rxjs';
import { AuthRequestDTO, AuthResponseDTO } from './dtos/auth';
import { isPlatformBrowser } from '@angular/common';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://127.0.0.1:8091/api/v1/auth';
  private readonly USER_KEY = 'currentUserTask';
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(this.isAuthenticated());
  isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { 
    // Inicializar el estado de autenticación
    this.isAuthenticatedSubject.next(this.isAuthenticated());
  }

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

    return this.http.post<AuthResponseDTO>(`${this.apiUrl}/login`,userData)
      .pipe(
        tap(response => {
          if (isPlatformBrowser(this.platformId)) {
            localStorage.setItem(this.USER_KEY, JSON.stringify(response));
            // Actualizo el estado de autenticación después de guardar en localStorage
            this.isAuthenticatedSubject.next(true);
          }
        })
      );
  }

  /**
   * Cerrar sesión
   */
  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem(this.USER_KEY);
    }
    this.isAuthenticatedSubject.next(false);
  }

  /**
   * Obtener usuario actual
   */
  getCurrentUser(): AuthResponseDTO | null {
     if (isPlatformBrowser(this.platformId)) {
      const userStr = localStorage.getItem(this.USER_KEY);
      if (userStr) {
        return JSON.parse(userStr);
      }
    }
    return null;
  }

  /**
   * Verificar si el usuario está autenticado
   */
  isAuthenticated(): boolean {
    //return this.getCurrentUser() !== null;
    if (isPlatformBrowser(this.platformId)) {
      return !!localStorage.getItem(this.USER_KEY);
    }
    return false;
  }
  

}