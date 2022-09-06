package consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import modelo.ProductoMenu;
import modelo.Restaurante;
import modelo.Ingrediente;
import modelo.Producto;
import modelo.ProductoAjustado;
import modelo.Pedido;
import modelo.Combo;

public class Aplicacion {
	
	private Restaurante infoRestaurante;

	public void ejecutarAplicacion() throws IOException {
		
		File ingredientesFile = new File("./data/ingredientes.txt");
		File menuFile = new File("./data/menu.txt");
		File combosFile = new File("./data/combos.txt");
		
		this.infoRestaurante = new Restaurante();
		
		this.infoRestaurante.cargarMenuRestaurante(ingredientesFile, menuFile, combosFile);
		
		System.out.println("Mix Burger - Servicio de pedidos: \n");

		boolean continuar = true;
		while (continuar) {
			try {
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción: "));
				if (opcion_seleccionada == 1)
					mostrarItemsMenu();
				else if (opcion_seleccionada == 2 && infoRestaurante != null)
					iniciarPedido();
				else if (opcion_seleccionada == 3 && infoRestaurante != null)
					menuOrProductSelecter();
				else if (opcion_seleccionada == 4 && infoRestaurante != null)
					cerrarPedidoYGuardarFactura();
				else if (opcion_seleccionada == 5 && infoRestaurante != null)
					consultarPedidoId();
				else if (opcion_seleccionada == 6) {
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else {
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}	
	}
	
	public void consultarPedidoId() {
		String idFactura = input("Ingrese un Id de 4 digitos a consultar: ");
		for (String factura: this.infoRestaurante.getFacturas()) {
			if (factura.substring(11, 15).equals(idFactura)) {
				System.out.println(factura);
			}
		}
	}
	
	// Opción 4
	public void cerrarPedidoYGuardarFactura() throws IOException {
		Pedido pedidoToClose = this.infoRestaurante.getPedidoEnCurso();
		String textoFactura = pedidoToClose.generarTextoFactura();
		this.infoRestaurante.getPedidos().add(pedidoToClose);
		this.infoRestaurante.iniciarPedido("", "");
		this.infoRestaurante.getFacturas().add(textoFactura);
		System.out.println(textoFactura);
	}
	
	// Opción 3
	public void menuOrProductSelecter() {
		System.out.println("1. Selecionar combos.");
		System.out.println("2. Selecionar producto individual.");
		int selectionCoP = Integer.parseInt(input("Ingrese el numero del tipo de item a agregar: "));
		boolean correctSelectionCoP = false;
		while (correctSelectionCoP == false) {
			if (selectionCoP == 1) {
				mostrarCombos();
				int selectionC = Integer.parseInt(input("Ingrese el numero del combo a agregar: "));
				if (this.infoRestaurante.getCombos().get(selectionC - 1) != null) {
					this.infoRestaurante.getPedidoEnCurso().agregarPedido(this.infoRestaurante.getCombos().get(selectionC - 1));
					correctSelectionCoP = true;
				}
				else {
					System.out.println("Ingresó numero de combo incorrecto.");
				}
			
			}
			else if (selectionCoP == 2) {
				mostrarItemsMenu();
				int selectionP = Integer.parseInt(input("Ingrese el numero del producto a agregar: "));
				ProductoMenu productoSeleccionado = this.infoRestaurante.getMenuBase().get(selectionP - 1);
				if (productoSeleccionado != null) {
					String addOrDel = input("¿Desea agregar o eliminar algun ingrediente? (a/e/n): ");
					if (addOrDel.equals("a")) {
						mostrarIngredientes();
						int selectionI = Integer.parseInt(input("Ingrese del numero de ingrediente a agregar: "));
						if (this.infoRestaurante.getIngredientes().get(selectionI - 1) != null) {
							ArrayList<Ingrediente> agregados = new ArrayList<>();
							agregados.add(this.infoRestaurante.getIngredientes().get(selectionI - 1));
							ProductoAjustado productoAjustado = new ProductoAjustado(productoSeleccionado.getNombre(), productoSeleccionado.getPrecio(), agregados, null);
							this.infoRestaurante.getPedidoEnCurso().agregarPedido(productoAjustado);
							correctSelectionCoP = true;
						}		
					}
					else if (addOrDel.equals("e")) {
						mostrarIngredientes();
						int selectionI = Integer.parseInt(input("Ingrese del numero de ingrediente a eliminar: "));
						if (this.infoRestaurante.getIngredientes().get(selectionI - 1) != null) {
							ArrayList<Ingrediente> eliminados = new ArrayList<>();
							eliminados.add(this.infoRestaurante.getIngredientes().get(selectionI - 1));
							ProductoAjustado productoAjustado = new ProductoAjustado(productoSeleccionado.getNombre(), productoSeleccionado.getPrecio(), null, eliminados);
							this.infoRestaurante.getPedidoEnCurso().agregarPedido(productoAjustado);
							correctSelectionCoP = true;
						}		
					}
					else {
						this.infoRestaurante.getPedidoEnCurso().agregarPedido(productoSeleccionado);
						correctSelectionCoP = true;
					}
				}
				else {
					System.out.println("Ingresó numero de producto incorrecto.");
				}
			}
		}
	}
		
	
	//Inicia un pedido - Opcion 2
	public void iniciarPedido() {
		String nombreCliente = input("Estimado cliente ingrese su nombre: ");
		String direccionCliente = input("Estimado cliente ingrese su direccion: ");
		this.infoRestaurante.iniciarPedido(nombreCliente, direccionCliente);
	}
	
	//Items menu - Opcion 1
	public void mostrarItemsMenu() {
		ArrayList<ProductoMenu> menu = this.infoRestaurante.getMenuBase();
		for (int i = 0; i < menu.size(); i++) {
			ProductoMenu productoMenu = menu.get(i);
			System.out.println(Integer.toString(i+1) + ". " + productoMenu.getNombre() + " - $" + productoMenu.getPrecio());
		}
	}
	
	public void mostrarIngredientes () {
		ArrayList<Ingrediente> ingredientes = this.infoRestaurante.getIngredientes();
		for (int i = 0; i < ingredientes.size(); i++) {
			Ingrediente ingrediente = ingredientes.get(i);
			System.out.println(Integer.toString(i+1) + ". " + ingrediente.getNombre() + " - $" + ingrediente.getCostoAdicional());
		}
	}
	
	public void mostrarCombos() {
		ArrayList<Combo> combos = this.infoRestaurante.getCombos();
		for (int i = 0; i < combos.size(); i++) {
			Combo combo = combos.get(i);
			System.out.println(Integer.toString(i+1) + ". " + combo.getNombre() + " - $" + combo.getPrecio());
		}
	}
	
	//Menu de opciones
	public void mostrarMenu() {
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar el menú");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido y guardar la factura");
		System.out.println("5. Consultar la información de un pedido dado su id");
		System.out.println("6. Salir de la aplicación");
	}
		
	public String input(String mensaje) {
		try {
			System.out.print(mensaje);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public Object foo() {
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}
}
