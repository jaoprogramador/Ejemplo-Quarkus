import { Routes } from '@angular/router';
import { RecetaTablaComponent } from './features/recetas/components/receta-tabla/receta-tabla.component';
import { RecetaFormComponent } from './features/recetas/components/receta-form/receta-form.component';

export const routes: Routes = [
  { path: 'recetas', component: RecetaTablaComponent },
  { path: 'recetas/nueva', component: RecetaFormComponent },
  { path: 'recetas/editar/:id', component: RecetaFormComponent },
  { path: '', redirectTo: '/recetas', pathMatch: 'full' },
  { path: '**', redirectTo: '/recetas' }
];
