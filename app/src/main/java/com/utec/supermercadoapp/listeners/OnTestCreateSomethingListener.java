package com.utec.supermercadoapp.listeners;

/**
 * Interfaz creada como canal de comunicacion para fragments y activities
 */
public interface OnTestCreateSomethingListener {

    /**
     * Metodo con parametros de prueba para ejecutar una llamada del fragment a la activity
     */
    void onCreateSomething(String firstParameter, int secondParameter);

    /**
     * Se pueden agregar mas metodos de ser necesario.
     */
}
