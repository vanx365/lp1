//Desarrolle un jprograma en el que el computador genere un número aleatorio entre 1 y 100. ambos extremos incluidos. El usuario debe intentar adivinarlo. El programa debe indicar:El número es mayor.El número es menor.¡Correcto!Al finalizar debe mostrar el número de intentos.Utilice:#include <random> tambiend std using namespace std;
#include <iostream>
#include <random>

using namespace std;

int main() {
    // Crear un generador de números aleatorios
    random_device rd; // Obtiene un número aleatorio del hardware
    mt19937 gen(rd()); // Inicializa el generador con el número aleatorio
    uniform_int_distribution<> distrib(1, 100); // Distribución uniforme entre 1 y 100

    int numero_secreto = distrib(gen); // Genera el número secreto
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

