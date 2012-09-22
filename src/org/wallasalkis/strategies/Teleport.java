package org.wallasalkis.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.bot.Context;
import org.wallasalkis.storage.Storage;

public class Teleport extends Strategy implements Runnable {
	public boolean validate() {
		return Inventory.getItem(Storage.prayerFlaskIds) == null
				&& Inventory.getItem(Storage.prayerPotionIds) == null;
	}

	@Override
	public void run() {
		if (Inventory.getItem(Storage.teleTabIds) != null) {
			Inventory.getItem(Storage.teleTabIds).getWidgetChild().click(true);
			Time.sleep(2000,3001);
			Game.logout(true);
			Context.get().getActiveScript().stop();
		}
	}
}
