#include <iostream>
using namespace std;

int main() {
    for (int i = 1; i < 10; i++) {
        if (i == 6) {
            break; // Termina el bucle aquí
        }
        cout << i << endl;
    }
    cout << "Break terminado antes de tiempo" << endl;
    return 0;
}