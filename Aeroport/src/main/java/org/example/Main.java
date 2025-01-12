package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.modules.Aeroport;
import org.example.modules.Zbor;
import org.example.modules.ZborInternational;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main TUI application
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Aeroport> aeroporturi = new ArrayList<>();
    private static final String FILE_PATH = "C:\\Users\\Miruna\\Desktop\\ANUL 2\\MIP\\PROIECTaeroport\\Aeroport\\src\\zboruri.json";

    public static void main(String[] args) {
        // Initialize with some airports and flights
        initializeData();

        while (true) {
            showMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    viewAllAirports();
                    break;
                case 2:
                    addFlightToAirport();
                    break;
                case 3:
                    addNewAirport();
                    break;
                case 4:
                    saveDataToFile();
                    break;
                case 5:
                    saveDataToFile();
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Aeroport> loadedAirports = objectMapper.readValue(
                    new File(FILE_PATH),
                    new TypeReference<>() {
                    }
            );

            if (loadedAirports != null && !loadedAirports.isEmpty()) {
                aeroporturi.addAll(loadedAirports);
                System.out.println(loadedAirports.size() + " airports loaded from file.");
            } else {
                System.out.println("No airports found in file.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while loading airports: " + e.getMessage());
        }
    }

    private static void showMenu() {
        System.out.println("\n=== AIRPORT MANAGEMENT SYSTEM ===");
        System.out.println("1. View all airports and their flights");
        System.out.println("2. Add a flight to an airport");
        System.out.println("3. Add a new airport");
        System.out.println("4. Save everything to file");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Invalid choice
        }
    }

    private static void viewAllAirports() {
        if (aeroporturi.isEmpty()) {
            System.out.println("No airports available.");
            return;
        }

        System.out.println("\n=== List of Airports ===");
        for (int i = 0; i < aeroporturi.size(); i++) {
            Aeroport aeroport = aeroporturi.get(i);
            System.out.println((i + 1) + ". " + aeroport.GetNume());
            aeroport.AfiseazaToateZborurile();
        }
    }

    private static void addFlightToAirport() {
        if (aeroporturi.isEmpty()) {
            System.out.println("No airports available. Please add an airport first.");
            return;
        }

        System.out.println("\n=== Add Flight to Airport ===");
        for (int i = 0; i < aeroporturi.size(); i++) {
            System.out.println((i + 1) + ". " + aeroporturi.get(i).GetNume());
        }
        System.out.print("Select an airport by number: ");
        int airportIndex = getChoice() - 1;

        if (airportIndex < 0 || airportIndex >= aeroporturi.size()) {
            System.out.println("Invalid airport selection.");
            return;
        }

        System.out.print("Enter Flight Type (1 for Domestic, 2 for International): ");
        int type = getChoice();

        System.out.print("Enter Destination: ");
        String destinatie = scanner.nextLine();

        System.out.print("Enter Flight Code: ");
        String codZbor = scanner.nextLine();

        System.out.print("Enter Capacity: ");
        int capacitate = getChoice();

        if (type == 1) {
            Zbor zbor = new Zbor(destinatie, codZbor, capacitate);
            aeroporturi.get(airportIndex).AdaugaZbor(zbor);
        } else if (type == 2) {
            System.out.print("Enter Destination Country: ");
            String taraDestinatie = scanner.nextLine();
            ZborInternational zbor = new ZborInternational(destinatie, taraDestinatie, codZbor, capacitate);
            aeroporturi.get(airportIndex).AdaugaZbor(zbor);
        } else {
            System.out.println("Invalid flight type.");
            return;
        }

        System.out.println("Flight added successfully!");
    }

    private static void addNewAirport() {
        System.out.println("\n=== Add New Airport ===");
        System.out.print("Enter Airport Name: ");
        String name = scanner.nextLine();

        Aeroport aeroport = new Aeroport(name);
        aeroporturi.add(aeroport);

        System.out.println("Airport \"" + name + "\" added successfully!");
    }

    private static void saveDataToFile() {
        System.out.println("\n=== Saving Data to File ===");
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Serialize the list of airports into the JSON file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), aeroporturi);

            System.out.println("Data successfully saved to file: " + FILE_PATH);
        } catch (Exception e) {
            System.out.println("An error occurred while saving data: " + e.getMessage());
        }
    }
}