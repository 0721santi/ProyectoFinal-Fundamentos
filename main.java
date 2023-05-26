import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class main{
    // este metodo tiene como funcion llenar el arreglo con las palabras que habian en el archivo .txt
    // static void llenarClase(Palabras p[], ArrayList<String> lista){
    //     for(int i=0;i<lista.size();i++){
    //         p[i] = new Palabras(lista.get(i).length(), lista.get(i));
    //         System.out.println(lista.get(i));
    //     }
    // }
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        System.out.print("Ingrese nombre del archivo: ");
        String path = myScan.nextLine();
        // Se crea y se organiza el arrayList
        ArrayList<String> listaPalabras = new ArrayList<>();
        MainSopaLetras.creaArrayPalabras(path, listaPalabras);
        MainSopaLetras.organizaLista(listaPalabras);
        // Se instancia la clase "Palabra" y se crean los objetos.
        Palabras p[] = new Palabras[listaPalabras.size()];
        //llenarClase(p, listaPalabras);
        int maxlong = p[0].getLongitude();
        //crear la sopa de letras
        Random myRandPos = new Random();
        Random myRandDirection = new Random();
        int tamañoSopa = maxlong+2;
        char[][] sopa = new char[tamañoSopa][tamañoSopa];
        for(int i = 0;i<tamañoSopa;i++){
            for(int j = 0;j<tamañoSopa;j++){
                sopa[i][j] = ' ';
            }
        }
        int x, direccion;
        int y = 0;
        // for(int i = 0;i<p[0].getLongitude();i++){
        //     if(sopa[0][i] == '\0'){
        //         sopa[0][i] = p[0].getName().charAt(i);
        //         System.out.print(sopa[0][i]);
        //     }
        // }
        for(int i = 0;i<listaPalabras.size();i++){
            direccion = 1 + myRandDirection.nextInt(2);
            System.out.println(p[i].getName());
            System.out.println("Direccion "+direccion);
            int lon = p[i].getLongitude();
            switch(direccion){
                case 1:
                    y = myRandPos.nextInt(tamañoSopa);
                    
                    x = myRandPos.nextInt(tamañoSopa-lon);
                    System.out.println("Long "+lon);
                    for(int j=x;j<lon+x;j++){
                        System.out.println("y"+ y);
                        System.out.println("x "+x);
                        System.out.println("j "+j);
                        System.out.println("j-x "+(j-x));
                        if(sopa[y][j] == '\0' || sopa[y][j] == p[i].getName().charAt(j-x) || sopa[y][j] == ' '){
                            sopa[y][j] = p[i].getName().charAt(j-x);
                            System.out.println(sopa[y][j]);
                        }
                        else{
                            x = myRandPos.nextInt(tamañoSopa-lon);
                            y = myRandPos.nextInt(tamañoSopa);
                            j = x-1;
                        }
                    }
                    break;
                case 2:
                    y = myRandPos.nextInt(tamañoSopa);
                    x = lon + myRandPos.nextInt(tamañoSopa-lon);
                    System.out.println("Long "+lon);
                    for(int j = x;j>(x-lon);j--){
                        System.out.println("y "+ y);
                        System.out.println("x "+x);
                        System.out.println("j "+j);
                        System.out.println("x-j "+(x-j));
                        if(sopa[y][j] == '\0' || sopa[y][j] == p[i].getName().charAt(x-j) || sopa[y][j] == ' '){
                            sopa[y][j] = p[i].getName().charAt(x-j);
                            System.out.println(sopa[y][j]);
                        }
                        else{
                            x = lon + myRandPos.nextInt(tamañoSopa-lon);
                            y = myRandPos.nextInt(tamañoSopa);
                            j = x+1;
                        }
                    }
                    break;
                case 3:
                    x = myRandPos.nextInt(tamañoSopa);
                    
                    y = myRandPos.nextInt(tamañoSopa-lon);
                    System.out.println("Long "+lon);
                    for(int j=x;j<lon+x;j++){
                        System.out.println("y"+ y);
                        System.out.println("x "+x);
                        System.out.println("j "+j);
                        System.out.println("j-x "+(j-x));
                        if(sopa[j][y] == '\0' || sopa[j][y] == p[i].getName().charAt(j-x) || sopa[j][y] == ' '){
                            sopa[j][y] = p[i].getName().charAt(j-x);
                            System.out.println(sopa[y][j]);
                        }
                        else{
                            y = myRandPos.nextInt(tamañoSopa-lon);
                            x = myRandPos.nextInt(tamañoSopa);
                            j = x-1;
                        }
                    }
                    break;
                case 4:
                    x = myRandPos.nextInt(tamañoSopa);
                    y = lon + myRandPos.nextInt(tamañoSopa-lon);
                    System.out.println("Long "+lon);
                    for(int j = x;j>(x-lon);j--){
                        System.out.println("y "+ y);
                        System.out.println("x "+x);
                        System.out.println("j "+j);
                        System.out.println("x-j "+(x-j));
                        if(sopa[j][y] == '\0' || sopa[j][y] == p[i].getName().charAt(x-j) || sopa[j][y] == ' '){
                            sopa[j][y] = p[i].getName().charAt(x-j);
                            System.out.println(sopa[j][y]);
                        }
                        else{
                            y = lon + myRandPos.nextInt(tamañoSopa-lon);
                            x = myRandPos.nextInt(tamañoSopa);
                            j = x+1;
                        }
                    }
                    break;
                case 5:

                case 6:
                    
            }
        }
        for(int i = 0;i<tamañoSopa;i++){
            for(int j = 0;j<tamañoSopa;j++){
                System.out.print(sopa[j][i] + " ");
            }
            System.out.println(" ");
        }
    }
}