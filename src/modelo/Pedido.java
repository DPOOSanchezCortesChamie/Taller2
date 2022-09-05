package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {
	
	private int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	
	public Pedido(String nombreCliente, String direccionCliente) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
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
	
	private String generarTextoFactura() {
		String textoFactura = "Mix - Burger\n";
		textoFactura += "Id Pedido: " + Integer.toString(this.idPedido);
		textoFactura += "\nCliente: " + this.nombreCliente;
		textoFactura += "\nDirección cliente: " + this.direccionCliente;
		textoFactura += "\n#Pedidos: " + Integer.toString(this.numeroPedidos);
		
		textoFactura += "\n Item  -  Valor \n";
		
		for (Producto item: itemsPedido) {
			textoFactura += "\n-" + item.getNombre() + " -" + Integer.toString(item.getPrecio());
		}
		textoFactura += "\n Precio Neto: " + Integer.toString(getPrecioNetoPedido());
		textoFactura += "\n Precio IVA: " + Integer.toString(getPrecioIVAPedido());
		textoFactura += "\n Precio Total: " + Integer.toString(getPrecioTotalPedido()) + "\n ";
		
		textoFactura += "\n   Regrese pronto a Mix Burguer   \n...Disfrute su compra...";
		
		return textoFactura;
	}
	
	public void guardarFactura(File archivo) throws IOException {
		FileWriter writer = new FileWriter(archivo.getPath());
		writer.write(generarTextoFactura());
		writer.close();
	}
}
