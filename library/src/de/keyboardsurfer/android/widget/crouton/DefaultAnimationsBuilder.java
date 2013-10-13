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

/** Builds the default animations for showing and hiding a {@link Crouton}. */
final class DefaultAnimationsBuilder {
  private static final long DURATION = 400;
  private static Animation slideInDownAnimation, slideOutUpAnimation;
  private static int lastInAnimationHeight, lastOutAnimationHeight;

  private DefaultAnimationsBuilder() {
    /* no-op */
  }

  /**
   * @param croutonView
   *   The croutonView which gets animated.
   *
   * @return The default Animation for a showing {@link Crouton}.
   */
  static Animation buildDefaultSlideInDownAnimation(View croutonView) {
    return buildDefaultSlideInDownAnimation(croutonView, false);
  }
  
  /**
   * @param croutonView
   *   The croutonView which gets animated.
   * @param isDownUnder
   *   True if the Crouton should animate in and out opposite to the "normal" way.
   *   This will in fact yield a Crouton that animates-in in an upwardly fashion,
   *   animates out in a downward fashion, and thus behaves more like its real-world
   *   counterpart bobbing up in a bowl of soup and then sinking down. Also note that
   *   water spins in the opposite direction in the southern hemisphere.
   *
   * @return The default Animation for a showing {@link Crouton}.
   */
  static Animation buildDefaultSlideInDownAnimation(View croutonView, boolean isDownUnder) {
    if (!areLastMeasuredInAnimationHeightAndCurrentEqual(croutonView) || (null == slideInDownAnimation)) {
    	int directionAdjust = (isDownUnder)? 1 : -1;
      slideInDownAnimation = new TranslateAnimation(
        0, 0,                               // X: from, to
        directionAdjust * croutonView.getMeasuredHeight(), 0 // Y: from, to
      );
      slideInDownAnimation.setDuration(DURATION);
      setLastInAnimationHeight(croutonView.getMeasuredHeight());
    }
    return slideInDownAnimation;
  }

  /**
   * @param croutonView
   *   The croutonView which gets animated.
   *
   * @return The default Animation for a hiding {@link Crouton}.
   */
  static Animation buildDefaultSlideOutUpAnimation(View croutonView) {
    return buildDefaultSlideOutUpAnimation(croutonView, false);
  }
  
  /**
   * @param croutonView
   *   The croutonView which gets animated.
   *
   * @return The default Animation for a hiding {@link Crouton}.
   */
  static Animation buildDefaultSlideOutUpAnimation(View croutonView, boolean isDownUnder) {
    if (!areLastMeasuredOutAnimationHeightAndCurrentEqual(croutonView) || (null == slideOutUpAnimation)) {
    	int directionAdjust = (isDownUnder)? 1 : -1;
      slideOutUpAnimation = new TranslateAnimation(
        0, 0,                               // X: from, to
        0, directionAdjust * croutonView.getMeasuredHeight() // Y: from, to
      );
      slideOutUpAnimation.setDuration(DURATION);
      setLastOutAnimationHeight(croutonView.getMeasuredHeight());
    }
    return slideOutUpAnimation;
  }

  private static boolean areLastMeasuredInAnimationHeightAndCurrentEqual(View croutonView) {
    return areLastMeasuredAnimationHeightAndCurrentEqual(lastInAnimationHeight, croutonView);
  }

  private static boolean areLastMeasuredOutAnimationHeightAndCurrentEqual(View croutonView) {
    return areLastMeasuredAnimationHeightAndCurrentEqual(lastOutAnimationHeight, croutonView);
  }

  private static boolean areLastMeasuredAnimationHeightAndCurrentEqual(int lastHeight, View croutonView) {
    return lastHeight == croutonView.getMeasuredHeight();
  }

  private static void setLastInAnimationHeight(int lastInAnimationHeight) {
    DefaultAnimationsBuilder.lastInAnimationHeight = lastInAnimationHeight;
  }

  private static void setLastOutAnimationHeight(int lastOutAnimationHeight) {
    DefaultAnimationsBuilder.lastOutAnimationHeight = lastOutAnimationHeight;
  }
}
