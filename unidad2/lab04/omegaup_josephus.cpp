// G-07 - OmegaUP 1502 "Josephus"
// https://omegaup.com/arena/problem/josephus/
// Entrada: n m   ->   Salida: los n olimpicos en el orden en que salen
// Ejemplo: 5 3   ->   3 1 5 2 4
//
// Idea: una forward_list de BLOQUES. Cada bloque es un grupito de
// olimpicos (unos 300). Para contar m no caminamos persona por
// persona: si el salto es mas grande que el bloque, pasamos al
// siguiente bloque de un golpe. Cuando un bloque se queda vacio lo
// quitamos con erase_after. Al llegar al final volvemos al primero
// para simular el circulo.
// Con n = m = 100000 caminar de uno en uno tarda ~10 s (limite: 2 s);
// con bloques tarda milisegundos.

#include <iostream>
#include <forward_list>
#include <vector>
#include <cmath>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    // 1) armar los bloques: {1..B}, {B+1..2B}, ...
    int B = (int)sqrt((double)n) + 1;
    forward_list<vector<int>> bloques;
    auto ultimo = bloques.before_begin();
    for (int ini = 1; ini <= n; ini += B) {
        vector<int> bloque;
        for (int x = ini; x < ini + B && x <= n; x++) bloque.push_back(x);
        ultimo = bloques.insert_after(ultimo, bloque);
    }

    // 2) simular: "actual" es el bloque donde estamos, "pos" la persona
    //    dentro del bloque desde donde empieza a contar
    auto previo = bloques.before_begin();
    auto actual = bloques.begin();
    auto volverSiFin = [&]() {                // simula el circulo
        if (actual == bloques.end()) {
            previo = bloques.before_begin();
            actual = bloques.begin();
        }
    };
    int pos = 0;
    int quedan = n;

    while (quedan > 0) {
        pos += (m - 1) % quedan;              // las vueltas completas no cambian nada
        while (pos >= (int)actual->size()) {  // saltamos bloques enteros
            pos -= actual->size();
            previo = actual++;
            volverSiFin();
        }

        cout << (*actual)[pos];
        actual->erase(actual->begin() + pos);
        quedan--;
        if (quedan > 0) cout << " ";

        if (actual->empty()) {                // bloque vacio: lo sacamos de la lista
            actual = bloques.erase_after(previo);
            pos = 0;
        } else if (pos == (int)actual->size()) {  // era el ultimo del bloque
            previo = actual++;
            pos = 0;
        }
        volverSiFin();
    }
    cout << "\n";
    return 0;
}
