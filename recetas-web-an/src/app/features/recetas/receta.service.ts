import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable,tap } from 'rxjs';

//import { Receta } from '../../../shared/models/receta.model';
//import { Receta } from '../../shared/models/receta.model';
import { Receta } from '../../models/receta.model';

@Injectable({ providedIn: 'root' })
export class RecetaService {
  private http = inject(HttpClient);
  private readonly URL = 'http://localhost:8080/recetas';

  // Listar todas (o filtradas por tiempo)
  getRecetas(tiempo?: number): Observable<Receta[]> {
    // Forzamos la ruta que funciona en Postman
    let endpoint = `${this.URL}/tiempo`;

    if (tiempo) {
      endpoint += `?tiempoPreparacion=${tiempo}`;
    }
    console.log('--- RecetaService: Llamando a GET ---');
    console.log('URL de destino:', endpoint);

    return this.http.get<Receta[]>(endpoint).pipe(
      // TRAZA 2: Ver qué responde el servidor (solo si la petición tiene éxito)
      tap({
        next: (data) => console.log('Datos recibidos de Quarkus:', data),
        error: (err) => console.error('Error capturado en el Service:', err)
      })
    );
  }

  // Crear
  save(receta: Receta): Observable<Receta> {
    return this.http.post<Receta>(this.URL, receta);
  }

  // Eliminar
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.URL}/${id}`);
  }

  // Obtener una por ID
  getRecetaById(id: number): Observable<Receta> {
    return this.http.get<Receta>(`${this.URL}/${id}`);
  }

// Actualizar
  update(receta: Receta): Observable<Receta> {
    return this.http.put<Receta>(`${this.URL}/${receta.id}`, receta);
  }
}
