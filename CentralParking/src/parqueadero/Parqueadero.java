package parqueadero;

import java.util.*;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Parqueadero {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long tiempo = System.nanoTime();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Puesto> puestos = new ArrayList<>();
        boolean iterator = true;

        for (int i = 0; i < 10000000; i++) {
            String is = Integer.toString(i);
            String A = "A";
            String ref = A.concat(is);
            Puesto puesto = new Puesto(ref);
            puestos.add(puesto);
        }
        tiempo = System.nanoTime() - tiempo;
        while (iterator) {
            System.out.println("El tiempo de ejecuccion es: " + tiempo + " ns");

            System.out.println("Central Parking "
                    + "\n Qué desea realizar?"
                    + "\n1--> Ingresar Automóvil"
                    + "\n2--> Retirar Automóvil"
                    + "\n3--> Reservar"
                    + "\n4--> salir");
            String seleccion = scan.nextLine();

            while (!seleccion.equals("1") && !seleccion.equals("2") && !seleccion.equals("3") && !seleccion.equals("4")) {
                System.out.println("Selección Inválida. Ingrese de nuevo");
                seleccion = scan.nextLine();
            }
            if (seleccion.equals("1")) {
                System.out.println("Ingrese su placa:");
                String placa = scan.nextLine().toLowerCase();
                while (placa.charAt(0) < 97
                        || placa.charAt(0) > 122
                        || placa.charAt(1) < 97
                        || placa.charAt(1) > 122
                        || placa.charAt(2) < 97
                        || placa.charAt(2) > 122
                        || placa.charAt(3) < 48
                        || placa.charAt(3) > 57
                        || placa.charAt(4) < 48
                        || placa.charAt(4) > 57
                        || placa.charAt(5) < 48
                        || placa.charAt(5) > 57) {
                    System.out.println("Placa no válida. Ingrese de nuevo su placa");
                    placa = scan.nextLine().toLowerCase();
                }
                boolean registrado = false;
                for (Puesto p : puestos) {
                    if (p.getCarro() != null && p.getCarro().getPlaca().equals(placa)) {
                        registrado = true;
                        System.out.println("Bienvenido! " + p.getCarro().getPropietario());
                    }
                }
                if (!registrado) {
                    System.out.println("Ingrese su nombre");
                    String nombre = scan.nextLine();
                    System.out.println("Digite 1 si es un carro o 2 si es una moto");
                    String tipo = scan.nextLine();
                    while (!tipo.equals("1") && !tipo.equals("2")) {
                        System.out.println("Selección Inválida, ingrese nuevamente");
                        tipo = scan.nextLine();
                    }
                    if (tipo.equals("1")) {
                        tipo = "carro";
                    }
                    if (tipo.equals("2")) {
                        tipo = "moto";
                    }
                    LocalDateTime hora = LocalDateTime.now();
                    DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String horaEntrada = hora.format(horaFormat);
                    Carro carro = new Carro(placa, nombre, tipo, hora);
                    Puesto p = null;
                    for (int i = 0; i < puestos.size(); i++) {
                        if (puestos.get(i).getCarro() == null) {
                            puestos.get(i).setCarro(carro);
                            puestos.get(i).setOcupado(true);
                            p = puestos.get(i);
                            break;
                        }
                    }
                    System.out.println("Bienvenido, " + nombre + "\nHora de Entrada: "
                            + horaEntrada + "\nPuesto: " + p.getReferencia());
                }
            }
            if (seleccion.equals("2")) {
                System.out.println("Ingrese la placa del carro que desea retirar");
                String placa = scan.nextLine();
                boolean containsCarro = false;
                int posicion = 0;
                for (Puesto p : puestos) {
                    if (p.getCarro() != null && p.getCarro().getPlaca().equals(placa)) {
                        containsCarro = true;
                        break;
                    }
                    posicion++;
                }
                if (containsCarro) {
                    boolean usuario = false;
                    for (Usuario u : usuarios) {
                        if (u.getCarro().getPlaca().equals(placa)) {
                            System.out.println("Hasta Luego, " + u.getCarro().getPropietario() + "!");
                            usuario = true;
                            break;
                        }
                    }
                    if (!usuario) {
                        puestos.get(posicion).setCarro(null);
                        puestos.get(posicion).setOcupado(false);
                    }
                } else {
                    System.out.println("El carro con este número de placa no se encuentra");
                }
            }
            if (seleccion.equals("3")) {
                System.out.println("Por cuánto tiempo desea reservar un parqueadero?");
                System.out.println("1--> Una semana");
                System.out.println("2--> Un mes");
                System.out.println("3--> 1 año");
                String estacia = scan.nextLine();
                while (!estacia.equals("1") && !estacia.equals("2") && !estacia.equals("3")) {
                    System.out.println("Selección invalida, ingrese de nuevo");
                    estacia = scan.nextLine();
                }
                LocalDate inicio = LocalDate.now();
                LocalDate expiracion = null;
                if (estacia.equals("1")) {
                    expiracion = inicio.plus(7, ChronoUnit.DAYS);
                }
                if (estacia.equals("2")) {
                    expiracion = inicio.plus(1, ChronoUnit.MONTHS);
                }
                if (estacia.equals("3")) {
                    expiracion = inicio.plus(1, ChronoUnit.YEARS);
                }
                System.out.println("Ingrese su nombre");
                String nombre = scan.nextLine();
                System.out.println("Ingrese su placa");
                String placa = scan.nextLine().toLowerCase();
                while (placa.charAt(0) < 97
                        || placa.charAt(0) > 122
                        || placa.charAt(1) < 97
                        || placa.charAt(1) > 122
                        || placa.charAt(2) < 97
                        || placa.charAt(2) > 122
                        || placa.charAt(3) < 48
                        || placa.charAt(3) > 57
                        || placa.charAt(4) < 48
                        || placa.charAt(4) > 57
                        || placa.charAt(5) < 48
                        || placa.charAt(5) > 57) {
                    System.out.println("Placa no válida. Ingrese de nuevo su placa");
                    placa = scan.nextLine().toLowerCase();
                }
                System.out.println("Ingrese 1 si es un carro o 2 si es moto");
                String tipo = scan.nextLine();
                while (!tipo.equals("1") && !tipo.equals("2")) {
                    System.out.println("Selección inválida, ingrese nuevamente");
                    tipo = scan.nextLine();
                }
                if (tipo.equals("1")) {
                    tipo = "Carro";
                }
                if (tipo.equals("2")) {
                    tipo = "Moto";
                }
                Carro carro = new Carro(placa, nombre, tipo);
                int n = 0;
                for (int i = 0; i < puestos.size(); i++) {
                    if (puestos.get(i).getCarro() == null) {
                        puestos.get(i).setCarro(carro);
                        puestos.get(i).setOcupado(true);
                        puestos.get(i).setReservado(true);
                        n = i;
                        break;
                    }
                }
                Usuario usuario = new Usuario(inicio, carro, puestos.get(n), expiracion);
                usuarios.add(usuario);
                System.out.println("Reserva exitosa!");
            }
            if (seleccion.equals("4")) {

                System.out.println("Central Parking "
                        + "\n cuantos datos desea probar?"
                        + "\n1--> 10.000"
                        + "\n2--> 100.000"
                        + "\n3--> 1.000.000"
                        + "\n4--> 10.000.000"
                        + "\n5--> 100.000.000");
                String opcion = scan.nextLine();
                while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4") && !opcion.equals("5")) {
                    System.out.println("OPcion Inválida. Ingrese de nuevo");
                    opcion = scan.nextLine();
                }

                if (opcion.equals("1")) {
                    tiempo = System.nanoTime();
                    for (int i = 0; i < 10000; i++) {
                        Carro carro = new Carro("123456789", "12345689", "123456789");
                        puestos.get(i).setCarro(carro);
                    }
                    tiempo = System.nanoTime() - tiempo;
                    System.out.println("El tiempo de ejecuccion es: " + tiempo + " ns");
                }
                if (opcion.equals("2")) {
                    tiempo = System.nanoTime();
                    for (int i = 0; i < 100000; i++) {
                        Carro carro = new Carro("123456789", "12345689", "123456789");
                        puestos.get(i).setCarro(carro);
                    }
                    tiempo = System.nanoTime() - tiempo;
                    System.out.println("El tiempo de ejecuccion es: " + tiempo + " ns");
                }
                if (opcion.equals("3")) {
                    tiempo = System.nanoTime();
                    for (int i = 0; i < 1000000; i++) {
                        Carro carro = new Carro("123456789", "12345689", "123456789");
                        puestos.get(i).setCarro(carro);
                    }
                    tiempo = System.nanoTime() - tiempo;
                    System.out.println("El tiempo de ejecuccion es: " + tiempo + " ns");
                }
                if (opcion.equals("4")) {
                    tiempo = System.nanoTime();
                    for (int i = 0; i < 10000000; i++) {
                        Carro carro = new Carro("123456789", "12345689", "123456789");
                        puestos.get(i).setCarro(carro);
                    }
                    tiempo = System.nanoTime() - tiempo;
                    System.out.println("El tiempo de ejecuccion es: " + tiempo + " ns");
                }
                if (opcion.equals("5")) {
                    tiempo = System.nanoTime();
                    for (int i = 0; i < 100000000; i++) {
                        Carro carro = new Carro("123456789", "12345689", "123456789");
                        puestos.get(i).setCarro(carro);
                    }
                    tiempo = System.nanoTime() - tiempo;
                    System.out.println("El tiempo de ejecuccion es: " + tiempo + " ns");
                }
                iterator = false;
            }
        }
    }
}
