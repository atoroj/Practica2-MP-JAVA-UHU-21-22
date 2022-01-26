package libClases;

public class ClienteMovil extends Cliente implements Cloneable, Proceso {
	private float minutos = 0;
	private Fecha fPermanencia;
	private float precio = 0;
	
	public ClienteMovil(String NIF, String nom, Fecha fNac,  Fecha fAlta, Fecha fechaPermanencia, float minutosHablados, float precioMinutos) {
		super(NIF, nom, fNac, fAlta);
		fPermanencia = fechaPermanencia;
		minutos = minutosHablados;
		precio = precioMinutos;
	}
	
	public ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta, float minutosHablados, float precioMinutos) {
		this(NIF, nom, fNac, fAlta, new Fecha(fAlta.getDia(), fAlta.getMes(), fAlta.getAnio()+1), minutosHablados, precioMinutos);
	}
	
	public ClienteMovil(String NIF, String nom, Fecha fNac, float minutosHablados, float precioMinutos) {
		this(NIF, nom, fNac, getFechaPorDefecto(), minutosHablados, precioMinutos);
	}
	
	public ClienteMovil(ClienteMovil c) 
	{
		super(c);
		fPermanencia = c.fPermanencia;
		minutos = c.minutos;
		precio = c.precio;
	}

	
	public float getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public Fecha getFPermanencia() {
		return fPermanencia;
	}
	
	public void setFPermanencia(Fecha fPermanencia) {
		this.fPermanencia.setFecha(fPermanencia.getDia(), fPermanencia.getMes(), fPermanencia.getAnio());;
	}

	public float factura() {
			float fac=0;
			fac = minutos * precio;
		return fac;
	}
	
	@Override
	public void ver() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return super.toString() + " " + fPermanencia + " " + minutos + " x " + precio + " --> " + factura();
	}
	
	@Override
	public Object clone(){
		return new ClienteMovil(getNif(), getNombre(), getFechaNac(), getFechaAlta(), getFPermanencia(), getMinutos(), getPrecio());
	}
	
}
