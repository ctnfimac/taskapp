import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { TaskblockAdminComponent } from './components/taskblock-admin/taskblock-admin.component';
import { TaskAdminComponent } from './components/task-admin/task-admin.component';
import { TaskblocksComponent } from './components/taskblocks/taskblocks.component';
import { TasksComponent } from './components/tasks/tasks.component';
import { authGuard } from './guards/auth.guard';
import { blockActiveGuard } from './guards/block-active.guard';


export const routes: Routes = [
    { 
      path: 'taskblock/add', 
      component: TaskblockAdminComponent,
      canActivate: [authGuard, blockActiveGuard]
    },
    { 
      path: 'taskblock', 
      component: TaskblocksComponent,
      canActivate: [authGuard]
    },
    { 
      path: 'taskblock/tasks/:blockId/:blockTitle',
      component: TasksComponent,
      canActivate: [authGuard]
    },
    { 
      path: 'tasks', 
      component: TaskAdminComponent,
      canActivate: [authGuard]
    },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: '', component: LoginComponent },
];