# Conclusiones — Laboratorio 05, Grupo 2 (`set` y `map`)

## 1. Resultados de las pruebas

Salida real del programa (opción 8).

### Prueba 1 — Duplicados

Entrada: `1001, 1002, 1003, 1001, 1002`

| Intento | `set` | `map` |
| --- | --- | --- |
| 1001 | insertado | insertado |
| 1002 | insertado | insertado |
| 1003 | insertado | insertado |
| 1001 | rechazado | rechazado |
| 1002 | rechazado | rechazado |

**¿Qué ocurre con los valores repetidos?** Se rechazan. `insert()` no modifica el contenedor y devuelve `false` en el `bool` del `pair` que retorna. Ambos contenedores terminan con tamaño 3. En el `map`, el estudiante que se conserva es el **primero** registrado; el segundo intento no lo reemplaza.

### Prueba 2 — Orden

| | Orden |
| --- | --- |
| Ingreso | 1050, 1002, 1010, 1001, 1030 |
| `set` | 1001, 1002, 1010, 1030, 1050 |
| `map` (por clave) | 1001, 1002, 1010, 1030, 1050 |

**¿En qué orden aparecen?** En orden ascendente, sin importar el orden de ingreso.

### Prueba 3 — Búsqueda

| Código | `set.find()` | `map.find()` |
| --- | --- | --- |
| 1001 | encontrado, devuelve el código | encontrado, devuelve el estudiante completo |
| 1050 | encontrado, devuelve el código | encontrado, devuelve el estudiante completo |
| 9999 | `end()` (no existe) | `end()` (no existe) |

Ambos usan la misma estrategia de búsqueda y comparan con `end()` para saber si existe. La diferencia es qué **devuelven**: `set` solo confirma que el código está; `map` da acceso a los datos asociados (`it->second`).

### Prueba 4 — Relación clave/valor

```text
map<int, Estudiante>
  int        → clave  (código único)
  Estudiante → valor  (nombre, carrera, promedio)
```

La clave identifica de forma única al estudiante y el valor contiene su información. `set<int>` solo almacena la clave.

## 2. Preguntas del Grupo 2

**1. ¿Por qué `set` no permite duplicados?**
Porque modela un conjunto matemático: cada elemento aparece una sola vez. Al insertar, el `set` busca si el valor ya existe y, si es así, no hace nada. Para permitir repetidos existe `multiset`.

**2. ¿Los elementos de `set` se mantienen ordenados?**
Sí. Se guardan siempre ordenados según el criterio de comparación (por defecto `std::less`, es decir, ascendente). Por eso recorrerlo con iteradores da los códigos ordenados sin ordenar manualmente.

**3. ¿Qué representa la clave en un `map`?**
El identificador único del elemento. En nuestro sistema, el código del estudiante. Las claves son únicas y determinan el orden del `map`.

**4. ¿Qué representa el valor?**
La información asociada a la clave. En nuestro caso, el objeto `Estudiante` con sus datos.

**5. ¿Cuál es la diferencia conceptual entre `set` y `map`?**
`set` responde a «¿este elemento existe?» y almacena solo valores. `map` responde a «¿qué dato corresponde a esta clave?» y almacena pares clave → valor. Dicho de otro modo: `set` es una colección de claves; `map` es una colección de claves con datos asociados.

**6. ¿Qué ventaja proporciona `map` cuando conocemos el código del estudiante?**
Acceso directo a toda su información con `find(codigo)`, sin escribir bucles ni comparar estudiante por estudiante. El código queda más corto y la búsqueda es más eficiente que en un contenedor secuencial.

**7. ¿Qué ocurre si intentamos insertar dos estudiantes con el mismo código?**
Con `insert()`, el segundo se rechaza y se conserva el primero (la prueba 1 lo muestra: `map.at(1001)` sigue siendo el estudiante original). Con `operator[]` o `insert_or_assign()` el valor sí se **sobrescribe**, por lo que hay que elegir la operación según lo que se quiera. En nuestro sistema usamos `insert()` precisamente para no pisar datos existentes.

