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
				&& Inventory.getItem(Storage.SCROLL_ID) != null
				&& WallasalkisMain.usingYak
				&& SceneEntities.getNearest(Storage.BASE_ID) != null
				&& (Inventory.containsOneOf(Storage.SKELETAL_IDS)
						|| Inventory.containsOneOf(Storage.SEED_IDS) || Inventory
							.containsOneOf(Storage.HERB_IDS))) 
							|| (!Summoning.isFamiliarSummoned()
									&& WallasalkisMain.usingYak);
	}

	@Override
	public void execute() {
		WallasalkisMain.s = "YakHandler";
		if (!Summoning.isFamiliarSummoned() && !Inventory.contains(Storage.POUCH_ID)) {
			WallasalkisMain.usingYak = false;
		}
		if (!Summoning.getLeftClickOption().equals(Summoning.Option.CAST)) {
			Summoning.setLeftClickOption(Summoning.Option.CAST);
			Task.sleep(600, 1001);
		}
		try {
			if (!Summoning.isFamiliarSummoned()) {
				if (Inventory.getItem(Storage.SUMMONING_POTION_IDS) != null
						&& Skills.getLevel(Skills.SUMMONING) < 30) {
					System.out
							.println("Points below 30 and yak needs a refresh, drinking potion");
					Inventory.getItem(Storage.SUMMONING_POTION_IDS)
							.getWidgetChild().click(true);
					Task.sleep(600,1001);
				}
				if (Inventory.getItem(Storage.POUCH_ID) != null) {
					Inventory.getItem(Storage.POUCH_ID).getWidgetChild()
							.interact("Summon");
					Task.sleep(1000, 1501);
				}
			}
			for (int i = 0; i < Storage.SKELETAL_IDS.length; i++) {
				if (Inventory.getItem(Storage.SKELETAL_IDS[i]) != null) {
					Widgets.get(548).getChild(165).interact("Cast");
					if (Inventory.getItem(Storage.SKELETAL_IDS[i])
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
			for (int i = 0; i < Storage.SEED_IDS.length; i++) {
				if (Inventory.getItem(Storage.SEED_IDS[i]) != null) {
					Widgets.get(548).getChild(165).interact("Cast");
					if (Inventory.getItem(Storage.SEED_IDS[i]).getWidgetChild()
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
			for (int i = 0; i < Storage.HERB_IDS.length; i++) {
				if (Inventory.getItem(Storage.HERB_IDS[i]) != null) {
					Widgets.get(548).getChild(165).interact("Cast");
					if (Inventory.getItem(Storage.HERB_IDS[i]).getWidgetChild()
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
          } catch (Exception e) {
			e.printStackTrace();
		}
	}
}
