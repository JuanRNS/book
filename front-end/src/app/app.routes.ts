import { Routes } from '@angular/router';
import { AuthGuard } from './core/security/guard/auth.guard';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./core/components/sign-in/sign-in.component').then(m => m.SiginComponent)
    },
    {
        path: 'login',
        loadComponent: () => import('./core/components/sign-in/sign-in.component').then(m => m.SiginComponent)
    },
    {
        path: 'register',
        loadComponent: () => import('./core/components/sign-up/sign-up.component').then(m => m.SignupComponent)
    },
    {
        path:'home',
        loadComponent: () => import('./features/views/home/home.component').then(m => m.HomeComponent),
        canActivate: [AuthGuard],
        children: [
            {
                path: 'contatos',
                loadComponent: () => import('./features/views/contatos/contatos.component').then(m => m.ContatosComponent)
            }
        ]
     
    }
];
