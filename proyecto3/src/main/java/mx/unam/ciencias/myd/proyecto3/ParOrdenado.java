package mx.unam.ciencias.myd.proyecto3;

/**
 * Clase para representar un par ordenado.
 */
public class ParOrdenado<T> {

    /* El primer valor del par ordenado. */
    private T x;
    /* El segundo valor del par ordenado. */
    private T y;

    /**
     * Construye un par ordenado con base en dos valores.
     * @param x el primer valor.
     * @param y el segundo valor.
     */
    public ParOrdenado(T x, T y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Regresa el primer valor del par ordenado.
     * @return el primer valor del par ordenado.
     */
    public T getX() {
        return this.x;
    }

    /**
     * Regresa el segundo valor del par ordenado.
     * @return el segundo valor del par ordenado.
     */
    public T getY() {
        return this.y;
    }

    /**
     * Define el primer valor del par ordenado.
     * @param x el nuevo primer valor del par ordenado.
     */
    public void setX(T x) {
        this.x = x;
    }

    /**
     * Define el segundo valor del par ordenado.
     * @param y el nuevo segundo valor del par ordenado.
     */
    public void setY(T y) {
        this.y = y;
    }

    /**
     * Regresa una representación en cadena del par ordenado.
     * @return una representación en cadena del par ordenado.
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

}