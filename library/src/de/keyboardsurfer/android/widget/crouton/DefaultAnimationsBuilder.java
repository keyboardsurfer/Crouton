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

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Builds the default animations for showing and hiding a {@link Crouton}.
 */
final class DefaultAnimationsBuilder {
  private static final long DURATION = 400;
  private static Animation slideInDownAnimation, slideOutUpAnimation;

  private DefaultAnimationsBuilder() {
    /* no-op */
  }

  /**
   * @param croutonView
   *   The croutonView which gets animated.
   * @return The default Animation for a showing {@link Crouton}.
   */
  public static Animation buildDefaultSlideInDownAnimation(View croutonView) {
    if (null == slideInDownAnimation) {
      slideInDownAnimation = new TranslateAnimation(
        0, 0,                               // X: from, to
        -croutonView.getMeasuredHeight(), 0 // Y: from, to
      );
      slideInDownAnimation.setDuration(DURATION);
    }
    return slideInDownAnimation;
  }

  /**
   * @param croutonView
   *   The croutonView which gets animated.
   * @return The default Animation for a hiding {@link Crouton}.
   */
  public static Animation buildDefaultSlideOutUpAnimation(View croutonView) {
    if (null == slideOutUpAnimation) {
      slideOutUpAnimation = new TranslateAnimation(
        0, 0,                               // X: from, to
        0, -croutonView.getMeasuredHeight() // Y: from, to
      );
      slideOutUpAnimation.setDuration(DURATION);
    }
    return slideOutUpAnimation;
  }
}
