// G-07 - Complemento: forward_list con la libreria <algorithm>
// Los iteradores de forward_list solo avanzan (forward iterators),
// asi que funcionan los algoritmos que recorren de ida.

#include <iostream>
#include <forward_list>
#include <algorithm>
#include <numeric>    // accumulate
using namespace std;

int main() {
    forward_list<int> notas = {14, 9, 17, 11, 20, 8, 17};

    // buscar
    auto it = find(notas.begin(), notas.end(), 20);
    if (it != notas.end()) cout << "Encontre la nota " << *it << endl;

    // contar
    cout << "Veces que aparece 17: " << count(notas.begin(), notas.end(), 17) << endl;
    cout << "Desaprobados: "
         << count_if(notas.begin(), notas.end(), [](int n) { return n < 11; }) << endl;

    // maximo y minimo
    cout << "Mayor: " << *max_element(notas.begin(), notas.end()) << endl;
    cout << "Menor: " << *min_element(notas.begin(), notas.end()) << endl;

    // sumar y promediar (no hay size, usamos distance)
    int suma = accumulate(notas.begin(), notas.end(), 0);
    long n = distance(notas.begin(), notas.end());
    cout << "Promedio: " << (double)suma / n << endl;

    // modificar con for_each: +1 punto a todos
    for_each(notas.begin(), notas.end(), [](int& x) { x++; });

    // std::sort NO compila con forward_list: se usa el metodo propio
    notas.sort();
    cout << "Ordenada: ";
    for (int x : notas) cout << x << " ";
    cout << endl;

    // binary_search funciona, pero avanza paso a paso
    cout << "Hay un 21? " << (binary_search(notas.begin(), notas.end(), 21) ? "si" : "no") << endl;
    return 0;
}
