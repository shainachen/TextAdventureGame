package ca.dobervich.graph.HW5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.dobervich.graph.HW5.Level.Room;

public abstract class MovingEntity extends Entity {
	private int distanceToPlayer;

	public MovingEntity(String name, Room startingRoom, String description, int health) {
		super(name, startingRoom, description, health);
		currentRoom.addEnemy(this);
	}

	public void moveRandom() {
		List<String> keys = new ArrayList<String>(currentRoom.getNeighbors().keySet());
		String randomKey = keys.get((int) (Math.random() * keys.size()));
		Level.Room randomRoom = currentRoom.getNeighbor(randomKey);

		move(randomRoom);
	}

	public void chasePlayer() {
		if(currentRoom == Main.getPlayer().getCurrentRoom()) return;
		distanceToPlayer = 1;
		HashMap<String, Level.Room> neighborsOfEnemy = currentRoom.getNeighbors();
		for (String neighborName : neighborsOfEnemy.keySet()) {
			Level.Room neighbor = neighborsOfEnemy.get(neighborName);
			if (searchPath(currentRoom, neighbor, 1) && distanceToPlayer > 1) {
				move(neighbor);
				return;
			}
		}
	}
	
	public boolean searchPath(Level.Room previousRoom, Level.Room room, int distance) {
		Level.Room playerRoom = Main.getPlayer().getCurrentRoom();
		if(playerRoom == room) return true;
		
		HashMap<String, Level.Room> neighbors = room.getNeighbors();
		
		for (String neighborName : neighbors.keySet()) {
			Level.Room neighbor = neighbors.get(neighborName);
			if(previousRoom != neighbor && searchPath(room, neighbor, distance+1)){
				distanceToPlayer++;
				return true;
			}
		}
		return false;
	}

	public void move(Level.Room room) {
		room.addEnemy(this);
		currentRoom.removeEnemy(this.getName());
		currentRoom = room;
	}

	public abstract void move();

}
