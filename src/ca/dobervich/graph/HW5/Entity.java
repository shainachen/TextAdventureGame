package ca.dobervich.graph.HW5;

public abstract class Entity {
	Level.Room currentRoom;
	private String name;
	private String description;
	private int health;
	private int tickCount=0;
	
	public Entity(String name, Level.Room startingRoom, String description, int health){
		currentRoom=startingRoom;
		this.name=name;
		this.description=description;
		this.health=health;
	}
	
	public Level.Room getCurrentRoom(){
		return currentRoom;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getTickCount() {
		return tickCount;
	}

	public void setCurrentRoom(Level.Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public void tick(){
		tickCount++;
	}
	
	public int distanceTo(Entity e){
		return 0;
	}
}
