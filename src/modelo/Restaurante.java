package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Restaurante {
	
	private ArrayList<ProductoMenu> menuBase;
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<Combo> combos;
	private ArrayList<Pedido> pedidos;
	private Pedido pedidoEnCurso;
	
	public Restaurante() {
		this.menuBase = new ArrayList<ProductoMenu>(22);
		this.ingredientes = new ArrayList<Ingrediente>(15);
		this.combos = new ArrayList<Combo>(4);
		this.pedidos = new ArrayList<Pedido>();
		this.pedidoEnCurso = null;
	}
	
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
	}
	
	public void cerrarYGuardarPedido() {
		if (!pedidos.contains(pedidoEnCurso))
			pedidos.add(pedidoEnCurso);
	}
	
	public Pedido getPedidoEnCurso() {
		return this.pedidoEnCurso;
	}
	
	public ArrayList<ProductoMenu> getMenuBase() {
		return this.menuBase;
	}
	
	public ArrayList<Ingrediente> getIngredientes() {
		return this.ingredientes;
	}
	
	public ArrayList<Combo> getCombos() {
		return this.combos;
	}
	
	public void cargarMenuRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) throws IOException {
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	
	private void cargarIngredientes(File archivoIngredientes) throws IOException {
		if (archivoIngredientes.exists()) {
			ArrayList<String[]> lineas = lineas(archivoIngredientes);
			for (int i = 0; i < lineas.size(); i++) {
				String[] linea = lineas.get(i);
				String nombreIngrediente = linea[0];
				int costoAdicional = Integer.parseInt(linea[1]);
				Ingrediente ingrediente = new Ingrediente(nombreIngrediente, costoAdicional);
				this.ingredientes.add(ingrediente);
			}
		}
		else {
			System.out.println("No se encontró el archivo de los Ingredientes");
		}
	}
	
	private void cargarMenu(File archivoMenu) throws IOException {
		if (archivoMenu.exists()) {
			ArrayList<String[]> lineas = lineas(archivoMenu);
			for (int i = 0; i < lineas.size(); i++) {
				String[] linea = lineas.get(i);
				String nombreMenu = linea[0];
				int precioBase = Integer.parseInt(linea[1]);
				ProductoMenu productoMenu = new ProductoMenu(nombreMenu, precioBase);
				this.menuBase.add(productoMenu);
			}
		}
		else {
			System.out.println("No se encontró el archivo del Menu");
		}
	}
	
	private void cargarCombos(File archivoCombos) throws IOException {
		ArrayList<String[]> lineas = lineas(archivoCombos);
		for (int i = 0; i < lineas.size(); i++) {
			String[] linea = lineas.get(i);
			String nombreCombo = linea[0];
			String precioBase = linea[1].substring(0, linea[1].length() - 1);
			int descuento = Integer.parseInt(precioBase);
			String[] productoMenuNombres = Arrays.copyOfRange(linea, 2, lineas.size());
			ArrayList<ProductoMenu> productosCombo = new ArrayList<>();
			for (ProductoMenu productoMenu: this.menuBase) {
				for (String productoInComboNombre: productoMenuNombres) {
					if (productoInComboNombre.equals(productoMenu.getNombre())) {
						productosCombo.add(productoMenu);
					}
				}
			}
			Combo combo = new Combo(productosCombo, descuento, nombreCombo);
			this.combos.add(combo);
		}
	}

	
	private ArrayList<String[]> lineas(File archivo) throws IOException {
		ArrayList<String[]> lineas = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea = br.readLine();
			while (linea != null) {
				String[] lineaPartes = linea.split(";");
				lineas.add(lineaPartes);
				linea = br.readLine();
			}
		} 
		return lineas;
	}
}
