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
	ArregloDinamico<Vertice> vertices = new ArregloDinamico<Vertice>(50000000);	
	ArregloDinamico<Integer> adyacentes = new ArregloDinamico<Integer>(1);
	GrafoNoDirigido<Integer, Coordenadas> grafo = new GrafoNoDirigido<Integer, Coordenadas>(2000000);
	public void cargarInfo() throws IOException
	{
		String rutaArchivoVertices = "data/bogota_vertices.txt";
		FileReader lectorArchivo = new FileReader(rutaArchivoVertices);
		BufferedReader lector = new BufferedReader(lectorArchivo);
		String linea = lector.readLine();
		int contador = 0;
		while(linea!=null)
		{
			if (contador==0)
			{
				contador++;
				linea = lector.readLine();
			}
			else
			{
				String [] partes = linea.split(";");
				grafo.addVertex(Integer.parseInt(partes[0]), new Coordenadas(Double.parseDouble(partes[2]),Double.parseDouble(partes[1])));
				linea = lector.readLine();
			}
		}
		lector.close();
		String rutaArchivoArcos = "data/bogota_arcos.txt";
		FileReader lectorArchivo2 = new FileReader(rutaArchivoArcos);
		BufferedReader lector2 = new BufferedReader(lectorArchivo2);
		String linea2 = lector2.readLine();
		while(linea2!=null)
		{
			String [] partes = linea2.split(" ");
			if(buscarIdEnVertices(Integer.parseInt(partes[0]))!=null)
			{
				int i=1;
				while(i<partes.length)
				{
					System.out.println("A");
					grafo.addEdge(Integer.parseInt(partes[0]), Integer.parseInt(partes[i]), calcularPeso(Integer.parseInt(partes[0]), Integer.parseInt(partes[i])));
					i++;
				}
				linea2 = lector2.readLine();
			}
			else
			{
				linea2 = lector2.readLine();	
			}
		}
	}

	/**public Vertice buscarIdEnVertices(int pId)
	{
		int inicio = 0;
		int fin = vertices.darTamano();
		int medio = (fin+inicio) / 2;
		while(vertices.darElementoPos(medio).darId() !=  pId) 
		{
			if (vertices.darElementoPos(medio).darId() < pId) 
			{
				inicio = medio;
			} 
			else 
			{
				fin = medio;
			}
			medio = (fin+inicio) / 2;
		}
		return vertices.darElementoPos(medio);	

	}
	*/
	public Coordenadas buscarIdEnVertices(int pId)
	{
		return grafo.getInfoVertex(pId);
	}
	public double calcularPeso(int pIdInicio, int pIdFinal)
	{
		double rta = 0.0;
		if(buscarIdEnVertices(pIdInicio)!=null && buscarIdEnVertices(pIdFinal)!=null)
		{
			double latInicial = buscarIdEnVertices(pIdInicio).darLatitud();
			double latFinal = buscarIdEnVertices(pIdFinal).darLatitud();
			double lonInicial = buscarIdEnVertices(pIdInicio).darLongitud();
			double lonFinal = buscarIdEnVertices(pIdFinal).darLongitud();

			rta = Haversine.distance(latInicial, lonInicial, latFinal, lonFinal);
		}
		return rta;
	}
	


}
