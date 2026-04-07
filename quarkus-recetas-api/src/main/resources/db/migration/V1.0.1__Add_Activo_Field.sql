-- Añadimos la columna con un valor por defecto para no romper los datos existentes
ALTER TABLE Receta ADD COLUMN activo VARCHAR(1) DEFAULT 'S' NOT NULL;

-- asegurar que los registros viejos son 'S' (aunque el DEFAULT ya lo hace)
UPDATE Receta SET activo = 'S' WHERE activo IS NULL;