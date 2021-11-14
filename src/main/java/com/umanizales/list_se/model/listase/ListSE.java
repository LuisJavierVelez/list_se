package com.umanizales.list_se.model.listase;


import com.umanizales.list_se.exception.ListaSeException;
import com.umanizales.list_se.model.Boy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase nos permite gestionar las listas simplemente enlazadas
 * la cual contiene los metodo u operaciones
 * agregar, agregar al inicio y final,agregar por posicion, invertir la lista, contar, listar, cambiar extremos,
 * Eliminar niño, Encontrar por identificacion, validar que la lista tenga datos, Listar los niños por genero, intercalar niño por genero,
 * contador de cada localidad de los niños, Listar por edad y municipio, listar por genero y edad, Eliminar niño por la edad indicada,
 * Eliminar niño por genero al que pertenece, Eliminar niño en la posicion indicada,Eliminar niño segun la posicion indicada,
 * Listar niño por grado escolar,invertir lista por genero y edad indicada, contador de niños por genero, contador por municipio.
 *
 * Solo cuenta con los atributos head = el cual representa al primer niño
 * es importante recordar que la cabeza solo toma el primer dato por tal motivo tenemos el atributo contador con el fin que referencia cuantos datos hay en la lista
 * y se modifica cada vez que adiciono uno,
 *
 * @author Luis Javier Velez Uribe
 * @Version 1.0 05-11-2021
 */

@Data
public class ListSE {
    /**
     * Atributo que representa el inicio de lista el cual es el unico que se visualiza desde el esterior.
     */
    private Node head;
    private int count;

    /**
     * Metodo que adiciona un niño al final de la lista
     * @param boy en este parametro llegan todos los datos de niño
     * @return verdadero
     * @throws ListaSeException resgistra todas la expeciones que se puedan presentar ejemplo "Ya existe el niño que se esta ingresando" o "la lista esta vacia"
     */

    public boolean add(Boy boy) throws ListaSeException {
        /**
         * Se invoca el motodo encontrar por identificacion, para verificar si el niño que se esta ingresando ya existe
         */
        Boy boyExist= findByIdentification(boy.getIdentification());
        if(boyExist != null)
        {
            /**
             * Si el niño ya exites se lanza esta excepcion para indicarle al usuario en el controller que el niño ya existe
             */
            throw new ListaSeException("La identificacion ingresada ya existe");
        }
        /**
         * Si el nuevo niño no existe continuamos indicando si la cabeza es igual a nula
         */
        if (head == null) {
            /**
             * Indicamos que la cabeza es igual a un nuevo niño
             */
            head = new Node(boy);
        } else {
            /**
             * Llamamos a un ayudante temporal al cual le indicamos que es igual a cabeza de la lista
             */
            Node temp = head;
            /**
             * Recorremos la lista con el un ciclo While donde le indicamos que mientras el siguiente del temporal sea diferente de nulo
             */
            while (temp.getNext() != null) {
                /**
                 * Se invoca el motodo encontrar por identificacion en el cual le indicamos que sea igual a la idenfifacion donde se encuentra el ayudante temporal
                 */
                if (boy.getIdentification().equals(temp.getData().getIdentification())) {
                    return false;
                }
                /**
                 * Se le indica al ayudante temporal se que se pare en el siguiente del temporal
                 */
                temp = temp.getNext();
            }
            /**
             * Quedo parado en el ultimo dato de la lista
             */
             temp.setNext(new Node(boy));
        }
        /**
         * Contamos cada un los niños que se agregaron a la lista
         */
        count++;
        /**
         * Retornamos verdadero al agregar el nuevo niño
         */
        return true;
    }

    /**
     * Metodo encargado de agregar el dato o niño al inicio de la lista
     * @param boy en este parametro llegan todos los datos de niño
     * @throws ListaSeException  resgistra todas la expeciones que se puedan presentar ejemplo "Ya existe el niño que se esta
     */
    public void addBoyToStart(Boy boy) throws ListaSeException {
        /**
         * Se invoca el motodo encontrar por identificacion, para verificar si el niño que se esta ingresando ya existe
         */
        Boy boyExist= findByIdentification(boy.getIdentification());
        /**
         * se indica si la identificacion ingresada es diferentente de null
         */
        if(boyExist != null)
        {
            /**
             * Si el niño ya exites se lanza esta excepcion para indicarle al usuario en el controller que el niño ya existe
             */
            throw new ListaSeException("La identificacion ingresada ya existe");
        }
        /**
         * Si el nuevo niño no existe continuamos indicando si la cabeza es igual a nula
         */
        if (this.head == null) {
            /**
             * Indicamos que la cabeza es igual a un nuevo niño
             */
            this.head = new Node(boy);
        } else {
            /**
             * Llamamos un ayudante y le indicamos que el nuevo niño
             */
            Node temp = new Node(boy);
            /**
             * Le decimos al ayudante que se coloque en el siguiente que es la cabeza
             */
            temp.setNext(this.head);
            /**
             * asignamos la cabeza que es igual al ayudante
             */
            this.head = temp;

        }
        /**
         * vamos contando los niños ingresados
         */
        count++;
    }

