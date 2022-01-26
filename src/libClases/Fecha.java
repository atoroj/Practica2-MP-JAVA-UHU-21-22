package libClases;

import java.util.Scanner;

public final class Fecha implements Cloneable, Proceso {
	private int dia;
	private int mes;
	private int anio;

	public void setFecha(int d, int m, int a) {
		int dmax, diaMes[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		anio = a; // VIP debo asignar año para al llamar a bisiesto() tengo el año bien
		if (m < 1) // si el mes es incorrecto
			m = 1;
		else if (m > 12) // si el mes es incorrecto
			m = 12;
		dmax = diaMes[m - 1];
		if (m == 2 && bisiesto())
			dmax++;
		if (d > dmax)
			d = dmax;
		else if (d < 1)
			d = 1;
		dia = d;
		mes = m;
	}

	public Fecha(int dia, int mes, int anio) {
		setFecha(dia, mes, anio);
	}

	public Fecha(Fecha f) {
		dia = f.dia;
		mes = f.mes;
		anio = f.anio;
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAnio() {
		return anio;
	}

	public String toString() {
		String s = "";
		if (dia < 10)
			s = s + 0;
		s = s + dia + "/";
		if (mes < 10)
			s = s + 0;
		s = s + mes + "/" + anio;
		return s;
		// LO ANTERIOR SE PUEDE SUSTITUIR POR LO SIGUIENTE
		// return String.format("%02d/%02d/%02d", dia, mes, anio);
	}

	public boolean bisiesto() {
		boolean b = false;
		if (anio % 4 == 0) {
			b = true;
			if (anio % 100 == 0 && anio % 400 != 0)
				b = false;
		}
		return b;
	}

	public void ver() {
		System.out.println(this/* .toString() */);
	}

	public static Fecha pedirFecha() {
		Fecha fecha = null;
		boolean valida = false;
		Scanner sc = new Scanner(System.in);
		int dia, mes, anio;
		do {
			System.out.println("Introduce la Fecha (dd/mm/aaaa): ");
			String cadena = sc.next();
			String[] tokens = cadena.split("/");
			try {
				if (tokens.length != 3)
					throw new NumberFormatException();
				dia = Integer.parseInt(tokens[0]); // parseInt lanza la excepcion
				mes = Integer.parseInt(tokens[1]); // NumberFormatException si no
				anio = Integer.parseInt(tokens[2]);// puede convertir el String a int
				fecha = new Fecha(dia, mes, anio);
				if (fecha.getDia() != dia || fecha.getMes() != mes)
					throw new NumberFormatException();
				valida = true;
			} catch (NumberFormatException e) {
				System.out.println("Fecha no valida");
			}
		} while (!valida);
		//sc.close();
		return fecha;
	}
	
	public Fecha diaSig(){
		
		Fecha f = (Fecha)this.clone();
		int d = f.getDia();
		int m = f.getMes();
		int a = f.getAnio();
		d++;
		int dmax, diaMes[] = {31,28,31,30,31,30,31,31,30,31,30,31};
		
		dmax=diaMes[m-1];
		if(m==2 && bisiesto())
			dmax++;
		if(d>dmax)
		{
			d=d-dmax;
			m++;
			if(m>12)
			{
				m=1;
				a++;
			}
		}
		
		f.setFecha(d, m, a);
		
		return f;
	}
	
	static public boolean mayor(Fecha f1, Fecha f2) {
		/*
		 * boolean esmayor=false; if (f1.anio>f2.anio) esmayor= true; else if
		 * (f1.anio<f2.anio) esmayor= false; else { if (f1.mes>f2.mes) esmayor= true;
		 * else if (f1.mes<f2.mes) esmayor= false; else { if (f1.dia>f2.dia) esmayor=
		 * true; else esmayor= false; } } return esmayor;
		 */
		if (f1.anio * 10000 + f1.mes * 100 + f1.dia > f2.anio * 10000 + f2.mes * 100 + f2.dia)
			return true;
		else
			return false;
	}

	public Object clone() {
		// return new Fecha(this);
		Object obj = null;
		try {
			obj = super.clone(); // se llama al clone() de la clase base (Object)
			// que hace copia binaria de los atributos
		} catch (CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}

	public boolean equals(Object obj) { // true sin son iguales
		if (this == obj)
			return true; // si apuntan al mismo sitio son iguales
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())// if (!(obj instanceof Cliente))
			return false; // si los 2 no son de la misma clase no son iguales
		Fecha c = (Fecha) obj;
		return (dia == c.dia && mes == c.mes && anio == c.anio);
	}

}
