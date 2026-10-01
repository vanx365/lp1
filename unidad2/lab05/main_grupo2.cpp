    // ============================================================
// Laboratorio 05 - Lenguaje de Programación I
// Grupo 2: contenedores asociativos set<int> y map<int, Estudiante>
// Sistema de registro de estudiantes
// Compilar: g++ -std=c++17 -Wall -Wextra -o registro main.cpp
// ============================================================

#include <algorithm>
#include <iomanip>
#include <iostream>
#include <limits>
#include <map>
#include <set>
#include <string>

#ifdef _WIN32
#include <windows.h>  // para activar UTF-8 en la consola de Windows
#endif

using namespace std;

// ------------------------------------------------------------
// Estructura de datos
// ------------------------------------------------------------
struct Estudiante {
    int codigo;
    string nombre;
    string carrera;
    double promedio;
};

// set  -> garantiza códigos únicos y ordenados
// map  -> relaciona CÓDIGO -> ESTUDIANTE
using SetCodigos = set<int>;
using MapEstudiantes = map<int, Estudiante>;

// ------------------------------------------------------------
// Utilidades de entrada (validan que el usuario escriba bien)
// ------------------------------------------------------------
string leerLinea(const string& mensaje) {
    string texto;
    do {
        cout << mensaje;
        getline(cin, texto);
        if (texto.empty()) cout << "  El texto no puede estar vacío.\n";
    } while (texto.empty());
    return texto;
}

int leerEntero(const string& mensaje) {
    while (true) {
        cout << mensaje;
        string linea;
        getline(cin, linea);
        try {
            size_t pos = 0;
            int valor = stoi(linea, &pos);
            if (pos == linea.size()) return valor;
        } catch (...) {
        }
        cout << "  Ingrese un número entero válido.\n";
    }
}

double leerPromedio(const string& mensaje) {
    while (true) {
        cout << mensaje;
        string linea;
        getline(cin, linea);
        try {
            size_t pos = 0;
            double valor = stod(linea, &pos);
            if (pos == linea.size() && valor >= 0.0 && valor <= 20.0) return valor;
        } catch (...) {
        }
        cout << "  Ingrese un promedio válido entre 0 y 20.\n";
    }
}

// ------------------------------------------------------------
// Funciones del sistema
// ------------------------------------------------------------
// setw() cuenta bytes, y las tildes en UTF-8 ocupan 2: este helper cuenta caracteres reales.
string rellenar(const string& texto, size_t ancho) {
    size_t caracteres = 0;
    for (unsigned char c : texto) {
        if ((c & 0xC0) != 0x80) caracteres++;  // omite bytes de continuación UTF-8
    }
    return texto + string(ancho > caracteres ? ancho - caracteres : 0, ' ');
}

void imprimirEstudiante(const Estudiante& e) {
    cout << rellenar(to_string(e.codigo), 6) << rellenar(e.nombre, 20) << rellenar(e.carrera, 26)
         << fixed << setprecision(1) << e.promedio << "\n";
}

// Registra el código en el set y el estudiante en el map.
// El set decide si el código es nuevo: insert() devuelve pair<iterador, bool>.
bool registrarEstudiante(SetCodigos& codigos, MapEstudiantes& estudiantes, const Estudiante& e) {
    auto resultado = codigos.insert(e.codigo);
    if (!resultado.second) {
        return false;  // código duplicado: no se registra nada
    }
    estudiantes.insert({e.codigo, e});
    return true;
}

// Busca con map::find(): no recorre todos los estudiantes.
void buscarPorCodigo(const MapEstudiantes& estudiantes, int codigo) {
    auto it = estudiantes.find(codigo);
    if (it == estudiantes.end()) {
        cout << "  No existe un estudiante con el código " << codigo << ".\n";
        return;
    }
    cout << "  Estudiante encontrado:\n";
    cout << "  Código:   " << it->first << "\n";            // clave
    cout << "  Nombre:   " << it->second.nombre << "\n";    // valor
    cout << "  Carrera:  " << it->second.carrera << "\n";
    cout << "  Promedio: " << fixed << setprecision(1) << it->second.promedio << "\n";
}

// Elimina el código del set y el estudiante del map (ambos deben quedar sincronizados).
bool eliminarEstudiante(SetCodigos& codigos, MapEstudiantes& estudiantes, int codigo) {
    size_t eliminadosSet = codigos.erase(codigo);       // erase por clave devuelve cuántos borró
    size_t eliminadosMap = estudiantes.erase(codigo);
    return eliminadosSet > 0 && eliminadosMap > 0;
}

