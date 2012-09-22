package org.wallasalkis.strategies;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.tab.Prayer;
import org.powerbot.game.api.methods.tab.Skills;

public class YakHandler extends Strategy implements Runnable {
	public boolean validate() {
		return Prayer.getPoints() > 200
				&& (Skills.getLevel(Skills.RANGE) -
						Skills.getRealLevel(Skills.RANGE)) > 3;
	}

	@Override
	public void run() {
		
	}
}
