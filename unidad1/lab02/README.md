# Explicacion  
Separa el número en dos partes:
  - `entero = entrada / 10` → todo menos el último dígito
  - `residuo = entrada % 10` → el último dígito
- Las suma: `entrada = entero + residuo`
- Repite mientras esa suma tenga más de un dígito (`> 9`)
- Cuando la suma ya es un solo dígito, se detiene y ese es el resultado
<img width="1482" height="536" alt="image" src="https://github.com/user-attachments/assets/7979f582-bcb2-48ec-94c7-8bc12dffc12e" />

