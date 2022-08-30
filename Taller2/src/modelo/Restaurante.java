package modelo;

import java.util.ArrayList;
import java.io.File;

public class Restaurante {
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<Pedido> pedidos;
	private Pedido pedidoEnCurso;
	public Restaurante() {
		this.ingredientes = new ArrayList<Ingrediente>();
		this.pedidos = new ArrayList<Pedido>();
	}
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		
	}
	public void cerrarYGuardarPedido() {
		
	}
	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
	}
	public ArrayList<Producto> getMenuBase(){
		
	}
	public ArrayList<Ingrediente> getIngredientes(){
		
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
