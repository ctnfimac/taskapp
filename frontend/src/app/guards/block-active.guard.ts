import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { TaskBlockService } from '../services/task-block.service';
import { AuthService } from '../services/auth.service';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

export const blockActiveGuard = () => {
  const taskBlockService = inject(TaskBlockService);
  const authService = inject(AuthService);
  const router = inject(Router);

  const userId = authService.getCurrentUser()?.id;

  if (!userId) {
    return router.parseUrl('/login');
  }

  return taskBlockService.hasBlockActive(userId).pipe(
    map(response => {
      if (response.blockActive) {
        return router.parseUrl('/tasks');
      }
      return true;
    })
  );
};