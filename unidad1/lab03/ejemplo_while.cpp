#include <iostream>
using namespace std;
//en este programa usamos una cuenta regresiva desde cualquier numero hasta 0 para llegar a  mostrar un texto
int main() {
  int countdown ;
cout<< "¿Desde que numero quiere la cuenta regresiva?";
cin >> countdown;
while (countdown>0) {
    cout << countdown << endl;
    countdown--;
}
  cout<<"Grupo 2, Aaron y Piero presente" << endl;
  return 0;
}