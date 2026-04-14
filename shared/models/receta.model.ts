export interface Receta {
  id: number;
  nombre: string;
  ingredientes: string;
  tiempoPreparacion: number;
  dificultad: string;
  fechaPublicacion: string;
  fechaCreacion?: string;
  fechaActuali?: string;
  activo: string;
  categoria?: any; // Luego lo podemos tipar con otro modelo
}