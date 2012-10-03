package wallasalkis.storage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.WidgetComposite;
import org.powerbot.game.api.util.internal.Multipliers;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.RegionOffset;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import org.powerbot.game.bot.Context;
import org.powerbot.game.client.Client;

public class Storage {
	public final static int[] cannonIds = { 6,8,10,12 },
			prayerFlaskIds = { 23253,23251,23249,23247,23245,23243 }, //1,2,3,4,5,6
			prayerPotionIds = { 143,141,139,2434 }, //1,2,3,4
			renewalFlaskIds = { 23619,23617,23615,23613,23611,23609 }, //1,2,3,4,5,6
			renewalPotionIds = { 21636,21534,21632,21630 }, //1,2,3,4
			teleTabIds = { 8007, 8008, 8009, 8010, 8011, 8013 },
			charmIds = { 12163,12160,12159,12158 }, //blue,crimson,green,gold
			runeIds = { 560, 565, 7937 }, //death, blood, ess
			skeletalIds = { 6153, 6147, 6163 }, //glove, boot, skull
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
			superStrengthIds = { 161, 159, 157, 2440 }, //1,2,3,4
			superAttackIds = { 149, 147, 145, 2436 }; //1,2,3,4
	
	public static final int baseId = 6,
			pouchId = 12093,
			scrollId = 12435,
			spinTicketId = 24154;
	
	public static final Tile cannonTile1 = new Tile(1802, 4372, 1),
			cannonTile2 = new Tile(1806, 4392, 1);
			//cannonTile = new Tile(1802, 4372, 1)
	
	public static Tile tile;
	
	public static Area area;
	
	public static final Area room1 = new Area(new Tile(1807, 4365, 1),
				new Tile(1807, 4385, 1), new Tile(1793, 4385, 1),
				new Tile(1793, 4365, 1), new Tile(1807, 4365, 1)),
				
			room2 = new Area(new Tile(1814, 4398, 1), new Tile(1796, 4398, 1),
					new Tile(1796, 4387, 1), new Tile(1814, 4387, 1),
					new Tile(1814, 4398, 1));
	
	public static boolean inArea(Tile t, Area a) {
		return a.contains(t);
	}
	
	public static void drawTile(Tile loc, Color c, Graphics g) {
		g.setColor(c);
		if (loc != null) {
			for (Polygon p : loc.getBounds()) {
				g.drawPolygon(p);
			}
		}
	}
	
	public static void areaToMinimap(Area a, Color c, Graphics g) {
		g.setColor(c);
		Polygon p = new Polygon();
		if (a != null) {
			for (Tile t : a.getBoundingTiles()) {
				Point onMap = worldToMap(t.getX(), t.getY());
				p.addPoint(onMap.x, onMap.y);
			}
			g.drawPolygon(p);
		}
	}

    public static int getLevelBoost(int skill) {
        return Skills.getLevel(skill) - Skills.getRealLevel(skill);
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

	public static long getPerHour(final long value, final long startTime) {
		return (long)(value * 3600000D / (System.currentTimeMillis() - startTime));
    }
	
	public static Point worldToMap(double x, double y) {
		final Client client = Context.client();
		final Multipliers multipliers = Context.multipliers();
		final Player local = Players.getLocal();
		x -= Game.getBaseX();
		y -= Game.getBaseY();

		final RegionOffset localTile = local.getRegionOffset();
		final int calculatedX = (int) (x * 4 + 2) - (localTile.getX() << 9) / 0x80;
		final int calculatedY = (int) (y * 4 + 2) - (localTile.getY() << 9) / 0x80;

		final WidgetChild mm2 = WidgetComposite.getMap();
		if (mm2 == null) {
			return new Point(-1, -1);
		}

		int angle = 0x3fff & (int) client.getMinimapAngle();
		final boolean setting4 = client.getMinimapSetting() * multipliers.GLOBAL_MINIMAPSETTING == 4;

		if (!setting4) {
			angle = 0x3fff & (client.getMinimapOffset() * multipliers.GLOBAL_MINIMAPOFFSET) + (int) client.getMinimapAngle();
		}

		int cs = Calculations.SIN_TABLE[angle];
		int cc = Calculations.COS_TABLE[angle];

		if (!setting4) {
			final int fact = 0x100 + (client.getMinimapScale() * multipliers.GLOBAL_MINIMAPSCALE);
			cs = 0x100 * cs / fact;
			cc = 0x100 * cc / fact;
		}

		final int calcCenterX = cc * calculatedX + cs * calculatedY >> 0xf;
		final int calcCenterY = cc * calculatedY - cs * calculatedX >> 0xf;
		final int screen_x = calcCenterX + mm2.getAbsoluteX() + mm2.getWidth() / 2;
		final int screen_y = -calcCenterY + mm2.getAbsoluteY() + mm2.getHeight() / 2;
		return new Point(screen_x, screen_y);
	}
}
