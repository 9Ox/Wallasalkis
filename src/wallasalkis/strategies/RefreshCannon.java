package wallasalkis.strategies;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;

import wallasalkis.main.WallasalkisMain;
import wallasalkis.storage.Storage;

public class RefreshCannon extends Node {
	@Override
	public boolean activate() {
		return WallasalkisMain.refresh >= 18;
	}

	@Override
	public void execute() {
		SceneObject cannon = SceneEntities.getNearest(Storage.baseId);
		WallasalkisMain.refreshCannon = true;
		WallasalkisMain.s = "RefreshCannon()";
		if (cannon != null && cannon.isOnScreen())
        {
			if (Inventory.getCount() > 24)
            {
                while (Inventory.getCount() > 24)
                {
                    if (Inventory.getItem(Storage.prayerFlaskIds) != null)
                    {
                        Inventory.getItem(Storage.prayerFlaskIds)
                                .getWidgetChild().interact("Drop");
                    }
                    else if (Inventory.getItem(Storage.prayerPotionIds) != null)
                    {
                        Inventory.getItem(Storage.prayerPotionIds)
                                .getWidgetChild().interact("Drop");
                    }
                    else if (Inventory.containsOneOf(Storage.renewalFlaskIds))
                    {
                        Inventory.getItem(Storage.renewalFlaskIds)
                        .getWidgetChild().interact("Drop");
                    }
                    else if (Inventory.containsOneOf(Storage.renewalPotionIds))
                    {
                        Inventory.getItem(Storage.renewalPotionIds)
                        .getWidgetChild().interact("Drop");
                    }
                }
            }
            else
            {
                if (!Players.getLocal().isMoving())
                {
                    if (cannon.interact("Pick-up"))
                    {
                        Task.sleep(5000, 6001);
                        WallasalkisMain.refresh = 0;
                        WallasalkisMain.refreshCannon = false;
                    }
                }
            }
		}
        else
        {
			Walking.walk(Storage.cannonTile);
		}
	}
}
