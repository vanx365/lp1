#include <iostream> 
using namespace std;

int RaizDig (int num){
    int entrada = num;
    bool seguir = true;
    
    while (seguir) {
        int entero = (entrada / 10);
        int residuo = (entrada % 10);
        
        if ((entero + residuo) > 9){
            seguir = true;
        } 
        else{
            seguir = false;
        }
        entrada = entero + residuo;
    } 
    return entrada;
}

int main(){
    int num;
    cout << "ingrese un numero: " << endl;
    cin >> num;

    cout << "la raiz digital es: " << RaizDig(num) << endl; 
    return 0; 
}