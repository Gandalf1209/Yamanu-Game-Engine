package com.gandalf1209.yamanu.physics;

import com.gandalf1209.yamanu.game.GameObject;

public class PhysicsHandler {

	public Thread pThread;
	
	public PhysicsHandler() {
		pThread = new Thread("Physics Handler Thread") {
			public void run() {
				while (true) {
					runPhysics();
				}
			}
		};
		pThread.start();
	}
	
	private static void runPhysics() {
		for (int i = 0; i < GameObject.getCount(); i++) {
			GameObject current = GameObject.getObjects().get(i);
			if (current.canCollide()) {
				for (int j = 0; j < GameObject.getCount(); j++) {
					GameObject target = GameObject.getObjects().get(j);
					if (i != j && target.canCollide() && current.canCollide()) {
						if (current.getX() + current.getWidth() > target.getX() &&
								current.getX() - current.getWidth() < target.getX() &&
								current.getY() + current.getHeight() > target.getY() &&
								current.getY() - current.getHeight() < target.getY()) {
							current.setSpeed(current.getOriginalSpeed() - (target.getFriction() / 10));
						} else {
							current.setSpeed(current.getOriginalSpeed());
						}
					}
				}
			}
		}
	}
	
}
