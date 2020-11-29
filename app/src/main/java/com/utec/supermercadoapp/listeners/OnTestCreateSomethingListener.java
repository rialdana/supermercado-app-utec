package com.utec.supermercadoapp.listeners;

/**
 * Interfaz creada como canal de comunicacion para fragments y activities
 */
public interface OnTestCreateSomethingListener {

    /**
     * Metodo con parametros de prueba para ejecutar una llamada del fragment a la activity
     */
    void onCreateSomething(String firstParameter, int secondParameter);

    void Fragment_Categoria(String name_categories);

    void Fragment_Sucursal(String name_sucursal, String address, String phone);
    /**
     * Se pueden agregar mas metodos de ser necesario.
     */
}