    /**
     * Metodo que adiciona el niño en una posicion indicada o ingresada
     * @param boy parametro que trae los datos de los niños nombre, edad, genero, locacion, grado
     * @param position parametro que optiene la posicion donde se va agreaga rel niño
     * @throws ListaSeException
     */
    public void addBoyByPosition(Boy boy, int position) throws ListaSeException
    {
        /**
         * Se invoca el motodo encontrar por identificacion, para verificar si el niño que se esta ingresando ya existe
         */
        Boy boyExist= findByIdentification(boy.getIdentification());
        /**
         * indicamos qu el niño encontrado sea diferente de nulo
         */
        if(boyExist != null)
        {
            /**
             * Si el niño ya exites se lanza esta excepcion para indicarle al usuario en el controller que el niño ya existe
             */
            throw new ListaSeException("La identificacion ingresada ya existe");
        }
        /**
         * Validamos que la posicion ingresada sea mayor al contador
         */
        if(position > count)
        {
            /**
             * Agregeamos el niño a la lista
             */
            this.add(boy);
            return;
            //throw new ListaSeException("La posicion ingresada no es valida");
        }
        /**
         * Validamos si la posicion es igual 1
         */
        if(position ==1)
        {
            /**
             * si la posicion ingresada es igual a uno indicamos que agrege el niño al inicio de la lista
             */
            addBoyToStart(boy);
        }
        else
        {
            /**
             * Is la posicion es diferente de 1 generamos un contrador el cual lo inicializamos en uno
             */
            int cont=1;
            /**
             * Llamos un ayudante al cual le indicamos que se ubique en la cabeza de la lista
             */
            Node temp = this.head;
            /**
             * Recorremos la lista indicandole al temporar que la recorra hasta que sea diferente de vacio o nulo
             */
            while (temp!=null)
            {
                /**
                 * Preguntamos si el contador creado previamente es igual a la posicion ingredas menos uno
                 */
                if(cont == position-1)
                {
                    /**
                     * Generamos un break para que termine el recorrido
                     */
                    break;
                }
                /**
                 * le indicamos al ayudante que es igual al dato siguiente del cuale esta parado
                 */
                temp = temp.getNext();
                cont++;
            }
            /**
             * Ayudante va estar posicionado en la posicion anterior
             */
            Node nodeNew = new Node(boy);
            nodeNew.setNext(temp.getNext());
            temp.setNext(nodeNew);
            count ++;

        }

    }

    /**
     * Metodo crado para invertir la lista de los niños ingresados en un orden previo
     * @throws ListaSeException resgistra todas la expeciones que se puedan presentar ejemplo "no hay datos en la lista"
     */
    public void invert() throws ListaSeException {
        /**
         * Validamos que la cabeza sea diferente de nulo o vacia
         */
        if (this.head != null) {
            /**
             * creamos un lista temporal la cual es una lista que contiene todos los metodos generados en las listas simplemente enlazadas
             */
            ListSE listTemp = new ListSE();

            /**
             * LLamamos un ayudante temporal y le indicamos que se ubique en la cabeza.
             */
            Node temp = this.head;
            /**
             * Recorrer la lista principal de principio a fin hasta que se diferente de null
             */
            while (temp != null) {
                /**
                 * Agregamos en la list temporal el ultimo dato recorrido y lo agregamos al inicio de la lista temporal
                 */
                listTemp.addBoyToStart(temp.getData());
                /**
                 * le decimos al ayudante que ubique en siguente del dato agregado al inicio de la lista temporal
                 */
                temp = temp.getNext();
            }
            /**
             * Indicamos que la cabeza es igual a lista temporal creada con el dato al inicio o cabeza
             */
            this.head = listTemp.getHead();
        }
    }

    /**
     * Metodo encargado de contar los datos ingresados en el metodo donde pueda ser invocado
     * @return retorna el conteo total de los datos
     */
    public int count() {
        /**
         * Se crea una varible contador la cual inicializamos en 0
         */
        int count = 0;
        /**
         * Validamos que la cabeza sea diferente de nulo o vacia
         */
        if (this.head != null) {
            /**
             * Llamamos a un ayudante temporal y le indicamos que se ubique en la cabeza
             */
            Node temp = this.head;
            /**
             * Le indicamos al ayudante que recorra la lista hasta que sea nulo o vacio
             */
            while (temp != null) {
                /**
                 * utilizamos la variable contador y le indicamos que se sume cada dato recorrido por el ayudante temporal
                 */
                count++;
                /**
                 * Le indicamos al aydante temporal que se pare en el siguiente dato del cual esta ubicado
                 */
                temp = temp.getNext();
            }

        }
        /**
         * Retornamos el contador con el nuemos de datos contados
         */
        return count;
    }

    /**
     *Metodo encargado de mostrar la lista de los niños ingresados
     * @return Retorna la lista con el total de los niños ingresados
     * @throws ListaSeException Genera un mensaje de excepcion si la lista no tiene datos relacionado
     */
    public List<Boy> list() throws ListaSeException {
        /**
         * Validamos que la cabeza sea diferente de nulo o vacia
         */
        if (this.head != null) {
            /**
             * Llamamos a un ayudante temporal y le indicamos que se ubique en la cabeza
             */
            Node temp = this.head;
            /**
             * Creamos un arreglo de la lista con la informacion del niños listada
             */
            List<Boy> list = new ArrayList<>();
            /**
             *le decimos al ayudante que recorra la lista hasta que el dato sea nulo o vacio
             */
            while (temp != null) {
                /**
                 * agregamos en la lista el dato o niño que en cual este ubicado el ayudante temporal.
                 */
                list.add(temp.getData());
                /**
                 * despues de agregar el dato o niño a la lista le indicamos al ayudante temporal que se ubique en el siguiente
                 * del dato en el que esta ubicado.
                 */
                temp = temp.getNext();
            }
            /**
             * Retornamos la lista donde se encuenta la informacion del dato o niños creados
             */
            return list;
        }
        /**
         * generamos una respuesta de excepcion por si la lista esta vacia
         */
        throw new ListaSeException("No hay datos en la lista");
        //return null;
    }

