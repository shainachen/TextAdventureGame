package ca.dobervich.graph.HW5;

import java.util.ArrayList;
import java.util.List;

import ca.dobervich.graph.HW5.Level.Room;

public abstract class MovingEntity extends Entity{

	public MovingEntity(String name, Room startingRoom, String description, int health) {
		super(name, startingRoom, description, health);
		currentRoom.addEnemy(this);
	}
	
	public void moveRandom(){
		List<String> keys = new ArrayList<String>(currentRoom.getNeighbors().keySet());
		String randomKey = keys.get((int)(Math.random()*keys.size());
		Level.Room randomValue = .get(randomKey);

	}
	
	public void move(Level.Room room){
		room.addEnemy(this);
		currentRoom.removeEnemy(this.getName());
		currentRoom = room;
	}
	
	public void tick(){
		move();
	}
	
	public abstract void move();

}
