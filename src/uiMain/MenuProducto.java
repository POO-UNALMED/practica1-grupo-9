package uiMain;

import java.util.Date;
import java.util.Scanner;

import gestorAplicacion.productos.Abarrote;
import gestorAplicacion.productos.Aseo;
import gestorAplicacion.productos.Juguete;

public class MenuProducto {

	public static void mostrarProducto() {
		System.out.println(Mensajes.listaDeProductos);
		System.out.println(Mensajes.cabeceras);

		int index = 0;

		if (!Aseo.productosAseo.isEmpty()) { // Cambiar cuando esten implementados los toString
			for (Aseo aseo : Aseo.productosAseo) {
				index++;
				System.out.println("| " + index + "  |  " + aseo.getCodigo() + "  |  " + aseo.getNombre() + "  |  "
						+ aseo.getInventario() + "  |  " + aseo.getPrecio());
			}
		} else {
			System.out.println(Mensajes.noProductosAseo);
		}

		if (!Abarrote.productosAbarrotes.isEmpty()) {
			for (Abarrote abarrote : Abarrote.productosAbarrotes) {
				index++;
				System.out.println("| " + index + "  |  " + abarrote.getCodigo() + "  |  " + abarrote.getNombre()
						+ "  |  " + abarrote.getInventario() + "  |  " + abarrote.getPrecio());
			}
		} else {
			System.out.println(Mensajes.noProductosAbarrote);
		}

		if (!Juguete.productosJuguetes.isEmpty()) {
			for (Juguete juguete : Juguete.productosJuguetes) {
				index++;
				System.out.println("| " + index + "  |  " + juguete.getCodigo() + "  |  " + juguete.getNombre()
						+ "  |  " + juguete.getInventario() + "  |  " + juguete.getPrecio());
			}
		} else {
			System.out.println(Mensajes.noProductosJuguete);
		}

	}

	public static boolean validarListas(int validador, int idProducto) {

		if (validador == 1 && idProducto <= Aseo.productosAseo.size()) {
			return true;
		} else if (validador == 2 && idProducto <= (Aseo.productosAseo.size() + Abarrote.productosAbarrotes.size())
				&& (idProducto - Aseo.productosAseo.size()) <= Abarrote.productosAbarrotes.size()) {
			return true;
		} else if (validador == 3
				&& idProducto <= (Aseo.productosAseo.size() + Abarrote.productosAbarrotes.size()
						+ Juguete.productosJuguetes.size())
				&& (idProducto
						- (Aseo.productosAseo.size() + Juguete.productosJuguetes.size())) <= Juguete.productosJuguetes
								.size()) {
			return true;
		} else {
			return false;
		}
	}

	public static void actualizarInventario(int opcion, int idProducto, int cantidad) {

		int index = -1;
		int nuevoInventario = -1;

		if (validarListas(1, idProducto)) {

			index = idProducto - 1;
			if (opcion == 1) {
				nuevoInventario = Aseo.productosAseo.get(index).getInventario() + cantidad;
			} else if (opcion == 2 && Aseo.productosAseo.get(index).getInventario() >= cantidad) {
				nuevoInventario = Aseo.productosAseo.get(index).getInventario() - cantidad;
			} else {
				System.out.println(Mensajes.accionNoRealizada);
			}

			if (nuevoInventario >= 0) {
				Aseo.productosAseo.get(index).setInventario(nuevoInventario);
			}

		} else if (validarListas(2, idProducto)) {

			index = idProducto - Aseo.productosAseo.size() - 1;
			if (opcion == 1) {
				nuevoInventario = Abarrote.productosAbarrotes.get(index).getInventario() + cantidad;
			} else if (opcion == 2 && Abarrote.productosAbarrotes.get(index).getInventario() >= cantidad) {
				nuevoInventario = Abarrote.productosAbarrotes.get(index).getInventario() - cantidad;
			} else {
				System.out.println(Mensajes.accionNoRealizada);
			}

			if (nuevoInventario >= 0) {
				Abarrote.productosAbarrotes.get(index).setInventario(nuevoInventario);
			}

		} else if (validarListas(3, idProducto)) {

			index = idProducto - (Aseo.productosAseo.size() + Juguete.productosJuguetes.size()) - 1;
			if (opcion == 1) {
				nuevoInventario = Juguete.productosJuguetes.get(index).getInventario() + cantidad;
			} else if (opcion == 2 && Juguete.productosJuguetes.get(index).getInventario() >= cantidad) {
				nuevoInventario = Juguete.productosJuguetes.get(index).getInventario() - cantidad;
			} else {
				System.out.println(Mensajes.accionNoRealizada);
			}

			if (nuevoInventario >= 0) {
				Juguete.productosJuguetes.get(index).setInventario(nuevoInventario);
			}

		} else {
			System.out.println(Mensajes.noExisteID);
		}
	}

	public static void crearProducto(int opcion, Scanner scan) {
		System.out.println(Mensajes.seccionar);
		int codigo;
		String nombre;
		int inventario;
		float precio;
		float inversion;
		String categoria;
		String tipo;
		String unidad;
		Boolean perecedero;
		String material;
		// Date fechaSalida;
		int garantia;

		System.out.print(Mensajes.codigo);
		codigo = scan.nextInt();
		System.out.print(Mensajes.nombre);
		nombre = scan.next();
		System.out.print(Mensajes.inventario);
		inventario = scan.nextInt();
		System.out.print(Mensajes.precio);
		precio = scan.nextFloat();
		System.out.print(Mensajes.inversion);
		inversion = scan.nextFloat();
		System.out.print(Mensajes.categoria);
		categoria = scan.next();
		System.out.print(Mensajes.tipo);
		tipo = scan.next();

		switch (opcion) {
		case 1:

			System.out.print(Mensajes.unidad);
			unidad = scan.next();

			new Aseo(codigo, nombre, inventario, precio, inversion, categoria, tipo, unidad);
			break;

		case 2:

			System.out.print(Mensajes.unidad);
			unidad = scan.next();
			System.out.print(Mensajes.perecedero);
			perecedero = scan.nextBoolean();

			new Abarrote(codigo, nombre, inventario, precio, inversion, categoria, tipo, perecedero, unidad);
			break;

		case 3:

			System.out.print(Mensajes.material);
			material = scan.next();
			System.out.print(Mensajes.garantia);
			garantia = scan.nextInt();

			new Juguete(codigo, nombre, inventario, precio, inversion, categoria, tipo, material, new Date(), garantia);
			break;

		}

	}

}
