#include <iostream>
#include <random>
using namespace std;
int main() {
    random_device rd; 
    mt19937 gen(rd()); 
    uniform_int_distribution<> distrib(1, 100); 

    int numero_secreto = distrib(gen);
    int intento;
    int contador_intentos = 0;

    cout << "Adivina el número entre 1 y 100: " << endl;

    do {
        cin >> intento;
        contador_intentos++;

        if (intento < numero_secreto) {
            cout << "El número es mayor." << endl;
        } else if (intento > numero_secreto) {
            cout << "El número es menor." << endl;
        } else {
            cout << "¡Correcto!" << endl;
            cout << "Número de intentos: " << contador_intentos << endl;
        }
    } while (intento != numero_secreto);

    return 0;
}
