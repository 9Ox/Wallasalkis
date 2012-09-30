package wallasalkis.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;


import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.LoopTask;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;

import wallasalkis.storage.Storage;
import wallasalkis.strategies.*;

@Manifest(name = "Auto Wallasalkis BETA", description = "Kills wallasalkis for charms.", version = 0.14, 
		authors = {"TaylorSwift", "Jdog653"},
		website = "http://www.powerbot.org/community/topic/807819-auto-wallasalkis-beta-80k-range-xp-per-hour-130-charms-per-hour-up-to-1m-cash-per-hour-with-yak/")
public class WallasalkisMain extends ActiveScript implements PaintListener {
	private final Tree scriptTree = new Tree(new Node[] {
			new RefreshCannon(), new Teleport(),
			new PlaceCannon(), new LoadCannon(),
			new PrayerHandler(), new PotionHandler(),
			new YakHandler(), new Loot()
	});
	
	public static int renewal = 0, refresh = 0, loadCannon = 50,
			bankedGloves = 0, bankedBoots = 0, bankedTorstol = 0,
			bankedLantadyme = 0, bankedSnapdragon = 0, bankedDwarfWeed = 0,
			lCount = 0, dCount = 0, rCount = 0, aCount = 0;

	private int startExp, gainedExp, goldCount, greenCount, crimsonCount,
			blueCount, charmCount, bootCount, gloveCount, bloodRuneCount,
			deathRuneCount, essCount, profit, bloodPrice, deathPrice, essPrice,
			glovePrice, bootPrice, lPrice, dPrice, rPrice, aPrice,
			tseedPrice, sseedPrice, lseedPrice, dseedPrice;

	public static boolean refreshCannon = false, usingYak = false,
			noPrayers = false, melee = false, range = false;

	long startTime = System.currentTimeMillis();
	
	public static String s = "";

	public void onStart() {
		if (Inventory.containsOneOf(Storage.rangingFlaskIds) || Inventory.containsOneOf(Storage.rangingPotionIds)) {
			range = true;
		} else if (Inventory.containsOneOf(Storage.superAttackIds) || Inventory.containsOneOf(Storage.superStrengthIds)) {
			melee = true;
		}
		if (Inventory.contains(Storage.scrollId)) {
			usingYak = true;
		}
		WallasalkisMain.this.getContainer().submit(new LoopTask() {
			@Override
			public int loop() {
				refresh++;
				return 60000;
			}
		});
		WallasalkisMain.this.getContainer().submit(new LoopTask() {
			@Override
			public int loop() {
				renewal++;
				loadCannon++;
				return 1000;
			}
		});
		WallasalkisMain.this.getContainer().submit(new LoopTask() {
			@Override
			public int loop() {
				gainedExp = Skills.getExperience(Skills.RANGE) - startExp;
				profit = (bloodPrice * bloodRuneCount)
						+ (deathPrice * deathRuneCount) + (essPrice * essCount)
						+ (glovePrice * gloveCount) + (bootPrice * bootCount)
						+ (lCount * lPrice) + (dCount + dPrice) + (rCount * rPrice)
						+ (aCount * aPrice) + (bankedTorstol * tseedPrice)
						+ (bankedSnapdragon * sseedPrice) + (bankedDwarfWeed * dseedPrice)
						+ (bankedLantadyme * lseedPrice);
				charmCount = goldCount + greenCount + crimsonCount + blueCount;
				if (Inventory.getItem(Storage.charmIds[0]) != null) {
					blueCount = Inventory.getCount(true, Storage.charmIds[0]);
				}
				if (Inventory.getItem(Storage.charmIds[1]) != null) {
					crimsonCount = Inventory.getCount(true, Storage.charmIds[1]);
				}
				if (Inventory.getItem(Storage.charmIds[2]) != null) {
					greenCount = Inventory.getCount(true, Storage.charmIds[2]);
				}
				if (Inventory.getItem(Storage.charmIds[3]) != null) {
					goldCount = Inventory.getCount(true, Storage.charmIds[3]);
				}
				if (Inventory.getItem(Storage.runeIds[0]) != null) {
					deathRuneCount = Inventory.getCount(true, Storage.runeIds[0]);
				}
				if (Inventory.getItem(Storage.runeIds[1]) != null) {
					bloodRuneCount = Inventory.getCount(true, Storage.runeIds[1]);
				}
				if (Inventory.getItem(Storage.runeIds[2]) != null) {
					essCount = Inventory.getCount(true, Storage.runeIds[2]);
				}
				gloveCount = bankedGloves;
				bootCount = bankedBoots;
				return 0;
			}
		});
		// Informs the user that the script has successfully started
		log.info("Wallsalkis BETA 0.14 started");
		log.info("If you experience any bugs, please report them on the script thread");

		// Stores prices
		deathPrice = Storage.getPrice(Storage.runeIds[0]);
		bloodPrice = Storage.getPrice(Storage.runeIds[1]);
		essPrice = Storage.getPrice(7936);
		glovePrice = Storage.getPrice(Storage.skeletalIds[0]);
		bootPrice = Storage.getPrice(Storage.skeletalIds[1]);
		lPrice = Storage.getPrice(Storage.herbIds[0]); 
		dPrice = Storage.getPrice(Storage.herbIds[1]); 
		rPrice = Storage.getPrice(Storage.herbIds[2]);
		aPrice = Storage.getPrice(Storage.herbIds[3]);
		tseedPrice = Storage.getPrice(Storage.seedIds[0]); 
		sseedPrice = Storage.getPrice(Storage.seedIds[1]);
		lseedPrice = Storage.getPrice(Storage.seedIds[2]);
		dseedPrice = Storage.getPrice(Storage.seedIds[3]); 

		// Gets the starting xp to track xp gained
		startExp = Skills.getExperience(Skills.RANGE);

		// Checks for renewal potions, if they exist,
		// start a new renewal timer and drink the renewal flask/potion
		if (Inventory.getItem(Storage.renewalFlaskIds) != null) {
			Inventory.getItem(Storage.renewalFlaskIds).getWidgetChild()
					.click(true);
			System.out.println("Renewals detected, starting timer");
		} else if (Inventory.getItem(Storage.renewalPotionIds) != null) {
			Inventory.getItem(Storage.renewalPotionIds).getWidgetChild()
					.click(true);
			System.out.println("Renewals detected, starting timer");
		}
		if (!Inventory.containsOneOf(Storage.prayerFlaskIds) 
				&& !Inventory.containsOneOf(Storage.prayerPotionIds)) {
			noPrayers = true;
			log.info("noprayers=true");
		}
	}

