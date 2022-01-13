
public class Node {
	
	public int x,y,size;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.size = 10;
	}
	
	public void moveDown() {
		this.y += this.size;
	}
	
	public void moveUp() {
		this.y -= this.size;
	}
	
	public void moveRight() {
		this.x += this.size;
	}	
	
	public void moveLeft() {
		this.x -= this.size;
	}


}