    /**
     * Metodo encargado de cambiar la posicion de los niños que se encuentran en los extremos de la lista generada
     * @throws ListaSeException se encarga de generar una respuesta al presentarse una excepcion en la lista
     */
    public void changeXtremes() throws ListaSeException {
        /**
         * Indicamos si la cabeza es diferente de null o vacia y que el siguiente datos de la lista tambien sea diferente de null
         */
        if (this.head != null && this.head.getNext() != null) {
            /**
             * Creamos una variable con los datos del niño y le indicamos que se ubique en la cabeza con la informacion del niño
             */
            Boy start = this.head.getData();
            /**
             * Llamamos a un ayudante temporal al cual le indicamos que es igual a cabeza de la lista
             */
            Node temp = head;
            /**
             * Recorremos la lista donde le decimos que la recorra mientras el ayudante temporal en el siguiente del dato donde esta ubicado
             * sea diferente de vacio o nulo
             */
            while (temp.getNext() != null) {
                /**
                 * Le indicamos al ayudante temporal que se ubique en siguiente dato o niño en el cual esta ubicado
                 */
                temp = temp.getNext();
            }
            /**
             * en la cabeza ponemos el niño o dato optenido por el ayudante temporal
             */
            this.head.setData(temp.getData());
            /**
             * le indicamos que el ayudante temporal contienen la informacion del niño ubicado al incio con la variable Star
             */
            temp.setData(start);
        }
        else
        {
            /**
             * se genera una excepcion la que indica que no se puede cambiar los extremos ya sea que hay datos en la lista o solo hay un dato
             */
            throw new ListaSeException("No es posible ejecutar el cambio de Extremos");
        }
    }
    /*
    public void deletBoy(String id) {
       /* Node temp = this.head;
        Node previous = null;
        boolean stop = false;
        while (temp !=null && stop == false ){
            if (temp.getData().equals(boy)){
                if (previous == null) {
                    this.head = temp.getNext();
                    temp.setNext(null);
                } else {
                    previous.setNext(temp.getNext());
                    temp.setNext(null);
                }
                stop = true;
            }
            previous = temp;
            temp = temp.getNext();
            }*/
       /* Node temp = this.head;

        if (temp.getData().getIdentification().equals(id)) {
            setHead(temp.getNext());
            count--;
        } else {
            while (temp.getNext() != null) {
                if (temp.getNext().getData().getIdentification().equals(id)) {
                    temp.setNext(temp.getNext().getNext());
                    count--;

                    break;
                }

                temp = temp.getNext();
            }
        }


    }*/

    /**
     * Metodo encargado de eliminiar los niños de la lista por medio de la identificacion ingresada
     * @param id de identificacion Cedula, TI, CE , Nit
     * @throws ListaSeException Genera un respuesta al tener una excepcion sobre el proseso del metodo
     */
    public void deletBoy(String id) throws ListaSeException
    {
        /**
         * Indicamos si la cabeza es diferente a vacio o nulo
         */
        if(this.head !=null)
        {
            /**
             * Validamos que los datos de la cabeza tenga la informacion del nillo y que sea igual a la identificacion del niño que vamos a borrar
             */
            if(this.head.getData().getIdentification().equals(id))
            {
                /**
                 * si es diferente le indicamos a la cabeza que es igual al siguiente niño.
                 */
                this.head = this.head.getNext();
            }
            else
            {
                /**
                 * si la identificacion del niño es igual a la idenficacion ingresada llamamos a un ayudante temporal que se ubique en la cabeza de la lista
                 */
                Node temp = this.head;
                /**
                 * Recorremos la lista indicandole al ayudante temporal que la recorra hasta que sea nulo o vacio
                 */
                while(temp!=null)
                {
                    /**
                     * Validamos que si el siguiente del ayudate temporal es diferente de nulo o vacio y que el siguiente del ayudante del temporal sea igual a la identificacion
                     * ingresada
                     */
                    if(temp.getNext() != null && temp.getNext().getData().getIdentification().equals(id))
                    {
                        /**
                         * generamos un break si la condicio se cumple
                         */
                        break;
                    }
                    /**
                     * Le indicamos al ayudante temporal que se ubique en el siguiente dato del que se va eliminar
                     */
                    temp = temp.getNext();
                }
                /**
                 * Temp va estar parado en el anterior al que debo eliminar o va ser null
                 */

                if(temp!= null)
                {
                    /**
                     * Le indicamos al ayudante que ubique en el siguiente del siguiente que se va a eliminar
                     */
                    temp.setNext(temp.getNext().getNext());
                }
                else
                {
                    /**
                     * Generamos una respuesta para la excepcion la cual se puede reportar por si la identificacion ingresada no existe en la lista
                     */
                    throw new ListaSeException("La identificacion" + id + "no existe en la Lista");
                }
            }
        }
        else
        {
            /**
             * Tambien se genera una respuesta para la excepcion la cual se puede generar por si no hay datos en la lista para eliminar
             */
            throw new ListaSeException("No hay datos en la lista");
        }
    }

