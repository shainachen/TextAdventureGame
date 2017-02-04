package ca.dobervich.graph.HW5;

import java.util.ArrayList;
import java.util.List;

import ca.dobervich.graph.HW5.Level.Room;

public abstract class MovingEntity extends Entity {

	public MovingEntity(String name, Room startingRoom, String description, int health) {
		super(name, startingRoom, description, health);
		currentRoom.addEnemy(this);
	}

	public void moveRandom() {
		List<String> keys = new ArrayList<String>(currentRoom.getNeighbors().keySet());
		String randomKey = keys.get((int) (Math.random() * keys.size()));
		Level.Room randomValue = currentRoom.getNeighbors().get(randomKey);

		move(randomValue);
	}

	public void chasePlayer() {
		String secondNeighborList = currentRoom.getSecondNeighborNames();
		String[] secondNeighborNames = secondNeighborList.split(" ");
		for (String secondNeighbor : secondNeighborNames) {
			if (currentRoom.getSecondNeighbor(secondNeighbor).hasEnemy()) { // implement getSecondNeighbor
				// make enemy's current room a neighbor of player's current room
			}

		}

	}

	public void move(Level.Room room) {
		room.addEnemy(this);
		currentRoom.removeEnemy(this.getName());
		currentRoom = room;
	}

	public abstract void move();

}
