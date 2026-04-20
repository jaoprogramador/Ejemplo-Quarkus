import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MessageService } from 'primeng/api'; // Para notificaciones
import { Toast } from 'primeng/toast';
import { Select } from 'primeng/select'; // Dropdown en v19
import { Button } from 'primeng/button';
import { InputText } from 'primeng/inputtext';
import { InputNumber } from 'primeng/inputnumber';
import { Textarea } from 'primeng/textarea';
import { FloatLabel } from 'primeng/floatlabel';
import { RecetaService } from '../../receta.service';
import { Receta } from '../../../../models/receta.model';

@Component({
  selector: 'app-receta-form',
  standalone: true,
  providers: [MessageService], // Proveedor local para el toast
  imports: [
    CommonModule, FormsModule, RouterLink,
    Button, InputText, InputNumber, Select, Toast, FloatLabel
  ],
  templateUrl: './receta-form.component.html'
})
export class RecetaFormComponent implements OnInit{
  private route = inject(ActivatedRoute);
  isEditMode = false;

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.isEditMode = true;
      this.cargarReceta(id);
    }
  }
  cargarReceta(id: number) {
    this.recetaService.getRecetaById(id).subscribe({
      next: (data) => this.nuevaReceta = data,
      error: () => this.messageService.add({ severity: 'error', summary: 'Error', detail: 'No se encontró la receta' })
    });
  }
  private recetaService = inject(RecetaService);
  private messageService = inject(MessageService);
  private router = inject(Router);

  // Opciones para los selectores
  dificultades = [
    { label: 'Fácil', value: 'Fácil' },
    { label: 'Media', value: 'Media' },
    { label: 'DIFÍCIL', value: 'DIFICIL' }
  ];

  categorias = [
    { label: 'Pasta', value: 'Pasta' },
    { label: 'Carne', value: 'Carne' },
    { label: 'Postre', value: 'Postre' }
  ];

  nuevaReceta: any = {
    nombre: '',
    tiempoPreparacion: 15, // Mínimo 10 por tu @Min(10)
    ingredientes: '',
    dificultad: 'Media',
    categoria: null,
    activo: 'S'
  };
  registrar(): void {

    const servicio = this.isEditMode
      ? this.recetaService.update(this.nuevaReceta)
      : this.recetaService.save(this.nuevaReceta);

    servicio.subscribe({
      next: () => {
        this.messageService.add({
          severity: 'success',
          summary: 'Completado',
          detail: this.isEditMode ? 'Receta actualizada' : 'Receta creada'
        });
        setTimeout(() => this.router.navigate(['/recetas']), 1500);
      }
    });
  }


}
