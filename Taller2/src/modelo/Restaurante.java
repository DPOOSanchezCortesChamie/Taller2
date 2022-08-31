package modelo;

import java.util.ArrayList;
import java.io.File;

public class Restaurante {
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<Pedido> pedidos;
	private ArrayList<ProductoMenu> menuBase;
	private Pedido pedidoEnCurso;
	public Restaurante() {
		this.ingredientes = new ArrayList<Ingrediente>();
		this.pedidos = new ArrayList<Pedido>();
	}
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
	}
	public void cerrarYGuardarPedido() {
		
	}
	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
	}
	public ArrayList<Producto> getMenuBase(){
		return menuBase;
	}
	public ArrayList<Ingrediente> getIngredientes(){
		return ingredientes;
	}
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) {
		cargarIngredientes();
		cargarMenu();
		cargarCombos();
	}
	private void cargarIngredientes(File archivoIngredientes) {
		
	}
	private void cargarMenu(File archivoMenu) {
		
	}
	private void cargarCombos(File archivoCombos) {
		
	}
}