    /*public List<Boy> Gender(String gender) {
        if (this.head != null) {
            Node temp = this.head;
            List<Boy> list = new ArrayList<>();
            while (temp != null) {
                if (gender.equals("Feminine")) {
                    list.add(temp.getData());
                    temp = temp.getNext();
                } else if (gender.equals("Male")) {
                    list.add(temp.getData());
                    temp = temp.getNext();
                }
            }
            return list;
        }
        return null;
    }


    public void ListGender(String gender) throws ListaSeException {
        if (this.head != null) {
            Node temp = this.head;
            Boy boy = null;
            while (temp != null) {
                if (temp.getData().getGender().equals(gender)) {
                    boy = new Boy(temp.getData().getIdentification(), temp.getData().getName(), temp.getData().getAge(), temp.getData().getGender(),temp.getData().getLocation(),
                            temp.getData().getDegree());
                    deletBoy(temp.getData().getIdentification());
                    addBoyToStart(boy);

                }

                temp = temp.getNext();
            }


        }

    }*/

    /**
     * Metodo que se encarga de contar la posicion del niño dada la identificacion
     * @param id identificacion Cedula, TI, CE , Nit
     * @return Retorna la posicion el niño
     */

    public int position(String id) {

        /**
         * llamamos a un ayudante temporal el cual queda ubicado en la cabeza de la lista
         */
        Node temp = this.head;
        /**
         * Creamos una variable contador y la inicializamos en 0
         */
        int cont = 0;
        /**
         * Recorremos la lista indicandole al ayudante temporal que la recorra hasta que sea nulo o vacio
         */
        while (temp != null) {
            /**
             * Le indicamos al ayudante temporal que pregunte si la indenfiticacion es igual a la indicada
             */
            if (temp.getData().getIdentification().equals(id)) {
                /**
                 * Retornamos un el contador
                 */
                return cont;
            }
            /**
             * el ayudante se pasa al siguiente nodo
             */
            temp = temp.getNext();
            /**
             *Sumanos el dato en el contador
             */
            cont++;

        }
        /**
         * retornamos menos uno para contar el ultimo
         */
        return -1;
    }

    /**
     * Metodo que permite decirle al niño que pierda N posiciones
     * @param id identificacion Cedula, TI, CE , Nit
     * @param num Parametro que indica el numero de posiciones que pierde
     * @return retorna falso
     * @throws ListaSeException Genera un respuesta al tener una excepcion sobre el proseso del metodo
     */
    public boolean changePosition(String id, int num) throws ListaSeException {
        /**
         * Generamos una variable donde se agrega la posicion definida con la idfentificacion
         *
         */
        int pos=(position(id));
        /**
         * llamamos a un ayudante temporal el cual queda ubicado en la cabeza de la lista
         */
        Node temp = this.head;
        /**
         * Creamos un ayudante temporal 3 y le indicamos que es igual a null;
         */
        Node temp3 = null;
        /**
         * generamos una variable aux de tipo contador y la inicializamos desde 0
         */
        int aux = 0;
        /**
         *  generamos una varible boy la cual contiene los datos de la clase Boy y le indicamos que es igual a null
         */
        Boy boy= null;

        /**
         * generamos una variable aux2 de tipo contador y la inicializamos desde 0
         */
        int aux2 = 0;
        /**
         * Recorremos la lista con la variable aux y le intdicamos que si es menor o igual a pos menos 2
         */
        while (aux<=pos-2)
        {
            /**
             * el ayudante se pasa al siguiente nodo
             */
            temp=temp.getNext();
            aux++;
        }
        /**
         * Validamos si la variable pos es igual a 0
         */
        if(pos==0)
        {
            /**
             * decimos que la varible boy es igual a los datos que contien la clase boy
             */
            /*boy = new Boy(temp.getData().getIdentification(),temp.getData().getName(),temp.getData().getAge(),
                    temp.getData().getGender(),temp.getData().getLocation(),temp.getData().getDegree());*/

            /**
             * Llamamos a un ayudante temporal y le indicamos que es igual a un nuevo nodo o niño
             */
            Node temp2 = new Node(boy);
            /**
             * invocamos el metodo eliminar niño con los datos que obtuvo el temporal con la identificacion
             */
            deletBoy(temp.getData().getIdentification());
            /**
             * preguntamos si el temporal es diferente de null.
             */
            if(temp!=null) {
                /**
                 * Recorremos la lista con ayudante temporal aux2 y le indicamos si es menor al nuemero de posicion indicado
                 */
                while (aux2 < num) {
                    /**
                     * Le decimos al ayudante temporal que tome al siguiente
                     */
                    temp = temp.getNext();
                    aux2++;
                }
                /**
                 * le indicamos al ayudante temporal temp3 que es igual al ayudante temporal temp en la siguiente posicion
                 */
                temp3=temp.getNext();
                /**
                 * el ayudante temporal  se coloca en la posicion en la que esta el ayudante temporal temp 2
                 */
                temp.setNext(temp2);
                /**
                 * y le indicamos al ayudante temporal temp que el siguiente es el que es colocado en el temp3
                 */
                temp.getNext().setNext(temp3);
                return true;
            }
            return false;

        }

        /**
         * decimos que la varible boy es igual a los datos que contien la clase boy
         */
        /*boy = new Boy(temp.getNext().getData().getIdentification(),temp.getNext().getData().getName(),temp.getNext().getData().getAge(),
                temp.getNext().getData().getGender(),temp.getData().getLocation(),temp.getData().getDegree());*/
        /**
         * invocamos el metodo eliminar niño con los datos que obtuvo el temporal con la identificacion
         */
        deletBoy(temp.getNext().getData().getIdentification());
        /**
         * Llamamos a un ayudante temporal y le indicamos que es igual a un nuevo nodo o niño
         */
        Node temp2 = new Node(boy);
        /**
         * preguntamos si el temporal es diferente de null.
         */
        if (temp!=null ) {
            /**
             * Recorremos la lista donde indicamos si el siguiente del temporal es diferente de null y aux 2 es menor al numero indicado
             */
            while (temp.getNext()!=null && aux2 < num) {
                /**
                 * Le decimos al ayudante temporal que tome al siguiente
                 */
                temp = temp.getNext();
                aux2++;
            }
            /**
             * le indicamos al ayudante temporal temp3 que es igual al ayudante temporal temp en la siguiente posicion
             */
            temp3=temp.getNext();
            /**
             * el ayudante temporal  se coloca en la posicion en la que esta el ayudante temporal temp 2
             */
            temp.setNext(temp2);
            /**
             * y le indicamos al ayudante temporal temp que el siguiente es el que es colocado en el temp3
             */
            temp.getNext().setNext(temp3);

            return true;
        }
        else
        {
            return false;
        }


    }

