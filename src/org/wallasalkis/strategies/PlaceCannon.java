package org.wallasalkis.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.wallasalkis.storage.Storage;

public class PlaceCannon extends Strategy implements Runnable {
	public boolean validate() {
		SceneObject cannon = SceneEntities.getNearest(6);
		return cannon == null;
	}

	@Override
	public void run() {
		if (Players.getLocal().getLocation().equals(Storage.cannonTile.getLocation())) {
			System.out.println("We are on the cannon tile, placing cannon");
			if (Players.getLocal().getAnimation() != 827 || !Players.getLocal().isMoving()) {
				Inventory.getItem(Storage.baseId).getWidgetChild().click(true);
				Time.sleep(1000,1501);
			}
		} else {
			if (Storage.cannonTile.isOnScreen()) {
				if (!Players.getLocal().isMoving()) {
					Storage.cannonTile.interact("Walk");
					Time.sleep(1000,1501);
				}
			} else {
				Camera.setPitch(true);
				Camera.turnTo(Storage.cannonTile);
				Camera.setPitch(Random.nextInt(30,70));
			}
		}
	}
}
