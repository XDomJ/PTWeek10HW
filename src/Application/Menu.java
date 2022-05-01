package Application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.GuitarDao;
import entity.Guitar;

public class Menu {
	
	private GuitarDao guitarDao = new GuitarDao(); 
	private Scanner kb = new Scanner(System.in);
	private List<String> menuList = Arrays.asList("Display Guitars", 
			"Inspect your instrument",
			"Add an Ax",
			"Edit Guitars",
			"Smash and Delete Guitar");
	
	
	public void start() { //start method for application
		String selection = "";
		
		do {
			printMenu();
			selection = kb.nextLine();
			
			try {
				if (selection.equals("1")) {
					showGuitars();
				} else if (selection.equals("2")) {
					examineGuitar(); 
				} else if (selection.equals("3")) {
					createGuitar();
				} else if (selection.equals("4")) {
					editGuitar();
				} else if (selection.equals("5")) {
					deleteGuitar();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Press enter to continue.....");
			kb.nextLine();
		} while(!selection.equals("-1")); 
	}

	private void printMenu() { //show's the menu
		System.out.println("Choose an option:\n*******************************");
		for (int j = 0; j < menuList.size(); j++) {
			System.out.println((j + 1) + ") " + menuList.get(j));
		}
	}
	
	private void showGuitars() throws SQLException { //shows the list of guitars
		List<Guitar> guitars = guitarDao.showGuitars();
		for (Guitar guitar : guitars) {
			System.out.println(guitar.getGuitarId() + ") " + guitar.getMakeModel());
		}
	}
	
	private void examineGuitar() throws SQLException { //shows a specific guitar
		System.out.print("Enter Guitar ID: ");
		int id = Integer.parseInt(kb.nextLine());
		Guitar guitar = guitarDao.getByID(id);
		System.out.println(guitar.getGuitarId() + ": " + guitar.getMakeModel() + " " +
				guitar.getStyle() + " " + guitar.getNickname());
	}
	
	private void createGuitar() throws SQLException { //creates new entry in guitars
		System.out.print("Enter new Guitar Make and Model: ");
		String makeModel = kb.nextLine();
		System.out.print("Enter new Guitar style: ");
		String style = kb.nextLine();
		System.out.print("Enter the Guitar's nickname: ");
		String nickname = kb.nextLine();
		guitarDao.createGuitar(makeModel, style, nickname);
	}
	
	private void editGuitar() throws SQLException { //will change any of the three columns in the database
		System.out.print("Enter Guitar ID: ");
		int guitar_id = Integer.parseInt(kb.nextLine());
		System.out.println("Which information needs to change?\n"
				+ "1) make/model\n 2) style\n 3) nickname");
		int choice = Integer.parseInt(kb.nextLine());
		System.out.println("Enter new info");
		String newInfo = kb.nextLine();
		guitarDao.editGuitar(choice, guitar_id, newInfo);
	}
	
	private void deleteGuitar() throws SQLException {  //Deletes. (I wanted to add a confirmation message
		System.out.println("Which guitar do you want to Smash?"); // but couldn't make it work in time)
		int decision = Integer.parseInt(kb.nextLine());
		guitarDao.deleteGuitar(decision);
		System.out.println("**SMASH!!!** It's gone.");
	}

}
