package modelo;

public interface Producto {
	/**
	 * Devuelve el precio del producto
	 * @return
	 */
	public int getPrecio();
	/**
	 * Devuelve el nombre del producto
	 * @return
	 */
	public String getNombre();
	/**
	 * Genera el string que se va a usar para la factura
	 * @return
	 */
	public String generarTextoFactura();
}
