package modelo;

import java.util.ArrayList;

public class Combo implements Producto {
	
	private ArrayList<ProductoMenu> itemsCombo;
	private double descuento;
	private String nombreCombo;
	
	
	public Combo(ArrayList<ProductoMenu> itemsCombo, double descuento, String nombreCombo) {
		this.itemsCombo = itemsCombo;
		this.descuento = descuento;
		this.nombreCombo = nombreCombo;
	}
	
	public void agregarItemACombo(Producto itemCombo) {
		this.itemsCombo.add((ProductoMenu) itemCombo);
	}

	@Override
	public int getPrecio() {
		int precio = 0;
		for (ProductoMenu itemCombo: itemsCombo) {
			int itemPrecio = itemCombo.getPrecio();
			precio += (itemPrecio - (descuento * 0.01));
		}
		return precio;
	}

	@Override
	public String getNombre() {
		return this.nombreCombo;
	}

	@Override
	public String generarTextoFactura() {
		String textoFactura = "";
		for (ProductoMenu producto: itemsCombo) {
			textoFactura += producto.generarTextoFactura() + "\n";
		}
		textoFactura += "Total + Descuento: " + Integer.toString(getPrecio());
		return textoFactura;
	}
	

}
