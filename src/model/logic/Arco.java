package model.logic;

import model.data_structures.ArregloDinamico;

public class Arco implements Comparable<Arco>
{
	private int id;
	private String idsAdyacentes;
	private double peso;
	
	public Arco(int pId, double pPeso)
	{
		id = pId;
		peso = pPeso;
	}
	public int darId()
	{
		return id;
	}
	public String darAdyacentes()
	{
		return idsAdyacentes;
	}
	public ArregloDinamico<Integer> recuperarAdyacentes()
	{
		ArregloDinamico<Integer> listaIds = new ArregloDinamico<Integer>(5);
		String ids [] = idsAdyacentes.split(" ");
		int i = 0;
		while(i<ids.length)
		{
			listaIds.agregar(Integer.parseInt(ids[i]));
		}
		return listaIds;
	}
	@Override
	public int compareTo(Arco o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
