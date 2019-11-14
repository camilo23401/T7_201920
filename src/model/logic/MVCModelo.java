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

	private GrafoNoDirigido<Integer, String> grafo = new GrafoNoDirigido<Integer, String>(2000000);
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
				grafo.addVertex(Integer.parseInt(partes[0]),partes[1]+","+partes[2]+","+partes[3] );
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
			String[]infoIn=grafo.getInfoVertex(pIdInicio).split(",");
			String[]infoFin=grafo.getInfoVertex(pIdFinal).split(",");
			double latInicial = Double.parseDouble(infoIn[0]);
			double latFinal = Double.parseDouble(infoFin[0]);
			double lonInicial =Double.parseDouble(infoIn[1]);
			double lonFinal = Double.parseDouble(infoFin[1]);

			rta = Haversine.distance(latInicial, lonInicial, latFinal, lonFinal);
		}
		return rta;
	}
	

	public int darCantidadConectadas() {
		return grafo.CC();
	}

}
