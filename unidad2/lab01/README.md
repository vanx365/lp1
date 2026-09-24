<div align="center">

# 🔗 std::forward_list

### La lista que solo mira hacia adelante

**Exposición del Grupo G-07 sobre Containers de C++**

![C++](https://img.shields.io/badge/C%2B%2B-17-00599C?style=for-the-badge&logo=cplusplus&logoColor=white)
![Container](https://img.shields.io/badge/Container-forward__list-E8833A?style=for-the-badge)
![Header](https://img.shields.io/badge/Header-%3Cforward__list%3E-14213D?style=for-the-badge)
![OmegaUP](https://img.shields.io/badge/OmegaUP-Josefo-2F6690?style=for-the-badge)

<br>

<table>
<tr>
<td align="center"><b>Universidad</b></td>
<td>Universidad La Salle, Arequipa</td>
</tr>
<tr>
<td align="center"><b>Carrera</b></td>
<td>Ingeniería de Software</td>
</tr>
<tr>
<td align="center"><b>Curso</b></td>
<td>Lenguaje de Programación I (ING-II)</td>
</tr>
<tr>
<td align="center"><b>Docente</b></td>
<td>Richart Smith Escobedo Quispe</td>
</tr>
<tr>
<td align="center"><b>Tema</b></td>
<td>Containers: <code>std::forward_list</code></td>
</tr>
<tr>
<td align="center"><b>Grupo</b></td>
<td>G-07</td>
</tr>
<tr>
<td align="center"><b>Año</b></td>
<td>2026</td>
</tr>
</table>

<br>

### 👥 Integrantes

| Integrante | Parte que expone |
|:---:|:---|
| **José Luis Hancco Valencia** | Presentación, qué es la forward_list y Ejercicio 1 |
| **Sebastián Coaguila** | Comparación, métodos, Ejercicio 2 |
| **Robert Vilca** | Ejercicio 3 y problema de OmegaUP |

<br>

🎥 **Video de la exposición:** [ver en YouTube](PEGAR_ENLACE_DEL_VIDEO) &nbsp;&nbsp; 📚 **Referencia:** [cppreference.com/cpp/container/forward_list](https://en.cppreference.com/w/cpp/container/forward_list)

</div>

<br>

## 📑 Contenido

1. [¿Qué es una forward_list?](#-qué-es-una-forward_list)
2. [Comparación con vector y list](#%EF%B8%8F-comparación-con-vector-y-list)
3. [Métodos principales](#-métodos-principales)
4. [forward_list y la librería algorithm](#-forward_list-y-la-librería-algorithm)
5. [Ejercicios del grupo](#-ejercicios-del-grupo)
6. [Problema de OmegaUP](#-problema-de-omegaup-josefo)
7. [Cómo compilar y ejecutar](#%EF%B8%8F-cómo-compilar-y-ejecutar)
8. [Estructura del repositorio](#-estructura-del-repositorio)
9. [Conclusiones](#-conclusiones)
10. [Referencias](#-referencias)

<br>

## 🧠 ¿Qué es una forward_list?

`std::forward_list` es el container de C++ que implementa una **lista enlazada simple**. Apareció en **C++11** y se usa con:

```cpp
#include <forward_list>
```

Cada elemento vive en un **nodo** que guarda dos cosas: el dato y un puntero al nodo siguiente. El último nodo apunta a `nullptr`. Como cada nodo solo conoce al que viene después, la lista **solo se puede recorrer hacia adelante**, y de ahí viene su nombre.

```mermaid
flowchart LR
    BB["before_begin()"]:::falso -.-> A["10 | next"]
    A --> B["20 | next"]
    B --> C["30 | next"]
    C --> N(["nullptr"])
    classDef falso stroke-dasharray: 5 5
```

`before_begin()` es una posición "falsa" que está antes del primer elemento. No se puede leer, pero sirve para insertar o borrar al inicio con las funciones `_after`.

**Lo bueno** ✅
- Insertar o borrar cuesta **O(1)** cuando ya tienes el iterador del nodo anterior.
- Gasta **un solo puntero** por elemento: es la lista que menos memoria usa.
- `sort`, `merge`, `reverse` y `splice_after` trabajan cambiando punteros, sin copiar datos.

**Lo malo** ⚠️
- No tiene acceso por índice (`lista[3]` no existe).
- No se puede recorrer hacia atrás.
- No tiene `size()`. Para contar se usa `std::distance(l.begin(), l.end())`, que es O(n).

<br>

## ⚖️ Comparación con vector y list

| Característica | `vector` | `list` | `forward_list` |
|:---|:---:|:---:|:---:|
| Cómo se guarda | Bloque contiguo | Enlace doble | **Enlace simple** |
| Acceso por índice `a[i]` | Sí, O(1) | No | No |
| Insertar al inicio | O(n) | O(1) | **O(1)** |
| Insertar en medio (con iterador) | O(n) | O(1) | **O(1), después del iterador** |
| Recorrer hacia atrás | Sí | Sí | No |
| Tiene `size()` | Sí | Sí | No |
| Punteros extra por elemento | 0 | 2 | **1** |

> 💡 La idea del estándar fue tener una lista que no gaste nada que no uses. Si no necesitas ir hacia atrás ni saber el tamaño a cada rato, `forward_list` es más liviana que `list`.

<br>

## 🧰 Métodos principales

Casi todo lo que modifica la lista termina en **`_after`**. La razón: en una lista simple, para meter o sacar un nodo hay que cambiar el puntero del nodo **anterior**, y desde un nodo no se puede volver atrás.

| Grupo | Métodos | Para qué sirven |
|:---|:---|:---|
| **Acceso** | `front()` | Devuelve el primer elemento |
| **Iteradores** | `before_begin()`, `begin()`, `end()` y sus versiones `c...` | Posición antes del inicio, inicio y fin |
| **Capacidad** | `empty()`, `max_size()` | Saber si está vacía y el máximo posible |
| **Modificadores** | `push_front()`, `pop_front()`, `emplace_front()` | Meter o sacar al inicio |
| | `insert_after()`, `emplace_after()`, `erase_after()` | Meter o borrar después de una posición |
| | `clear()`, `resize()`, `swap()` | Vaciar, cambiar tamaño, intercambiar |
| **Operaciones** | `sort()`, `merge()`, `unique()`, `reverse()` | Ordenar, juntar listas ordenadas, quitar repetidos seguidos, voltear |
| | `remove()`, `remove_if()` | Borrar por valor o por condición |
| | `splice_after()` | Mover nodos desde otra forward_list |

**No tiene:** `size()`, `back()`, `push_back()`, `operator[]`.

<br>

## 🧮 forward_list y la librería algorithm

Además de sus métodos propios, `forward_list` se puede usar con la librería `<algorithm>` (y `<numeric>`). La regla es sencilla: sus iteradores son **forward iterators**, es decir, solo avanzan. Por eso funcionan los algoritmos que recorren "de ida", pero no los que necesitan saltar a cualquier posición o retroceder.

| Algoritmo | ¿Funciona? | Comentario |
|:---|:---:|:---|
| `find`, `find_if` | ✅ | Busca recorriendo desde el inicio |
| `count`, `count_if` | ✅ | Cuenta por valor o por condición |
| `min_element`, `max_element` | ✅ | Menor y mayor elemento |
| `for_each` | ✅ | Aplica una función a cada elemento |
| `accumulate` (`<numeric>`) | ✅ | Suma los elementos |
| `replace`, `fill`, `is_sorted` | ✅ | Recorren una sola vez hacia adelante |
| `binary_search` | ✅ | Funciona, pero avanza paso a paso, así que no es tan rápido como en un vector |
| `std::sort` | ❌ | Necesita acceso aleatorio. Se usa el método **`lista.sort()`** |
| `std::reverse` | ❌ | Necesita ir hacia atrás. Se usa el método **`lista.reverse()`** |
| `qsort`, `bsearch` (de C) | ❌ | Piden un arreglo contiguo y la lista está repartida en nodos |

El archivo [`ejemplo_algorithms.cpp`](ejemplo_algorithms.cpp) lo muestra con un conjunto de notas:

```cpp
forward_list<int> notas = {14, 9, 17, 11, 20, 8, 17};

auto it = find(notas.begin(), notas.end(), 20);                        // busca el 20
count_if(notas.begin(), notas.end(), [](int n) { return n < 11; });    // desaprobados
*max_element(notas.begin(), notas.end());                              // nota mayor
accumulate(notas.begin(), notas.end(), 0);                             // suma
for_each(notas.begin(), notas.end(), [](int& x) { x++; });             // +1 a todos

notas.sort();   // std::sort(notas.begin(), notas.end()) NO compila
```

<details>
<summary><b>Ver salida</b></summary>

```
Encontre la nota 20
Veces que aparece 17: 2
Desaprobados: 2
Mayor: 20
Menor: 8
Promedio: 13.7143
Ordenada: 9 10 12 15 18 18 21
Hay un 21? si
```
</details>

<br>

## 💻 Ejercicios del grupo

Cada integrante desarrolló y explica un ejercicio. Los tres usan situaciones del día a día para que se vea para qué sirve cada método.

### 📚 Ejercicio 1: La pila de libros devueltos
**Expone:** José Luis Hancco Valencia &nbsp;·&nbsp; **Archivo:** [`ejercicio1_pila_de_libros.cpp`](ejercicio1_pila_de_libros.cpp)

**Resumen.** En la mesa de la biblioteca los libros devueltos se apilan: el último que llega queda arriba. Eso es justo lo que hace `push_front`. El programa apila tres libros, muestra el de arriba con `front`, cuenta cuántos hay con `distance` (porque no existe `size`), mete un libro debajo del primero con `insert_after` y al final los va guardando uno por uno con `pop_front` hasta que la pila queda vacía.

**Métodos usados:** `push_front` · `front` · `distance` · `insert_after` · `pop_front` · `empty`

<details>
<summary><b>Ver salida</b></summary>

```
Pila: [Fisica] [Algebra Lineal] [Calculo I]
Libro de arriba: Fisica
Cantidad de libros: 3
Pila: [Fisica] [Programacion en C++] [Algebra Lineal] [Calculo I]
Guardando: Fisica
Guardando: Programacion en C++
Guardando: Algebra Lineal
Guardando: Calculo I
Pila vacia? si
```
</details>

### 📝 Ejercicio 2: Juntar las notas de dos secciones
**Expone:** Sebastián Coaguila &nbsp;·&nbsp; **Archivo:** [`ejercicio2_notas_secciones.cpp`](ejercicio2_notas_secciones.cpp)

**Resumen.** Tenemos las notas de la sección A y de la sección B, y queremos una sola lista limpia. Primero se ordenan las dos con `sort`, porque `merge` solo funciona bien con listas ya ordenadas. `merge` pasa los nodos de B a A sin copiarlos, así que B queda vacía. Luego `unique` quita las notas repetidas (sirve porque ya están ordenadas y los repetidos quedan juntos), `remove_if` con una lambda saca a los desaprobados y `reverse` deja la lista de mayor a menor.

**Métodos usados:** `sort` · `merge` · `unique` · `remove_if` · `reverse`

<details>
<summary><b>Ver salida</b></summary>

```
Seccion A: 14 9 17 11 20 8
Seccion B: 15 11 6 18 14
Juntas:    6 8 9 11 11 14 14 15 17 18 20
B quedo vacia? si
Sin repetir: 6 8 9 11 14 15 17 18 20
Aprobados:   11 14 15 17 18 20
Mayor a menor: 20 18 17 15 14 11
```
</details>

### 🧾 Ejercicio 3: Lista de asistencia en orden alfabético
**Expone:** Robert Vilca &nbsp;·&nbsp; **Archivo:** [`ejercicio3_lista_asistencia.cpp`](ejercicio3_lista_asistencia.cpp)

**Resumen.** Los alumnos llegan en desorden y la lista tiene que quedar siempre en orden alfabético. Aquí se ve el truco más importante de `forward_list`: se camina con **dos iteradores**, `anterior` y `actual`. Para insertar, se avanza hasta encontrar el lugar y se usa `insert_after(anterior, ...)`. Si el nombre va primero, `anterior` sigue en `before_begin()` y el alumno queda al inicio. Para retirar a un alumno se hace lo mismo y se borra con `erase_after(anterior)`. Si el nombre no está en la lista, no se toca nada.

**Métodos usados:** `before_begin` · `begin` · `end` · `insert_after` · `erase_after`

<details>
<summary><b>Ver salida</b></summary>

```
Lista ordenada:
  1. Apaza
  2. Condori
  3. Mamani
  4. Quispe
  5. Zevallos
Mamani se retiro del curso
Huaman no estaba en la lista
Lista final:
  1. Apaza
  2. Condori
  3. Quispe
  4. Zevallos
```
</details>

<br>

## 🏆 Problema de OmegaUP: Josefo

**Expone:** Robert Vilca &nbsp;·&nbsp; **Archivo:** [`omegaup_josefo.cpp`](omegaup_josefo.cpp) &nbsp;·&nbsp; **Problema:** [enlace en OmegaUP](PEGAR_ENLACE_DE_OMEGAUP)

**Enunciado.** Hay `n` personas en círculo, numeradas del 1 al `n`. Se cuenta de `k` en `k` y la persona donde cae la cuenta sale del círculo. Se sigue contando desde la siguiente hasta que queda una sola. Hay que imprimir el orden en que salen y quién sobrevive.

| Entrada | Salida |
|:---:|:---|
| `7 3` | `3 6 2 7 5 1`<br>`Sobreviviente: 4` |

**¿Por qué forward_list?** Porque todo el problema es **avanzar y borrar al siguiente**, y eso es lo que mejor hace este container: `erase_after` borra en O(1) sin mover a nadie.

**Idea de la solución.**
1. **Llenar:** `push_front` desde `n` hasta 1, así la lista queda `1, 2, ..., n`.
2. **Contar:** un iterador `anterior` avanza `k - 1` veces. Cuando llega al último nodo, vuelve a `before_begin()`: así se simula el círculo.
3. **Sacar:** se imprime el siguiente de `anterior` y se borra con `erase_after(anterior)`.
4. Se repite hasta que queda uno, que es `front()`.

```cpp
auto anterior = circulo.before_begin();
while (quedan > 1) {
    for (int paso = 0; paso < k - 1; paso++) {
        ++anterior;
        if (next(anterior) == circulo.end())   // dimos la vuelta
            anterior = circulo.before_begin();
    }
    cout << *next(anterior) << " ";
    circulo.erase_after(anterior);
    quedan--;
    if (next(anterior) == circulo.end())
        anterior = circulo.before_begin();
}
```

✔️ **Verificación:** la solución se probó con todos los casos de `n` y `k` entre 1 y 14 (196 casos) contra una simulación independiente, y coincidió en todos.

<br>

## ⚙️ Cómo compilar y ejecutar

Con g++ (C++11 o superior):

```bash
g++ -std=c++17 -Wall ejercicio1_pila_de_libros.cpp -o ejercicio1
./ejercicio1

g++ -std=c++17 -Wall omegaup_josefo.cpp -o josefo
echo "7 3" | ./josefo
```

También se pueden pegar directamente en [OnlineGDB](https://www.onlinegdb.com/online_c++_compiler) eligiendo el lenguaje C++17.

<br>

## 📂 Estructura del repositorio

```
G07-forward_list/
├── README.md
├── ejercicio1_pila_de_libros.cpp     # José Luis Hancco Valencia
├── ejercicio2_notas_secciones.cpp    # Sebastián Coaguila
├── ejercicio3_lista_asistencia.cpp   # Robert Vilca
├── omegaup_josefo.cpp                # Robert Vilca
└── ejemplo_algorithms.cpp            # complemento con <algorithm>
```

<br>

## 🎯 Conclusiones

- `forward_list` es la lista más liviana de la STL: un solo puntero por nodo y nada de datos extra.
- La clave para usarla es pensar siempre en el **nodo anterior**. Por eso sus métodos terminan en `_after` y por eso existe `before_begin()`.
- Conviene cuando solo recorres hacia adelante y haces muchas inserciones o borrados en medio. Si necesitas índices, el tamaño a cada rato o ir hacia atrás, es mejor `vector` o `list`.
- Con `<algorithm>` funcionan los algoritmos que avanzan (`find`, `count`, `for_each`...). Para ordenar o voltear se usan sus métodos propios `sort()` y `reverse()`.

<br>

## 📚 Referencias

- cppreference.com. *std::forward_list*. https://en.cppreference.com/w/cpp/container/forward_list
- cppreference.com. *Containers library*. https://en.cppreference.com/w/cpp/container
- cppreference.com. *Algorithms library*. https://en.cppreference.com/w/cpp/algorithm
- Wikipedia. *Problema de Flavio Josefo*. https://es.wikipedia.org/wiki/Problema_de_Flavio_Josefo

<br>

<div align="center">

**Grupo G-07** · Lenguaje de Programación I · Universidad La Salle · 2026

</div>
