package libClases;

public class ClienteTarifaPlana extends Cliente implements Cloneable, Proceso{
	private static float precioTarifa = 20;
	private static final float precioExcesoMinutos = (float) 0.15;
	private static float minutos = 300;
	private float minutosHablados;
	private String nacionalidad;
	
	
	public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, Fecha fAlta, float excesoMinutos, String nac) {
		super(NIF, nom, fNac, fAlta);
		minutosHablados = excesoMinutos;
		nacionalidad = nac;
	}
	
	public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, float excesoLimiteMinutos, String nac) {
		this(NIF, nom, fNac, new Fecha(1, 1, 2018), excesoLimiteMinutos, nac);
	}
	
	public ClienteTarifaPlana(ClienteTarifaPlana c) 
	{
		super(c); 
		this.nacionalidad = c.nacionalidad;
		this.minutosHablados = c.minutosHablados;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public float getMinutos() {
		return minutosHablados;
	}

	public void setMinutos(int minutos) {
		this.minutosHablados = minutos;
	}

	public static float getPrecioTarifa() {
		return precioTarifa;
	}

	public static void setPrecioTarifa(float precioTarifa) {
		ClienteTarifaPlana.precioTarifa = precioTarifa;
	}

	public static float getPrecioexcesominutos() {
		return precioExcesoMinutos;
	}
	
	public static float getTarifa() 
	{
		return precioTarifa;
	}
	
	public static float getLimite() 
	{
		return minutos;
	}
	
	public static void setTarifa(int m, float p){
		ClienteTarifaPlana.minutos = m;
		ClienteTarifaPlana.precioTarifa = p;
	}
	
	public float factura(){
		if(minutosHablados > minutos)
            return precioTarifa + (minutosHablados - minutos)*precioExcesoMinutos;
        else
            return precioTarifa;
	}
	
	@Override
	public void ver() {
		System.out.println(this.toString());
	}
	
	@Override
	public String toString(){
		String s="";
        s = super.toString() + " " + nacionalidad + " " + " ["+ minutos +" por "+ precioTarifa +"] "+ minutosHablados + " ---> "+factura();
        return s;
	}
	
	@Override
	public Object clone(){
		return new ClienteTarifaPlana(getNif(), getNombre(), getFechaNac(), getFechaAlta(), getMinutos(), getNacionalidad());
    }
}