// Recorre el set con iteradores: siempre sale en orden ascendente.
void mostrarCodigos(const SetCodigos& codigos) {
    if (codigos.empty()) {
        cout << "  No hay códigos registrados.\n";
        return;
    }
    cout << "  Códigos (ordenados): ";
    for (auto it = codigos.begin(); it != codigos.end(); ++it) {
        cout << *it << " ";
    }
    cout << "\n  Total: " << codigos.size() << "\n";
}

// Recorre el map con iteradores: sale ordenado por clave (código).
void mostrarEstudiantes(const MapEstudiantes& estudiantes) {
    if (estudiantes.empty()) {
        cout << "  No hay estudiantes registrados.\n";
        return;
    }
    cout << "  " << rellenar("COD", 6) << rellenar("NOMBRE", 20) << rellenar("CARRERA", 26) << "PROM\n";
    cout << "  " << string(58, '-') << "\n";
    for (auto it = estudiantes.begin(); it != estudiantes.end(); ++it) {
        cout << "  ";
        imprimirEstudiante(it->second);
    }
}

// El map está ordenado por código, no por promedio, así que aquí sí hay que recorrer.
void mostrarMayorPromedio(const MapEstudiantes& estudiantes) {
    if (estudiantes.empty()) {
        cout << "  No hay estudiantes registrados.\n";
        return;
    }
    auto mejor = max_element(estudiantes.begin(), estudiantes.end(),
                             [](const auto& a, const auto& b) {
                                 return a.second.promedio < b.second.promedio;
                             });
    cout << "  Estudiante con mayor promedio:\n  ";
    imprimirEstudiante(mejor->second);
}

// Verifica con set::find() (solo existencia, sin datos asociados).
void verificarCodigo(const SetCodigos& codigos, int codigo) {
    auto it = codigos.find(codigo);
    if (it != codigos.end()) {
        cout << "  El código " << codigo << " SÍ está registrado.\n";
    } else {
        cout << "  El código " << codigo << " NO está registrado.\n";
    }
}

// ------------------------------------------------------------
// Pruebas del reto (usan contenedores propios para no mezclar datos)
// ------------------------------------------------------------
void prueba1Duplicados() {
    cout << "\n--- PRUEBA 1: Duplicados ---\n";
    set<int> s;
    map<int, Estudiante> m;
    int entrada[] = {1001, 1002, 1003, 1001, 1002};
    int intento = 0;

    for (int c : entrada) {
        intento++;
        bool nuevoSet = s.insert(c).second;
        // Cada intento usa un nombre distinto para ver cuál se conserva en el map
        bool nuevoMap = m.insert({c, Estudiante{c, "Intento " + to_string(intento), "Prueba", 10.0}}).second;
        cout << "  insert(" << c << ")  set: " << (nuevoSet ? "insertado" : "RECHAZADO (duplicado)")
             << "  | map: " << (nuevoMap ? "insertado" : "RECHAZADO (duplicado)") << "\n";
    }
    cout << "  Tamaño final -> set: " << s.size() << ", map: " << m.size() << "\n";

    // Qué pasa si insertamos otro estudiante con una clave existente
    m.insert({1001, Estudiante{1001, "Otro Nombre", "Otra Carrera", 20.0}});
    cout << "  map[1001] después de insert() repetido: " << m.at(1001).nombre
         << " (se conserva el original)\n";
}

void prueba2Orden() {
    cout << "\n--- PRUEBA 2: Orden ---\n";
    set<int> s;
    map<int, Estudiante> m;
    int entrada[] = {1050, 1002, 1010, 1001, 1030};

    cout << "  Orden de ingreso: ";
    for (int c : entrada) {
        cout << c << " ";
        s.insert(c);
        m.insert({c, Estudiante{c, "Estudiante " + to_string(c), "Prueba", 10.0}});
    }
    cout << "\n  Orden en set:     ";
    for (int c : s) cout << c << " ";
    cout << "\n  Orden en map:     ";
    for (const auto& par : m) cout << par.first << " ";
    cout << "\n";
}

