package wallasalkis.strategies;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.node.SceneObject;

import wallasalkis.main.WallasalkisMain;
import wallasalkis.storage.Storage;

public class PotionHandler extends Node {
	@Override
	public boolean activate() {
		SceneObject cannon = SceneEntities.getNearest(Storage.BASE_ID);
		if (WallasalkisMain.range) {
			return cannon != null
					&& Prayer.getPoints() > 155
					// Range
					&& ((Skills.getLevel(Skills.RANGE) - Skills
							.getRealLevel(Skills.RANGE)) <= 3)
					&& !WallasalkisMain.refreshCannon
					&& (Inventory.containsOneOf(Storage.EXTREME_FLASK_IDS)
							|| Inventory
									.containsOneOf(Storage.EXTREME_POTION_IDS)
							|| Inventory.containsOneOf(Storage.RANGING_FLASK_IDS) || Inventory
								.containsOneOf(Storage.RANGING_POTION_IDS));
		} else if (WallasalkisMain.melee) {
			return cannon != null
					&& Prayer.getPoints() > 155
					// Attack
					&& (Skills.getLevel(Skills.ATTACK)
							- Skills.getRealLevel(Skills.ATTACK) <= 3
					// Strength
					|| Skills.getLevel(Skills.STRENGTH)
							- Skills.getRealLevel(Skills.STRENGTH) <= 3)
					&& !WallasalkisMain.refreshCannon
					&& (Inventory.containsOneOf(Storage.SUPER_ATTACK_IDS) || Inventory
							.containsOneOf(Storage.SUPER_STRENGTH_IDS));
		}
		return false;
	}

	@Override
	public void execute() {
		WallasalkisMain.s = "PotionHandler()";
		try {
			if (Inventory.getItem(Storage.EXTREME_FLASK_IDS) != null
					&& Storage.getLevelBoost(Skills.RANGE) <= 3) {
				Inventory.getItem(Storage.EXTREME_FLASK_IDS).getWidgetChild()
						.click(true);
				Task.sleep(1000, 1501);
			} else if (Inventory.getItem(Storage.EXTREME_POTION_IDS) != null
					&& Storage.getLevelBoost(Skills.RANGE) <= 3) {
				Inventory.getItem(Storage.EXTREME_POTION_IDS).getWidgetChild()
						.click(true);
				Task.sleep(1000, 1501);
			} else if (Inventory.getItem(Storage.RANGING_FLASK_IDS) != null
					&& Storage.getLevelBoost(Skills.RANGE) <= 3) {
				Inventory.getItem(Storage.RANGING_FLASK_IDS).getWidgetChild()
						.click(true);
				Task.sleep(1000, 1501);
			} else if (Inventory.getItem(Storage.RANGING_POTION_IDS) != null
					&& Storage.getLevelBoost(Skills.RANGE) <= 3) {
				Inventory.getItem(Storage.RANGING_POTION_IDS).getWidgetChild()
						.click(true);
				Task.sleep(1000, 1501);
			} else if (Inventory.getItem(Storage.SUPER_ATTACK_IDS) != null
					&& Storage.getLevelBoost(Skills.ATTACK) <= 3) {
				Inventory.getItem(Storage.SUPER_ATTACK_IDS).getWidgetChild()
						.click(true);
				Task.sleep(1000, 1501);
			} else if (Inventory.getItem(Storage.SUPER_STRENGTH_IDS) != null
					&& Storage.getLevelBoost(Skills.STRENGTH) <= 3) {
				Inventory.getItem(Storage.SUPER_STRENGTH_IDS).getWidgetChild()
						.click(true);
				Task.sleep(1000, 1501);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