    /**
     * Metodo de buscaqueda por identificion, este metodo busca en la lista simplemente enlzada,un niño a partir de la identificacion
     * si no encuentra el niño retorana vacio
     * @param id cedula, TI, CE, dato que identifica el niño que voy a buscar
     * @return El niño que encontre con todos sus datos
     */
    public Boy findByIdentification(String id) {
        /**
         * como no se puede mover la cabeza por que se vuelan todos lo niños
         * llamo a un ayudante y lo ubico en la cabeza
         */
        Node temp = this.head;
        /**
         * se crea un ciclo para recorrer la lista de principio a fin se llega al final cuando el ayudante llegue a vacio
         */
        while (temp!=null)
        {
            /**
             * pregunto si el niño en el cual esta ubicado el ayudante es el de la identificacion que estoy buscando
             * ingresado en el parametro identificacion
             */
           if(temp.getData().getIdentification().equals(id))
           {
               /**
                * se encontro el niño y lo retorna de inmediato
                * finalizo mi mentodo
                */
               return temp.getData();
           }
            /**
             * el ayudante se pasa al siguiente nodo
             */
           temp=temp.getNext();
        }
        /**
         * si llega a esta linea significa que no encontre el niño y retorna vacio
         */
        return null;
    }

    /**
     * Metedo encargado de validar que en la lista cuente con informacion
     * @throws ListaSeException Genera mensajes de excepcion que se puedan presentar en el metodo
     */
    public void validateListEmpty()throws ListaSeException
    {
        /**
         * Validamos que la cabeza sea igual a vacio o nula
         */
        if(this.head==null)
        {
            /**
             * Si la cabeza es vacia generamos una mensaje de excepcion que nos indica que no hay datos en la lista
             */
            throw new ListaSeException("No hay datos en la lista");
        }
    }

    /**
     * Metodo encargado de listar los niños segun el genero ingresado
     * @param gender Masculion , Femenino
     * @return Retorna la lista de los niños ordenados segun el genero indicado
     * @throws ListaSeException Genera una respuesta al tener una excepcion sobre el proseso del metodo
     */
    public ListSE getListSeBoysByGender(String gender) throws ListaSeException
    {
        /**
         * Validamos que la lista tenga niños
         */
        validateListEmpty();
        /**
         * Llamamos a un ayudante temporal y lo ubicamos en la cabeza de la lista
         */
        Node temp = this.head;
        /**
         * Creamos una lista temporal que tenga los metodos generados en la clase de listas SE
         */
        ListSE listTemp = new ListSE();
        /**
         * Recorremos la lista indicandole al ayudante temporal que la recorra hasta que se nulo o vacio
         */
        while(temp != null)
        {
            /**
             * El ayudante temporal pregunta al niño si el genero que tiene es igual al genero ingresado
             */
            if(temp.getData().getGender().getDescription().equals(gender))
            {
                /**
                 * Agregamos a lista temporal el niño con el genero indicado
                 */
                listTemp.add(temp.getData());
            }
            /**
             * Si el genero es diferente al indicado le decimos al ayudante temporal que se ubique en el siguiente del que se encuentra.
             */
            temp = temp.getNext();
        }
        /**
         * Retornamos a lista temporal con lo datos de los niños con el genero indicado
         */
        return listTemp;
    }

    /**
     * Metedo encargado de intercarlos los niños por genero
     * @throws ListaSeException Genera una respuesta al tener una excepcion sobre el proseso del metodo
     */

