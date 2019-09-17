package listasdoblementeligadas;

public class NodoDoble {
    //Las variables de esta clase sólo podrán ser usadas por ella misma
    private Object info;
    private NodoDoble anterior;
    private NodoDoble siguiente;
    
    //el constructor crea un nuevo objeto con su respectivo dato y con ambas referencias vacías
    public NodoDoble(Object datoRecibido){
        info = datoRecibido;
        anterior = siguiente = null;
    }
    
    //Modifica el contenido del nodo, lo que estaba anterior a él
    public void setAnterior(NodoDoble nodoRecibido){
        anterior = nodoRecibido;
    }
    
     //Modifica el contenido del nodo, lo que estaba después a él
    public void setSiguiente(NodoDoble nodoRecibido){
        siguiente = nodoRecibido;
    }
    
    //Obtiene el valor contenido en el nodo actual
    public Object getInfo(){
        return info;
    }
    
    //Obtiene el nodo anterioir al que se hace referencia con el actual
    public NodoDoble getAnterior(){
        return anterior;
    }
    
    //Obtiene el nodo siguiente al que se hace referencia con el actual
    public NodoDoble getSiguiente(){
        return siguiente;
    }
}
