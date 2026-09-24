// G-07 - Ejercicio 3: Lista de asistencia en orden alfabetico
// Los alumnos llegan en desorden y hay que meterlos en la lista
// ya ordenada. Luego un alumno se retira del curso.
// Truco de forward_list: siempre trabajamos con el nodo ANTERIOR.
// Usamos: before_begin, insert_after, erase_after

#include <iostream>
#include <forward_list>
#include <string>
using namespace std;

void mostrar(const forward_list<string>& lista) {
    int i = 1;
    for (const string& nombre : lista)
        cout << "  " << i++ << ". " << nombre << endl;
}

// inserta el nombre en su lugar para que la lista siga ordenada
void insertarOrdenado(forward_list<string>& lista, const string& nombre) {
    auto anterior = lista.before_begin();
    auto actual = lista.begin();
    while (actual != lista.end() && *actual < nombre) {
        anterior = actual;
        ++actual;
    }
    lista.insert_after(anterior, nombre);
}

// borra al alumno buscando el nodo anterior a el
bool retirar(forward_list<string>& lista, const string& nombre) {
    auto anterior = lista.before_begin();
    for (auto actual = lista.begin(); actual != lista.end(); ++actual) {
        if (*actual == nombre) {
            lista.erase_after(anterior);
            return true;
        }
        anterior = actual;
    }
    return false;
}

int main() {
    forward_list<string> asistencia;
    string llegadas[] = {"Quispe", "Apaza", "Mamani", "Zevallos", "Condori"};

    for (const string& alumno : llegadas)
        insertarOrdenado(asistencia, alumno);

    cout << "Lista ordenada:" << endl;
    mostrar(asistencia);

    string sale = "Mamani";
    if (retirar(asistencia, sale))
        cout << sale << " se retiro del curso" << endl;

    if (!retirar(asistencia, "Huaman"))
        cout << "Huaman no estaba en la lista" << endl;

    cout << "Lista final:" << endl;
    mostrar(asistencia);
    return 0;
}