    public void variantBoys() throws ListaSeException
    {
        /**
         * Validamos que la lista tenga niños
         */
        validateListEmpty();
        /**
         * Creamos dos listas temporales de la lista SE con los metodo relacionadas en ellas una de niños y otra de niñas en las cuales
         * les indicamos que son iguales al metodo obentener lista de niños por genero.
         */
        ListSE kids = this.getListSeBoysByGender("MASCULINO");
        ListSE girls = this.getListSeBoysByGender("FEMENINO");
        /**
         * Cramos dos listas temporales de ls lista SE uan maxima y minima las cuales se indican como nulas
         */
        ListSE minList = null;
        ListSE maxList = null;
        /**
         * preguntamos si la cantidad de niños es mayor a la cantidad de niñas
         */
        if(kids.getCount()> girls.getCount())
        {
            /**
             * si se cumple la codicionn agregamos la informacion a la lista de niñas
             */
            minList = girls;
            maxList = kids;
        }
        else
        {
            /**
             * si se cumple la codicionn agregamos la informacion a la lista de niños
             */
            minList = kids;
            maxList = girls;
        }
        /**
         * llamamos a un ayudante temporal al cual le indicamos que es igual a la cabeza de la lista minima
         */
        Node temp = minList.getHead();
        /**
         * cramos una variable de pos y la inicializamos en 2
         */
        int pos=2;
        /**
         * Recorremos la lista indicandole al ayudante temporal que la recorra hasta que se nulo o vacio
         */
        while(temp != null)
        {
            /**
             * en la lista maxima agretamos la posicion del datos indicado
             */
            maxList.addBoyByPosition(temp.getData(), pos);
            pos = pos +2;
            /**
             * le decimos al temporal que se pase al siguiente dato donde esta ubicado
             *
             */
            temp = temp.getNext();
        }
        /**
         * le indicamos a la cabeza que es igual a la lista maxima.
         */
        this.head = maxList.getHead();



    }

    /**
     * Método que cuenta el la cantidad de municiopios ingresados
     * @param code codigo del municipio code:16917001 - Manizales code:16917002 - Chichina
     * @return Retorna la lista de los municipios
     */
    public int getCountBoyByLocation(String code)
    {
        /**
         * Llamamos a un ayudante temporal y le indicamos que es la cabeza de la lista
         */
        Node temp = this.getHead();
        /**
         * Creamos una variable contador la cual inicializamos en 0
         */
            int count=0;
        /**
         * Recorremos la lista indicandole al ayudante temporal que la recorra hasta que se nulo o vacio
         */
        while(temp != null)
            {
                /**
                 * le indicamos al ayudante temporal que pregunte si es code del municipio es igual al code del niño.
                 */
                if(temp.getData().getLocation().getCode().equals(code))
                {
                    /**
                     * Si es igual cuente el municipio al que hace referencia el codigo
                     */
                    count++;
                }
                /**
                 * si el codigo no es igual al del niño que le esta preguntando le decimos al ayudante temporal que se pase al siguiente del que esta ubicado
                 */
                temp = temp.getNext();
            }
        /**
         * Retornammos la varible contador con la cantidad de municipios
         */
            return count;
        }

    /**
     * Metodo encargado de listar los niños por edad y municipio y que la edad sea igual o menor a la indicada
     * @param age parametro que carga la edad de los niños creados en la lista
     * @param location Manizales - Chinchina - Neira
     * @return Retorna la lista de los niños indicados segun la edad y el municipio y que san menores o igual a la edad indicada
     */
    public List<Boy> listLocationByAge(byte age, String location)
    {
        /**
         * Validamos si la cabeza es difente de nulo o vacia
         */
        if(this.head != null){
            /**
             * Llamos a un ayudante temporal el cual le indicamos que es la cabeza
             */
            Node temp = this.head;
            /**
             * Creamos un lista la cual es el arreglo de la lista los datos del niño
             */
            List<Boy> list = new ArrayList<>();
            /**
             * Le indicamos al ayudante temporal que recorra la lista hasta que sea nula o vacia
             */
            while(temp != null){
                /**
                 * Validamos con el ayudante temporal que pregunte al niño si la edad que tiene es menor o igual a la indicada
                 */
                if(temp.getData().getAge()<=age)
                {
                    /**
                     * Si la edad ingresada es menor o igual a la del niño consultado le decimos al ayudante temporal que pregunte si el municipio
                     * al que pertences es igual al indicado
                     */
                    if(temp.getData().getLocation().getDescription().equals(location)){
                        /**
                         * Si los datos consultados por el ayudante temporal son iguales a los indicados agregamos el niño al lista creada.
                         */
                        list.add(temp.getData());
                    }
                }
                /**
                 * Si los datos son diferentes a los indicados le decimos al ayudante temporal que se pasa al siguiente dato del que esta ubicado
                 *
                 */
                temp = temp.getNext();
            }
            /**
             * Retornamos la lista con los datos o niños que cumplen con los paremetros indicados
             */
            return list;

        }
        /**
         * Si no se cumple ninguna de las condiciones retorna Nulo
         */
        return null;
    }

