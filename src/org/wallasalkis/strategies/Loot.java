package org.wallasalkis.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.wallasalkis.storage.Storage;

public class Loot extends Strategy implements Runnable {
	public boolean validate() {
		SceneObject cannon = SceneEntities.getNearest(6);
		return Prayer.getPoints() > 150
				&& cannon != null
				&& (Skills.getLevel(Skills.RANGE) - Skills.getRealLevel(Skills.RANGE)) > 3;
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {
		GroundItem bloodRune = GroundItems.getNearest(Storage.bloodRuneId);
		GroundItem deathRune = GroundItems.getNearest(Storage.deathRuneId);
		GroundItem notedEss = GroundItems.getNearest(Storage.notedEssId);
		GroundItem glove = GroundItems.getNearest(Storage.gloveId);
		GroundItem boot = GroundItems.getNearest(Storage.bootId);
		GroundItem charms = GroundItems.getNearest(Storage.charmIds);
	}
}
