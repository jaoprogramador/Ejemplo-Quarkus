import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable,tap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Receta,RespuestaPaginada } from '../../models/receta.model';

@Injectable({ providedIn: 'root' })
export class RecetaService {
  private http = inject(HttpClient);

  private readonly URL = `${environment.apiUrl}/recetas`;
  // Listar todas (o filtradas por tiempo)
  getRecetas(pagina: number = 1, tiempo?: number): Observable<RespuestaPaginada<Receta>> {
    // Si tu nuevo endpoint de Quarkus sigue la lógica de categorías,
    // lo normal es que la URL base sea `${this.URL}` con QueryParams
    let endpoint = `${this.URL}?pagina=${pagina}`;

    if (tiempo) {
      endpoint += `&tiempoPreparacion=${tiempo}`;
    }

    return this.http.get<RespuestaPaginada<Receta>>(endpoint).pipe(
      tap(resp => console.log('Respuesta paginada recibida:', resp))
    );
  }

  // Crear
  save(receta: Receta): Observable<Receta> {
    console.log("SAVE:::SERVICE::Datos que se van a enviar al servidor:", receta);
    return this.http.post<Receta>(this.URL, receta);
  }

  // Eliminar
  delete(id: number): Observable<void> {
    console.log("DELETE:::SERVICE::Datos que se van a enviar al servidor:", id);
    return this.http.delete<void>(`${this.URL}/${id}`);
  }

  // Obtener una por ID
  getRecetaById(id: number): Observable<Receta> {
    console.log("getid:::SERVICE::Datos que se van a enviar al servidor:", id);
    return this.http.get<Receta>(`${this.URL}/${id}`);
  }

// Actualizar
  update(receta: Receta): Observable<Receta> {
    console.log("UPDATE:::SERVICE::Datos que se van a enviar al servidor:", receta);
    return this.http.put<Receta>(`${this.URL}/${receta.id}`, receta);
  }
}
