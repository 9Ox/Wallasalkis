package wallasalkis.storage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.powerbot.game.api.wrappers.Tile;

public class Storage {
	public static int[] cannonIds = { 6,8,10,12 },
			prayerFlaskIds = { 23253,23251,23249,23247,23245,23243 }, //1,2,3,4,5,6
			prayerPotionIds = { 143,141,139,2434 }, //1,2,3,4
			renewalFlaskIds = { 23619,23617,23615,23613,23611,23609 }, //1,2,3,4,5,6
			renewalPotionIds = { 21636,21534,21632,21630 }, //1,2,3,4
			teleTabIds = { 8007, 8008, 8009, 8010, 8011, 8013 },
			charmIds = { 12163,12160,12159,12158 }, //blue,crimson,green,gold
			runeIds = { 560, 565, 7937 }, //death, blood, ess
			skeletalIds = { 6153, 6147 }, //glove, boot
			rangingPotionIds = { 173, 171, 169, 2444 }, //1,2,3,4
			rangingFlaskIds = { 23313, 23311, 23309, 23307, 23305, 23303 }, //1,2,3,4,5,6
			extremeFlaskIds = { 23524, 23523, 23522, 23521, 23520, 23519 },
			extremePotionIds = { 15327, 15326, 15325, 15324 }, //1,2,3,4
			summoningPotionIds = { 12146, 12144, 12142, 12140 }, //1,2,3,4
			junkIds = { 556, 557, 555, 526 },
			seedIds = { 5304, 5300, 5302, 5304 }, //torstol, snapdragon, lantadyme, dwarfweed
			herbIds = { 2485, 217, 207, 211 }, //lantadyme, dwarfweed, ranarr, avantoe
			superPrayerFlaskIds = { 23525 },
			superPrayerPotionIds = { 15328 },
			superStrengthIds = { 161, 159, 157, 2440 },
			superAttackIds = { 149, 147, 145, 2436 };
	
	public static Tile cannonTile = new Tile(1802, 4372, 1),
			cannonTile2 = new Tile(1798,4378,1);
			//cannonTile = new Tile(1802, 4372, 1)
	
	public static int baseId = 6,
			pouchId = 12093,
			scrollId = 12435;
	
	public static void drawTile(Tile loc, Color c, Graphics g) {
		g.setColor(c);
		if (loc != null) {
			for (Polygon p : loc.getBounds()) {
				g.drawPolygon(p);
			}
		}
	}
	
	public static int getPrice(final int id) {
		try {
			final URL url = new URL(
					"http://open.tip.it/json/ge_single_item?item="
							.concat(Integer.toString(id)));
			final BufferedReader reader = new BufferedReader(
					new InputStreamReader(url.openStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains("mark_price")) {
					reader.close();
					return Integer.parseInt(line.substring(
							line.indexOf("mark_price") + 13,
							line.indexOf(",\"daily_gp") - 1)
							.replaceAll(",", ""));
				}
			}
		} catch (final Exception e) {
			return -1;
		}
		return -1;
	}
	
	public static long startTime = System.currentTimeMillis();
	
	public static int getPerHour(final long value) {
		   return (int) (value * 3600000D / (System.currentTimeMillis() - startTime));
    }
}
