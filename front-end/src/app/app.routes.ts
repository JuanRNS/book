import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./core/components/signin/sigin.component').then(m => m.SiginComponent)
    }
];
