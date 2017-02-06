package ca.dobervich.graph.HW5;

import ca.dobervich.graph.HW5.Level.Room;

public class Potato extends MovingEntity{
	public Potato(String name, Room startingRoom, String description, int health) {
		super(name, startingRoom, description, health);
	}

	@Override
	public void move() {
		chasePlayer();
	}

}
