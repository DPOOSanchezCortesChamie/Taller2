package modelo;

import java.util.ArrayList;
import java.io.File;

public class Pedido {
	private int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	/**
	 * Metodo constructor del objeto Pedido
	 * @param nombreCliente
	 * @param direccionCliente
	 */
	public Pedido(String nombreCliente, String direccionCliente) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.itemsPedido = new ArrayList<Producto>();
	}
	/**
	 * Devuelve el id del pedido
	 * @return
	 */
	public int getIdPedido() {
		return idPedido;
	}
	/**
	 * Agrega un nuevo producto a la lista de productos del pedido
	 * @param nuevoItem
	 */
	public void agregarProducto(Producto nuevoItem) {
		itemsPedido.add(nuevoItem);
	}
	/**
	 * Devuelve el precio sin impuestos del pedido
	 * @return
	 */
	private int getPrecioNetoPedido() {
		int neto = 0;
		for(int i = 0; i<this.itemsPedido.size();i++) {
			neto += itemsPedido.get(i).getPrecio();
		}
		return neto;
	}
	/**
	 * Devuelve el precio con impuestos del pedido
	 * @return
	 */
	private int getPrecioTotalPedido() {
		return getPrecioNetoPedido() + getPrecioIVAPedido();
	}
	/**
	 * Devuelve el impuesto del pedido
	 * @return
	 */
	private int getPrecioIVAPedido() {
		int precio = getPrecioNetoPedido();
		return (int)(precio*0.19);
	}
	
	private String generarTextoFactura() {
		
	}
	public void guardarFactura(File archivo) {
		
	}
}
