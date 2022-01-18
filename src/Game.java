import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
	
	public Node[] nodeSnake = new Node[10];
	
	public boolean left,right,up,down;
	
	public int speed = 15, dimension = 480;
	
	public Game() {
		this.setPreferredSize(new Dimension(dimension,dimension));
		for(int i = 0; i < this.nodeSnake.length; i++) {
			nodeSnake[i] = new Node((i*10)+40, 40);
		}
		this.addKeyListener(this);
	}
	
	public void tick() {
		
		if(left || right || up || down) {
			for(int i = this.nodeSnake.length - 1; i > 0; i--) {
				nodeSnake[i].x = nodeSnake[i-1].x;
				nodeSnake[i].y = nodeSnake[i-1].y;
			}
		}

		if(left) {
			nodeSnake[0].moveLeft();
		} else if(right) {
			nodeSnake[0].moveRight();
		} else if(up) {
			nodeSnake[0].moveUp();
		} else if(down) {
			nodeSnake[0].moveDown();
		}
		this.delimitaMovimento();
		
	}
	
	public void delimitaMovimento() {
		if(nodeSnake[0].x > dimension)
			nodeSnake[0].x = 0;
		if(nodeSnake[0].y > dimension)
			nodeSnake[0].y = 0;
		if(nodeSnake[0].x < 0)
			nodeSnake[0].x = dimension;
		if(nodeSnake[0].y < 0)
			nodeSnake[0].y = dimension;
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 480, 480);
		
		for(int i = 0; i < nodeSnake.length; i++) {
			g.setColor(Color.blue);
			g.fillRect(nodeSnake[i].x, nodeSnake[i].y, 10, 10);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		JFrame frame = new JFrame("Snake");
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}

	@Override
	public void run() {
		
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub		
	} 
	
	@Override
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			left = false;
			right = true;
			up = false;
			down = false;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
			right = false;
			up = false;
			down = false;
		} else if(e.getKeyCode() == KeyEvent.VK_UP) {
			left = false;
			right = false;
			up = true;
			down = false;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			left = false;
			right = false;
			up = false;
			down = true;
		} else {
			left = right = up = down = false;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
