import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-taskblock-admin',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './taskblock-admin.component.html',
  styleUrl: './taskblock-admin.component.scss'
})
export class TaskblockAdminComponent {
  title: string = '';

  onSubmit() {
    console.log('Email:', this.title);
    // Aquí puedes agregar la lógica para manejar el envío del formulario
  }
}