    /**
     * Metodo encargado de listas los niños por genero y edad donde la edad sea menor o igual a la indicada
     * @param age parametro que carga la edad de los niños creados en la lista
     * @param gender Masculino - Femenino
     * @throws ListaSeException Genera una respuesta al tener una excepcion sobre el proseso del metodo
     */
    public void ListByGenderAge(byte age, String gender) throws ListaSeException{
        /**
         * Validamos si la cabeza es difente de nulo o vacia
         */
        if(this.head != null)
        {
            /**
             * Llamos a un ayudante temporal el cual le indicamos que es la cabeza
             */
            Node temp = this.head;
            /**
             * Creamos una lista temporal de lista SE que contiene los metodo creados
             */
            ListSE list = new ListSE();
            /**
             * Le indicamos al ayudante temporal que recorra la lista hasta que sea nula o vacia
             */
            while(temp != null)
            {
                /**
                 * le indicamos al ayudante temporal que preguente en el niño en el que esta ubicado si el genero ingresado es igual al genero del niño
                 * y si la edad es igual al indicada
                 */
                if(temp.getData().getGender().equals(gender) && temp.getData().getAge()<= age){
                    /**
                     * Si el genero y la edad es igual a las indicada lo agrege al incio de lista temporal creada
                     */
                    list.addBoyToStart(temp.getData());
                }else{
                    /**
                     * si no solo agrege al niño
                     */
                    list.add(temp.getData());
                }
                /**
                 * Si ninguna de las anteriores se cumple de decimos al ayudante temporal se para al siguiente niño
                 */
                temp = temp.getNext();

            }
            /**
             * le indicamos que la cabeza es ibual a lista temporal creada.
             */
            this.head = list.getHead();
        }
    }

    /**
     * Metedoto que se encarga de borra el niño o niños dependiendo la edad ingresadao o solicitada
     * @param age parametro que carga la edad de los niños creados en la lista
     * @throws ListaSeException Genera una respuesta al tener una excepcion sobre el proseso del metodo
     */
    public void DeleteBoyByAge(byte age) throws ListaSeException{
        /**
         * Validamos que la lista tenga datos
         */
        validateListEmpty();
        /**
         * Llamos a un ayudante temporal el cual le indicamos que es la cabeza
         */
        Node temp = this.head;
        /**
         * Creamos dos listas temporales de la lista SE que contiene los metodo creados
         */
        ListSE listOne = new ListSE();
        ListSE listTwo = new ListSE();
        /**
         * Le indicamos al ayudante temporal que recorra la lista hasta que sea nula o vacia
         */
        while (temp != null){
            /**
             * le indicamos al ayudante temporal que en el niño que esta ubicado  pregunte si la edad es mayor a la indicada
             */
            if(temp.getData().getAge()>age){
                /**
                 * Si la edad es mayor a la indicada agregamos a la primera lista temporal el dato optenido por el temporal
                 */
                listOne.add(temp.getData());
            }else{
                /**
                 * Si la edad no es mayor a la indicada agregamos a la segunda lista temporal el dato optenido por el temporal
                 */
                listTwo.add(temp.getData());
            }
            /**
             * si no se cumple las condiciones le indicamos al temporal que se ubique en el siguiente dato
             */
            temp = temp.getNext();
        }
        /**
         * le decimos a la cabeza que es igual a la lista temporal dos.
         */
        this.head = listTwo.getHead();

    }

    /**
     * Metedoto que se encarga de borra el niño o niños dependiendo el genero solicitado
     * @param gender Masculion - Femenino
     * @throws ListaSeException Genera una respuesta al tener una excepcion sobre el proseso del metodo
     */
    public void DeleteBoyByGender(String gender)throws ListaSeException{
        /**
         * Validamos que la lista tenga datos
         */
        validateListEmpty();
        /**
         * Llamos a un ayudante temporal el cual le indicamos que es la cabeza
         */
        Node temp = this.head;
        /**
         * Creamos dos listas temporales de la lista SE que contiene los metodo creados
         */
        ListSE listOne = new ListSE();
        ListSE listTwo = new ListSE();
        /**
         * Le indicamos al ayudante temporal que recorra la lista hasta que sea nula o vacia
         */
        while(temp != null){
            /**
             * le indicamos al ayudante temporal que en el niño que esta ubicado  pregunte si el genero es igual al indicado
             */
            if(temp.getData().getGender().equals(gender)){
                /**
                 * Si el genero es igual al indicado agregamos a la primera lista temporal el dato optenido por el temporal
                 */
                listOne.add(temp.getData());

            }else {
                /**
                 * Si el genero no es igual al indicada agregamos a la segunda lista temporal el dato optenido por el temporal
                 */
                listTwo.add(temp.getData());
            }
            /**
             * si no se cumple las condiciones le indicamos al temporal que se ubique en el siguiente dato
             */
            temp = temp.getNext();
        }
        /**
         * le decimos a la cabeza que es igual a la lista temporal dos.
         */
        this.head = listTwo.getHead();
    }

