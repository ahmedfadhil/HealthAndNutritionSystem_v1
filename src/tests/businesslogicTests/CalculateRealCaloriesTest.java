package tests.businesslogicTests;

import hns.businesslogic.CalculateRealCalories;
import hns.objects.*;
import junit.framework.TestCase;

public class CalculateRealCaloriesTest extends TestCase {
	public void testCalculateRealCaloriesInvalidField() throws Exception{
		System.out.println("\nStarting testCalculateRealCaloriesInvalidField: null values");
		try{
			CalculateRealCalories.calculateDailyNetCalories(null);
			CalculateRealCalories.calculateAverageNetCalories(null);
			CalculateRealCalories.calculateAverageTotalIntakenCalories(null);
			CalculateRealCalories.calculateAverageTotalBurnedCalories(null);
			CalculateRealCalories.calculateDailyTotalIntakenCalories(null);
			CalculateRealCalories.calculateDailyTotalBurnedCalories(null);		

		}catch(NullPointerException npe){}
		
		System.out.println("\nFinished testCalculateRealCaloiresInvalidField: null values");
	}
	
	public void testCalculateRealCaloriesValidField() throws Exception{
		System.out.println("\nStarting testCalculateRealCaloriesValidField: valid values");
		try{

			Record monday = new Record(new Date(3,1,2010),50);
			Record tuesday = new Record(new Date(3,2,2010),60);
			Record wednesday = new Record(new Date(3,3,2010),60);
			
			Journal j = new Journal(1);
			
			Food abalone = new Food("ABALONE,MIXED SPECIES,RAW",105,20);
			Food beer  = new Food("ALCOHOLIC BEV,BEER,LT",29,10); 
			Food yogurt = new Food ("YOGURT,FRUIT VAR,NON-FAT",95,50);
			Food wheat = new Food ("WHEAT,HARD WHITE",342,200);
			Food pizza = new Food ("PIZZA HUT 14 CHS PIZZA,THIN N CRISPY CRUST",306,400);
			Food peanutButter = new Food ("PEANUT BUTTER,RED NA",590,8);
			Food orange = new Food ("ORANGE PEEL,RAW",97,500);

			Activity basketball = new Activity("BASKETBALL GAME, COMPETITIVE",472,30);
			Activity bowling = new Activity("BOWLING",177,120);
			Activity volleyball = new Activity ("VOLLEYBALL, BEACH",472,40);
			Activity swimming = new Activity("SWIMMING SIDESTROKE",472,45);
			Activity walking = new Activity("WALKING DOWNSTAIRS",177,10);
			
			monday.AddToList(abalone);
			monday.AddToList(beer);
			monday.AddToList(basketball);
			monday.AddToList(bowling);
			
			tuesday.AddToList(yogurt);
			tuesday.AddToList(wheat);
			tuesday.AddToList(pizza);
			tuesday.AddToList(peanutButter);
			tuesday.AddToList(volleyball);
			tuesday.AddToList(swimming);
			
			j.insertNewRecord(monday);
			j.insertNewRecord(tuesday);	
			
			assertEquals(CalculateRealCalories.calculateDailyNetCalories(monday), -566);
			assertEquals(CalculateRealCalories.calculateDailyNetCalories(tuesday), 1334);
			assertEquals(CalculateRealCalories.calculateAverageTotalIntakenCalories(j), 1013);
			assertEquals(CalculateRealCalories.calculateAverageTotalBurnedCalories(j), 629);
			assertEquals(CalculateRealCalories.calculateAverageNetCalories(j), 384);
			
			wednesday.AddToList(orange);
			wednesday.AddToList(walking);
			j.insertNewRecord(wednesday);
			
			assertFalse(CalculateRealCalories.calculateAverageTotalIntakenCalories(j) == 1013);
			assertFalse(CalculateRealCalories.calculateAverageTotalBurnedCalories(j) == 629);
			assertFalse(CalculateRealCalories.calculateAverageNetCalories(j) == 384);
			assertTrue(CalculateRealCalories.calculateAverageTotalIntakenCalories(j) == 837);
			assertTrue(CalculateRealCalories.calculateAverageTotalBurnedCalories(j) == 429);
			assertTrue(CalculateRealCalories.calculateAverageNetCalories(j) == 408);
		

		}catch(NullPointerException npe){}
		
		System.out.println("\nFinished testCalculateRealCaloiresValidField: valid values");
	}
	
	
}
