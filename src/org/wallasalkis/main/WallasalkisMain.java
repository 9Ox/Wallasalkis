package org.wallasalkis.main;
import org.wallasalkis.storage.Storage;
import org.wallasalkis.strategies.*;

import org.powerbot.concurrent.LoopTask;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;

@Manifest(name = "Wallasalkis",
		description = "Kills wallasalkis for charms.",
		version = 0.01,
		authors = "TaylorSwift")
public class WallasalkisMain extends ActiveScript implements MessageListener {
	public static int renewal = 0;
	@SuppressWarnings("unused")
	private int startExp, gainedExp;
	
	public static boolean refreshCannon = false;
	
	@Override
	protected void setup() {
		if (Inventory.getItem(Storage.scrollId) != null) {
			provide(new YakHandler());
		}
		startExp = Skills.getExperience(Skills.RANGE);
		//provide(new Teleport());
		//provide(new PrayerHandler());
		//provide(new PlaceCannon());
		//provide(new LoadCannon());
		provide(new Loot());
		submit(new LoopTask() {
			public int loop() {
				renewal++;
				return 1000;
			}
		});
		submit(new LoopTask() {
			public int loop() {
				gainedExp = Skills.getExperience(Skills.RANGE) - startExp;
				return 0;
			}
		});
	}

	@Override
	public void messageReceived(MessageEvent e) {
		if (e.getMessage().equals("Your cannon has almost decayed!")) {
			refreshCannon = true;
		}
	}
}
