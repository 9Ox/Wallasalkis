package org.wallasalkis.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class LoadCannon extends Strategy implements Runnable {
	public boolean validate() {
		SceneObject cannon = SceneEntities.getNearest(6);
		return Settings.get(1) != 1052670
				&& cannon != null;
	}

	@Override
	public void run() {
		System.out.println("Cannon not firing, loading");
		SceneObject cannon = SceneEntities.getNearest(6);
		if (cannon != null) {
			if (!Players.getLocal().isMoving()) {
				cannon.click(true);
				Time.sleep(1000,1501);
			}
		}
	}
}
