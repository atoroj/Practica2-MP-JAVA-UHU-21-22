package libClases;

import java.util.Arrays;
import java.util.Scanner;

public class Empresa implements Cloneable {
	private Cliente[] clientes;
	private int ncli=0;
	
	public Empresa() {
		clientes = new Cliente [10];
		ncli = 0;
	}
	
	public Empresa(Empresa e) {
		for(int i=0; i<ncli; i++) {
			clientes[i] = e.clientes[i];
		}
	}
	
	public void alta (Cliente c){
		int pos = BuscarCliente(c.getNif());
		
		if(pos == -1){
			clientes[ncli] = c;
			ncli++;	
		
			if(ncli == clientes.length){
				Cliente[] aux = new Cliente[ncli + 5];
				for(int i = 0; i<ncli; i++){
					aux[i] = clientes[i];
				}
				
				clientes = aux;
			}
		}
	}
	
	public void alta(){
		String NIF;
		Fecha FNac;
		Fecha FAlta;
		float minutoshablados;
		String nombre;
		Fecha fPermanencia;
		int opc = 0;
		Scanner entrada = new Scanner (System.in);
		ClienteMovil cm = null;
		ClienteTarifaPlana ct = null;
		
		System.out.println("DNI: "); NIF = entrada.nextLine();
		int pos = BuscarCliente(NIF);///BUSCAMOS SI EXISTE ESE CLIENTE
		
		if(pos!=-1)///SI EL CLIENTE YA EXISTE SE NOTIFICA Y SE MUESTRA
		{
			System.out.println("Ya existe un cliente con ese dni: \n");
			clientes[pos].ver();
		}else
		{
			System.out.println("Nombre: "); nombre = entrada.nextLine();
			System.out.println("Fecha Nacimiento:\n");
			FNac = Fecha.pedirFecha();
			System.out.println("Fecha de Alta:\n");
			FAlta = Fecha.pedirFecha();
			System.out.println("Minutos que habla al mes: "); minutoshablados = entrada.nextFloat();
			System.out.println("Indique el tipo de cliente (1-Movil, 2-Tarifa Plana): "); opc = entrada.nextInt();
			
			if(opc==1 && pos ==-1) ///CUANDO EL CLIENTE QUE SE VA A AÑADIR ES UN CLIENTE MOVIL
			{
				float PrecioMinuto;
				fPermanencia = null;
				
				System.out.println("Precio por minuto: "); PrecioMinuto = entrada.nextFloat();
				System.out.println("Fecha fin permanencia: ");
				fPermanencia = Fecha.pedirFecha();
				
				cm = new ClienteMovil(NIF, nombre, FNac, FAlta, fPermanencia, PrecioMinuto, minutoshablados);
				
				alta(cm);
		
			}else if(opc==2 && pos ==-1)///CUANDO EL CLIENTE QUE SE VA A AÑADIR ES UN CLIENTE TARIFA PLANA
				{
				String nac;
				System.out.println("Introduce nacionalidad: "); nac = entrada.nextLine();
				
				ct = new ClienteTarifaPlana(NIF, nombre, FNac, FAlta, minutoshablados, nac);
				
				alta(ct);
			}
		}
			
	}
	
	public int BuscarCliente(String nif){
		boolean encontrado=false;
		int i = 0;
		
		while(!encontrado && i<ncli)
		{
			if(this.clientes[i].getNif().equals(nif))
			{
				encontrado = true;
			}else
				i++;
		}
		
		if(encontrado==true)
			return i;
		else
			return -1;
	}
	
	public float factura() {
		ClienteMovil cm = null;
		ClienteTarifaPlana ct = null;
		float fact = 0;
		
		for(int i = 0; i<ncli; i++){
			if(clientes[i].getClass() == ClienteMovil.class) {
				cm = (ClienteMovil) clientes[i];
				fact = fact + cm.factura();
			}else if(clientes[i].getClass() == ClienteTarifaPlana.class) {
				ct = (ClienteTarifaPlana) clientes[i];
				fact = fact + ct.factura();
			}
		}
		return fact;
		
	}
	
	public void descuento(int dto) {
		float descuento = (float)((100-dto))/100.0f;
		for(int i = 0; i  < ncli; i++){			
			if(clientes[i] instanceof ClienteMovil){
				ClienteMovil c = (ClienteMovil)clientes[i];
				c.setPrecio(c.getPrecio()*descuento);
			}
		}	
	}
	
	public int nClienteMovil() {
		int contador = 0;
		
		for(int i = 0; i<ncli; i++) {
			if(clientes[i] instanceof ClienteMovil ) {
				contador++;
			}
		}
		return contador;
	}
	
	public void baja(String nif) {
		int pos = BuscarCliente(nif);
		
		if(pos!=-1){
			for(int i = 0; i<ncli-1; i++) {
				clientes[i] = clientes[i+1];
			}
			ncli--;
		}
	}
	
	public void baja() {
		String nif;
		char opc;
		Scanner entrada = new Scanner (System.in);
		System.out.println("Introduzca nif cliente a dar de baja:"); nif = entrada.nextLine();
		
		int pos = BuscarCliente(nif);
		
		clientes[pos].ver();
		
		System.out.println("¿Seguro que desea eliminarlo (s/n)?: "); opc = entrada.next().charAt(0);
		
		if(opc == 's')
		{
			baja(nif);
			System.out.println("El cliente " + clientes[pos].getNombre() + " con nif " + clientes[pos].getNif() + " ha sifo eliminado");
		}else
		{
			System.out.println("El cliente con nif " + clientes[pos].getNif() + " no se elimina");
		}
	}
	
	public int getN() {
		return ncli;
	}
	
	@Override
	public String toString() {
		String s="";
        for(int i = 0; i < ncli; i++) {        	
        	s += clientes[i]+"\n";
        }

        return s;
	}
	
	@Override
	public Object clone(){
		Empresa obj = null;
		try{
			obj = (Empresa) super.clone();
			obj.clientes = clientes.clone();
			for(int i = 0; i<ncli; i++)
			{
				obj.clientes[i] = (Cliente) clientes[i].clone();
			}
	
		}catch(CloneNotSupportedException ex)
		{
			System.out.println("No se ha podido clonar el objeto");
		}
		
		return (Object)obj;
	}
	
}
