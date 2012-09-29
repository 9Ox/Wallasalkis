package wallasalkis.strategies;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.bot.Context;

import wallasalkis.main.WallasalkisMain;
import wallasalkis.storage.Storage;

public class Teleport extends Node {
	@Override
	public boolean activate() {
		if (WallasalkisMain.noPrayers) {
			return !Inventory.containsOneOf(Storage.renewalFlaskIds)
					&& !Inventory.containsOneOf(Storage.renewalPotionIds);
		} else if (!WallasalkisMain.noPrayers) {
			return Inventory.getItem(Storage.prayerFlaskIds) == null
				&& Inventory.getItem(Storage.prayerPotionIds) == null;
		}
		return false;
	}

	@Override
	public void execute() {
		SceneObject cannon = SceneEntities.getNearest(6);
		WallasalkisMain.s = "Teleport()";
		try {
			if (WallasalkisMain.noPrayers
					&& (!Inventory.containsOneOf(Storage.renewalFlaskIds) && !Inventory
							.containsOneOf(Storage.renewalPotionIds))) {
				if (cannon != null) {
					if (cannon.interact("Pick-up")) {
						Task.sleep(3000,5001);
						if (Inventory.getItem(Storage.teleTabIds) != null) {
							Inventory.getItem(Storage.teleTabIds)
									.getWidgetChild().click(true);
							Task.sleep(2000, 3001);
							Context.get().getScriptHandler().stop();
						}
					}
				}
			} else if (!WallasalkisMain.noPrayers) {
				if (cannon != null) {
					if (cannon.interact("Pick-up")) {
						Task.sleep(3000,5001);
						if (Inventory.getItem(Storage.teleTabIds) != null) {
							Inventory.getItem(Storage.teleTabIds)
									.getWidgetChild().click(true);
							Task.sleep(2000, 3001);
							Context.get().getScriptHandler().stop();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
