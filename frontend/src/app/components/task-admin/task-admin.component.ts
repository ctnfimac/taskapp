import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import {MatButton} from '@angular/material/button';
import {MatTooltip} from '@angular/material/tooltip';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { AuthService } from '../../services/auth.service';


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
  tarea: string = '';


  onSubmit() {
    console.log('Email:', this.tarea);
    // Aquí agrego la lógica
  }
  title: string = '';

  constructor(
    private route: ActivatedRoute, 
    private authService: AuthService, 
    private router: Router) {
      
    if (this.authService && !this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
    }

  }

  ngOnInit() {
    // pongo este titulo cuando redirijo de la pantalla en donde pongo
    // el nombre del bloque de tareas
    this.route.queryParams.subscribe(params => {
      this.title = params['title'] || 'Título por defecto';
    });
  }
}
