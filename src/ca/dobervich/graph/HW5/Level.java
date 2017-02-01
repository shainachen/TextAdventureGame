package ca.dobervich.graph.HW5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.dobervich.graph.HW5.Level.Room;

public class Level implements Serializable {
	private HashMap<String, Room> nodes;

	public Level() {
		nodes = new HashMap<String, Room>();
	}

	public void addRoom(String name, String description) {
		Room n = new Room(name);
		n.setDescription(description);
		nodes.put(name, n);
	}

	public void addRoom(String name) {
		addRoom(name, "a nondescript room");
	}

	public void addOneWayDoor(String name1, String name2) {
		Room n1 = getRoom(name1);
		Room n2 = getRoom(name2);
		n1.addNeighbor(n2);
	}

	public void addTwoWayDoor(String name1, String name2) {
		addOneWayDoor(name1, name2);
		addOneWayDoor(name2, name1);
	}

	public Room getRoom(String name) {
		return nodes.get(name);
	}

	public boolean saveLevel(String path) {
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.printf("Your graph data is saved in " + path);
			return true;
		} catch (IOException i) {
			i.printStackTrace();
			return false;
		}
	}

	public static Level loadLevel(String path) {
		Level g = new Level();
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			g = (Level) in.readObject();
			in.close();
			fileIn.close();
			return g;
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("Graph class not found");
			c.printStackTrace();
			return null;
		}
	}

	public class Room implements Serializable {
		private HashMap<String, Room> neighbors;
		private String name;
		private String description;
		private List<Item> items;
		
		private Room(String name) {
			neighbors = new HashMap<String, Room>();
			items = new ArrayList<Item>();
			this.name = name;
		}
		
		public void addItem(Item item) {
			items.add(item);
		}
		
		public Item removeItem(String itemName) {
			for (int i = 0; i < items.size(); i++) {
				Item item = items.get(i);
				if (item.getName().equals(itemName)) {
					return items.remove(i);
				}
			}
			
			return null;
		}
		
		public String getItemDisplayList() {
			String str = "";
			for (Item item : items) {
				str += item.getName() + " "; 
			}
			
			return str;
		}

		private void addNeighbor(Room n) {
			neighbors.put(n.getName(), n);
		}

		public Room getNeighbor(String name) {
			return neighbors.get(name);
		}

		public String getName() {
			return name;
		}

		public String getNeighborNames() {
			String names = "";

			for (String name : neighbors.keySet()) {
				names += name + " ";
			}

			return names;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public boolean hasNeighbor(Room nextRoom) {
			return neighbors.containsKey(nextRoom.getName());
		}

	}

}
