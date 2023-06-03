public interface MetodosSolucion{
    /*
     * Se crea esta interfaz que contiene métodos en común entre las clases que solucionan la sopa de letras en las
     * diferentes posibilidades (Solución1 y Solución2).
     */
    public int[] solucionSopa();
    /*
     * El método solución sopa es el método "general" para la búsqueda de las palabras. Es el método que envía "una a una"
     * las palabras al método de búsqueda que utiliza el Depth-First Search y que en últimas devuelve la cantidad de pasos
     * que se utilizaron en la búsqueda de todas las palabras.
     */
    public void imprimeSopa();
}