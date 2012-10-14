package de.neofonie.mobile.app.android.widget.crouton;


import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;


final class AnimationsBuilder
{
	private static final class SlideInDownAnimationParameters
	{
		private SlideInDownAnimationParameters() {
		}

		public static final float FROM_X_DELTA = 0;
		public static final float TO_X_DELTA = 0;
		public static final float FROM_Y_DELTA = -50;
		public static final float TO_Y_DELTA = 0;

		public static final long DURATION = 400;
	}

	private static final class SlideOutUpAnimationParameters
	{
		private SlideOutUpAnimationParameters() {
		}

		public static final float FROM_X_DELTA = 0;
		public static final float TO_X_DELTA = 0;
		public static final float FROM_Y_DELTA = 0;
		public static final float TO_Y_DELTA = -50;

		public static final long DURATION = 400;
	}

	private AnimationsBuilder() {
	}

	public static Animation buildSlideInDownAnimation() {
		Animation slideInDownAnimation = new TranslateAnimation(
			SlideInDownAnimationParameters.FROM_X_DELTA, SlideInDownAnimationParameters.TO_X_DELTA,
			SlideInDownAnimationParameters.FROM_Y_DELTA, SlideInDownAnimationParameters.TO_Y_DELTA);
		slideInDownAnimation.setDuration(SlideInDownAnimationParameters.DURATION);

		return slideInDownAnimation;
	}

	public static Animation buildSlideOutUpAnimation() {
		Animation slideOutUpAnimation = new TranslateAnimation(
			SlideOutUpAnimationParameters.FROM_X_DELTA, SlideOutUpAnimationParameters.TO_X_DELTA,
			SlideOutUpAnimationParameters.FROM_Y_DELTA, SlideOutUpAnimationParameters.TO_Y_DELTA);
		slideOutUpAnimation.setDuration(SlideOutUpAnimationParameters.DURATION);

		return slideOutUpAnimation;
	}
}
