package modelo;

import java.util.ArrayList;

public class ProductoAjustado extends ProductoMenu implements Producto {
	
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	
	public ProductoAjustado(String nombre, int precioBase, ArrayList<Ingrediente> agregados, ArrayList<Ingrediente> eliminados) {
		super(nombre, precioBase);
		this.agregados = agregados;
		this.eliminados = eliminados;
	}
	
	@Override
	public int getPrecio() {
		int precio = this.getPrecio();
		if (this.agregados.size() != 0) {
			for (Ingrediente ingrediente: agregados) {
				precio += ingrediente.getCostoAdicional();
			}
		}
		return precio;
	}
	@Override
	public String generarTextoFactura() {
		String facturaNames = this.getNombre();
		if (this.eliminados.size() != 0) {
			for (Ingrediente ingrediente: eliminados) {
				facturaNames += " sin " + ingrediente.getNombre();
			}
			facturaNames += ". ";
		}
		if (this.agregados.size() != 0) {
			for (Ingrediente ingrediente: agregados) {
			facturaNames += " con " + ingrediente.getNombre();
			}
			facturaNames += ". ";
		}
		return facturaNames + " -" + Integer.toString(this.getPrecio());
	}
	
}
