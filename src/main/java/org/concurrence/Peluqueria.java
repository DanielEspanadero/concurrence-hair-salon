package org.concurrence;

import java.util.LinkedList;
import java.util.Queue;

public class Peluqueria {
    private static final int NUM_PELUQUERAS = 3;
    private static final int NUM_CLIENTES = 10;

    private Queue<Cliente> colaClientes = new LinkedList<>();
    private Object lock = new Object();

    private Thread[] peluqueras;

    public Peluqueria() {
        peluqueras = new Thread[NUM_PELUQUERAS];
        for (int i = 0; i < NUM_PELUQUERAS; i++) {
            peluqueras[i] = new Thread(new Peluquera(this, "Peluquera" + (i + 1)));
        }
    }

    public void iniciar() {
        for (Thread peluquera : peluqueras) {
            peluquera.start();
        }

        for (int i = 1; i <= NUM_CLIENTES; i++) {
            Cliente cliente = new Cliente("Cliente" + i);
            agregarCliente(cliente);
        }
    }

    public void agregarCliente(Cliente cliente) {
        synchronized (lock) {
            colaClientes.offer(cliente);
            lock.notify();
        }
    }

    public Cliente obtenerCliente() throws InterruptedException {
        synchronized (lock) {
            while (colaClientes.isEmpty()) {
                lock.wait();
            }
            return colaClientes.poll();
        }
    }
}
