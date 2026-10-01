# Laboratorio 05 — Grupo 2: `set` y `map`

**Curso:** Lenguaje de Programación I · **Unidad II:** Contenedores de C++
**Lenguaje:** C++17 · **Modalidad:** trabajo grupal

**Integrantes:**

| Sebastian Coaguila Suarez | 100% |
| Cristhian Chunga Pacheco | 100% |
| Robert Vilca Mendoza | 100% |

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

## 7. Cómo se resolvió el problema

Cada requisito del enunciado se resolvió con una función de `main.cpp` y una operación de la STL:

| Requisito | Función | Solución |
| --- | --- | --- |
| No existen códigos duplicados | `registrarEstudiante()` | `codigos.insert(codigo)` devuelve un `pair<iterador, bool>`. Si el `bool` es `false`, el código ya existía y la función retorna `false` sin tocar el `map`. |
| Códigos ordenados | `mostrarCodigos()` | El `set` los guarda siempre en orden ascendente. Basta recorrerlo con iteradores (`begin()` → `end()`), sin ordenar manualmente. |
| Buscar estudiante por código | `buscarPorCodigo()` | `estudiantes.find(codigo)` devuelve un iterador. Si es `end()`, no existe. Si no, `it->first` es el código y `it->second` es el `Estudiante`. No se recorre todo el contenedor. |
| Eliminar estudiante | `eliminarEstudiante()` | `erase(codigo)` en el `set` y en el `map`. Ambos devuelven cuántos elementos borraron; solo se informa éxito si los dos borraron algo. |
| Mostrar toda la información | `mostrarEstudiantes()` | Recorrido del `map` con iteradores. Sale ordenado por código porque el `map` ordena por clave. |
| Estudiante con mayor promedio | `mostrarMayorPromedio()` | El `map` está ordenado por código, no por promedio, así que se recorre con `max_element` y una lambda que compara `promedio`. |
| Verificar si un código existe | `verificarCodigo()` | `codigos.find(codigo)` y comparación con `end()`. Solo confirma existencia, sin traer datos. |

### Flujo de un registro

```text
Usuario ingresa código
        │
        ▼
¿codigos.count(codigo) > 0? ── sí ──► "Código duplicado", no pide más datos
        │ no
        ▼
Se piden nombre, carrera y promedio (con validación)
        │
        ▼
codigos.insert(codigo)  ──►  estudiantes.insert({codigo, estudiante})
        │
        ▼
Set y map quedan sincronizados
```

### Por qué usamos los dos contenedores

- **`set<int>`:** responde «¿existe este código?». Garantiza unicidad y orden, y no guarda datos adicionales.
- **`map<int, Estudiante>`:** responde «¿qué estudiante tiene este código?». Relaciona código → estudiante.
- **Sincronía:** registrar y eliminar actualizan ambos en la misma función. Así, los códigos del `set` siempre coinciden con las claves del `map`.

### Validación de entrada

- Código y promedio se leen con `getline` y se convierten con `stoi` / `stod`. Si no se puede convertir, se vuelve a pedir.
- El promedio debe estar entre 0 y 20.
- Nombre y carrera no pueden estar vacíos.

### Las pruebas del reto (opción 8)

Las cuatro pruebas usan contenedores propios, así que no alteran los datos del sistema:

1. **Duplicados:** inserta `1001, 1002, 1003, 1001, 1002` y muestra qué `insert()` devuelve `false`.
2. **Orden:** inserta `1050, 1002, 1010, 1001, 1030` y muestra que salen ordenados.
3. **Búsqueda:** busca `1001, 1050, 9999` con `set.find()` y `map.find()`.
4. **Clave/valor:** muestra qué representan la clave y el valor en `map<int, Estudiante>`.
