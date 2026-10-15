#include <iostream>
#include <type_traits>
using namespace std;

template<typename T> struct NombreTipo { static const char* valor() { return "Desconocido"; } };

template<> struct NombreTipo<int> { static const char* valor() { return "int"; } };
template<> struct NombreTipo<unsigned int> { static const char* valor() { return "unsigned int"; } };
template<> struct NombreTipo<float> { static const char* valor() { return "float"; } };
template<> struct NombreTipo<double> { static const char* valor() { return "double"; } };
template<> struct NombreTipo<char> { static const char* valor() { return "char"; } };
template<> struct NombreTipo<bool> { static const char* valor() { return "bool"; } };

template<typename T>
void mostrarTipo(T) {
    cout << "Tipo: " << NombreTipo<T>::valor() << endl;
    cout << "Entero: " << (is_integral_v<T> ? "SI" : "NO") << endl;
    cout << "Punto flotante: " << (is_floating_point_v<T> ? "SI" : "NO") << endl;
    cout << "Booleano: " << (is_same_v<T, bool> ? "SI" : "NO") << endl;
    cout << "Firmado: " << (is_signed_v<T> ? "SI" : "NO") << endl;
    cout << "No firmado: " << (is_unsigned_v<T> ? "SI" : "NO") << endl;
    cout << "-------------------------------" << endl;
}

int main() {
    mostrarTipo(int{});
    mostrarTipo(unsigned int{});
    mostrarTipo(float{});
    mostrarTipo(double{});
    mostrarTipo(char{});
    mostrarTipo(bool{});
    return 0;
}