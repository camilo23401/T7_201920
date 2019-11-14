package controller;

import java.util.Scanner;

import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;
	
	/* Instancia de la Vista*/
	private MVCView view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();
			int option = lector.nextInt();
			switch(option){
				case 1:
					try
					{
						modelo.cargarInfo();	
						System.out.println("Se cargaron satisfactoriamente los datos al sistema");
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}	
					break;

				case 2:
					int conec=modelo.darCantidadConectadas();
					System.out.println("Hay "+conec+" componentes conectadas");
					break;

				case 3:
					break;

				case 4:
					break;

				case 5: 
					break;	
					
				case 6: 
					break;	

				default: 
					break;
			}
		}
		
	}	
}
