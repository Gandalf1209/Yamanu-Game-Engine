package com.gandalf1209.yamanu.game;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class GameObject {

	private int x;
	private int y;
	private int w;
	private int h;
	private boolean canCollide;
	private int speed;
	private int friction;
	private boolean smart;
	private Image img;
	
	private int originalSpeed;
	private int xDir = 0;
	private int yDir = 0;
	
	private static int count = 0;
	private static List<GameObject> objects = new ArrayList<GameObject>();
	
	public GameObject(int x, int y, int w, int h, boolean canCollide, int speed, int friction, boolean smart) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.canCollide = canCollide;
		this.speed = speed;
		this.originalSpeed = speed;
		this.friction = friction;
		this.smart = smart;
		
		count++;
		objects.add(this);
	}
	
	public void move(int x, int y) {
		if (x == -1) {
			this.setX(this.getX() - this.speed);
			this.xDir = -1;
		}
		if (x == 0) {
			this.xDir = 0;
		}
		if (x == 1) {
			this.setX(this.getX() + this.speed);
			this.xDir = 1;
		}
		if (y == -1) {
			this.setY(this.getY() + this.speed);
			this.yDir = -1;
		}
		if (y == 0) {
			this.yDir = 0;
		}
		if (y == 1) {
			this.setY(this.getY() - this.speed);
			this.yDir = 1;
		}
	}
	
	public void move(int x, int y, boolean isPushed) {
		if (x == -1) {
			this.setX(this.getX() - this.speed);
		}
		if (x == 1) {
			this.setX(this.getX() + this.speed);
		}
		if (y == -1) {
			this.setY(this.getY() + this.speed);
		}
		if (y == 1) {
			this.setY(this.getY() - this.speed);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return w;
	}

	public void setWidth(int w) {
		this.w = w;
	}

	public int getHeight() {
		return h;
	}

	public void setHeight(int h) {
		this.h = h;
	}

	public boolean canCollide() {
		return canCollide;
	}

	public void setCanCollide(boolean canCollide) {
		this.canCollide = canCollide;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFriction() {
		return friction;
	}

	public void setFriction(int friction) {
		this.friction = friction;
	}

	public boolean isSmart() {
		return smart;
	}

	public void setSmart(boolean smart) {
		this.smart = smart;
	}
	
	public void setImage(Image img) {
		this.img = img;
	}
	
	public Image getImage() {
		return this.img;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		GameObject.count = count;
	}

	public static List<GameObject> getObjects() {
		return objects;
	}

	public static void setObjects(List<GameObject> objects) {
		GameObject.objects = objects;
	}
	
	public int getXDir() {
		return xDir;
	}

	public void setXDir(int xDir) {
		this.xDir = xDir;
	}

	public int getYDir() {
		return yDir;
	}

	public void getYDir(int yDir) {
		this.yDir = yDir;
	}

	public int getOriginalSpeed() {
		return this.originalSpeed;
	}
	
	public void destroy() {
		x = 0;
		y = 0;
		w = 0;
		y = 0;
		canCollide = false;
		speed = 0;
		friction = 0;
		smart = false;
		img = null;
	}
	
}
