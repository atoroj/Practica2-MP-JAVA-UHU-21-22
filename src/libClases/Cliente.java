package libClases;

public class Cliente {
	private static int n = 1;
	private final static Fecha fechaPorDefecto = new Fecha(1, 1, 2018);
	private final String nif; // dni del cliente (letra incluida) (NO puede cambiar)
	private final int codCliente; // codigo único (y fijo) generado por la aplicación
	private String nombre; // nombre completo del cliente (SI se puede modificar)
	private final Fecha fechaNac; // fecha nacimiento del cliente (NO se puede cambiar)
	private final Fecha fechaAlta; // fecha de alta del cliente (SI se puede modificar)

	public Cliente(String NIF, String nom, Fecha fNac, Fecha fAlta){
		codCliente = n++;
		nif = NIF;
		nombre = nom;
		fechaNac = fNac;
		fechaAlta = fAlta;
	}

	public Cliente(String NIF, String nom, Fecha fNac) {
		this(NIF, nom, fNac, fechaPorDefecto);
	}
	
	public Cliente(Cliente c) {
		this(c.nif,c.nombre,c.fechaNac,c.fechaAlta);
	}
	
	public void ver() {
		System.out.println(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public Object clone() {
		return new Cliente(this.nif, this.nombre, this.fechaNac, this.fechaAlta);
	}

	public String toString() {
		return nif + " " + fechaNac + ": " + nombre + " " + "( " +codCliente+ " - " + fechaAlta + ")"; 
	}

	
	
	//---------------------------GETTERS/SETTERS----------------------------------
	public String getNombreAtributo() {
		return nombre;
	}

	public void setNombreAtributo(String nombre) {
		this.nombre = nombre;
	}

	public static Fecha getFechaPorDefecto() {
		return fechaPorDefecto;
	}

	public Fecha getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(Fecha fecha) {
		fechaAlta.setFecha(fecha.getDia(), fecha.getMes(), fecha.getAnio());
	}
	
	public final static void setFechaPorDefecto(Fecha f){
        fechaPorDefecto.setFecha(f.getDia(), f.getMes(), f.getAnio());
    }
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Fecha getFechaNac() {
		return fechaNac;
	}

	public String getNif() {
		return nif;
	}

	public int getCodCliente() {
		return codCliente;
	}
	
}
