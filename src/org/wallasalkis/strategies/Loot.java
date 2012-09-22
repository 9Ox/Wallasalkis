package org.wallasalkis.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.wallasalkis.storage.Storage;

public class Loot extends Strategy implements Runnable {
	public boolean validate() {
		SceneObject cannon = SceneEntities.getNearest(6);
		return Prayer.getPoints() > 150
			&& cannon != null
					&& (Skills.getLevel(Skills.RANGE) -
							Skills.getRealLevel(Skills.RANGE)) > 3;
	}

	@Override
	public void run() {
		GroundItem charms = GroundItems.getNearest(Storage.charmIds), runes = GroundItems
				.getNearest(Storage.runeIds), skeletalObjects = GroundItems
				.getNearest(Storage.skeletalIds);
		if (charms != null) {
			if (charms.isOnScreen()) {
				if (!Players.getLocal().isMoving()) {
					Mouse.move(charms.getCentralPoint().x,
							charms.getCentralPoint().y);
					Time.sleep(5, 11);
					if (charms.getGroundItem().getName()
							.equalsIgnoreCase("Blue charm")) {
						Menu.select("Take", "Blue");
						Time.sleep(600, 1001);
					} else if (charms.getGroundItem().getName()
							.equalsIgnoreCase("Crimson charm")) {
						Menu.select("Take", "Crimson");
						Time.sleep(600, 1001);
					} else if (charms.getGroundItem().getName()
							.equalsIgnoreCase("Green charm")) {
						Menu.select("Take", "Green");
						Time.sleep(600, 1001);
					} else if (charms.getGroundItem().getName()
							.equalsIgnoreCase("Gold charm")) {
						Menu.select("Take", "Gold");
						Time.sleep(600, 1001);
					}
				}
			} else {
				Walking.walk(charms);
			}
		} else if (runes != null) {
			if (runes.isOnScreen()) {
				if (!Players.getLocal().isMoving()) {
					Mouse.move(runes.getCentralPoint().x,
							runes.getCentralPoint().y);
					Time.sleep(50, 101);
					if (runes.getGroundItem().getName()
							.equalsIgnoreCase("Death rune")) {
						Menu.select("Take", "Death");
						Time.sleep(600, 1001);
					} else if (runes.getGroundItem().getName()
							.equalsIgnoreCase("Blood rune")) {
						Menu.select("Take", "Blood");
						Time.sleep(600, 1001);
					} else if (runes.getGroundItem().getName()
							.equalsIgnoreCase("Pure essence")) {
						Menu.select("Take", "Pure");
						Time.sleep(600, 1001);
					}
				}
			} else {
				Walking.walk(runes);
			}
		} else if (skeletalObjects != null) {
			if (skeletalObjects.isOnScreen()) {
				if (!Players.getLocal().isMoving()) {
					Mouse.move(skeletalObjects.getCentralPoint().x,
							skeletalObjects.getCentralPoint().y);
					Time.sleep(50, 101);
					if (skeletalObjects.getGroundItem().getName()
							.equalsIgnoreCase("Skeletal gloves")) {
						Menu.select("Take", "Skeletal gloves");
						Time.sleep(600, 1001);
					} else if (skeletalObjects.getGroundItem().getName()
							.equalsIgnoreCase("Skeletal boots")) {
						Menu.select("Take", "Skeletal boots");
						Time.sleep(600, 1001);
					}
				}
			} else {
				Walking.walk(skeletalObjects);
			}
		}
	}
}
