package wallasalkis.strategies;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.node.SceneObject;

import wallasalkis.main.WallasalkisMain;
import wallasalkis.storage.Storage;

public class YakHandler extends Node {
	@Override
	public boolean activate() {
		SceneObject cannon = SceneEntities.getNearest(Storage.baseId);
		return Prayer.getPoints() > 155 && !WallasalkisMain.refreshCannon
				&& Inventory.getItem(Storage.scrollId) != null
				&& cannon != null
				&& (Inventory.containsOneOf(Storage.skeletalIds)
						|| Inventory.containsOneOf(Storage.seedIds)
						|| Inventory.containsOneOf(Storage.herbIds));
	}

	@Override
	public void execute() {
		WallasalkisMain.s = "YakHandler()";
		try {
			WallasalkisMain.usingYak = true;
			if (Settings.get(448) == -1) {
				if (Inventory.getItem(Storage.summoningPotionIds) != null
						&& Skills.getLevel(Skills.SUMMONING) < 30) {
					System.out
							.println("Points below 30 and yak needs a refresh, drinking potion");
					Inventory.getItem(Storage.summoningPotionIds)
							.getWidgetChild().click(true);
				}
				if (Inventory.getItem(Storage.pouchId) != null) {
					Inventory.getItem(Storage.pouchId).getWidgetChild()
							.interact("Summon");
					Task.sleep(1000, 1501);
				}
			}
			if (Inventory.getItem(Storage.skeletalIds[0]) != null) {
				Widgets.get(548).getChild(165).interact("Cast");
				if (Inventory.getItem(Storage.skeletalIds[0]).getWidgetChild()
						.click(true)) {
					System.out.println("Click success");
					WallasalkisMain.bankedGloves++;
					Task.sleep(1500, 2001);
				}
			} else if (Inventory.getItem(Storage.skeletalIds[1]) != null) {
				Widgets.get(548).getChild(165).interact("Cast");
				if (Inventory.getItem(Storage.skeletalIds[1]).getWidgetChild()
						.click(true)) {
					System.out.println("Click success");
					WallasalkisMain.bankedBoots++;
					Task.sleep(1500, 2001);
				}
			} else if (Inventory.getItem(Storage.seedIds[0]) != null) {
				Widgets.get(548).getChild(165).interact("Cast");
				if (Inventory.getItem(Storage.seedIds[0]).getWidgetChild()
						.click(true)) {
					System.out.println("Click success");
					WallasalkisMain.bankedTorstol++;
					Task.sleep(1500, 2001);
				}
			} else if (Inventory.getItem(Storage.seedIds[1]) != null) {
				Widgets.get(548).getChild(165).interact("Cast");
				if (Inventory.getItem(Storage.seedIds[1]).getWidgetChild()
						.click(true)) {
					System.out.println("Click success");
					WallasalkisMain.bankedSnapdragon++;
					Task.sleep(1500, 2001);
				}
			} else if (Inventory.getItem(Storage.seedIds[2]) != null) {
				Widgets.get(548).getChild(165).interact("Cast");
				if (Inventory.getItem(Storage.seedIds[2]).getWidgetChild()
						.click(true)) {
					System.out.println("Click success");
					WallasalkisMain.bankedLantadyme++;
					Task.sleep(1500, 2001);
				}
			} else if (Inventory.getItem(Storage.seedIds[3]) != null) {
				Widgets.get(548).getChild(165).interact("Cast");
				if (Inventory.getItem(Storage.seedIds[3]).getWidgetChild()
						.click(true)) {
					System.out.println("Click success");
					WallasalkisMain.bankedDwarfWeed++;
					Task.sleep(1500, 2001);
				}
			} else if (Inventory.getItem(Storage.herbIds[0]) != null) {
				Widgets.get(548).getChild(165).interact("Cast");
				if (Inventory.getItem(Storage.herbIds[0]).getWidgetChild()
						.click(true)) {
					System.out.println("Click success");
					WallasalkisMain.lCount++;
					Task.sleep(1500, 2001);
				}
			} else if (Inventory.getItem(Storage.herbIds[1]) != null) {
				Widgets.get(548).getChild(165).interact("Cast");
				if (Inventory.getItem(Storage.herbIds[1]).getWidgetChild()
						.click(true)) {
					System.out.println("Click success");
					WallasalkisMain.dCount++;
					Task.sleep(1500, 2001);
				}
			} else if (Inventory.getItem(Storage.herbIds[2]) != null) {
				Widgets.get(548).getChild(165).interact("Cast");
				if (Inventory.getItem(Storage.herbIds[2]).getWidgetChild()
						.click(true)) {
					System.out.println("Click success");
					WallasalkisMain.rCount++;
					Task.sleep(1500, 2001);
				}
			} else if (Inventory.getItem(Storage.herbIds[3]) != null) {
				Widgets.get(548).getChild(165).interact("Cast");
				if (Inventory.getItem(Storage.herbIds[3]).getWidgetChild()
						.click(true)) {
					System.out.println("Click success");
					WallasalkisMain.aCount++;
					Task.sleep(1500, 2001);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}