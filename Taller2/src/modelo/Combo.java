package modelo;

import java.util.ArrayList;

public class Combo implements Producto{

	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> itemsCombo;
	/**
	 * Constructor de la clase Combo
	 * El atributo descuento lo convierte en un valor util para multiplicar
	 * @param nombre
	 * @param descuento
	 */
	public Combo(String nombre, double descuento) {
		this.nombreCombo = nombre;
		this.descuento = 1.0-(descuento*0.01);
		this.itemsCombo = new ArrayList<ProductoMenu>();
	}
	/**
	 * Agrega un producto a la lista de productos del combo
	 * @param itemCombo
	 */
	public void agregarItemCombo(Producto itemCombo) {
		itemsCombo.add(itemCombo);
	}

	@Override
	public int getPrecio() {
		int total = 0;
		for(ProductoMenu items: this.itemsCombo) {
			total += items.getPrecio();
		}
		return (int)(total * this.descuento);
	}

	@Override
	public String getNombre() {
		return this.nombreCombo;
	}

	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
