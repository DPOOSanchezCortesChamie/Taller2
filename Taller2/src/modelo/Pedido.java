package modelo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Pedido {
	private int numeroPedidos; //incluir en factura
	private int idPedido;
	private String nombreCliente; //incluir en factura
	private String direccionCliente; //incluir en factura
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
		return this.idPedido;
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
		for(Producto p: itemsPedido) { 
			neto += p.getPrecio();
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
	/**
	 * Devuelve el texto para usarse en la factura, con el id del pedido y el precio total
	 * @return
	 */
	private String generarTextoFactura() {
		return idPedido + "; " + getPrecioTotalPedido();
	}
	
	/**
	 * Guarda en el archivo el texto de la factura
	 * @param archivo
	 * @throws IOException 
	 */
	public void guardarFactura(File archivo) throws IOException {
		FileWriter writer = new FileWriter(archivo.getPath());
		writer.write(generarTextoFactura());
		writer.close();
	}
}
