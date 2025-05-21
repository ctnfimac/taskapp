import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import {MatButton} from '@angular/material/button';
import {MatTooltip} from '@angular/material/tooltip';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatCheckboxModule} from '@angular/material/checkbox';


@Component({
  selector: 'app-taskblocks',
  standalone: true,
  imports: [CommonModule, FormsModule, 
      RouterModule, MatButton, 
      MatTooltip, MatIconModule,
      MatListModule, MatDividerModule,
      MatCheckboxModule],
  templateUrl: './taskblocks.component.html',
  styleUrl: './taskblocks.component.scss'
})
export class TaskblocksComponent {
  tarea: string = '';


  onSubmit() {
    console.log('Email:', this.tarea);
    // Aquí agrego la lógica
  }
}
