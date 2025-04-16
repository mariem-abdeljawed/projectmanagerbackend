import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component'; // Correct path
import { provideHttpClient } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { routes } from './app/app-routing.module';
import { importProvidersFrom } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(), // Provides HttpClient for HTTP requests
    provideRouter(routes), // Provides routing using the routes from AppRoutingModule
    importProvidersFrom(FormsModule, ReactiveFormsModule) // Provides FormsModule and ReactiveFormsModule globally
  ]
}).catch(err => console.error(err));