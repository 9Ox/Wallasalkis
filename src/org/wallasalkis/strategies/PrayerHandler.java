package org.wallasalkis.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.util.Time;
import org.wallasalkis.main.WallasalkisMain;
import org.wallasalkis.storage.Storage;

public class PrayerHandler extends Strategy implements Runnable {
	public boolean validate() {
		return Settings.get(1582) != 128 
				|| Prayer.getPoints() < 150
				|| WallasalkisMain.renewal > 280;
	}

	@Override
	public void run() {
		if (!Prayer.isQuickOn()) {
			Prayer.toggleQuick(true);
			Time.sleep(1000, 1501);
		}
		if (Prayer.getPoints() < 150) {
			if (Inventory.getItem(Storage.prayerFlaskIds) != null) {
				Inventory.getItem(Storage.prayerFlaskIds).getWidgetChild().click(true);
				Time.sleep(1100, 1201);
				Inventory.getItem(Storage.prayerFlaskIds).getWidgetChild().click(true);
			} else if (Inventory.getItem(Storage.prayerPotionIds) != null) {
				Inventory.getItem(Storage.prayerPotionIds).getWidgetChild().click(true);
				Time.sleep(1100, 1201);
				Inventory.getItem(Storage.prayerPotionIds).getWidgetChild().click(true);
			}
		}
		if (WallasalkisMain.renewal > 280) {
			if (Inventory.getItem(Storage.renewalFlaskIds) != null) {
				Inventory.getItem(Storage.renewalFlaskIds).getWidgetChild().interact("Drink");
				Time.sleep(150,201);
				WallasalkisMain.renewal = 0;
			} else if (Inventory.getItem(Storage.renewalPotionIds) != null) {
				Inventory.getItem(Storage.renewalPotionIds).getWidgetChild().interact("Drink");
				Time.sleep(150,201);
				WallasalkisMain.renewal = 0;
			}
		}
	}
}
