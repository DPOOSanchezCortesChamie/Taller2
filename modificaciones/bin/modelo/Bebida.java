
public class Bebida extends ProductoMenu{
	private int calorias;
	public Bebida(int calorias, String nombre, int precio) {
		this.calorias = calorias;
		super(nombre, precio);
	}
}
