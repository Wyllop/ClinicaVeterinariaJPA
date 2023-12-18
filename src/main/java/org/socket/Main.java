package org.socket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    // Creación del 'EntityManagerFactory' para gestionar la unidad de persistencia 'ClinicaVeterinariaPU'
    private static EntityManagerFactory em = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");
    EntityManager emf = em.createEntityManager();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Creación de 'EntityManager' para interactuar con la base de datos
        EntityManager em = Main.em.createEntityManager();

        try {
            boolean exit = false; // Control de flujo para salir del programa
            while (!exit) {
                printHeader("Menú Principal de Clínica Veterinaria"); // Imprime el encabezado del menú principal
                // Opciones del menú principal
                System.out.println("1. Listado");
                System.out.println("2. Añadir");
                System.out.println("3. Actualizar");
                System.out.println("4. Eliminar");
                System.out.println("5. Consultas JPQL");
                // Aplicar color rojo a la opción de salir
                System.out.println(ANSI_RED + "6. Salir" + ANSI_RESET);
                System.out.print("Seleccione una opción: ");

                int option = scanner.nextInt(); // Lee la opción del usuario
                scanner.nextLine(); // Consume el salto de línea restante

                switch (option) {
                    case 1: // Opción de listado
                        showListMenu(scanner, em);
                        break;
                    case 2: // Opción de añadir
                        showAddMenu(scanner, em);
                        break;
                    case 3: // Opción de actualizar
                        showUpdateMenu(scanner, em);
                        break;
                    case 4: // Opción de eliminar
                        showDeleteMenu(scanner, em);
                        break;
                    case 5: // Opción de Consultas
                        menuConsultasJPQL(scanner, em);
                        break;
                    case 6:
                        exit = true;
                        System.out.println(ANSI_RED + "\nSaliendo del programa..." + ANSI_RESET);
                        break;
                }
            }
        } finally {
            // Cerrar el 'EntityManager' y 'EntityManagerFactory' al finalizar el programa
            em.close();
            Main.em.close();
            scanner.close();
        }
    }

    // Imprime el encabezado del menú con un color específico
    // Constantes ANSI para colores
    private static final String ANSI_PURPLE = "\u001B[35m"; // Código ANSI para el color morado
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m"; // Código ANSI para el color rojo
    private static void printHeader(String title) {


        System.out.println("================================================");
        System.out.println(ANSI_YELLOW + title + ANSI_RESET); // Título con color amarillo
        System.out.println("================================================");
    }

    // Imprime el encabezado del menú
    private static void printHeaderr(String title) {
        System.out.println("================================================");
        System.out.println(ANSI_YELLOW + title + ANSI_RESET);
        System.out.println("================================================");
    }

