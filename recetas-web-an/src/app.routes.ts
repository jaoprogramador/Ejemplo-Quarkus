import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: 'recetas', component: RecetaTablaComponent },
  { path: 'recetas/nueva', component: RecetaFormComponent },
  { path: 'recetas/editar/:id', component: RecetaFormComponent }, // Reutilizable para editar
  { path: '', redirectTo: 'recetas', pathMatch: 'full' }
    // Por ahora, como estamos empezando, lo dejamos vacío
    // Más adelante aquí pondremos la ruta a 'detalles' o 'editar'
];
