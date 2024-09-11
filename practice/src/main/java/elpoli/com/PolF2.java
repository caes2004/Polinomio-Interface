package elpoli.com;

import java.awt.*;
import javax.swing.*;

public class PolF2 {
    // *********************************************************
    // ATRIBUTOS DE LA CLASE
    // N PARA GUARDAR EL TAMANO DEL VECTOR VEC
    // *********************************************************
    String nombre; // PARA GUARDAR EL NOMBRE DEL POLINOMIO
    int n, vec[];

    public PolF2(String nombre) {
        this.nombre = nombre;
        n = 1;
        vec = new int[1];
        vec[0] = 0;

    }

    // *********************************************************
    // METODO ENCARGADO DE AUMENTAR EL TAMAÑO DEL VECTOR, SI EL
    // PARÁMETRO ES POSITIVO EL VECTOR CRECE EN CASO CONTRARIO DECRECE
    // *********************************************************
    public void redimensionar(int x) {
        int aux[];
        if (x > 0) {
            aux = new int[n + x];
            for (int i = 0; i < vec[0] * 2 + 1; i++) {
                aux[i] = vec[i];
            }
            n = n + x;
            vec = aux;
        } else {
            if (n + x >= vec[0] * 2 + 1) {
                aux = new int[n + x];
                for (int i = 0; i < n + x; i++) {
                    aux[i] = vec[i];
                }
                n = n + x;
                vec = aux;
            }
        }
    }

    // ********************************************************
    // MÉTODO QUE REGRESA EL DATO QUE ESTA EN LA POSICON P
    // DEL VECTOR VEC
    // ********************************************************
    public int obtenerDato(int p) {
        return vec[p];
    }

    // ********************************************************
    // MÉTODO QUE REGRESA EL TAMAÑO DEL VECTOR
    // ********************************************************
    public int obtenerN() {
        return n;
    }

    public int obtenerGrado() {
        return vec[0];
    }

    // ********************************************************
    // MÉTODO QUE COLOCA EL DATO D EN LA POSICIÓN P DEL VECTOR
    // ********************************************************
    public void asignarDato(int p, int d) {
        vec[p] = d;
    }

    // *******************************************
    // ESTE MÉTODO SE ENCARGA DE PINTAR EL PolF2
    // CON EL OBJETO GRAPHICS( LAPIZ ) RECIBIDO
    // A PARTIR DE LA POSICION RECIBIDA COMO PARAMETRO
    // ********************************************
    public void pintar(Graphics g, int c, int f) {
        g.setColor(Color.red);
        Font font1, font2, font3, font4;
        font1 = new Font("Times Roman", Font.BOLD, 14);
        font2 = new Font("Times Roman", Font.BOLD, 12);
        font3 = new Font("Times Roman", Font.BOLD, 16);
        font4 = new Font("Times Roman", Font.BOLD, 12);
        f = f + 20;
        int i, j, k, c1, f1;
        c1 = c;
        f1 = f;
        char ch;
        String exp, dato;
        g.setFont(font1);
        if (nombre != null) {
            for (j = 0; j < nombre.length(); j++) {
                ch = nombre.charAt(j);
                g.drawString("" + ch, c1, f1);
                c1 = c1 + 20;
            }
        }
        g.drawString(" = ", c1, f1);
        c1 = c1 + 60;
        // para pintar el polinomio
        if (vec[0] == 0) {

            g.setColor(Color.blue);
            g.drawString(" Polinomio vacio ", c1, f1);
        } else {
            for (i = 1; i < vec[0] * 2 + 1; i = i + 2) {

                g.setColor(Color.blue);
                dato = "" + vec[i + 1];
                g.setFont(font1);
                // pintar el signo
                if (vec[i + 1] > 0 && i > 1) {
                    g.drawString(" + ", c1, f1);
                    c1 = c1 + 40;
                }
                // pintar el coeficiente
                for (j = 0; j < dato.length(); j++) {
                    ch = dato.charAt(j);
                    g.drawString("" + ch, c1, f1);
                    c1 = c1 + 20;
                }

                // pintar La variable x
                g.setColor(Color.red);
                g.drawString(" X ", c1, f1);
                c1 = c1 + 30;
                // pintando el exponente
                g.setFont(font2);
                exp = "" + (vec[i]);
                for (j = 0; j < exp.length(); j++) {
                    ch = exp.charAt(j);
                    g.drawString("" + ch, c1, f1 - 15);
                    c1 = c1 + 10;
                }

            }
        }
        // PINTANDO EL VECTOR

        int alto = 40, ancho = 60;
        int f11, c11;
        f11 = f1 + 40;
        c11 = c + 50;

        for (j = 0; j < vec.length; j++) {
            g.setFont(font3);
            if (j < vec[0] * 2 + 1) {
                if (j == 0) {
                    g.setColor(Color.white);
                } else if (j % 2 == 0) {
                    g.setColor(Color.lightGray);
                } else {
                    g.setColor(Color.yellow);
                }
            } else {
                g.setColor(Color.white);
            }

            g.fillRect(c11, f11, ancho, alto);
            g.setColor(Color.blue);
            g.drawRect(c11, f11, ancho, alto);
            g.drawString("" + vec[j], c11 + 10, f11 + 25);
            g.setFont(font4);
            // PARA COLOCAR LA POSICION DEL VECTOR
            g.setColor(Color.blue);
            g.drawString("" + (j + 1), c11 + 30, f11 - 10);

            c11 = c11 + ancho;
        }

    }
    // *******************************************
    // ESTE MÉTODO SE ENCARGA DE ISERTAR ELTERMINO
    // RECIBIDO COMO PARÁMETRO, SI YA HAY UN TERMINO
    // CON EL MISMO EXPONENTE SUMA LOS COEFICIENTES, SI
    // LA SUMA DA CERO SE ELIMINA EL TERMINO.
    // ********************************************

