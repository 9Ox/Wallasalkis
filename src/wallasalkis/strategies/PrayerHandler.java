package wallasalkis.strategies;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Prayer;

import wallasalkis.main.WallasalkisMain;
import wallasalkis.storage.Storage;

public class PrayerHandler extends Node {
	@Override
	public boolean activate() { 
		return !WallasalkisMain.refreshCannon &&
		(!Prayer.isQuickOn()
				|| Prayer.getPoints() <= 150
				|| (WallasalkisMain.renewal > 300 
						&& (Inventory.containsOneOf(Storage.renewalFlaskIds) 
								|| Inventory.containsOneOf(Storage.renewalPotionIds))));
	}

	@Override
	public void execute() {
		WallasalkisMain.s = "PrayerHandler";
		if (!Prayer.isQuickOn()) {
			Prayer.toggleQuick(true);
			Task.sleep(1000, 1501);
		}
		if (Prayer.getPoints() < 150) {
			if (Inventory.getItem(Storage.prayerFlaskIds) != null) {
				Inventory.getItem(Storage.prayerFlaskIds).getWidgetChild().click(true);
				Task.sleep(1100, 1201);
			} else if (Inventory.getItem(Storage.prayerPotionIds) != null) {
				Inventory.getItem(Storage.prayerPotionIds).getWidgetChild().click(true);
				Task.sleep(1100, 1201);
			}
		}
		if (WallasalkisMain.renewal > 300) {
			System.out.println("Renewal timed out, refreshing");
			if (Inventory.getItem(Storage.renewalFlaskIds) != null 
					|| Inventory.getItem(Storage.renewalPotionIds) != null) {
				WallasalkisMain.renewal = 0;
			}
			if (Inventory.getItem(Storage.renewalFlaskIds) != null) {
				Inventory.getItem(Storage.renewalFlaskIds).getWidgetChild().interact("Drink");
				Task.sleep(150,201);
				WallasalkisMain.renewal = 0;
			} else if (Inventory.getItem(Storage.renewalPotionIds) != null) {
				Inventory.getItem(Storage.renewalPotionIds).getWidgetChild().interact("Drink");
				Task.sleep(150,201);
				WallasalkisMain.renewal = 0;
			}
		}
	}
}
