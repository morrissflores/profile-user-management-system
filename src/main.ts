import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(),
    provideRouter([
      {path: '', loadComponent: () => import('./app/components/profile-list/profile-list.component').then(m => m.ProfileListComponent)},
      {path: 'add-profile', loadComponent: () => import('./app/components/profile-form/profile-form.component').then(m => m.ProfileFormComponent)},
    ])
  ]
} ).catch((err) => console.error(err));
