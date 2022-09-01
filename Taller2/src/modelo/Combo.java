package modelo;

import java.util.ArrayList;

public class Combo implements Producto{

	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> itemsCombo;
	
	public Combo(String nombre, double descuento) {
		this.nombreCombo = nombre;
		this.descuento = descuento;
		this.itemsCombo = new ArrayList<ProductoMenu>();
	}
	
	public void agregarItemCombo(Producto itemCombo) {
		itemsCombo.add(itemCombo);
	}
	
	@Override
	public int getPrecio() {
	
		return null;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
