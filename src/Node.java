
public class Node {
	
	public int x,y,speed;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.speed = 2;
	}
	
	public void moveDown() {
		this.y += this.speed;
	}
	
	public void moveUp() {
		this.y -= this.speed;
	}
	
	public void moveRight() {
		this.x += this.speed;
	}	
	
	public void moveLeft() {
		this.x -= this.speed;
	}


}
