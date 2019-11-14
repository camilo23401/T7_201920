package model.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.data_structures.ArregloDinamico;
import model.data_structures.GrafoNoDirigido;


/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo 
{

	private GrafoNoDirigido<Integer, Coordenadas> grafo = new GrafoNoDirigido<Integer, Coordenadas>(9999999);
	public void cargarInfo() throws IOException
	{
		String rutaArchivoVertices = "./data/bogota_vertices.txt";
		FileReader lectorArchivo = new FileReader(rutaArchivoVertices);
		BufferedReader lector = new BufferedReader(lectorArchivo);
		String linea = lector.readLine();
		while((linea=lector.readLine())!=null)
		{
			String [] partes = linea.split(";");
			System.out.println(partes[0]);
			Coordenadas interseccion = new Coordenadas(Double.parseDouble(partes[1]), Double.parseDouble(partes[2]) , Integer.parseInt(partes[3]));
			grafo.addVertex(Integer.parseInt(partes[0]),interseccion);
			linea = lector.readLine();
		}
		lector.close();
		String rutaArchivoArcos = "data/bogota_arcos.txt";
		FileReader lectorArchivo2 = new FileReader(rutaArchivoArcos);
		BufferedReader lector2 = new BufferedReader(lectorArchivo2);
		String linea2 = lector2.readLine();
		while(linea2!=null)
		{
			String [] partes = linea2.split(" ");

			int i=1;
			while(i<partes.length)
			{

				grafo.addEdge(Integer.parseInt(partes[0]), Integer.parseInt(partes[i]), calcularPeso(Integer.parseInt(partes[0]), Integer.parseInt(partes[i])));
				i++;
			}
			linea2 = lector2.readLine();
		}
		System.out.println("Cantidad de vertices cargados:"+ grafo.V());
		System.out.println("Cantidad de Arcos cargados:"+ grafo.E());

	}


	public double calcularPeso(int pIdInicio, int pIdFinal)
	{
		double rta = 0.0;
		if(grafo.getInfoVertex(pIdInicio)!=null && grafo.getInfoVertex(pIdFinal)!=null)
		{
			double latInicial = grafo.getInfoVertex(pIdInicio).darLatitud();
			double latFinal = grafo.getInfoVertex(pIdFinal).darLatitud();
			double lonInicial =grafo.getInfoVertex(pIdInicio).darLongitud();
			double lonFinal = grafo.getInfoVertex(pIdFinal).darLongitud();

			rta = Haversine.distance(latInicial, lonInicial, latFinal, lonFinal);
		}
		return rta;
	}


	public int darCantidadConectadas() {
		return grafo.CC();
	}

}
