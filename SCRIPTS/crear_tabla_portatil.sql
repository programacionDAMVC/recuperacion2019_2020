DROP TABLE IF EXISTS portatil;
--insert into portatil ( ram, sdd, pantalla) values ( 4, 3000, 19);
CREATE TABLE portatil (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	ram INTEGER NOT NULL,
	ssd INTEGER NOT NULL,
	pantalla INTEGER NOT NULL
)
--- TIPOS DATOS
-- TEXT: cadenas
-- INTEGER: enteros
-- REAL: reales

