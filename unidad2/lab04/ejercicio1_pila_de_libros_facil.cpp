// G-07 - Ejercicio 1: Pila de libros en la mesa
// La biblioteca deja los libros devueltos uno encima de otro.
// El ultimo que llega queda arriba (inicio de la lista).
// Usamos: push_front, front, pop_front, empty, insert_after y distance

#include <iostream>
#include <forward_list>
#include <string>
#include <iterator>   // para distance
using namespace std;

void mostrar(const forward_list<string>& pila) {
    cout << "Pila: ";
    for (const string& libro : pila)
        cout << "[" << libro << "] ";
    cout << endl;
}

int main() {
    forward_list<string> pila;

    // van llegando los libros devueltos
    pila.push_front("Calculo I");
    pila.push_front("Algebra Lineal");
    pila.push_front("Fisica");
    mostrar(pila);

    cout << "Libro de arriba: " << pila.front() << endl;

    // forward_list no tiene size(), contamos con distance
    cout << "Cantidad de libros: " << distance(pila.begin(), pila.end()) << endl;

    // alguien mete un libro justo debajo del primero
    pila.insert_after(pila.begin(), "Programacion en C++");
    mostrar(pila);

    // el bibliotecario va guardando de arriba hacia abajo
    while (!pila.empty()) {
        cout << "Guardando: " << pila.front() << endl;
        pila.pop_front();
    }

    cout << "Pila vacia? " << (pila.empty() ? "si" : "no") << endl;
    return 0;
}
