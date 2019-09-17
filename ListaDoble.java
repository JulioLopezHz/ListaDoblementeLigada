package listasdoblementeligadas;

import javax.swing.JOptionPane;
public class ListaDoble {
    private NodoDoble cabeza;
    private NodoDoble cola;
    
    //Crea una lista vacía
    public ListaDoble(){
        cabeza = cola = null;
    }
    
    //Determina si una lista tiene elementos o no
    public boolean listaVacia(){
        return cabeza == null;
    }
    
    public void insertarEnCabeza(Object dato){
        //Si no hay ningún elemento las etiquetas de cabeza y cola
        //referenciarán a este nuevo elemento
        if (listaVacia())
            cabeza = cola = new NodoDoble(dato);
        //El enlace de cabeza apuntará a este nuevo nodo y el nodo nuevo
        //apuntará hacia cabeza, por último se actualiza la etiqueta cabeza        
        else{
            NodoDoble nuevo = new NodoDoble(dato);
            cabeza.setAnterior(nuevo);
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
        }
    }
    
    public void insertarEnCola(Object dato){
        //Si no hay elementos en la lista, este nuevo nodo será tanto la cabeza como la cola
        if(listaVacia())
            cabeza = cola = new NodoDoble(dato);
        //El enlace de cola puntará a este nuevo nodo y el nodo nuevo apuntará
        //hacia cola, por último se actualiza la etiqueta cola        
        else{
            NodoDoble nuevo = new NodoDoble(dato);
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
    }
    
    public void insertarOrdenado(int dato){
        if(listaVacia())
            cabeza = cola = new NodoDoble(dato);
        else{
            //El valor a ingresar se coloca al inicio si es
            //anterior a la variable de cola
            if(dato <= (int)cabeza.getInfo()){
                insertarEnCabeza(dato);
            }
            else{
                //Hayar entre cuales nodos ingresar el nuevo elemento
                NodoDoble actual = cabeza;
                NodoDoble anterior = null;
                //Busca ese par de nodos hasta llegar a la cola o hasta que 
                //el nodo actual sea mayor al dato recibido
                while((actual.getSiguiente() != null)  && (dato > (int)actual.getInfo())){
                    anterior = actual;
                    actual = actual.getSiguiente();
                }
                //Si el dato ingresado es el mayor de todos, agregarlo en la cola
                if(dato > (int)actual.getInfo())
                    insertarEnCola(dato);                                
                else{
                    //Se crea un nodo nuevo y se modifican los enlaces para que sigan
                    //un orden
                    NodoDoble nuevo = new NodoDoble(dato);
                    nuevo.setSiguiente(actual);
                    nuevo.setAnterior(anterior);
                    anterior.setSiguiente(nuevo);
                    actual.setAnterior(nuevo);
                }
            }
        }
    }
    
    public void insertarOrdenado(String dato){
        if(listaVacia())
            cabeza = cola = new NodoDoble(dato);
        else{
            //El valor a ingresar se coloca al inicio si es alfabéticamente
            //anterior a la variable de cola
            if(dato.compareToIgnoreCase(cabeza.getInfo().toString()) <= 0){
                insertarEnCabeza(dato);
            }
            else{
                //Hayar entre cuales nodos ingresar el nuevo elemento
                NodoDoble actual = cabeza;
                NodoDoble anterior = null;
                //Busca ese par de nodos hasta llegar a la cola o hasta que el nodo actual sea mayor
                //(alfabéticamente) al dato recibido
                while((actual.getSiguiente() != null)  && (dato.compareToIgnoreCase(actual.getInfo().toString()) > 0)){
                    anterior = actual;
                    actual = actual.getSiguiente();
                }
                //Si el dato ingresado es el mayor de todos, agregarlo en la cola
                if(dato.compareToIgnoreCase(actual.getInfo().toString()) > 0)
                    insertarEnCola(dato);                                
                else{
                    //Se crea un nodo nuevo y se modifican los enlaces para que sigan
                    //un orden
                    NodoDoble nuevo = new NodoDoble(dato);
                    nuevo.setSiguiente(actual);
                    nuevo.setAnterior(anterior);
                    anterior.setSiguiente(nuevo);
                    actual.setAnterior(nuevo);
                }
            }
        }
    } 
    
    public void eliminarCabeza(){
        //Si no hay elementos para eliminar muestra un mensaje de error
        if(listaVacia())
            JOptionPane.showMessageDialog(null, "¡LA LISTA ESTÁ VACÍA!", 
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        else{
            //Si hay un único elemento en la lista, poner la lista vacia
            if (cabeza == cola)
                cabeza = cola = null;
            /*En caso contrario actualizar el nodo cabeza y modificar su parte
            de anterior a null*/
            else{
                cabeza = cabeza.getSiguiente();
                cabeza.setAnterior(null);
            }
        }        
    }
    
    public void eliminarCola(){
        if(listaVacia())
            JOptionPane.showMessageDialog(null, "¡LA LISTA ESTÁ VACÍA!", 
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        else{
            if(cabeza == cola)
                cabeza = cola = null;
            else{
                /*Se modifica el nodo cola por el valor que tiene anterior a él
                y el nuevo valor de cola no tendrá nada en su parte de siguiente*/
                cola = cola.getAnterior();
                cola.setSiguiente(null);
            }
        }
    }
    
    public void elimiarSeleccionado(Object dato){
        NodoDoble anterior = null;
        NodoDoble actual = cabeza;
        //Recorrer la lista con 2 nodos auxiliares hasta que el valor a encontrar sea igual
        // a lo contenido en "actual" o hasta finalizar la lista
        while((actual != null) && (!actual.getInfo().equals(dato))){
            anterior = actual;
            actual = actual.getSiguiente();
        }
        //Si el dato no fue encontrado o si la lista está vacía, mostrar mensaje
        if(actual == null)
            JOptionPane.showMessageDialog(null, "EL DATO NO HA SIDO ENCONTRADO", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        else{
            //Dejar fuera de la lista el elemento deseado, contenido en el nodo "actual"
            if(actual == cabeza)
                eliminarCabeza();            
            else{
                if(actual == cola)
                    eliminarCola();                
                else{
                    anterior.setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(anterior);
                    actual = null;
                }
            }
        }
    }
    
    //Método que crea una cadena en la cual se podrán leer los elementos contenidos en la lista
    public String imprimir(){
        String valores="";
        if (listaVacia())
            valores = "LA LISTA ESTÁ VACÍA";
        /*Si la lista no está vacía se crea un nodo auxiliar que recorrerá la lista completa,
        tomará los valores almacenados en cada nodo y los irá compiando en la variable de tipo
        cadena hasta llegar a la cola*/
        else{
            NodoDoble auxiliar = cabeza;
            while (auxiliar != null){
                valores += auxiliar.getInfo() + "\n";
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return valores;
    }
}
