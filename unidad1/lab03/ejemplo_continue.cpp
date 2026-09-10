#include <iostream> 
using namespace std;
int main() {
    int i;
    for (i = 0; i < 10; i++) {
        if (i == 5) {
            continue; // Salta el resto del código en esta iteración
        }
        cout << i << endl; // Imprime los números del 0 al 9, excepto el 5
    }
    return 0;
}