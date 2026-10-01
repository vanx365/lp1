// G-07 - Ejercicio 2: Juntar las notas de dos secciones
// Tenemos las notas de la seccion A y la seccion B.
// Queremos una sola lista ordenada, sin notas repetidas,
// sin desaprobados (menos de 11) y de mayor a menor.
// Usamos: sort, merge, unique, remove_if y reverse

#include <iostream>
#include <forward_list>
using namespace std;

void mostrar(const string& titulo, const forward_list<int>& lista) {
    cout << titulo;
    for (int n : lista) cout << n << " ";
    cout << endl;
}

int main() {
    forward_list<int> seccionA = {14, 9, 17, 11, 20, 8};
    forward_list<int> seccionB = {15, 11, 6, 18, 14};

    mostrar("Seccion A: ", seccionA);
    mostrar("Seccion B: ", seccionB);

    // merge solo funciona bien si las dos listas ya estan ordenadas
    seccionA.sort();
    seccionB.sort();

    seccionA.merge(seccionB);   // B queda vacia, sus nodos pasan a A
    mostrar("Juntas:    ", seccionA);
    cout << "B quedo vacia? " << (seccionB.empty() ? "si" : "no") << endl;

    seccionA.unique();          // quita repetidos seguidos (por eso ordenamos antes)
    mostrar("Sin repetir: ", seccionA);

    seccionA.remove_if([](int nota) { return nota < 11; });
    mostrar("Aprobados:   ", seccionA);

    seccionA.reverse();
    mostrar("Mayor a menor: ", seccionA);

    return 0;
}
