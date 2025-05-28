/*import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from "./components/register/register.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
}
*/

import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth.service';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { filter, Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, MatButtonModule, MatIconModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy{
  showLogoutButton: boolean = false;
  private subscription: Subscription = new Subscription();

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
  }

  ngOnInit() {
    // Suscribirse a los cambios de autenticación
    this.subscription.add(
      this.authService.isAuthenticated$.subscribe(isAuthenticated => {
        this.updateLogoutButtonVisibility(isAuthenticated);
      })
    );

    // Suscribirse a los cambios de ruta
    this.subscription.add(
      this.router.events.pipe(
        filter(event => event instanceof NavigationEnd)
      ).subscribe((event: any) => {
        this.updateLogoutButtonVisibility(this.authService.isAuthenticated());
      })
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  private updateLogoutButtonVisibility(isAuthenticated: boolean) {
    const currentRoute = this.router.url;
    this.showLogoutButton = isAuthenticated && !['/login', '/register', ''].includes(currentRoute);
  }

}

