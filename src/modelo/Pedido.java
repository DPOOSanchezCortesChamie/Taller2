package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {
	
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	
	public Pedido(String nombreCliente, String direccionCliente, int idPedido) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.idPedido = idPedido;
		this.itemsPedido = new ArrayList<Producto>();
	}

	public int getIdPedido() {
		return this.idPedido;
	}
	
	public void agregarPedido(Producto nuevoItem) {
		this.itemsPedido.add(nuevoItem);
	}
	
	private int getPrecioNetoPedido() {
		int precioNeto = 0;
		for (Producto item: this.itemsPedido) {
			precioNeto += item.getPrecio();
		}
		return precioNeto;
	}
	
	private int getPrecioIVAPedido() {
		return (int) (getPrecioNetoPedido() * 0.19);
	}

	private int getPrecioTotalPedido( ) {
		return getPrecioNetoPedido() + getPrecioIVAPedido();
	}
	
	public String generarTextoFactura() {
		String textoFactura = "Mix - Burger\n";
		textoFactura += "Id Pedido: " + this.idPedido;
		textoFactura += "\nCliente: " + this.nombreCliente;
		textoFactura += "\nDirección cliente: " + this.direccionCliente;
		textoFactura += "\nTotal productos" + this.itemsPedido.size();
		textoFactura += "\n Precio Neto: " + Integer.toString(getPrecioNetoPedido());
		textoFactura += "\n Precio IVA: " + Integer.toString(getPrecioIVAPedido());
		textoFactura += "\n Precio Total: " + Integer.toString(getPrecioTotalPedido()) + "\n ";
		
		textoFactura += "\nRegrese pronto a Mix Burguer\n...Disfrute su compra...";
		return textoFactura;
	}
}
