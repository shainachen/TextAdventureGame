package ca.dobervich.graph.HW5;

import java.util.Scanner;

public class Main {
	private static Player player;
	private static Level level;
	
	public static void main(String[] args) {
		level = new Level();

		level.addRoom("hall", "a long dank hallway");
		level.addRoom("closet", "a dark, dark closet");
		level.addRoom("dungeon", "like a children's party");

		level.addOneWayDoor("hall", "dungeon");
		level.addTwoWayDoor("hall", "closet");
		
		Item lobster = new Item("lobster", "a delicous friend");
		Item orb = new Item("orb", "the powerful orb of Emmert");

		level.getRoom("closet").addItem(lobster);
		level.getRoom("closet").addItem(orb);
		MovingEntity pot = new Potato("potato", level.getRoom("hall"), "tasty", 10);

		player = new Player(level.getRoom("hall"), "Carlos", "the Bold");

		String response = "";
		Scanner s = new Scanner(System.in);

		displayCommands();
		do {
			System.out.println("\nYou are in the " + player.getCurrentRoom().getName());
			System.out.print("What do you want to do? >");
			response = s.nextLine();

			String[] words = response.split(" ");
			
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
					System.out.println("You dropped the " + item.getName());
				}

			} else if (words[0].equals("save")) {
				String name = words[1];

				level.saveLevel(name + ".graph");

			} else if (words[0].equals("look")) {
				System.out.println("You see exits to: " + player.getCurrentRoom().getNeighborNames());
				System.out.println("This room contains the items: " + player.getCurrentRoom().getItemDisplayList());
			} else if (words[0].equals("add")) {
				if (words.length < 3) {
					System.out.println("I don't know what you mean!");
				} else {
					if (words[1].equals("room")) {
						String name = words[2];
						level.addRoom(name);
						level.addTwoWayDoor(player.getCurrentRoom().getName(), name);
						System.out.println("Added exit to room " + name);
					}
				}
			} else if (words[0].equals("quit")) {
				System.out.println("Goodbye!");
				System.exit(0);
			} else {
				displayCommands();
			}
			
			level.tick();

		} while (!response.equals("quit"));
	}
	
	public static Player getPlayer(){
		return player;
	}

	private static void displayCommands() {
		System.out.println("Commands you can type are:");
		System.out.println("look: to display exits from your current room");
		System.out.println("go <roomname>: to go to a room");
		System.out.println("add room <roomname>: adds a new room to the game from your current room");
		System.out.println("quit: quits");
	}
}
