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
  categoria?: any;
}

// Nueva interfaz para las respuestas del servidor
export interface RespuestaPaginada<T> {
  pagina: number;
  totalPaginas: number;
  datos: T[]; // <-- Verifica que se llame 'datos', tal como sale en tu consola
}
