# ProyectoFinal-Fundamentos
Este programa responde al trabajo final del primer semestre de ingenieria de sistemas en la univesidad EAFIT y está diseñado para hacer principalmente 3 funciones:
    1. Leer un archivo de texto (.txt) y a partir de este hacer una sopa de letras que además se guarda de nuevo en 
    otro archivo de texto (.txr).
    2. Resolver la sopa de letras que se ha creado a partir del archivo ingresado en el punto 1.
    3. Resolver una sopa de letras que el usuario ingresa en un archivo de texto (.txt) y con unas palabras que el mimso 
    usuario ingresa.
    Se utiliza el paradigma de Programación Orientada a Objetos (POO) de dos formas distintas:
    1. Al crear específicamente una clase que contiene los atributos más importantes de una palabra aplicadas al contexto
    de una sopa de letras que serían su ubicación, la dirección en la que está ubicada y si ya ha sido encontrada, para
    con esta poder llevar un control de las palabras que incialmente el usuario había ingresado para crear la sopa.
    2. Al utilizar el concepto de interfaces (interface) en las clases que permiten la solución de la sopa de letras
    en sus dos posibles variantes
    3. Al hacer uso del polimorfismo y hacer uso de los constructores para crear/instanciar objetos.
    El método utilizado para la resolución de la sopa de letras se llama Depth-First Search (DFS) o búsqueda en profundidad.
    Este es un algoritmo que se utiliza para recorrer entre otras cosas, graficos y arboles. Básicamente este selecciona 
    aleatoriamente un nodo (en nuestro caso, un punto dentro de la sopa de letras) y analiza todas las posiciones que 
    derivan de este.
