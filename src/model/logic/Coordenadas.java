package model.logic;

public class Coordenadas implements Comparable <Coordenadas>
{
	private double latitud;
	private double longitud;
	public double darLatitud() {
		return latitud;
	}

	public Coordenadas(double pLatitud,double pLongitud) {
		latitud=pLatitud;
		longitud=pLongitud;
	}
	public void cambiarLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double darLongitud() {
		return longitud;
	}
	public void cambiarLongitud(double longitud) {
		this.longitud = longitud;
	}

	@Override
	public int compareTo(Coordenadas arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

