package ca.dobervich.graph.HW5;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private Level.Room currentName;
	private String name, description;
	private List<Item> items;

	public Player(Level.Room room, String n, String d) {
		this.currentName = room;
		name = n;
		description = d;
		items = new ArrayList<Item>();
	}

	public boolean move(Level.Room nextRoom) {
		if (currentName.hasNeighbor(nextRoom)) {
			currentName = nextRoom;
			return true;
		}

		return false;
	}

	public boolean move(String nextRoomName) {
		Level.Room nextRoom = currentName.getNeighbor(nextRoomName);
		if (nextRoom == null) return false;
		return move(nextRoom);
	}

	public void takeItem(Item i) {
		items.add(i);
	}

	public Item dropItem(String itemName) {
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if (item.getName().equals(itemName)) {
				return items.remove(i);
			}
		}
		
		return null;
	}

	public String getCarryingItemNames() {
		String str = "";
		for (Item item : items) {
			str += item.getName() + " "; 
		}
		
		return str;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Level.Room getCurrentRoom() {
		return currentName;
	}

}
