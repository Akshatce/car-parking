package com.sample.car.parking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
	static int totalParkingSlot = 0;

	static TreeSet<Integer> tree = new TreeSet<Integer>();
	static Map<Integer, Car> carMap = new HashMap<>();

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = null;
		
		
		File file = new File("input.txt");
		if(file.exists())
			sc = new Scanner(file);
		else
			sc = new Scanner(System.in);

		while (true) {
			String command = sc.next();

			if (command.equalsIgnoreCase("create_parking_lot")) {
				totalParkingSlot = sc.nextInt();
				createParkingLot(totalParkingSlot, tree);

			} else if (command.equalsIgnoreCase("park")) {
				String registrationNumber = sc.next();
				String color = sc.next();
				prepareCarParking(registrationNumber, color, tree, carMap);

			} else if (command.equalsIgnoreCase("leave")) {
				int slotNo = sc.nextInt();
				processLeftCar(tree, carMap, slotNo);
			} else if (command.equalsIgnoreCase("status")) {
				printExistingCars(carMap);
			} else if (command.equalsIgnoreCase("registration_numbers_for_cars_with_colour")) {
				String color = sc.next();
				printRegistrationNumberForGivenColor(color, carMap);
			} else if (command.equalsIgnoreCase("slot_numbers_for_cars_with_colour")) {
				String color = sc.next();
				printSlotNumbersForGivencolor(color, carMap);
			} else if (command.equalsIgnoreCase("slot_number_for_registration_number")) {
				String rNo = sc.next();
				printSlotNumberForGivenRegistrationNumber(rNo, carMap);
			} else if (command.equalsIgnoreCase("exit")) {
				sc.close();
				break;
			}

			else {
				System.out.println("Please enter correct command");
			}
		}
	}

	
//	private File getFileFromResource(String fileName) throws URISyntaxException{
//
//        ClassLoader classLoader = getClass().getClassLoader();
//        URL resource = classLoader.getResource(fileName);
//        if (resource == null) {
//            throw new IllegalArgumentException("file not found! " + fileName);
//        } else {
//            return new File(resource.toURI());
//        }
//
//    }
	public static void prepareCarParking(String registrationNumber, String color, TreeSet<Integer> tree,
			Map<Integer, Car> carMap) {
		if (tree.isEmpty()) {
			System.out.println("Sorry, parking lot is full");
		} else {
			int slotNo = tree.pollFirst();
			carMap.put(slotNo, new Car(registrationNumber, color, slotNo));
			System.out.println("Allocated slot number: " + slotNo);
		}
	}

	public static void createParkingLot(int totalParkingSlot, TreeSet<Integer> tree) {
		for (int i = 1; i <= totalParkingSlot; i++) {
			tree.add(i);
		}
		System.out.println("Created a parking lot with " + totalParkingSlot + " slots");
	}

	public static void printExistingCars(Map<Integer, Car> carMap) {
		System.out.println("Slot No. Registration No. Colour");
		carMap.forEach(
				(k, v) -> System.out.println(v.getSlotNo() + " " + v.getRegistrationNumber() + " " + v.getColor()));
	}

	public static void processLeftCar(TreeSet<Integer> tree, Map<Integer, Car> carMap, int slotNo) {
		carMap.remove(slotNo);
		tree.add(slotNo);
		System.out.println("Slot number " + slotNo + " is free");
	}

	public static void printRegistrationNumberForGivenColor(String color, Map<Integer, Car> carMap) {
		Optional<String> str = carMap.values().stream().filter(c -> c.getColor().equalsIgnoreCase(color))
				.map(c -> c.getRegistrationNumber()).reduce((v1, v2) -> v1 + ", " + v2);

		if (str.isPresent()) {
			System.out.println(str.get());
		} else {
			System.out.println("Not found");
		}
	}

	public static void printSlotNumberForGivenRegistrationNumber(String rNo, Map<Integer, Car> carMap) {
		List<Integer> numbers = carMap.values().stream().filter(c -> c.getRegistrationNumber().equalsIgnoreCase(rNo))
				.map(c -> c.getSlotNo()).collect(Collectors.toList());

		if (!numbers.isEmpty()) {
			System.out.println(numbers);
		} else {
			System.out.println("Not found");
		}
	}

	public static void printSlotNumbersForGivencolor(String color, Map<Integer, Car> carMap) {
		List<Integer> numbers = carMap.values().stream().filter(c -> c.getColor().equalsIgnoreCase(color))
				.map(c -> c.getSlotNo()).collect(Collectors.toList());
		if (!numbers.isEmpty()) {
			System.out.println(numbers);
		} else {
			System.out.println("Not found");
		}
	}

}