	// 4,54
	Color fadeBlack = new Color(0, 0, 0, 150);
	Font paintFont = new Font("Arial", 0, 10);

	@Override
	public void onRepaint(Graphics g1) {
		try {
		long millis = System.currentTimeMillis() - startTime;
		long hours = millis / (1000 * 60 * 60);
		millis -= hours * (1000 * 60 * 60);
		long minutes = millis / (1000 * 60);
		millis -= minutes * (1000 * 60);
		long seconds = millis / 1000;
		int x = Mouse.getX(), y = Mouse.getY();
		int px = Mouse.getPressX(), py = Mouse.getPressY();
		Graphics2D g = (Graphics2D) g1;
		if (Storage.cannonTile != null) {
			Storage.drawTile(Storage.cannonTile, Color.BLUE, g);
		}
		if (Mouse.isPressed()) {
			g.setColor(Color.RED);
			g.drawLine(px - 5, py + 5, px + 5, py - 5);
			g.drawLine(px + 5, py + 5, px - 5, py - 5);
		} else {
			g.setColor(Color.YELLOW);
			g.drawLine(x - 5, y + 5, x + 5, y - 5);
			g.drawLine(x + 5, y + 5, x - 5, y - 5);
		}
		g.setColor(fadeBlack);
		g.fillRect(4, 54, 115, 150);
		g.setColor(Color.WHITE);
		g.setFont(paintFont);
		g.drawString("Auto Wallasalkis BETA", 5, 64);
		g.drawString("__________________", 5, 67);
		g.drawString("Gloves: " + gloveCount, 5, 80); // gloveCount
		g.drawString("Boots: " + bootCount, 5, 90); // bootCount
		g.drawString(
				"Charms: " + charmCount + " (" + Storage.getPerHour(charmCount, startTime)
						+ ")", 5, 100);
		g.drawString("Blood runes: " + bloodRuneCount, 5, 110);
		g.drawString("Death runes: " + deathRuneCount, 5, 120);
		g.drawString("Pure essence: " + essCount, 5, 130);
		g.drawString("Profit: " + profit, 5, 140);
		g.drawString("Profit /h: " + Storage.getPerHour(profit, startTime), 5, 150);
		g.drawString("Range xp: " + gainedExp, 5, 160);
		g.drawString("Range xp /h: " + Storage.getPerHour(gainedExp, startTime), 5, 170);
		g.drawString("Decay timer: " + (18 - refresh) + " minutes", 5, 180);
		g.drawString("Node: " + s, 5, 190);
		g.drawString("Timer: " + hours + ":" + minutes + ":" + seconds, 5, 201);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public int loop() {
		final Node stateNode = scriptTree.state();
		if (stateNode != null) {
			scriptTree.set(stateNode);
			final Node setNode = scriptTree.get();
			if (setNode != null) {
				getContainer().submit(setNode);
				setNode.join();
			}
		}
		return Random.nextInt(100, 200);
	}
}
