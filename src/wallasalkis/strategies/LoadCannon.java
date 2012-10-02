package wallasalkis.strategies;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.SceneObject;

import wallasalkis.main.WallasalkisMain;
import wallasalkis.storage.Storage;

public class LoadCannon extends Node {
	@Override
	public boolean activate() {
		SceneObject cannon = SceneEntities.getNearest(6);
		return WallasalkisMain.loadCannon > Random.nextInt(30, 40)
				&& cannon != null && Prayer.getPoints() > 155
				&& !WallasalkisMain.refreshCannon;
	}

	@Override
	public void execute() {
		WallasalkisMain.s = "LoadCannon";
		try {
			SceneObject cannon = SceneEntities.getNearest(6);
			if (cannon != null) {
				if (cannon.isOnScreen()) {
					if (cannon.interact("Fire")) {
						WallasalkisMain.loadCannon = 0;
						Task.sleep(1000, 1501);
					}
				} else {
					Walking.walk(Storage.tile);
				}
			}
		} catch (Exception e) {

		}
	}
}
