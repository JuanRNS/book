import { Routes } from '@angular/router';

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
        children: [
            {
                path: 'contatos',
                loadComponent: () => import('./features/views/contatos/contatos.component').then(m => m.ContatosComponent)
            }
        ]
     
    }
];
