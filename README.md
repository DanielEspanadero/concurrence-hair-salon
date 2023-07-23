# CONCURRENCIA  EN JAVA

## Ejemplo sencillo para explicar la concurrencia en Java

Este proyecto es un ejemplo sencillo de cómo implementar concurrencia en Java utilizando hilos (threads) y mecanismos de sincronización. El objetivo es simular el funcionamiento de una peluquería, donde hay tres peluqueras y diez clientes. Cada peluquera atiende a un cliente en un tiempo aleatorio entre 1 y 10 segundos.

## Estructura del proyecto

El proyecto consta de varias clases que representan las diferentes partes del sistema:

1. **PeluqueriaApp**: Esta es la clase principal que contiene el método main y es responsable de iniciar la simulación de la peluquería.

2. **Peluqueria**: En esta clase, se gestiona el flujo de clientes y las peluqueras. Contiene la cola de clientes y los métodos agregarCliente() y obtenerCliente(), que son sincronizados para evitar race conditions.

3. **Peluquera**: Esta clase representa a una peluquera y es un hilo que atiende a los clientes. Cada peluquera es una instancia de la clase Thread, y la lógica de atención se encuentra en el método run(). Se utiliza Thread.sleep() para simular el tiempo de atención a los clientes.

4. **Cliente**: Esta clase representa a un cliente y se utiliza para generar instancias de clientes con nombres distintos.


## Funcionamiento del programa

1. Al iniciar la ejecución de la aplicación (PeluqueriaApp.main()), se crea una instancia de Peluqueria y se inicia la simulación llamando al método iniciar().

2. En el método iniciar(), se crean las peluqueras como hilos y se inician con el método start().

3. Luego, se crean diez clientes y se agregan a la cola de clientes de la peluquería llamando al método agregarCliente().

4. Las peluqueras comienzan a atender a los clientes en paralelo. Cada peluquera, representada por un hilo, obtiene un cliente de la cola llamando a obtenerCliente() y lo atiende.

5. El tiempo de atención se simula mediante el uso de la clase Random para generar un valor aleatorio entre 1 y 10 segundos con nextInt(10) + 1, y luego se utiliza Thread.sleep(tiempoAtencion * 1000) para dormir el hilo durante el tiempo de atención.

6. Una vez que una peluquera ha terminado de atender a un cliente, vuelve a obtener otro cliente de la cola y continúa con el proceso.

7. Los mensajes de inicio y finalización de atención, junto con el tiempo de duración, se imprimen en la consola para mostrar la interacción entre las peluqueras y los clientes.

## Uso de la concurrencia

El principal uso de la concurrencia en este proyecto se encuentra en la simulación de las peluqueras atendiendo a los clientes en paralelo. Cada peluquera es un hilo independiente que ejecuta la lógica de atención en el método run(). Para garantizar la sincronización y evitar problemas de concurrencia, se utilizan los siguientes mecanismos:

👉 Se utiliza un objeto de bloqueo (lock) para sincronizar el acceso a la cola de clientes en los métodos agregarCliente() y obtenerCliente(). Esto evita race conditions cuando varios hilos intentan modificar la cola al mismo tiempo.

👉 Los métodos agregarCliente() y obtenerCliente() están sincronizados, lo que asegura que solo un hilo pueda acceder a la cola de clientes a la vez. Si la cola está vacía, los hilos esperan (wait()) hasta que haya clientes disponibles y se les notifica (notify()) cuando se agrega un cliente.

👉 El tiempo de atención de las peluqueras se simula utilizando Thread.sleep(). Si bien esto no está directamente relacionado con la concurrencia, es importante tener en cuenta que el tiempo de atención es independiente entre las peluqueras y se ejecuta en paralelo.

