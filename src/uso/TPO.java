package uso;

import tda.*;
import imple.*;
import implementacion.DiccionarioSimpleEstatico;
import implementacion.PilaEstatica;
import implementacion.ConjuntoEstatico;
import interfaz.DiccionarioSimpleTDA;
import interfaz.PilaTDA;
import interfaz.ColaTDA;
import interfaz.ConjuntoTDA;

public class TPO {

	
	public static float ejercicio6(PilaTDA p) {//el metodo es lineal puesto que posee un ciclo
		PilaTDA aux = new PilaEstatica();
		aux.inicializa();
		int pares=0;
		int total=0;
		while(!p.pilaVacia()) {
			int n = p.tope();
			if(n % 2 == 0) 
				pares++;
			total++;
			aux.apilar(p.tope());
			p.desapilar();
		}
		float porcentaje = ((float)pares/total)*100 ;
		while(!aux.pilaVacia()) {
			p.apilar(aux.tope());
			aux.desapilar();
		}
	
		
		return porcentaje;
	}
	
	public static void main(String[] args) {
		

		PilaTDA pila = (PilaTDA) new Pila();
		pila.inicializa();	
		
		ConjuntoTDA ejercicio7 = ElementosRepetidosPila(pila);
		
		/****************/
		
		ColaTDA cola = (ColaTDA) new Cola();
		cola.inicializar();
		
		ColaTDA ejercicio8 = ColaSinRepetidos(cola);

	}
	
	
	/****************/
	/****  EJ: 7 ****/
	/****************/
	public static ConjuntoTDA ElementosRepetidosPila(PilaTDA pila)  
	{
		ConjuntoTDA respuesta = (ConjuntoTDA) new Conjunto(); 
		respuesta.inicializarConjunto();

		ConjuntoTDA utilizados = (ConjuntoTDA) new Conjunto();
		utilizados.inicializarConjunto();
		
		PilaTDA pilaAux = (PilaTDA) new Pila();
		pilaAux.inicializa();		
		
		//Comenzamos a recorrer la pila, elemento por elemento hasta que quede vacia.
		while(!pila.pilaVacia()) 
		{
			int valor = pila.tope();	
			pilaAux.apilar(valor);
			
			// Preguntas si el valor ya esta en el conjunto auxiliar llamado utilizados
			// en caso de que ya exista el valor en utilizados, es decir que el valor esta repetido,
			// lo agregamos al conjunto respuesta.
			if(utilizados.pertenece(valor)) 
			{
				respuesta.agregar(valor);
			}
			
			utilizados.agregar(valor);			
			pila.desapilar();		
		}
		
		// recuperamos la pila que nos llego como input al metodo
		while(!pilaAux.pilaVacia()) 
		{
			pila.apilar(pilaAux.tope());
			pilaAux.desapilar();
		}		
		
		return respuesta;
	}
	
	/****************/
	/****  EJ: 8 ****/
	/****************/
	public static ColaTDA ColaSinRepetidos(ColaTDA cola)  
	{
		ConjuntoTDA conjunto = (ConjuntoTDA) new Conjunto(); 
		conjunto.inicializarConjunto();
		
		ColaTDA respuesta = (ColaTDA) new Cola(); 
		cola.inicializar();	
		
		//Comenzamos a recorrer la cola, elemento por elemento hasta que quede vacia.
		while(!cola.colaVacia()) 
		{
			int valor = cola.primero();	
			
			// Preguntas si el valor ya esta en el conjunto para omitir agregar elementos repetidos.
			// en caso de que no exista el valor, lo agregamos a la nueva cola respuesta.
			if(!conjunto.pertenece(valor)) 
			{
				respuesta.acolar(valor);
				conjunto.agregar(valor);
			}
					
			cola.desacolar();		
		}			
		
		return respuesta;
	}
	
	public static ConjuntoTDA ejercicio9(ColaTDA c, PilaTDA p) {//el metodo es polinomico puesto que posee un ciclo dentro de otro
		ConjuntoTDA res = new ConjuntoEstatico();
		ConjuntoTDA auxC = new ConjuntoEstatico();
		ConjuntoTDA auxP = new ConjuntoEstatico();
		res.inicializarConjunto();
		auxC.inicializarConjunto();
		auxP.inicializarConjunto();
		while(!p.pilaVacia()) {
			auxP.agregar(p.tope());
			p.desapilar();
		}
		while(!c.colaVacia()) {
			auxC.agregar(c.primero());
			c.desacolar();
		}
		while(!auxC.conjuntoVacio()) {
			int v = auxC.elegir();
			if(auxP.pertenece(v))
				res.agregar(v);
			auxC.sacar(v);
		}
		
		return res;
	}
	
	public static DiccionarioSimpleTDA ejercicio10(PilaTDA p) {//el metodo es polinomico puesto que posee un ciclo dentro de otro
		DiccionarioSimpleTDA ds = new DiccionarioSimpleEstatico();
		PilaTDA aux = new PilaEstatica();
		ds.inicializar();
		aux.inicializa();
		while(!p.pilaVacia()) {
			int v = p.tope();
			p.desapilar();
			int cont = 1;
			while(!p.pilaVacia()) {
				if(v == p.tope()) {
					cont++;
					p.desapilar();
				}
				else {
					aux.apilar(p.tope());
					p.desapilar();
				}
			}
			ds.agregar(v, cont);
			while(!aux.pilaVacia()) {
				p.apilar(aux.tope());
				aux.desapilar();
			}
		}
		return ds;
	}
	
	
	/****************/
	/****  EJ: 11 ****/
	/****************/

	public class ConvertirDicMultipleACola {

		 public static void main(String[] args) {
		        // Inicializar diccionario múltiple
		        DiccionarioMultipleTDA dicMultiple = new DiccionarioMultiple();
		        dicMultiple.inicializarDiccionario();

		        dicMultiple.agregar(1, 2);
		        dicMultiple.agregar(1, 3);
		        dicMultiple.agregar(2, 4);
		        dicMultiple.agregar(2, 5);
		        dicMultiple.agregar(3, 5);
		        dicMultiple.agregar(3, 2);

		        // Convertir diccionario múltiple a cola sin repeticiones
		        ColaTDA cola = (ColaTDA) new Cola();
		        cola.inicializar();

		        ConjuntoTDA valoresUnicos = (ConjuntoTDA) new Conjunto();
		        valoresUnicos.inicializarConjunto();

		        ConjuntoTDA claves = (ConjuntoTDA) dicMultiple.claves();
		        while (!claves.conjuntoVacio()) {
		            int clave = claves.elegir();
		            claves.sacar(clave);

		            ConjuntoTDA valores = (ConjuntoTDA) dicMultiple.recuperar(clave);
		            while (!valores.conjuntoVacio()) {
		                int valor = valores.elegir();
		                valores.sacar(valor);
		                if (!valoresUnicos.pertenece(valor)) {
		                    valoresUnicos.agregar(valor);
		                    cola.acolar(valor);
		                }
		            }
		        }

		        // Imprimir cola
		        while (!cola.colaVacia()) {
		            System.out.print(cola.primero() + " ");
		            cola.desacolar();
		        }
		    }
		
	}


}
