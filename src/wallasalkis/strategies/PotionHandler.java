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
		SceneObject cannon = SceneEntities.getNearest(Storage.baseId);
		if (WallasalkisMain.range) {
			return cannon != null
					&& Prayer.getPoints() > 155
					// Range
					&& ((Skills.getLevel(Skills.RANGE) - Skills
							.getRealLevel(Skills.RANGE)) <= 3)
					&& !WallasalkisMain.refreshCannon
					&& (Inventory.containsOneOf(Storage.extremeFlaskIds)
							|| Inventory
									.containsOneOf(Storage.extremePotionIds)
							|| Inventory.containsOneOf(Storage.rangingFlaskIds) || Inventory
								.containsOneOf(Storage.rangingPotionIds));
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
					&& (Inventory.containsOneOf(Storage.superAttackIds) || Inventory
							.containsOneOf(Storage.superStrengthIds));
		}
		return false;
	}

	@Override
	public void execute() {
		WallasalkisMain.s = "PotionHandler()";
		try {
			if (Inventory.getItem(Storage.extremeFlaskIds) != null
					&& Storage.levelMinusRealLevel(Skills.RANGE) <= 3) {
				Inventory.getItem(Storage.extremeFlaskIds).getWidgetChild()
						.click(true);
				Task.sleep(1000, 1501);
			} else if (Inventory.getItem(Storage.extremePotionIds) != null
					&& Storage.levelMinusRealLevel(Skills.RANGE) <= 3) {
				Inventory.getItem(Storage.extremePotionIds).getWidgetChild()
						.click(true);
				Task.sleep(1000, 1501);
			} else if (Inventory.getItem(Storage.rangingFlaskIds) != null
					&& Storage.levelMinusRealLevel(Skills.RANGE) <= 3) {
				Inventory.getItem(Storage.rangingFlaskIds).getWidgetChild()
						.click(true);
				Task.sleep(1000, 1501);
			} else if (Inventory.getItem(Storage.rangingPotionIds) != null
					&& Storage.levelMinusRealLevel(Skills.RANGE) <= 3) {
				Inventory.getItem(Storage.rangingPotionIds).getWidgetChild()
						.click(true);
				Task.sleep(1000, 1501);
			} else if (Inventory.getItem(Storage.superAttackIds) != null
					&& Storage.levelMinusRealLevel(Skills.ATTACK) <= 3) {
				Inventory.getItem(Storage.superAttackIds).getWidgetChild()
						.click(true);
				Task.sleep(1000, 1501);
			} else if (Inventory.getItem(Storage.superStrengthIds) != null
					&& Storage.levelMinusRealLevel(Skills.STRENGTH) <= 3) {
				Inventory.getItem(Storage.superStrengthIds).getWidgetChild()
						.click(true);
				Task.sleep(1000, 1501);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
