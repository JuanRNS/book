import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./core/components/sign-up/sign-up.component').then(m => m.SignupComponent)
    },
    {
        path: 'login',
        loadComponent: () => import('./core/components/sign-in/sign-in.component').then(m => m.SiginComponent)
    }
];