    public void insertarTermino(int coe, int exp) {

        int i = 1;
        while (i < vec[0] * 2 + 1 && vec[i] > exp) {
            i = i + 2;
        }
        if (i < vec[0] * 2 + 1 && vec[i] == exp) {
            int suma = vec[i + 1] + coe;
            if (suma != 0) {
                vec[i + 1] = suma;
            } else {
                for (int j = i + 2; j < vec[0] * 2 + 1; j++) {
                    vec[j - 2] = vec[j];
                }
                vec[0] = vec[0] - 1;
                redimensionar(-2);
            }
        } else {
            redimensionar(2);
            for (int j = vec[0] * 2; j >= i; j--) {
                vec[j + 2] = vec[j];
            }
            vec[i] = exp;
            vec[i + 1] = coe;
            vec[0] = vec[0] + 1;
        }
    }

    public PolF2 sumar(PolF2 B) {
        PolF2 resultado = new PolF2("A+B");
        int i, j, expA, expB, coeA, coeB;

        i = 1;
        j = 1;

        // Bucle principal para sumar terminos
        while (i <= vec[0] * 2 + 1 && j <= B.obtenerDato(0) * 2 + 1) {
            // Verificar que i+1 y j+1 estén dentro de los límites(Que los indices del
            // vector sigan esten dentro del limite del polinomio).
            if (i + 1 <= vec[0] * 2 + 1 && j + 1 <= B.obtenerDato(0) * 2 + 1) {
                expA = vec[i];
                expB = B.obtenerDato(j);

                coeA = vec[i + 1];
                coeB = B.obtenerDato(j + 1);

                if (expA == expB) {
                    resultado.insertarTermino(coeA + coeB, expB);
                    i = i + 2;
                    j = j + 2;
                } else {
                    if (expA > expB) {
                        resultado.insertarTermino(coeA, expA);
                        i = i + 2;
                    } else {
                        resultado.insertarTermino(coeB, expB);
                        j = j + 2;
                    }
                }
            } else {
                break; 
            }
        }

        // Procesar términos restantes del primer polinomio
        while (i <= vec[0] * 2 + 1 && i + 1 <= vec[0] * 2 + 1) {
            resultado.insertarTermino(vec[i + 1], vec[i]);
            i = i + 2;
        }

        // Procesar términos restantes del segundo polinomio
        while (j <= B.obtenerDato(0) * 2 + 1 && j + 1 <= B.obtenerDato(0) * 2 + 1) {
            resultado.insertarTermino(B.obtenerDato(j + 1), B.obtenerDato(j));
            j = j + 2;
        }

        return resultado;
    }
    // *No se que tan necesario es validar el siguiente indice de los dos vectores
    // pero sin esta linea de codigo ejecutaba el error */
    // *ArrayIndexOutOfBoundsException que siginifica que estoy intentando acceder a
    // un indice fuera del arreglo. */

    public PolF2 multiplicar(PolF2 B) {
        PolF2 producto = new PolF2("AxB");

        int i, j, expA, expB, expC, coeC;

        for (j = 1; j < B.obtenerDato(0) * 2 + 1; j = j + 2) {

            for (i = 1; i < vec[0] * 2 + 1; i = i + 2) {
                expA = vec[i];
                expB = B.obtenerDato(j);
                expC = expA + expB;
                coeC = vec[i + 1] * B.obtenerDato(j + 1);
                producto.insertarTermino(coeC, expC);

            }
        }

        return producto;
    }


    public PolF2 eliminacion(int exp) {
        int i = 1; // Inicializa el índice para recorrer el vector a partir del primer término (excluyendo el exponente mayor en vec[0]).
        
        // Bucle para buscar el término con el exponente `exp`.
        // El bucle se detiene cuando encuentra un exponente menor o igual a `exp`.
        while (i <= vec[0] * 2 && vec[i] > exp) {
            i += 2; // Avanza en el vector de dos en dos, ya que cada término ocupa dos posiciones (exponente y coeficiente).
        }
        
        // Verifica si el exponente `exp` fue encontrado.
        if (i <= vec[0] * 2 && vec[i] == exp) {
            // Bucle para eliminar el término con el exponente `exp` desplazando los elementos restantes hacia la izquierda.
            for (int j = i + 2; j <= vec[0] * 2; j++) {
                vec[j - 2] = vec[j]; // Desplaza el exponente y su coeficiente dos posiciones a la izquierda.
            }
            vec[0]--; // Decrementa el exponente del término más alto para reflejar la eliminación de un término.
            redimensionar(-2); // Ajusta el tamaño del vector reduciéndolo en dos posiciones.
        } else {
            // Si no encuentra el exponente `exp`, muestra un mensaje al usuario.
            System.out.println("Exponente no encontrado en el polinomio");
        }
    
        return this; // Devuelve el polinomio modificado.
    }
    
}