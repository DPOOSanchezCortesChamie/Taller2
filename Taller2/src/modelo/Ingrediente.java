package modelo;

public class Ingrediente {

	private String nombre;
	private int costoAdicional;
	/**
	 * Constructor para el objeto ingrediente
	 * @param nombre
	 * @param costoAdicional
	 */
	public Ingrediente(String nombre, int costoAdicional) {
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
	}
	/**
	 * Devuelve el nombre del ingrediente
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * Devuelve el costo adicional del ingrediente
	 * @return
	 */
	public int getCostoAdicional() {
		return this.costoAdicional;
	}
}
