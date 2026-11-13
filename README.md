````markdown
# 🛵 Domi
 
Aplicación de consola en Java puro desarrollada para el **Hackathon Java · Generation Colombia · Cohorte 15**. Permite gestionar clientes, repartidores y pedidos de Domi en Bogotá, cotizando y asignando automáticamente el repartidor óptimo según distancia, tamaño del pedido, disponibilidad y clima.
 
---
 
## 👥 Equipo de Desarrollo
 
| Integrante | Rol en el proyecto | Responsabilidades principales |
| :--- | :--- | :--- |
| **Laura García Angel** | Aplicación de Consola (`app`) y Cierre del Día | Interfaz de usuario en terminal (`Main`, `Menu`), captura y validación de entradas con `Scanner`, manejo de excepciones (`try/catch`) y reporte de cierre del día. |
| **Michalle Daniela Muñoz Ortegón** | Modelo y POO (`modelo`) | Diseño de clases del dominio (`Cliente`, `Repartidor`, `Bici`, `Moto`, `Carro`), encapsulamiento, herencia, polimorfismo e interfaz `Calificable`. |
| **Guido Fernando Majín Ibarra** | Integración, Git, QA y Pedidos (`modelo`) | Configuración del repositorio, gestión de ramas y Pull Requests, implementación de `Pedido` y `Dron`, y ejecución de casos de prueba de aceptación. |
| **Jensen Quintero** | Servicio y Reglas de Negocio (`servicio`) | Implementación de `DomiService`, gestión de colecciones en memoria, carga de datos de arranque, motor de cotización y asignación automática, y modo lluvia. |
 
---
 
## 🚀 Cómo ejecutar el proyecto en IntelliJ IDEA
 
1. Clonar este repositorio:
   ```bash
   git clone GitHub - guido102002/JavaEntregas
   ```
2. Abrir **IntelliJ IDEA** y seleccionar **File → Open...** eligiendo la carpeta del proyecto.
3. Verificar que el proyecto tenga configurado un **JDK 21 o Superior** (*File → Project Structure → Project SDK*).
4. En el panel del proyecto, abrir el archivo `src/app/Main.java`.
5. Hacer clic derecho sobre `Main.java` y seleccionar **Run 'Main.main()'** (o presionar el botón verde ▶️ junto al método `main`).
 
---
 
## 🎬 Video Demo (3 minutos)
 
🔗 **Enlace al video:** [Pegar aquí el link de YouTube oculto o Google Drive]
 
---
 
## 🧠 Preguntas de diseño
 
### 1. ¿Por qué un mapa y no una lista? (Clientes por cédula)
Las llaves de un `Map` no se repiten, cosa que una lista sí permite. Así **no puede haber dos clientes con la misma cédula**.
 
Además, la búsqueda es más fácil: un `HashMap` busca directamente por la clave y va justo a lo que se pide, sin recorrer toda la colección.
 
---
 
### 2. ¿Qué ventaja tiene que no se repitan? (Barrios con cobertura en `HashSet`)
Los **9 barrios** siempre están disponibles, no hay datos duplicados y la respuesta al consultar si un barrio tiene cobertura es siempre la misma e inmediata.
 
---
 
### 3. ¿Por qué el operador nunca escribe los km? (Distancias en `HashMap`)
Los kilómetros salen del **barrio del cliente**. Así se evitan errores, como que dos pedidos del mismo barrio terminen con distancias distintas.
 
---
 
### 4. ¿Por qué aquí sí sirve una lista? (Repartidores en `ArrayList`)
Porque hay que **recorrer todos los repartidores disponibles** para el pedido, y una lista lo hace de forma sencilla.
 
---
 
### 5. ¿Cómo encuentran un pedido por su número? (Pedidos en `ArrayList`)
Recorriendo la lista de pedidos con un ciclo (`for`) hasta encontrar el que coincide con el número buscado.
 
---
 
### 6. ¿Guardan cada calificación en una lista o llevan solo la suma y la cantidad? ¿Por qué?
Guardamos cada calificación en una lista (`List<Integer>` dentro de `Repartidor`). El promedio se calcula recorriendo la lista y **queda el historial de cada nota**.
 
Llevar solo la suma y la cantidad ocupa menos memoria, pero no deja ver las notas una por una. Aquí hay tan pocos datos que la memoria no es un problema.
 
---
 
### 7. ¿Quién debería saber que está lloviendo y dónde va la regla de la lluvia?
- **Estado de lluvia:** lo guarda `DomiService` (`modoLluvia`), porque es un dato de todo el sistema y no de un repartidor o un pedido.
- **Restricción de la bici (y del dron):** está en el método `puedeLlevar` de cada vehículo, que recibe `modoLluvia` como parámetro. Así el servicio nunca tiene que preguntar de qué tipo es cada repartidor.
- **Aumento del 20 %:** se aplica en el servicio (`calcularCostoFinal`), porque es igual para todos y no tiene sentido repetirlo en cada clase.
 
---
 
### 8. Jefe final: ¿Cuántos archivos tuvieron que tocar para agregar el dron y por qué?
Creamos la clase `Dron.java` (heredando de `Repartidor`) y solo agregamos la opción `"Dron"` en el registro de repartidores (`DomiService` y el mensaje en `Menu`). **No tuvimos que tocar ni una línea de la lógica de cotización ni de asignación**, porque el servicio trabaja con polimorfismo sobre `Repartidor`: le hace las mismas preguntas (`puedeLlevar`, `calcularCosto`, `calcularTiempo`) a cualquier vehículo sin importar cuál sea.
 
---
 
### 9. Cierren el programa y vuelvan a abrirlo. ¿Qué pasó con los pedidos del día?
**Se perdieron.** Los clientes, repartidores y pedidos solo viven en memoria (`HashMap` y `ArrayList`). Al cerrar el programa se borran, y al abrirlo arranca otra vez solo con los datos iniciales.
 
---
 
### 10. Si Domi abre una segunda sede con otro operador, ¿cómo compartirían los clientes y los pedidos?
Guardando los datos en una **base de datos central** a la que se conecten las dos sedes, en vez de tenerlos en la memoria de cada programa. Así los dos operadores leen y escriben sobre los mismos clientes y pedidos.
````
 
---
