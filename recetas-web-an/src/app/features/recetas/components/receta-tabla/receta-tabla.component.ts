import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { RouterLink } from '@angular/router';

import { Receta } from '../../../../models/receta.model';
import { RecetaService } from '../../receta.service';

@Component({
  selector: 'app-receta-tabla',
  standalone: true,
  // 2. Añadir RouterLink a los imports
  imports: [CommonModule, TableModule, ButtonModule, RouterLink],
  templateUrl: './receta-tabla.component.html',
})
export class RecetaTablaComponent implements OnInit {
  private recetaService = inject(RecetaService);
  recetas = signal<Receta[]>([]);

  ngOnInit() {
    this.cargarRecetas();
  }

  cargarRecetas() {
    this.recetaService.getRecetas().subscribe(data => this.recetas.set(data));
  }

  eliminar(id: number) {
    if (confirm('¿Estás seguro de que deseas eliminar esta receta?')) {
      this.recetaService.delete(id).subscribe({
        next: () => {
          this.cargarRecetas();
          console.log('Receta eliminada correctamente');
        },
        error: (err) => console.error('Error al eliminar:', err)
      });
    }
  }
}
