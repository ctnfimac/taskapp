import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { TaskblockAdminComponent } from './components/taskblock-admin/taskblock-admin.component';
import { TaskAdminComponent } from './components/task-admin/task-admin.component';
import { TaskblocksComponent } from './components/taskblocks/taskblocks.component';

export const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'taskblock/add', component: TaskblockAdminComponent },
    { path: 'taskblock', component: TaskblocksComponent },
    { path: 'tasks', component: TaskAdminComponent }
];