//Muestra el Submmenu de las listas de mascotas, dueños y visitas
    private static void showListMenu(Scanner scanner, EntityManager em) {
        boolean back = false;
        while (!back) {
            printHeaderr("Submenú de Listado");
            System.out.println("1. Listado de Mascotas");
            System.out.println("2. Listado de Dueños");
            System.out.println("3. Listado de Visitas");
            // Aplicar color morado a la opción de regresar
            System.out.println(ANSI_PURPLE + "4. Regresar al Menú Principal" + ANSI_RESET);
            System.out.print("Seleccione una opción: ");

            int listOption = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (listOption) {
                case 1:
                    listMascotas(em);
                    break;
                case 2:
                    listDuenos(em);
                    break;
                case 3:
                    listVisitas(em);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }


    // Submenú de Añadir
    private static void showAddMenu(Scanner scanner, EntityManager em) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Submenú de Añadir ---");
            System.out.println("1. Añadir Mascota");
            System.out.println("2. Añadir Dueño");
            System.out.println("3. Añadir Visita");
            // Aplicar color morado a la opción de regresar
            System.out.println(ANSI_PURPLE + "4. Regresar al Menú Principal" + ANSI_RESET);
            System.out.print("Seleccione una opción: ");

            int addOption = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (addOption) {
                case 1:
                    addMascota(scanner, em);
                    break;
                case 2:
                    addDueno(scanner, em);
                    break;
                case 3:
                    addVisita(scanner, em);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    // Submenú de Actualizar
    private static void showUpdateMenu(Scanner scanner, EntityManager em) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Submenú de Actualizar ---");
            System.out.println("1. Actualizar Mascota");
            System.out.println("2. Actualizar Dueño");
            System.out.println("3. Actualizar Visita");
            // Aplicar color morado a la opción de regresar
            System.out.println(ANSI_PURPLE + "4. Regresar al Menú Principal" + ANSI_RESET);
            System.out.print("Seleccione una opción: ");

            int updateOption = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (updateOption) {
                case 1:
                    updateMascota(scanner, em);
                    break;
                case 2:
                    updateDueno(scanner, em);
                    break;
                case 3:
                    updateVisita(scanner, em);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    // Submenú de Eliminar
    private static void showDeleteMenu(Scanner scanner, EntityManager em) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Submenú de Eliminar ---");
            System.out.println("1. Eliminar Mascota");
            System.out.println("2. Eliminar Dueño");
            System.out.println("3. Eliminar Visita");
            // Aplicar color morado a la opción de regresar
            System.out.println(ANSI_PURPLE + "4. Regresar al Menú Principal" + ANSI_RESET);
            System.out.print("Seleccione una opción: ");

            int deleteOption = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (deleteOption) {
                case 1:
                    deleteMascota(scanner, em);
                    break;
                case 2:
                    deleteDueno(scanner, em);
                    break;
                case 3:
                    deleteVisita(scanner, em);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }


    //Metodo para añadir Mascotas
    private static void addMascota(Scanner scanner, EntityManager em) {
        // Primero, listamos los dueños existentes
        System.out.println("Dueños registrados:");
        listDuenos(em); // Asume que este método ya imprime la lista de dueños en un formato legible

        System.out.println("\nSeleccione el ID del dueño para la nueva mascota:");
        Long duenoId = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea

        Dueno dueno = em.find(Dueno.class, duenoId);
        if (dueno == null) {
            System.out.println("Dueño no encontrado con el ID: " + duenoId + ". Por favor, introduzca un ID válido.");
            return;
        }

        // Continuamos con el proceso de añadir una nueva mascota
        System.out.print("Introduce el nombre de la mascota: ");
        String nombre = scanner.nextLine();

        System.out.print("Introduce el tipo de mascota (ej. Perro, Gato): ");
        String tipo = scanner.nextLine();

        System.out.print("Introduce la raza de la mascota: ");
        String raza = scanner.nextLine();

        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setTipo(tipo);
        mascota.setRaza(raza);
        mascota.setDueno(dueno); // Asignamos el dueño seleccionado a la mascota

        em.getTransaction().begin();
        try {
            em.persist(mascota);
            em.getTransaction().commit();
            System.out.println("Mascota añadida con éxito bajo el cuidado de " + dueno.getNombre() + ".");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }


    //Metodo para añadir Dueños
    private static void addDueno(Scanner scanner, EntityManager em) {
        System.out.print("Introduce el nombre del dueño: ");
        String nombre = scanner.nextLine();

        System.out.print("Introduce la dirección del dueño: ");
        String direccion = scanner.nextLine();

        System.out.print("Introduce el teléfono del dueño: ");
        String telefono = scanner.nextLine();

        Dueno dueno = new Dueno();
        dueno.setNombre(nombre);
        dueno.setDireccion(direccion);
        dueno.setTelefono(telefono);

        em.getTransaction().begin();
        try {
            em.persist(dueno);
            em.getTransaction().commit();
            System.out.println("Dueño añadido con éxito.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    //Metodo para añadir Visitas
    private static void addVisita(Scanner scanner, EntityManager em) {
        Long mascotaId = null;
        Mascota mascota = null;
        boolean idValido = false;

        // Mostrar el listado de mascotas para que el usuario pueda elegir
        System.out.println("Mascotas registradas:");
        listMascotas(em); // Asegúrate de que este método lista las mascotas con sus IDs

        while (!idValido) {
            try {
                System.out.print("\nSeleccione el ID de la mascota para la visita: ");
                mascotaId = scanner.nextLong();
                scanner.nextLine(); // Consumir la nueva línea

                mascota = em.find(Mascota.class, mascotaId);
                if (mascota == null) {
                    System.out.println("Mascota no encontrada con el ID: " + mascotaId + ". Intente nuevamente.");
                } else {
                    idValido = true; // ID es válido y la mascota existe
                }
            } catch (InputMismatchException ime) {
                System.out.println("Entrada inválida. Por favor, introduzca un número de ID válido.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
        // Continuar con el proceso de añadir una nueva visita
        System.out.print("Introduce la fecha de la visita (yyyy-MM-dd): ");
        String fechaStr = scanner.nextLine();
        Date fecha = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = sdf.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Asegúrese de que esté en formato 'yyyy-MM-dd'");
            return;
        }

        System.out.print("Introduce el motivo de la visita: ");
        String motivo = scanner.nextLine();

        System.out.print("Introduce el diagnóstico de la visita: ");
        String diagnostico = scanner.nextLine();

        Visita visita = new Visita();
        visita.setMascota(mascota);
        visita.setFecha(fecha);
        visita.setMotivoConsulta(motivo);
        visita.setDiagnostico(diagnostico);

        em.getTransaction().begin();
        try {
            em.persist(visita);
            em.getTransaction().commit();
            System.out.println("Visita añadida con éxito para la mascota: " + mascota.getNombre());
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    // Método para actualizar la información de una mascota existente
    private static void updateMascota(Scanner scanner, EntityManager em) {
        // Muestra el listado de mascotas para que el usuario pueda elegir cuál actualizar
        System.out.println("Mascotas registradas:");
        listMascotas(em); // Asume que este método imprime la lista de mascotas con sus IDs

        System.out.print("\nSeleccione el ID de la mascota que desea actualizar: ");
        Long mascotaId = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea

        Mascota mascota = em.find(Mascota.class, mascotaId);
        if (mascota == null) {
            System.out.println("Mascota no encontrada con el ID: " + mascotaId + ". Intente nuevamente.");
            return;
        }

        // Solicita al usuario la nueva información de la mascota
        System.out.print("Introduce el nuevo nombre de la mascota: ");
        String nuevoNombre = scanner.nextLine();
        mascota.setNombre(nuevoNombre);

        System.out.print("Introduce el nuevo tipo de la mascota (ej. Perro, Gato): ");
        String nuevoTipo = scanner.nextLine();
        mascota.setTipo(nuevoTipo);

        System.out.print("Introduce la nueva raza de la mascota: ");
        String nuevaRaza = scanner.nextLine();
        mascota.setRaza(nuevaRaza);

        // Inicia una transacción para persistir los cambios en la base de datos
        em.getTransaction().begin();
        try {
            em.merge(mascota); // Actualiza la información de la mascota
            em.getTransaction().commit();
            System.out.println("Mascota actualizada correctamente.");
        } catch (Exception e) {
            em.getTransaction().rollback(); // En caso de error, deshace los cambios
            e.printStackTrace();
        }
    }


    // Método para actualizar la información de un dueño existente
    private static void updateDueno(Scanner scanner, EntityManager em) {
        // Muestra el listado de dueños para que el usuario pueda elegir cuál actualizar
        System.out.println("Dueños registrados:");
        listDuenos(em); // Asume que este método imprime la lista de dueños con sus IDs

        System.out.print("\nSeleccione el ID del dueño que desea actualizar: ");
        Long duenoId = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea

        Dueno dueno = em.find(Dueno.class, duenoId);
        if (dueno == null) {
            System.out.println("Dueño no encontrado con el ID: " + duenoId + ". Intente nuevamente.");
            return;
        }

        // Solicita al usuario la nueva información del dueño
        System.out.print("Introduce el nuevo nombre del dueño: ");
        String nuevoNombre = scanner.nextLine();
        dueno.setNombre(nuevoNombre);

        System.out.print("Introduce la nueva dirección del dueño: ");
        String nuevaDireccion = scanner.nextLine();
        dueno.setDireccion(nuevaDireccion);

        System.out.print("Introduce el nuevo teléfono del dueño: ");
        String nuevoTelefono = scanner.nextLine();
        dueno.setTelefono(nuevoTelefono);

        // Inicia una transacción para persistir los cambios en la base de datos
        em.getTransaction().begin();
        try {
            em.merge(dueno); // Actualiza la información del dueño
            em.getTransaction().commit();
            System.out.println("Dueño actualizado correctamente.");
        } catch (Exception e) {
            em.getTransaction().rollback(); // En caso de error, deshace los cambios
            e.printStackTrace();
        }
    }

    // Método para actualizar una visita existente
    private static void updateVisita(Scanner scanner, EntityManager em) {
        // Muestra el listado de visitas para que el usuario pueda elegir cuál actualizar
        System.out.println("Visitas registradas:");
        listVisitas(em); // Asume que este método imprime la lista de visitas con sus IDs

        System.out.print("\nSeleccione el ID de la visita que desea actualizar: ");
        Long visitaId = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea

        Visita visita = em.find(Visita.class, visitaId);
        if (visita == null) {
            System.out.println("Visita no encontrada con el ID: " + visitaId + ". Intente nuevamente.");
            return;
        }

        // Solicita al usuario la nueva información de la visita
        System.out.print("Introduce la nueva fecha de la visita (yyyy-MM-dd): ");
        String nuevaFechaStr = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date nuevaFecha = sdf.parse(nuevaFechaStr);
            visita.setFecha(nuevaFecha);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Asegúrese de que esté en formato 'yyyy-MM-dd'");
            return;
        }

        System.out.print("Introduce el nuevo motivo de la visita: ");
        String nuevoMotivo = scanner.nextLine();
        visita.setMotivoConsulta(nuevoMotivo);

        System.out.print("Introduce el nuevo diagnóstico de la visita: ");
        String nuevoDiagnostico = scanner.nextLine();
        visita.setDiagnostico(nuevoDiagnostico);

        // Inicia una transacción para persistir los cambios en la base de datos
        em.getTransaction().begin();
        try {
            em.merge(visita); // Actualiza la información de la visita
            em.getTransaction().commit();
            System.out.println("Visita actualizada correctamente.");
        } catch (Exception e) {
            em.getTransaction().rollback(); // En caso de error, deshace los cambios
            e.printStackTrace();
        }
    }

    // Método para eliminar una Mascota
    private static void deleteMascota(Scanner scanner, EntityManager em) {
        // Mostrar listado de mascotas
        System.out.println("Mascotas registradas:");
        listMascotas(em);

        System.out.print("\nSeleccione el ID de la mascota que desea eliminar: ");
        Long mascotaId = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea

        Mascota mascota = em.find(Mascota.class, mascotaId);
        if (mascota == null) {
            System.out.println("Mascota no encontrada con el ID: " + mascotaId + ". Intente nuevamente.");
            return;
        }

        // Proceder a eliminar la mascota
        em.getTransaction().begin();
        try {
            em.remove(mascota);
            em.getTransaction().commit();
            System.out.println("Mascota eliminada correctamente.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    // Método para eliminar un Dueño
    private static void deleteDueno(Scanner scanner, EntityManager em) {
        // Mostrar listado de dueños
        System.out.println("Dueños registrados:");
        listDuenos(em);

        System.out.print("\nSeleccione el ID del dueño que desea eliminar: ");
        Long duenoId = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea

        Dueno dueno = em.find(Dueno.class, duenoId);
        if (dueno == null) {
            System.out.println("Dueño no encontrado con el ID: " + duenoId + ". Intente nuevamente.");
            return;
        }

        // Proceder a eliminar el dueño
        em.getTransaction().begin();
        try {
            em.remove(dueno);
            em.getTransaction().commit();
            System.out.println("Dueño eliminado correctamente.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    // Método para eliminar una Mascota
    private static void deleteVisita(Scanner scanner, EntityManager em) {
        // Mostrar listado de visitas
        System.out.println("Visitas registradas:");
        listVisitas(em);

        System.out.print("\nSeleccione el ID de la visita que desea eliminar: ");
        Long visitaId = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea

        Visita visita = em.find(Visita.class, visitaId);
        if (visita == null) {
            System.out.println("Visita no encontrada con el ID: " + visitaId + ". Intente nuevamente.");
            return;
        }

        // Proceder a eliminar la visita
        em.getTransaction().begin();
        try {
            em.remove(visita);
            em.getTransaction().commit();
            System.out.println("Visita eliminada correctamente.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    // Método para listar todas las Mascotas
    private static void listMascotas(EntityManager em) {
        List<Mascota> mascotas = em.createQuery(
                "SELECT m FROM Mascota m JOIN FETCH m.dueno", Mascota.class
        ).getResultList();

        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
            return;
        }

        // Encabezados de la tabla
        System.out.printf("%-5s %-20s %-10s %-15s %-20s%n", "ID", "Nombre", "Tipo", "Raza", "Dueño");
        System.out.println("--------------------------------------------------------------------------------");

        // Datos de la tabla
        for (Mascota m : mascotas) {
            String duenoNombre = m.getDueno() != null ? m.getDueno().getNombre() : "Sin dueño";
            System.out.printf("%-5d %-20s %-10s %-15s %-20s%n", m.getId(), m.getNombre(), m.getTipo(), m.getRaza(), duenoNombre);
        }
    }


    //Método para listar todos los dueños
    private static void listDuenos(EntityManager em) {
        List<Dueno> duenos = em.createQuery("SELECT d FROM Dueno d", Dueno.class).getResultList();
        if (duenos.isEmpty()) {
            System.out.println("No hay dueños registrados.");
            return;
        }

        // Encabezados de la tabla
        System.out.printf("%-5s %-20s %-15s %-20s%n", "ID", "Nombre", "Dirección", "Teléfono");
        System.out.println("------------------------------------------------------------------");

        // Datos de la tabla
        for (Dueno d : duenos) {
            System.out.printf("%-5d %-20s %-15s %-20s%n", d.getId(), d.getNombre(), d.getDireccion(), d.getTelefono());
        }
    }
    //Método para listar todas las visitias
    private static void listVisitas(EntityManager em) {
        // Incluimos el JOIN FETCH para obtener los datos de la mascota asociada a cada visita
        List<Visita> visitas = em.createQuery(
                "SELECT v FROM Visita v JOIN FETCH v.mascota", Visita.class
        ).getResultList();

        if (visitas.isEmpty()) {
            System.out.println("No hay visitas registradas.");
            return;
        }

        System.out.printf("%-5s %-15s %-20s %-25s %-20s%n", "ID", "Fecha", "Motivo", "Diagnóstico", "Mascota");
        System.out.println(String.join("", Collections.nCopies(85, "-")));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Visita v : visitas) {
            String fechaStr = (v.getFecha() != null) ? sdf.format(v.getFecha()) : "N/A";
            String nombreMascota = v.getMascota() != null ? v.getMascota().getNombre() : "Sin mascota";
            System.out.printf("%-5d %-15s %-20s %-25s %-20s%n",
                    v.getId(), fechaStr, v.getMotivoConsulta(), v.getDiagnostico(), nombreMascota);
        }
    }

    // Menu de consulpas JPQL
    private static void menuConsultasJPQL(Scanner scanner, EntityManager em ) {
        System.out.println("\nMenu de Consultas:");
        System.out.println("1. Buscar mascotas por tipo");
        System.out.println("2. Buscar dueños con más de 2 mascotas");
        System.out.println("3. Buscar visitas por nombre de mascota");
        System.out.println("4. Buscar visitas por motivo de 'Vacunación'");
        System.out.println("5. Salir");
        System.out.print("Elige una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
                buscarMascotasPorTipo(scanner, (EntityManager) em);
                break;
            case 2:
                buscarDuenosConMuchasMascotas((EntityManager) em);
                break;
            case 3:
                buscarVisitasPorNombreMascota(scanner, (EntityManager) em);
                break;
            case 4:
                buscarVisitasPorMotivoVacunacion((EntityManager) em);
                break;
            case 5:
                // Salir del menú de consultas JPQL
                break;
            default:
                System.out.println("Opción no válida. Por favor, intenta de nuevo.");
        }

    }

    // Método para buscar mascotas por tipo. El usuario escribirá un tipo (string) y se mostrarán todas las mascotas
// de ese tipo
    private static void buscarMascotasPorTipo(Scanner scanner, EntityManager em) {
        System.out.print("Ingresa el tipo de mascota (ej. 'Perro'): ");
        String tipo = scanner.nextLine();
        List<Mascota> mascotas = em.createQuery("SELECT m FROM Mascota m WHERE m.tipo = :tipo", Mascota.class)
                .setParameter("tipo", tipo)
                .getResultList();
        mascotas.forEach(m -> System.out.println("Mascota: " + m.getNombre() + ", Tipo: " + m.getTipo()));
    }

    // Método para buscar dueños que tengan más de 2 mascotas.
    private static void buscarDuenosConMuchasMascotas(EntityManager em) {
        List<Dueno> duenos = em.createQuery("SELECT d FROM Dueno d WHERE SIZE(d.mascotas) > 2", Dueno.class)
                .getResultList();
        if (duenos.isEmpty()) {
            System.out.println("No se encontraron dueños con más de 2 mascotas.");
        } else {
            duenos.forEach(d -> System.out.println("Dueño: " + d.getNombre() + ", Número de Mascotas: " + d.getMascotas().size()));
        }
    }

    // Método para mostrar todas las visitas de tipo vacunación.
    private static void buscarVisitasPorMotivoVacunacion(EntityManager em) {
        String motivo = "Vacunación";
        List<Visita> visitas = em.createQuery("SELECT v FROM Visita v WHERE v.motivoConsulta = :motivo", Visita.class)
                .setParameter("motivo", motivo)
                .getResultList();
        if (visitas.isEmpty()) {
            System.out.println("No se encontraron visitas con el motivo de 'Vacunación'.");
        } else {
            visitas.forEach(v -> System.out.println("Fecha: " + v.getFecha() + ", Mascota: " + v.getMascota().getNombre()));
        }
    }

    // Método para buscar visitas por nombre de mascota
    private static void buscarVisitasPorNombreMascota(Scanner scanner, EntityManager em) {
        System.out.print("Ingresa el nombre de la mascota: ");
        String nombreMascota = scanner.nextLine();
        List<Visita> visitas = em.createQuery("SELECT v FROM Visita v WHERE v.mascota.nombre = :nombreMascota", Visita.class)
                .setParameter("nombreMascota", nombreMascota)
                .getResultList();
        visitas.forEach(v -> System.out.println("Fecha: " + v.getFecha() + ", Motivo: " + v.getMotivoConsulta()));
    }


}
