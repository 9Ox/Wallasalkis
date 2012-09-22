package org.wallasalkis.storage;

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
			skeletalIds = { 6153, 6147 }; //glove, boot
	
	public static Tile cannonTile = new Tile(3007, 3352, 0);
	
	public static int baseId = 6,
			pouchId = 12093,
			scrollId = 12435;
}
