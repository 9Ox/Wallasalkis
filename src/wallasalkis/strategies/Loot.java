package wallasalkis.strategies;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.SceneObject;

import wallasalkis.main.WallasalkisMain;
import wallasalkis.storage.Storage;

public class Loot extends Node {
	@Override
	public boolean activate() {
		SceneObject cannon = SceneEntities.getNearest(6);
		return Prayer.getPoints() > 155 && cannon != null
				&& WallasalkisMain.refresh <= 18;
	}

	@Override
	public void execute() {
		WallasalkisMain.s = "Loot";
		String[] nameParts;
		if (Inventory.containsOneOf(Storage.junkIds)) {
			Inventory.getItem(Storage.junkIds).getWidgetChild()
					.interact("Drop");
			Task.sleep(1000, 1501);
		}
		if (Inventory.contains(Storage.spinTicketId)) {
			Inventory.getItem(Storage.spinTicketId).getWidgetChild().click(true);
			Task.sleep(1000, 1501);
		}
		try {
			GroundItem charms = GroundItems.getNearest(Storage.charmIds), runes = GroundItems
					.getNearest(Storage.runeIds), skeletalObjects = GroundItems
					.getNearest(Storage.skeletalIds), flasks = GroundItems
					.getNearest(Storage.prayerFlaskIds), potions = GroundItems
					.getNearest(Storage.prayerPotionIds), seeds = GroundItems
					.getNearest(Storage.seedIds), herbs = GroundItems
					.getNearest(Storage.herbIds),
					ticket = GroundItems.getNearest(Storage.spinTicketId);
			if (ticket != null && Storage.area.contains(ticket)) {
				if (ticket.isOnScreen() && !Players.getLocal().isMoving()) {
					ticket.interact("Take");
				} else {
					Walking.walk(ticket);
				}
			} else if (flasks != null && Inventory.getCount() < 24 && Storage.area.contains(flasks)) {
				if (flasks.isOnScreen()) {
					if (!Players.getLocal().isMoving()) {
						flasks.interact("Take");
					}
				} else {
					Walking.walk(flasks);
				}
			} else if (potions != null && Inventory.getCount() < 24 && Storage.area.contains(potions)) {
				if (potions.isOnScreen()) {
					if (!Players.getLocal().isMoving()) {
						potions.interact("Take");
					}
				} else {
					Walking.walk(potions);
				}
			} else if (seeds != null && Storage.area.contains(seeds)) {
				if (Inventory.getCount() < 24
						|| Inventory.contains(seeds.getId())) {
					if (seeds.isOnScreen()) {
						if (!Players.getLocal().isMoving()) {
							Mouse.move(seeds.getCentralPoint().x,
									seeds.getCentralPoint().y);
							Task.sleep(5, 11);

							// This does the same things as that huge thing down
							// there
							nameParts = seeds.getGroundItem().getName()
									.split(" ");

							// Super awesome boss ternary operator
							// (http://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html)
							Menu.select("Take",nameParts.length == 0 ? nameParts[0]
											: nameParts[0] + " " + nameParts[1]);
							Task.sleep(600, 1001);
							/*
							 * if (seeds.getGroundItem().getName()
							 * .equalsIgnoreCase("Torstol seed")) {
							 * Menu.select("Take", "Torstol"); Task.sleep(600,
							 * 1001); } else if (seeds.getGroundItem().getName()
							 * .equalsIgnoreCase("Snapdragon seed")) {
							 * Menu.select("Take", "Snapdragon");
							 * Task.sleep(600, 1001); } else if
							 * (seeds.getGroundItem().getName()
							 * .equalsIgnoreCase("Lantadyme seed")) {
							 * Menu.select("Take", "Lantadyme"); Task.sleep(600,
							 * 1001); } else if (seeds.getGroundItem().getName()
							 * .equalsIgnoreCase("Dwarf weed seed")) {
							 * Menu.select("Take", "Dwarf weed");
							 * Task.sleep(600, 1001); }
							 */
						}
					} else {
						Walking.walk(seeds);
					}
				}
			} else if (charms != null && Storage.area.contains(charms)) {
				if (!Inventory.isFull() || Inventory.contains(charms.getId())) {
					if (charms.isOnScreen()) {
						if (!Players.getLocal().isMoving()) {
							Mouse.move(charms.getCentralPoint().x,
									charms.getCentralPoint().y);
							Task.sleep(5, 11);

							// Same as before
							nameParts = charms.getGroundItem().getName()
									.split(" ");
							Menu.select("Take", nameParts[0]);
							Task.sleep(600, 1001);
							/*
							 * if (charms.getGroundItem().getName()
							 * .equalsIgnoreCase("Blue charm")) {
							 * Menu.select("Take", "Blue"); Task.sleep(600,
							 * 1001); } else if
							 * (charms.getGroundItem().getName()
							 * .equalsIgnoreCase("Crimson charm")) {
							 * Menu.select("Take", "Crimson"); Task.sleep(600,
							 * 1001); } else if
							 * (charms.getGroundItem().getName()
							 * .equalsIgnoreCase("Green charm")) {
							 * Menu.select("Take", "Green"); Task.sleep(600,
							 * 1001); } else if
							 * (charms.getGroundItem().getName()
							 * .equalsIgnoreCase("Gold charm")) {
							 * Menu.select("Take", "Gold"); Task.sleep(600,
							 * 1001); }
							 */
						}
					} else {
						Walking.walk(charms);
					}
				}
			} else if (runes != null && Storage.area.contains(runes)) {
				if (!Inventory.isFull() || Inventory.contains(runes.getId())) {
					if (runes.isOnScreen()) {
						if (!Players.getLocal().isMoving()) {
							Mouse.move(runes.getCentralPoint().x,
									runes.getCentralPoint().y);
							Task.sleep(50, 101);

							// And... again
							nameParts = runes.getGroundItem().getName()
									.split(" ");
							Menu.select("Take", nameParts[0]);
							Task.sleep(600, 1001);
							/*
							 * if (runes.getGroundItem().getName()
							 * .equalsIgnoreCase("Death rune")) {
							 * Menu.select("Take", "Death"); Task.sleep(600,
							 * 1001); } else if (runes.getGroundItem().getName()
							 * .equalsIgnoreCase("Blood rune")) {
							 * Menu.select("Take", "Blood"); Task.sleep(600,
							 * 1001); } else { Menu.select("Take", "Pure");
							 * Task.sleep(600, 1001); }
							 */
						}
					} else {
						Walking.walk(runes);
					}
				}
			} else if (skeletalObjects != null && Inventory.getCount() < 24 
					&& Storage.area.contains(skeletalObjects)) {
				if (skeletalObjects.isOnScreen()) {
					if (!Players.getLocal().isMoving()) {
						Mouse.move(skeletalObjects.getCentralPoint().x,
								skeletalObjects.getCentralPoint().y);
						Task.sleep(50, 101);
						String s = skeletalObjects.getGroundItem().getName();
						if (s.equalsIgnoreCase("Skeletal gloves")
								|| s.equalsIgnoreCase("Skeletal boots")
								|| s.equalsIgnoreCase("Skull piece")) {
							System.out.println("We found item: " + s);
							Menu.select("Take", s);
							Task.sleep(600, 1001);
						}
						/*
						 * if (skeletalObjects.getGroundItem().getName()
						 * .equalsIgnoreCase("Skeletal gloves")) {
						 * System.out.println("We found item: " +
						 * skeletalObjects.getGroundItem() .getName());
						 * Menu.select("Take", "Skeletal gloves");
						 * Task.sleep(600, 1001); } else if
						 * (skeletalObjects.getGroundItem().getName()
						 * .equalsIgnoreCase("Skeletal boots")) { System.out
						 * .println("We found item: " +
						 * skeletalObjects.getGroundItem() .getName());
						 * Menu.select("Take", "Skeletal boots");
						 * Task.sleep(600, 1001); }
						 */
					}
				} else {
					Walking.walk(skeletalObjects);
				}

			} else if (herbs != null && WallasalkisMain.usingYak && Storage.area.contains(herbs)) {
				if (Inventory.getCount() < 24) {
					if (herbs.isOnScreen()) {
						if (!Players.getLocal().isMoving()) {
							Mouse.move(herbs.getCentralPoint().x,
									herbs.getCentralPoint().y);
							Task.sleep(5, 11);

							nameParts = herbs.getGroundItem().getName()
									.split(" ");

							Menu.select("Take", nameParts[0] + " "
									+ nameParts[1]);
							Task.sleep(600, 1001);
							/*
							 * if (herbs.getGroundItem().getName()
							 * .equalsIgnoreCase("Grimy lantadyme")) {
							 * Menu.select("Take", "Grimy lantadyme");
							 * Task.sleep(600, 1001); } else if
							 * (herbs.getGroundItem().getName()
							 * .equalsIgnoreCase("Grimy dwarf weed")) {
							 * Menu.select("Take", "Grimy dwarf");
							 * Task.sleep(600, 1001); } else if
							 * (herbs.getGroundItem().getName()
							 * .equalsIgnoreCase("Grimy ranarr")) {
							 * Menu.select("Take", "Grimy ranarr");
							 * Task.sleep(600, 1001); } else if
							 * (herbs.getGroundItem().getName()
							 * .equalsIgnoreCase("Grimy avantoe")) {
							 * Menu.select("Take", "Grimy avantoe");
							 * Task.sleep(600, 1001); }
							 */
						}
					} else {
						Walking.walk(herbs);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
