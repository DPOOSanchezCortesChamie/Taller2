package modelo;

import java.util.ArrayList;
import java.io.File;

public class Restaurante {
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<Pedido> pedidos;
	private ArrayList<ProductoMenu> menuBase;
	private ArrayList<Combo> combos;
	private Pedido pedidoEnCurso;
	/**
	 * Metodo constructor del restaurante
	 */
	public Restaurante() {
		this.ingredientes = new ArrayList<Ingrediente>();
		this.pedidos = new ArrayList<Pedido>();
		this.menuBase = new ArrayList<ProductoMenu>();
		this.combos = new ArrayList<Combo>();
	}
	/**
	 * Inicia el pedido, creando un nuevo objeto Pedido
	 * @param nombreCliente
	 * @param direccionCliente
	 */
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
	}
	/**
	 * Cierra el pedido y lo guarda en la lista de pedidos completos
	 */
	public void cerrarYGuardarPedido() {
		if (!pedidos.contains(pedidoEnCurso))
			pedidos.add(pedidoEnCurso);
	}
	/**
	 * Devuelve el pedido que esta en proceso, si no hay imprime por consola
	 * @return
	 */
	public Pedido getPedidoEnCurso() {
		if(pedidoEnCurso != null) 
			return pedidoEnCurso;
		System.out.println("No hay pedido en curso");
		return null;
	}
	/**
	 * Devuelve el menu base del restaurante (preguntar)
	 * @return
	 */
	public ArrayList<ProductoMenu> getMenuBase(){
		return menuBase;
	}
	/**
	 * Devuelve la lista de ingredientes del restaurante
	 * @return
	 */
	public ArrayList<Ingrediente> getIngredientes(){
		return ingredientes;
	}
	/**
	 * Llama los metodos que cargan la informacion de los ingredientes, el menu y los combos
	 * @param archivoIngredientes
	 * @param archivoMenu
	 * @param archivoCombos
	 */
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) {
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	/**
	 * Carga el arraylist de ingrediente con su archivo
	 * @param archivoIngredientes
	 */
	private void cargarIngredientes(File archivoIngredientes) {
		 if (archivoIngredientes.exists()) {
			 String ingredientes[] = archivoIngredientes.list();
			 for(String ingrediente: ingredientes) {
				 int pos = ingrediente.indexOf(';');
				 String nombre = ingrediente.substring(0, pos);
				 int precio = Integer.parseInt(ingrediente.substring(pos+1, ingrediente.length()));
				 this.ingredientes.add(new Ingrediente(nombre, precio));
			 }
		 } else 
			 System.out.println("No se encontro el archivo de ingredientes");
	}
	/**
	 * Carga el arraylist del menu con su archivo
	 * @param archivoMenu
	 */
	private void cargarMenu(File archivoMenu) {
		if (archivoMenu.exists()) {
			 String menus[] = archivoMenu.list();
			 for(String menu: menus) {
				 int pos = menu.indexOf(';');
				 String nombre = menu.substring(0, pos);
				 int precio = Integer.parseInt(menu.substring(pos+1, menu.length()));
				 this.menuBase.add(new ProductoMenu(nombre, precio));
			 }
		 } else 
			 System.out.println("No se encontro el archivo del menu");
	}
	/**
	 * Carga el arraylist de los combos con su archivo
	 * @param archivoCombos
	 */
	private void cargarCombos(File archivoCombos) {
		if (archivoCombos.exists()) {
			 String combos[] = archivoCombos.list();
			 for(String combo: combos) {
				 int pos = combo.indexOf(';');
				 String c = combo;
				 String nombre = c.substring(0, pos);
				 c = c.substring(pos+1, combo.length());
				 pos = c.indexOf('%');
				 int descuento = Integer.parseInt(combo.substring(0, pos));
				 Combo combi = new Combo(nombre, descuento);
				 c = c.substring(pos+2, combo.length());
				 pos = c.indexOf(';');
				 ArrayList<String> items = new ArrayList<String>();
				 while (pos != -1) {
					 String prod = c.substring(0, pos);
					 items.add(prod);
					 c = c.substring(pos+1, combo.length());
					 pos = c.indexOf(';');
				 }
				 for (String producto: items) {
					 for (ProductoMenu p: menuBase) {
						 if (p.getNombre() == producto) {
							combi.agregarItemACombo(p);
						 	break;
						 }
					 }
					 
				 }
				 this.combos.add(combi);
			 }
		 } else 
			 System.out.println("No se encontro el archivo de los combos");
	}
}
