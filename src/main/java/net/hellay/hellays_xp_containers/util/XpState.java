package net.hellay.hellays_xp_containers.util;

import net.minecraft.util.math.MathHelper;

public class XpState {
	private int experienceLevel;
	private float experienceProgress;
	private int totalExperience;

	public XpState() {
	}

	public XpState(int experiencePoints) {
		addExperience(experiencePoints);
	}

	public void addExperience(int xpPoints) {
		this.experienceProgress = this.experienceProgress + (float) xpPoints / (float) getNextLevelExperience(experienceLevel);
		this.totalExperience = MathHelper.clamp(this.totalExperience + xpPoints, 0, Integer.MAX_VALUE);

		while (this.experienceProgress < 0.0F) {
			float f = this.experienceProgress * (float) getNextLevelExperience(experienceLevel);
			if (this.experienceLevel > 0) {
				this.adjustExperienceLevels(-1);
				this.experienceProgress = 1.0F + f / (float) getNextLevelExperience(experienceLevel);
			} else {
				this.adjustExperienceLevels(-1);
				this.experienceProgress = 0.0F;
			}
		}

		while (this.experienceProgress >= 1.0F) {
			this.experienceProgress = (this.experienceProgress - 1.0F) * (float) getNextLevelExperience(experienceLevel);
			this.adjustExperienceLevels(1);
			this.experienceProgress = this.experienceProgress / (float) getNextLevelExperience(experienceLevel);
		}
	}

	private void adjustExperienceLevels(int levels) {
		this.experienceLevel += levels;
		if (this.experienceLevel < 0) {
			this.experienceLevel = 0;
			this.experienceProgress = 0.0F;
			this.totalExperience = 0;
		}
	}

	public static int getNextLevelExperience(int experienceLevel) {
		if (experienceLevel >= 30) {
			return 112 + (experienceLevel - 30) * 9;
		} else {
			return experienceLevel >= 15 ? 37 + (experienceLevel - 15) * 5 : 7 + experienceLevel * 2;
		}
	}

	public static int levelToPoints(int level) {
		XpState state = new XpState();
		for (int i = 0; i < level; i++) {
			state.addExperience(getNextLevelExperience(i));
		}
		return state.totalExperience;
	}

	public static float pointsToLevelsDecimal(int points) {
		var state = new XpState(points);
		return state.experienceLevel + state.experienceProgress;
	}

	public static int levelAndProgressToPoints(int level, float progress) {
		int levelPoints = levelToPoints(level);
		int experienceProgressPoints = Math.round(progress * getNextLevelExperience(level));
		return levelPoints + experienceProgressPoints;
	}
}
