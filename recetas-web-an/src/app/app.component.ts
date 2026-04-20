import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <div style="max-width: 1000px; margin: 0 auto; padding: 20px;">
      <header style="border-bottom: 2px solid #eee; margin-bottom: 20px; padding-bottom: 10px;">
        <h1>👨‍🍳 Mi Libro de Recetas JAO</h1>
        <nav style="display: flex; gap: 15px;">
          <a routerLink="/recetas" style="text-decoration: none; color: #007bff; font-weight: bold;">📋 Listado</a>
          <a routerLink="/recetas/nueva" style="text-decoration: none; color: #28a745; font-weight: bold;">➕ Nueva Receta</a>
        </nav>
      </header>

      <main>
        <router-outlet />
      </main>
    </div>
  ` //
})
export class AppComponent { }
