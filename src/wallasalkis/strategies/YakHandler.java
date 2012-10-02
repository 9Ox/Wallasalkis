package wallasalkis.strategies;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.tab.Summoning;
import wallasalkis.main.WallasalkisMain;
import wallasalkis.storage.Storage;

public class YakHandler extends Node {
	@Override
	public boolean activate() {
		return (Prayer.getPoints() > 155
				&& !WallasalkisMain.refreshCannon
				&& Inventory.getItem(Storage.scrollId) != null
				&& WallasalkisMain.usingYak
				&& SceneEntities.getNearest(Storage.baseId) != null
				&& (Inventory.containsOneOf(Storage.skeletalIds)
						|| Inventory.containsOneOf(Storage.seedIds) || Inventory
							.containsOneOf(Storage.herbIds))) 
							|| (!Summoning.isFamiliarSummoned()
									&& WallasalkisMain.usingYak);
	}

	@Override
	public void execute() {
		WallasalkisMain.s = "YakHandler";
		if (!Summoning.isFamiliarSummoned() && !Inventory.contains(Storage.pouchId)) {
			WallasalkisMain.usingYak = false;
		}
		if (!Summoning.getLeftClickOption().equals(Summoning.Option.CAST)) {
			Summoning.setLeftClickOption(Summoning.Option.CAST);
			Task.sleep(600, 1001);
		}
		try {
			if (!Summoning.isFamiliarSummoned()) {
				if (Inventory.getItem(Storage.summoningPotionIds) != null
						&& Skills.getLevel(Skills.SUMMONING) < 30) {
					System.out
							.println("Points below 30 and yak needs a refresh, drinking potion");
					Inventory.getItem(Storage.summoningPotionIds)
							.getWidgetChild().click(true);
					Task.sleep(600,1001);
				}
				if (Inventory.getItem(Storage.pouchId) != null) {
					Inventory.getItem(Storage.pouchId).getWidgetChild()
							.interact("Summon");
					Task.sleep(1000, 1501);
				}
			}
			for (int i = 0; i < Storage.skeletalIds.length; i++) {
				if (Inventory.getItem(Storage.skeletalIds[i]) != null) {
					Widgets.get(548).getChild(165).interact("Cast");
					if (Inventory.getItem(Storage.skeletalIds[i])
							.getWidgetChild().click(true)) {
						System.out.println("Click success");
						switch (i) {
						case 0:
							WallasalkisMain.bankedGloves++;
							break;
						case 1:
							WallasalkisMain.bankedBoots++;
							break;
						}
						Task.sleep(1500, 2001);
					}
				}
			}
			for (int i = 0; i < Storage.seedIds.length; i++) {
				if (Inventory.getItem(Storage.seedIds[i]) != null) {
					Widgets.get(548).getChild(165).interact("Cast");
					if (Inventory.getItem(Storage.seedIds[i]).getWidgetChild()
							.click(true)) {
						System.out.println("Click success");
						switch (i) {
						case 0:
							WallasalkisMain.bankedTorstol++;
							break;
						case 1:
							WallasalkisMain.bankedSnapdragon++;
							break;
						case 2:
							WallasalkisMain.bankedLantadyme++;
							break;
						case 3:
							WallasalkisMain.bankedDwarfWeed++;

						}
						Task.sleep(1500, 2001);
					}
				}
			}
			for (int i = 0; i < Storage.herbIds.length; i++) {
				if (Inventory.getItem(Storage.herbIds[i]) != null) {
					Widgets.get(548).getChild(165).interact("Cast");
					if (Inventory.getItem(Storage.herbIds[i]).getWidgetChild()
							.click(true)) {
						System.out.println("Click success");
						// l d r a
						switch (i) {
						case 0:
							WallasalkisMain.lCount++;
							break;
						case 1:
							WallasalkisMain.dCount++;
							break;
						case 2:
							WallasalkisMain.rCount++;
							break;
						case 3:
							WallasalkisMain.aCount++;

						}
						Task.sleep(1500, 2001);
					}
				}
			}

			/*
			 * if (Inventory.getItem(Storage.skeletalIds[0]) != null) {
			 * Widgets.get(548).getChild(165).interact("Cast"); if
			 * (Inventory.getItem(Storage.skeletalIds[0]).getWidgetChild()
			 * .click(true)) { System.out.println("Click success");
			 * WallasalkisMain.bankedGloves++; Task.sleep(1500, 2001); } } else
			 * if (Inventory.getItem(Storage.skeletalIds[1]) != null) {
			 * Widgets.get(548).getChild(165).interact("Cast"); if
			 * (Inventory.getItem(Storage.skeletalIds[1]).getWidgetChild()
			 * .click(true)) { System.out.println("Click success");
			 * WallasalkisMain.bankedBoots++; Task.sleep(1500, 2001); } } else
			 * if (Inventory.getItem(Storage.seedIds[0]) != null) {
			 * Widgets.get(548).getChild(165).interact("Cast"); if
			 * (Inventory.getItem(Storage.seedIds[0]).getWidgetChild()
			 * .click(true)) { System.out.println("Click success");
			 * WallasalkisMain.bankedTorstol++; Task.sleep(1500, 2001); } } else
			 * if (Inventory.getItem(Storage.seedIds[1]) != null) {
			 * Widgets.get(548).getChild(165).interact("Cast"); if
			 * (Inventory.getItem(Storage.seedIds[1]).getWidgetChild()
			 * .click(true)) { System.out.println("Click success");
			 * WallasalkisMain.bankedSnapdragon++; Task.sleep(1500, 2001); } }
			 * else if (Inventory.getItem(Storage.seedIds[2]) != null) {
			 * Widgets.get(548).getChild(165).interact("Cast"); if
			 * (Inventory.getItem(Storage.seedIds[2]).getWidgetChild()
			 * .click(true)) { System.out.println("Click success");
			 * WallasalkisMain.bankedLantadyme++; Task.sleep(1500, 2001); } }
			 * else if (Inventory.getItem(Storage.seedIds[3]) != null) {
			 * Widgets.get(548).getChild(165).interact("Cast"); if
			 * (Inventory.getItem(Storage.seedIds[3]).getWidgetChild()
			 * .click(true)) { System.out.println("Click success");
			 * WallasalkisMain.bankedDwarfWeed++; Task.sleep(1500, 2001); } }
			 * else if (Inventory.getItem(Storage.herbIds[0]) != null) {
			 * Widgets.get(548).getChild(165).interact("Cast"); if
			 * (Inventory.getItem(Storage.herbIds[0]).getWidgetChild()
			 * .click(true)) { System.out.println("Click success");
			 * WallasalkisMain.lCount++; Task.sleep(1500, 2001); } } else if
			 * (Inventory.getItem(Storage.herbIds[1]) != null) {
			 * Widgets.get(548).getChild(165).interact("Cast"); if
			 * (Inventory.getItem(Storage.herbIds[1]).getWidgetChild()
			 * .click(true)) { System.out.println("Click success");
			 * WallasalkisMain.dCount++; Task.sleep(1500, 2001); } } else if
			 * (Inventory.getItem(Storage.herbIds[2]) != null) {
			 * Widgets.get(548).getChild(165).interact("Cast"); if
			 * (Inventory.getItem(Storage.herbIds[2]).getWidgetChild()
			 * .click(true)) { System.out.println("Click success");
			 * WallasalkisMain.rCount++; Task.sleep(1500, 2001); } } else if
			 * (Inventory.getItem(Storage.herbIds[3]) != null) {
			 * Widgets.get(548).getChild(165).interact("Cast"); if
			 * (Inventory.getItem(Storage.herbIds[3]).getWidgetChild()
			 * .click(true)) { System.out.println("Click success");
			 * WallasalkisMain.aCount++; Task.sleep(1500, 2001); } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
