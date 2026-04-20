import { Component, OnInit, inject, signal, computed } from '@angular/core'; // 1. Añadido computed
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext'; // 2. Añadir este módulo para el input
import { RouterLink } from '@angular/router';

import { Receta } from '../../../../models/receta.model';
import { RecetaService } from '../../receta.service';

@Component({
  selector: 'app-receta-tabla',
  standalone: true,
  // 3. Importante incluir InputTextModule aquí
  imports: [CommonModule, TableModule, ButtonModule, RouterLink, InputTextModule],
  templateUrl: './receta-tabla.component.html',
})
export class RecetaTablaComponent implements OnInit {
  private recetaService = inject(RecetaService);

  // Lista original que viene de la base de datos
  recetas = signal<Receta[]>([]);

  // Texto que el usuario escribe en el buscador
  filtro = signal<string>('');

  // 4. Lógica de filtrado automática
  recetasFiltradas = computed(() => {
    const busqueda = this.filtro().toLowerCase().trim();

    // Si no hay nada escrito, devolvemos la lista completa
    if (!busqueda) {
      return this.recetas();
    }

    // Si hay texto, filtramos por el campo ingredientes
    return this.recetas().filter(receta =>
      receta.ingredientes?.toLowerCase().includes(busqueda)
    );
  });

  ngOnInit() {
    this.cargarRecetas();
  }

  cargarRecetas() {
    this.recetaService.getRecetas().subscribe({
      next: (respuesta: any) => {
        console.log('Respuesta recibida:', respuesta);

        if (respuesta && respuesta.datos) {
          this.recetas.set(respuesta.datos);
        }
      },
      error: (err) => console.error('Error al cargar recetas:', err)
    });
  }

  // 5. Método para capturar lo que el usuario escribe
  onSearch(event: Event) {
    const input = event.target as HTMLInputElement;
    this.filtro.set(input.value);
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