void prueba3Busqueda() {
    cout << "\n--- PRUEBA 3: Búsqueda ---\n";
    set<int> s = {1050, 1002, 1010, 1001, 1030};
    map<int, Estudiante> m;
    for (int c : s) m.insert({c, Estudiante{c, "Estudiante " + to_string(c), "Prueba", 10.0}});

    int buscar[] = {1001, 1050, 9999};
    for (int c : buscar) {
        auto its = s.find(c);
        auto itm = m.find(c);
        cout << "  Código " << c << "\n";
        cout << "    set.find():  " << (its != s.end() ? "encontrado, solo devuelve el código " + to_string(*its)
                                                       : "no encontrado")
             << "\n";
        cout << "    map.find():  " << (itm != m.end() ? "encontrado, devuelve el estudiante: " + itm->second.nombre
                                                       : "no encontrado")
             << "\n";
    }
}

void prueba4ClaveValor() {
    cout << "\n--- PRUEBA 4: Relación clave/valor ---\n";
    cout << "  map<int, Estudiante>\n";
    cout << "    int         -> clave (código único, define la posición en el árbol)\n";
    cout << "    Estudiante  -> valor (toda la información asociada al código)\n";
    cout << "  Ejemplo: 1001 -> {Ana Torres, Ingeniería de Software, 17.5}\n";
    cout << "  set<int> solo guarda el código; map<int, Estudiante> guarda código + datos.\n";
}

void ejecutarPruebas() {
    prueba1Duplicados();
    prueba2Orden();
    prueba3Busqueda();
    prueba4ClaveValor();
}

// ------------------------------------------------------------
// Menú
// ------------------------------------------------------------
void mostrarMenu() {
    cout << "\n===================================\n";
    cout << " SISTEMA DE REGISTRO DE ESTUDIANTES\n";
    cout << "===================================\n\n";
    cout << "1. Registrar estudiante\n";
    cout << "2. Buscar por código\n";
    cout << "3. Eliminar estudiante\n";
    cout << "4. Mostrar códigos registrados\n";
    cout << "5. Mostrar estudiantes\n";
    cout << "6. Mostrar estudiante con mayor promedio\n";
    cout << "7. Verificar código\n";
    cout << "8. Ejecutar pruebas del reto (extra)\n";
    cout << "0. Salir\n";
}

int main() {
#ifdef _WIN32
    SetConsoleOutputCP(CP_UTF8);
#endif

    SetCodigos codigos;
    MapEstudiantes estudiantes;

    // Datos de ejemplo del enunciado
    registrarEstudiante(codigos, estudiantes, {1001, "Ana Torres", "Ingeniería de Software", 17.5});
    registrarEstudiante(codigos, estudiantes, {1002, "Carlos Pérez", "Ingeniería de Sistemas", 15.8});
    registrarEstudiante(codigos, estudiantes, {1003, "María López", "Ingeniería de Software", 18.2});

    int opcion;
    do {
        mostrarMenu();
        opcion = leerEntero("\nOpción: ");
        cout << "\n";

        switch (opcion) {
            case 1: {
                Estudiante e;
                e.codigo = leerEntero("Código: ");
                if (codigos.count(e.codigo)) {  // aviso temprano para no pedir todos los datos en vano
                    cout << "  Código duplicado: el set no permite repetidos. No se registró.\n";
                    break;
                }
                e.nombre = leerLinea("Nombre: ");
                e.carrera = leerLinea("Carrera: ");
                e.promedio = leerPromedio("Promedio (0-20): ");
                if (registrarEstudiante(codigos, estudiantes, e)) {
                    cout << "  Estudiante registrado correctamente.\n";
                } else {
                    cout << "  Código duplicado. No se registró.\n";
                }
                break;
            }
            case 2:
                buscarPorCodigo(estudiantes, leerEntero("Código a buscar: "));
                break;
            case 3:
                if (eliminarEstudiante(codigos, estudiantes, leerEntero("Código a eliminar: "))) {
                    cout << "  Estudiante eliminado.\n";
                } else {
                    cout << "  No existe ese código.\n";
                }
                break;
            case 4:
                mostrarCodigos(codigos);
                break;
            case 5:
                mostrarEstudiantes(estudiantes);
                break;
            case 6:
                mostrarMayorPromedio(estudiantes);
                break;
            case 7:
                verificarCodigo(codigos, leerEntero("Código a verificar: "));
                break;
            case 8:
                ejecutarPruebas();
                break;
            case 0:
                cout << "Hasta luego.\n";
                break;
            default:
                cout << "  Opción inválida.\n";
        }
    } while (opcion != 0);

    return 0;
}
