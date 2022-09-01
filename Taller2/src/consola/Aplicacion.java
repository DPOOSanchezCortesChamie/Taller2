package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import modelo.Restaurante;

public class Aplicacion {
	
	private Restaurante infoRestaurante;
	
	public void ejecutarAplicacion() {
		System.out.println("Mix Burger - Servicio de pedidos: \n");

		boolean continuar = true;
		while (continuar) {
			try {
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					foo();
				else if (opcion_seleccionada == 2 && calculadora != null)
					foo();
				else if (opcion_seleccionada == 3 && calculadora != null)
					foo();
				else if (opcion_seleccionada == 4 && calculadora != null)
					foo();
				else if (opcion_seleccionada == 5 && calculadora != null)
					foo();
				else if (opcion_seleccionada == 6) {
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (calculadora == null {
					System.out.println("Para poder ejecutar esta opción primero debe cargar un archivo de atletas.");
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
		/*
		 * Simula la función input de Python...
		 */
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
	
	public static void main(String[] args) {
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}
}