    /**
     * Metodo que elimina el niño dependiendo la posicion indicada
     * @param position numero de posicion que se indica en el cual esta el niño a eliminar
     * @throws ListaSeException Genera una respuesta al tener una excepcion sobre el proseso del metodo
     */
    public void DeleteBoyPosition(int position) throws ListaSeException{
        /**
         * Validamos que la lista tenga datos
         */
        validateListEmpty();
        /**
         * Indicamos si la posicion indicada es mayora la cantidad de datos en la lista
         */
        if(position > count){
            /**
             * si la posicion es mayor se genera una mensaje de excepcion indicando que la posicion no existe
             */
            throw new ListaSeException("La posicion" + position + "No existe");
        }
        /**
         * indicamos que si la posicion indicada es igual a 1
         */
        if(position == 1){
            /**
             * le decimos al cabeza que es igual al siguiente dato en la lista
             */
            this.head = this.head.getNext();
        }else{
            /**
             * Si no se cumple cramos una variable contador iniciada en 1
             */
            int count = 1;
            /**
             * Llamos a un ayudante temporal el cual le indicamos que es la cabeza
             */
            Node temp = this.head;
            /**
             * Le indicamos al ayudante temporal que recorra la lista hasta que sea nula o vacia
             */
            while (temp!= null)
            {
                /**
                 * Validamos si el contador es igual a la posicion menos 1
                 */
               if(count == position - 1)
               {
                   /**
                    * Generamos un break
                    */
                   break;
               }
                /**
                 * si no se cumple las condiciones le indicamos al temporal que se ubique en el siguiente dato
                 */
               temp = temp.getNext();
                /**
                 * le indicamos al contado que cuente la posicion
                 */
               count++;
            }
            /**
             * si no se cumple le decimos al temporal que se para en el siguiente de su siguiente
             */
            temp.setNext(temp.getNext().getNext());
            count--;

        }
    }

    /**
     * Metodo encargado de listar los niños por grado
     * @param degree parametros del  grado del niño 1 -2- 3- 4- 5
     * @return retorna la lista de los niños segun el grado indicado
     */
    public List<Boy> listBoyDegree(Integer degree){
        /**
         * Validamos si la cabeza es difente de nulo o vacia
         */
        if(this.head != null)
        {
            /**
             * Llamos a un ayudante temporal el cual le indicamos que es la cabeza
             */
            Node temp = this.head;
            /**
             * Creamos una lista la cual es el arreglo de la lista los datos del niño
             */
            List<Boy> list = new ArrayList<>();
            /**
             * Le indicamos al ayudante temporal que recorra la lista hasta que sea nula o vacia
             */
            while (temp != null){
                /**
                 * le decimos al ayudante temporal que pregunte al niño en el que esta si el grado indicado es igual al garo al que pertenece.
                 */
                if(temp.getData().getDegree().getDegree().equals(degree))
                {
                    /**
                     * Si el grado indicado es igual agregamos el niño a lista
                     */
                    list.add(temp.getData());
                }
                /**
                 * si la condicion no se cumple le decimos al temporal que se pase al siguiente
                 */
                temp = temp.getNext();
            }
            /**
             * Retornamso el la lista con los niños del grado indicado
             */
            return list;
        }
        /**
         * si no se cumple las condiciones rentornamos nullo o vacio.
         */
        return null;
    }

    /**
     * Metodo encargado de invertir la lista por genero y edad indicada
     * @param gender Masculion - Femenino
     * @param age parametro que carga la edad de los niños creados en la lista
     * @throws ListaSeException Genera una respuesta al tener una excepcion sobre el proseso del metodo
     */
    public void invertBoyByGenderAge(String gender, byte age) throws ListaSeException {
        /**
         * Llamos a un ayudante temporal el cual le indicamos que es la cabeza
         */
        Node temp = this.head;
        /**
         * Creamos una lista temporal de lista SE que contiene los metodo creados
         */
        ListSE listTemp = new ListSE();
        /**
         * Le indicamos al ayudante temporal que recorra la lista hasta que sea nula o vacia
         */
        while (temp != null) {
            /**
             * Le indicamos al ayudante temporal que pregunte si el genero igual al indicado y si la edad es menor o igual a la indicada
             */
            if (temp.getData().getGender().getDescription().equals(gender) && temp.getData().getAge() <= age) {
                /**+
                 * si se cumple la condicion le indicamos que agrege el niño al inicio de la lista temporal
                 */
                listTemp.addBoyToStart(temp.getData());
            } else {
                /**
                 * si no que agrege el niño a lista temporal
                 */
                listTemp.add(temp.getData());
            }
            /**
             * le decimos al temporal que se ubique en el siguiente
             */
            temp = temp.getNext();
        }
        /**
         * le indicamos a la cabeza que es igual a lista temporal generada
         */
        this.head = listTemp.getHead();
    }

    /**
     * Genero encargado de contar los niños por genero
     * @param Description Femenino - Masculino
     * @return Retorna el contador de los niños
     */
    public int getCountBoyByGender(String Description)
    {
        /**
         * Llamos a un ayudante temporal el cual le indicamos que es la cabeza
         */
        Node temp = this.getHead();
        /**
         * cramos una variable contador que se inicializa en 0
         */
        int count=0;
        /**
         * Le indicamos al ayudante temporal que recorra la lista hasta que sea nula o vacia
         */
        while(temp != null)
        {
            /**
             * le indicamos al ayudante temporal que pregunte si el genero es igual al indicado
             */
            if(temp.getData().getGender().getDescription().equals(Description))
            {
                /**
                 * Si el genero es igual al indicado que lo cuente en la varible contador
                 */
                count++;
            }
            /**
             * le decimos al temporal que se ubique en el siguiente
             */
            temp = temp.getNext();
        }
        /**
         * retornamos el contador con la cantidad
         */
        return count;
    }











}
