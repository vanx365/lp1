# Laboratorio 05 — Grupo 2: `set` y `map`

**Curso:** Lenguaje de Programación I · **Unidad II:** Contenedores de C++
**Lenguaje:** C++17 · **Modalidad:** trabajo grupal

**Integrantes:**

- Sebastian Coaguila Suarez 
- Cristhian Chunga Pacheco
- Robert Vilca Mendoza

## 1. Descripción

Prototipo de un **sistema de registro de estudiantes** que usa dos contenedores asociativos de la STL:

| Contenedor | Rol en el sistema |
| --- | --- |
| `set<int> codigos` | Garantiza que los códigos sean **únicos** y los mantiene **ordenados**. |
| `map<int, Estudiante> estudiantes` | Relaciona **código → estudiante** para consultar los datos sin recorrer todo. |

```cpp
struct Estudiante {
    int codigo;
    string nombre;
    string carrera;
    double promedio;
};
```

## 2. Compilación y ejecución

```bash
g++ -std=c++17 -Wall -Wextra -o registro main.cpp
./registro          # en Windows: registro.exe
```

Si las tildes no se ven bien en la consola de Windows, el programa ya activa UTF-8 con `SetConsoleOutputCP(CP_UTF8)`. Guarda `main.cpp` como UTF-8.

## 3. Menú

```text
===================================
 SISTEMA DE REGISTRO DE ESTUDIANTES
===================================

1. Registrar estudiante
2. Buscar por código
3. Eliminar estudiante
4. Mostrar códigos registrados
5. Mostrar estudiantes
6. Mostrar estudiante con mayor promedio
7. Verificar código
8. Ejecutar pruebas del reto (extra)
0. Salir
```

La opción **8** no es parte del menú mínimo: ejecuta las cuatro pruebas del reto (duplicados, orden, búsqueda y relación clave/valor) con contenedores propios, sin alterar los datos del sistema.

El programa arranca con tres estudiantes de ejemplo (1001, 1002 y 1003).

## 4. Operaciones de la STL utilizadas

| Operación | Dónde se usa |
| --- | --- |
| `set::insert()` (devuelve `pair<iterador, bool>`) | Rechaza códigos duplicados al registrar |
| `set::find()` | Verificar código |
| `set::erase()` / `map::erase()` | Eliminar estudiante (se borra de ambos) |
| `map::insert()` | Registrar estudiante |
| `map::find()` | Buscar por código |
| `map::at()` | Prueba de duplicados |
| Iteradores `begin()` / `end()` | Mostrar códigos y estudiantes |
| `max_element` con lambda | Estudiante con mayor promedio |

## 5. Decisiones de diseño

- **Sincronía set/map:** el registro y la eliminación actualizan ambos contenedores en la misma función, para que los códigos del `set` siempre coincidan con las claves del `map`.
- **Validación de entrada:** se lee con `getline` y se valida (enteros, promedio entre 0 y 20, textos no vacíos) para que el programa no falle con entradas inválidas.
- **Mayor promedio:** el `map` está ordenado por código, no por promedio, así que esta consulta sí requiere recorrer los elementos.
- **Funciones separadas:** cada operación del menú es una función independiente; `main` solo coordina.

## 6. Archivos

```text
lab05/
├── README.md
├── main_grupo2.cpp
└── conclusiones.md
```
