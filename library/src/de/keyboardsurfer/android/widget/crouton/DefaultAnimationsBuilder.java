/*
 * Copyright 2012 - 2013 Benjamin Weiss
 * Copyright 2012 Neofonie Mobile GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.keyboardsurfer.android.widget.crouton;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Builds the default animations for showing and hiding a {@link Crouton}.
 */
final class DefaultAnimationsBuilder {
  private static Animation slideInDownAnimation, slideOutUpAnimation;

  protected static final class SlideInDownAnimationParameters {
    private SlideInDownAnimationParameters() {
      /* no-op */
    }

    public static final float FROM_X_DELTA = 0;
    public static final float TO_X_DELTA = 0;
    public static final float FROM_Y_DELTA = -50;
    public static final float TO_Y_DELTA = 0;

    public static final long DURATION = 400;
  }

  protected static final class SlideOutUpAnimationParameters {
    private SlideOutUpAnimationParameters() {
      /* no-op */
    }

    public static final float FROM_X_DELTA = 0;
    public static final float TO_X_DELTA = 0;
    public static final float FROM_Y_DELTA = 0;
    public static final float TO_Y_DELTA = -50;

    public static final long DURATION = 400;
  }

  private DefaultAnimationsBuilder() {
    /* no-op */
  }

  /**
   * @return The default Animation for a showing {@link Crouton}.
   */
  public static Animation buildDefaultSlideInDownAnimation() {
    if (null == slideInDownAnimation) {
      slideInDownAnimation = new TranslateAnimation(SlideInDownAnimationParameters.FROM_X_DELTA,
        SlideInDownAnimationParameters.TO_X_DELTA,
        SlideInDownAnimationParameters.FROM_Y_DELTA, SlideInDownAnimationParameters.TO_Y_DELTA);
      slideInDownAnimation.setDuration(SlideInDownAnimationParameters.DURATION);
    }

    return slideInDownAnimation;
  }

  /**
   * @return The default Animation for a hiding {@link Crouton}.
   */
  public static Animation buildDefaultSlideOutUpAnimation() {
    if (null == slideOutUpAnimation) {
      slideOutUpAnimation = new TranslateAnimation(SlideOutUpAnimationParameters.FROM_X_DELTA,
        SlideOutUpAnimationParameters.TO_X_DELTA,
        SlideOutUpAnimationParameters.FROM_Y_DELTA, SlideOutUpAnimationParameters.TO_Y_DELTA);
      slideOutUpAnimation.setDuration(SlideOutUpAnimationParameters.DURATION);
    }
    return slideOutUpAnimation;
  }
}
