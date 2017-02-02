package ca.dobervich.graph.HW5;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Level g = new Level();

		g.addRoom("hall", "a long dank hallway");
		g.addRoom("closet", "a dark, dark closet");
		g.addRoom("dungeon", "like a children's party");

		g.addOneWayDoor("hall", "dungeon");
		g.addTwoWayDoor("hall", "closet");
		
		Item lobster = new Item("lobster", "a delicous friend");
		Item orb = new Item("orb", "the powerful orb of Emmert");

		g.getRoom("closet").addItem(lobster);
		g.getRoom("closet").addItem(orb);
		
		// Level g = Level.loadLevel("carnival.graph");

		Player player = new Player(g.getRoom("hall"), "Carlos", "the Bold");

		String response = "";
		Scanner s = new Scanner(System.in);

		displayCommands();
		do {
			System.out.println("You are in the " + player.getCurrentRoom().getName());
			System.out.print("What do you want to do? >");
			response = s.nextLine();

			String[] words = response.split(" ");
			
			g.tick(); //tick here or inside specific if-statements?
			
			if (words[0].equals("go")) {
				String name = "";
				if (words.length >= 2) {
					name = words[1];
				}

				boolean successfulMove = player.move(name);
				if (!successfulMove) {
					System.out.println("There is no such room!  Maybe you should type 'look'");
				} else {
					System.out.println("You are entering " + name);
				}
			} else if (words[0].equals("take")) {
				String name = words[1];

				Item item = player.getCurrentRoom().removeItem(name);
				if (item == null) {
					System.out.println("there is no such item!");
				} else {
					player.takeItem(item);
					System.out.println("You pick up the " + item.getName());
				}

			} else if (words[0].equals("drop")) {
				String name = words[1];

				Item item = player.dropItem(name);
				if (item == null) {
					System.out.println("You're not carrying that!");
				} else {
					player.getCurrentRoom().addItem(item);
					System.out.println("You drop the " + item.getName());
				}

			} else if (words[0].equals("save")) {
				String name = words[1];

				g.saveLevel(name + ".graph");

			} else if (words[0].equals("look")) {
				System.out.println(player.getCurrentRoom().getDescription());
				System.out.println("You see exits to: " + player.getCurrentRoom().getNeighborNames());
				System.out.println("This room contains the items: " + player.getCurrentRoom().getItemDisplayList());
			} else if (words[0].equals("add")) {
				if (words.length < 3) {
					System.out.println("I don't know what you mean!");
				} else {
					if (words[1].equals("room")) {
						String name = words[2];
						g.addRoom(name);
						g.addTwoWayDoor(player.getCurrentRoom().getName(), name);
						System.out.println("Added exit to room " + name);
					}
				}
			} else if (words[0].equals("quit")) {
				System.out.println("Goodbye!");
				System.exit(0);
			} else {
				displayCommands();
			}

		} while (!response.equals("quit"));
	}

	private static void displayCommands() {
		System.out.println("Commands you can type are:");
		System.out.println("look: to display exits from your current room");
		System.out.println("go <roomname>: to go to a room");
		System.out.println("add room <roomname>: adds a new room to the game from your current room");
		System.out.println("quit: quits");
	}
}
