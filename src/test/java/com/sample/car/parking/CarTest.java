package com.sample.car.parking;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Test;

public class CarTest {

	@Test
	public void testCreateParkingLot() {
		TreeSet<Integer> ts = new TreeSet<>();
		Main.createParkingLot(5, ts);
		assertEquals(5, ts.size());
		Map<Integer, Car> carMap = new HashMap<>();

		
		Main.prepareCarParking("park GJ-01-HH-1234", "White", ts, carMap);
		Main.prepareCarParking("park GJ-01-HH-9999", "White", ts, carMap);
		Main.prepareCarParking("park GJ-01-BB-0001", "Black", ts, carMap);
		Main.prepareCarParking("park GJ-01-HH-7777", "Red", ts, carMap);
		Main.prepareCarParking("park GJ-01-HH-2701", "Blue", ts, carMap);
		Main.prepareCarParking("park GJ-01-HH-3141", "Black", ts, carMap);
		
		assertEquals(6, carMap.size());
		
		Main.processLeftCar(ts, carMap, 4);
		assertEquals(5, carMap.size());
		
		Main.prepareCarParking("park GJ-01-P-333", "White", ts, carMap);
		Main.prepareCarParking("park GJ-01-P-333", "White", ts, carMap);
		assertEquals(carMap.size(),  5);
		
		Main.printRegistrationNumberForGivenColor("Whilte", carMap);
	}
	

}
