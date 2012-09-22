package org.wallasalkis.main;
import org.wallasalkis.strategies.*;

import org.powerbot.concurrent.LoopTask;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Skills;

@Manifest(name = "Wallasalkis",
		description = "Kills wallasalkis for charms.",
		version = 0.01,
		authors = "TaylorSwift")
public class WallasalkisMain extends ActiveScript {
	public static int renewal = 0;
	@SuppressWarnings("unused")
	private int startExp, gainedExp;
	
	@Override
	protected void setup() {
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
}
