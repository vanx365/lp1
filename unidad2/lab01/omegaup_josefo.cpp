// G-07 - Problema tipo OmegaUP: Problema de Josefo
// Entrada: n (personas en circulo) y k (cada cuantos se elimina)
// Salida: orden en que salen y el sobreviviente
// Ejemplo: 7 3  ->  3 6 2 7 5 1   sobreviviente 4
//
// Idea: guardamos 1..n en una forward_list y usamos un iterador
// "anterior". Contar k significa avanzar k-1 veces y borrar el
// siguiente con erase_after. Cuando llegamos al final, volvemos
// a before_begin para simular el circulo.

#include <iostream>
#include <forward_list>
using namespace std;

int main() {
    int n, k;
    cin >> n >> k;

    forward_list<int> circulo;
    for (int i = n; i >= 1; i--)
        circulo.push_front(i);       // queda 1 2 3 ... n

    auto anterior = circulo.before_begin();
    int quedan = n;

    while (quedan > 1) {
        // avanzamos k-1 posiciones dando la vuelta si hace falta
        for (int paso = 0; paso < k - 1; paso++) {
            ++anterior;
            if (next(anterior) == circulo.end())
                anterior = circulo.before_begin();
        }
        cout << *next(anterior) << " ";
        circulo.erase_after(anterior);
        quedan--;

        if (next(anterior) == circulo.end())
            anterior = circulo.before_begin();
    }

    cout << endl << "Sobreviviente: " << circulo.front() << endl;
    return 0;
}