**8. ¿Por qué no sería necesario recorrer todos los estudiantes para buscar un código con `map::find()`?**
Porque el `map` mantiene sus claves ordenadas en una estructura de árbol balanceado (típicamente rojo-negro en las implementaciones habituales). La búsqueda descarta la mitad de los elementos en cada paso, con complejidad **O(log n)**, en lugar de revisar uno por uno como haría un `vector` (**O(n)**).

## 3. Tabla comparativa final

| Característica | `deque` | `list` | `set` | `map` |
| --- | --- | --- | --- | --- |
| Tipo de contenedor | Secuencial | Secuencial (lista doblemente enlazada) | Asociativo | Asociativo |
| Permite duplicados | Sí | Sí | No | No (en las claves) |
| Mantiene orden | Orden de inserción | Orden de inserción | Sí, ordenado por valor | Sí, ordenado por clave |
| Acceso mediante `[]` | Sí, por posición | No | No | Sí, por clave |
| Inserción al inicio | Sí, eficiente (`push_front`) | Sí, eficiente (`push_front`) | No por posición; la ubicación la decide el orden | No por posición; la ubicación la decide la clave |
| Inserción al final | Sí, eficiente (`push_back`) | Sí, eficiente (`push_back`) | No por posición; la ubicación la decide el orden | No por posición; la ubicación la decide la clave |
| Búsqueda | Lineal, O(n) | Lineal, O(n) | `find()`, O(log n) | `find()` por clave, O(log n) |
| Uso principal | Colas y operaciones en ambos extremos | Muchas inserciones/eliminaciones con iteradores | Elementos únicos y ordenados | Relacionar clave → valor |

> `deque` y `list` se completaron como referencia; los resultados del Grupo 1 deben contrastarse en la puesta en común.

## 4. ¿Por qué no un `vector<Estudiante>`? (pregunta de la exposición)

Con un `vector<Estudiante>` tendríamos que:

- **Evitar duplicados a mano:** recorrer todo el vector antes de cada inserción para comprobar que el código no exista.
- **Buscar linealmente** cada vez que se consulta un código (O(n)).
- **Ordenar manualmente** (`sort`) cada vez que se quiera mostrar ordenado.

Con `set` y `map`, el contenedor resuelve esas tres necesidades por sí mismo: unicidad, orden y búsqueda rápida por clave.

## 5. Ventajas y limitaciones observadas

| | Ventajas | Limitaciones |
| --- | --- | --- |
| `set<int>` | Unicidad y orden automáticos; búsqueda rápida | No guarda datos asociados; no se accede por posición; los elementos no se pueden modificar directamente |
| `map<int, Estudiante>` | Acceso directo por código; unicidad de claves; recorrido ordenado | Mayor consumo de memoria que un `vector`; ordenado por clave, no por otros campos (el mayor promedio exige recorrer); `operator[]` crea elementos si la clave no existe |

**Cuándo usarlos en un sistema real:** `set` para listas de códigos permitidos, etiquetas o IDs ya procesados; `map` para diccionarios, índices y tablas de consulta (código → registro).

Mantener `set` y `map` en simultáneo es redundante en este prototipo, ya que las claves del `map` ya son únicas y ordenadas. Lo hicimos porque el enunciado pide contrastar ambos contenedores. En un sistema real bastaría con el `map`.

## 6. Conclusión general

La elección del contenedor depende del problema y de las operaciones más frecuentes:

```text
vector → acceso secuencial mediante posición
deque  → operaciones eficientes en ambos extremos
list   → inserciones/eliminaciones con iteradores
set    → elementos únicos y ordenados
map    → clave → valor
```

Para un registro de estudiantes donde se consulta por código y no se aceptan repetidos, `set` y `map` encajan mejor que cualquier contenedor secuencial.
