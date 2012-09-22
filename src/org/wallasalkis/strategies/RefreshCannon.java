package org.wallasalkis.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.wallasalkis.main.WallasalkisMain;
import org.wallasalkis.storage.Storage;

public class RefreshCannon extends Strategy implements Runnable {
	public boolean validate() {
		return WallasalkisMain.refreshCannon;
	}

	@Override
	public void run() {
		SceneObject cannon = SceneEntities.getNearest(Storage.baseId);
		if (cannon != null) {
			if (!Players.getLocal().isMoving()) {
				cannon.interact("Pick-up");
				Time.sleep(1000,1501);
				WallasalkisMain.refreshCannon = false;
			}
		}
	}
}
