/*
 * Copyright 2012 Neofonie Mobile GmbH
 *	
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package de.neofonie.mobile.app.android.widget.crouton;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/*
 * Based on an article by Cyril Mottier (http://android.cyrilmottier.com/?p=773) <br>
 */

/**
 * Crouton <br>
 * <br>
 *
 * Displays information in a non-invasive context related manner. Like
 * {@link Toast}, but better.
 *
 * @author weiss@neofonie.de
 *
 */
public final class Crouton {
	private Activity activity;
	private View view;
	private CharSequence text;
	private Style style;

	/**
	 * Creates the {@link Crouton}.
	 *
	 * @param activity
	 *            The {@link Activity} that the {@link Crouton} should be
	 *            attached to.
	 * @param text
	 *            The text you want to display.
	 * @param style
	 *            The style that this {@link Crouton} should be created with.
	 */
	private Crouton(Activity activity, CharSequence text, Style style) {
		if (activity == null || text == null || style == null) {
			throw new IllegalArgumentException(
					"Null parameters are NOT accepted");
		}
		this.activity = activity;
		this.text = text;
		this.style = style;
	}

	// /////////////////////////////////////////////////////////////////////////
	// Public methods
	// /////////////////////////////////////////////////////////////////////////

	/**
	 * Creates a {@link Crouton} with provided text and style for a given
	 * activity.
	 *
	 * @param activity
	 *            The {@link Activity} that the {@link Crouton} should be
	 *            attached to.
	 * @param text
	 *            The text you want to display.
	 * @param style
	 *            The style that this {@link Crouton} should be created with.
	 * @return The created {@link Crouton}.
	 */

	public static Crouton makeText(Activity activity, CharSequence text,
			Style style) {
		return new Crouton(activity, text, style);
	}

	/**
	 * Creates a {@link Crouton} with provided text-resource and style for a
	 * given activity.
	 *
	 * @param activity
	 *            The {@link Activity} that the {@link Crouton} should be
	 *            attached to.
	 * @param resId
	 *            The resource id of the text you want to display.
	 * @param style
	 *            The style that this {@link Crouton} should be created with.
	 * @return The created {@link Crouton}.
	 */
	public static Crouton makeText(Activity activity, int resId, Style style) {
		return makeText(activity, activity.getString(resId), style);
	}

	/**
	 * Cancels all queued {@link Crouton}s. If there is a {@link Crouton}
	 * displayed currently, it will be the last one displayed.
	 */
	public static void cancelAllCroutons() {
		Manager.getInstance().clearCroutonQueue();
	}

    /**
     * Clears (and removes from {@link Activity}'s content view, if necessary) all croutons for the provided activity
     * @param activity - The {@link} Activity to clear the croutons for
     */
    public static void clearCroutonsForActivity(Activity activity) {
        Manager.getInstance().clearCroutonsForActivity(activity);
    }

	/**
	 * Displays the {@link Crouton}. If there's another {@link Crouton} visible
	 * at the time, this {@link Crouton} will be displayed afterwards.
	 */
	public void show() {
		Manager manager = Manager.getInstance();
		manager.add(this);
	}

	/**
	 * Cancels a {@link Crouton} immediately.
	 */
	public void cancel() {
		Manager manager = Manager.getInstance();
		manager.removeCroutonImmediately(this);
	}

	// /////////////////////////////////////////////////////////////////////////
	// Package private methods
	// /////////////////////////////////////////////////////////////////////////

	/**
	 * @return <code>true</code> if the {@link Crouton} is being displayed, else
	 *         <code>false</code>.
	 */
	boolean isShowing() {
		return activity != null && view != null && view.getParent() != null;
	}

	/**
	 * @return the activity
	 */
	Activity getActivity() {
		return activity;
	}

    /**
     * Removes the activity reference this {@link Crouton} is holding
     */
    void detachActivity() {
        activity = null;
    }

	/**
	 * @return the style
	 */
	Style getStyle() {
		return style;
	}

	/**
	 * @return the text
	 */
	CharSequence getText() {
		return text;
	}

	/**
	 * @return the view
	 */
	View getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	void setView(View view) {
		this.view = view;
	}

    /**
     * Style <br>
     * <br>
     *
     * The style for a {@link Crouton}.
     *
     * @author weiss@neofonie.de
     *
     */

    public static class Style {
        public static final Style ALERT = new Style(5000,
                android.R.color.holo_red_light,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        public static final Style CONFIRM = new Style(3000,
                android.R.color.holo_green_light,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        public static final Style INFO = new Style(3000,
                android.R.color.holo_blue_bright,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        /**
         * The duration the {@link Crouton} will be displayed in milliseconds.
         */
        int duration;
        /**
         * The background color's resource id
         * <p/>
         * 0 for transparent background
         */
        int backgroundColorResId;
        /**
         * The height of the {@link Crouton} in pixels.
         */
        int height;
        /**
         * The resource id of the background.
         * <p/>
         * 0 for no background.
         */
        int backgroundResId;
        /**
         * Whether we should tile the background or not.
         */
        boolean tile;
        /**
         * The text color's resource id.
         * <p/>
         * 0 sets the text color to the system theme default.
         */
        int textColorResId;
        /**
         * The text size in sp
         * <p/>
         * 0 sets the text size to the system theme default
         */
        int textSize;
        /**
         * The text shadow color's resource id
         */
        int textShadowColorResId;
        /**
         * The text shadow radius
         */
        float textShadowRadius;
        /**
         * The text shadow vertical offset
         */
        float textShadowDy;
        /**
         * The text shadow horizontal offset
         */
        float textShadowDx;
        /**
         * The text appearance resource id for the text.
         */
        int textAppearanceResId;
        /**
         * The resource id for the in animation
         */
        int inAnimationResId;
        /**
         * The resource id for the out animation
         */
        int outAnimationResId;

        // Constructor for builder
        private Style() {
        }

        /**
         * Creates a new {@link Crouton} with the provided parameters.
         *
         * @param duration             The duration the crouton will be displayed {@link Crouton} in milliseconds.
         * @param backgroundColorResId The background color's resource id.
         * @param height               The height of the {@link Crouton}. Either {@link android.view.ViewGroup.LayoutParams#MATCH_PARENT} or
         *                             {@link android.view.ViewGroup.LayoutParams#WRAP_CONTENT}.
         */
        public Style(int duration, int backgroundColorResId, int height) {
            this(duration, backgroundColorResId, height, 0, false, 0);
        }

        /**
         * Creates a new {@link Crouton} with the provided parameters
         *
         * @param duration             The duration the crouton will be displayed {@link Crouton} in milliseconds.
         * @param backgroundColorResId The backgroundcolor's resource id.
         * @param height               The height of the {@link Crouton}. Either {@link android.view.ViewGroup.LayoutParams#MATCH_PARENT} or
         *                             {@link android.view.ViewGroup.LayoutParams#WRAP_CONTENT}.
         * @param textColorResId       The resource id of the text's color.
         */
        public Style(int duration, int backgroundColorResId, int height, int textColorResId) {
            this(duration, backgroundColorResId, height, 0, false, textColorResId);
        }

        /**
         * Creates a new {@link Crouton} with the provided parameters.
         *
         * @param duration             The duration the crouton will be displayed {@link Crouton} in milliseconds.
         * @param backgroundColorResId The background color's resource id.
         * @param height               The height of the {@link Crouton}. Either {@link android.view.ViewGroup.LayoutParams#MATCH_PARENT} or
         *                             {@link android.view.ViewGroup.LayoutParams#WRAP_CONTENT}.
         * @param backgroundResId      A background image drawable's resource id.
         * @param tile                 <code>true</code> if you want the background to be tiled, else <code>false</code>.
         * @param textColorResId       The resource id of the text's color.
         */
        public Style(int duration, int backgroundColorResId, int height, int backgroundResId, boolean tile, int textColorResId) {
            this.duration = duration;
            this.backgroundColorResId = backgroundColorResId;
            this.height = height;
            this.backgroundResId = backgroundResId;
            this.tile = tile;
            this.textColorResId = textColorResId;
        }

        public static class Builder {

            int mDuration;
            int mBackgroundColorResId;
            int mHeight;
            int mBackgroundResId;
            boolean mTile;
            int mTextColorResId;
            int mTextSize;
            int mTextShadowColorResId;
            float mTextShadowRadius;
            float mTextShadowDx;
            float mTextShadowDy;
            int mTextAppearanceResId;
            int mInAnimationResId;
            int mOutAnimationResId;

            public Builder() {/* no-op */}

            /**
             * The duration the {@link Crouton} will be displayed in milliseconds.
             */
            public Builder setDuration(int duration) {
                mDuration = duration;
                return this;
            }

            /**
             * The background color's resource id
             */
            public Builder setBackgroundColor(int backgroundColorResId) {
                mBackgroundColorResId = backgroundColorResId;
                return this;
            }

            /**
             * The resource id of the background.
             */
            public Builder setBackgroundResource(int backgroundResId) {
                mBackgroundResId = backgroundResId;
                return this;
            }

            /**
             * The height of the {@link Crouton} in pixels.
             */
            public Builder setHeight(int height) {
                mHeight = height;
                return this;
            }

            /**
             * Whether we should tile the background or not.
             */
            public Builder setTile(boolean tile) {
                mTile = tile;
                return this;
            }

            /**
             * The text color's resource id.
             */
            public Builder setTextColor(int textColorResId) {
                mTextColorResId = textColorResId;
                return this;
            }

            /**
             * The text size in sp
             */
            public Builder setTextSize(int textSize) {
                mTextSize = textSize;
                return this;
            }

            /**
             * The text shadow color's resource id
             */
            public Builder setTextShadowColor(int textShadowColorResId) {
                mTextShadowColorResId = textShadowColorResId;
                return this;
            }

            /**
             * The text shadow radius
             */
            public Builder setTextShadowRadius(float textShadowRadius) {
                mTextShadowRadius = textShadowRadius;
                return this;
            }

            /**
             * The text shadow horizontal offset
             */
            public Builder setTextShadowDx(float textShadowDx) {
                mTextShadowDx = textShadowDx;
                return this;
            }

            /**
             * The text shadow vertical offset
             */
            public Builder setTextShadowDy(float textShadowDy) {
                mTextShadowDy = textShadowDy;
                return this;
            }

            /**
             * The text appearance resource id for the text.
             */
            public Builder setTextAppearance(int textAppearanceResId) {
                mTextAppearanceResId = textAppearanceResId;
                return this;
            }

            /**
             * The resource id for the in animation
             */
            public Builder setInAnimation(int inAnimationResId) {
                mInAnimationResId = inAnimationResId;
                return this;
            }

            /**
             * The resource id for the out animation
             */
            public Builder setOutAnimation(int outAnimationResId) {
                mOutAnimationResId = outAnimationResId;
                return this;
            }

            public Style build() {
                Style style = new Style();
                style.duration = mDuration;
                style.height = mHeight;
                style.backgroundColorResId = mBackgroundColorResId;
                style.backgroundResId = mBackgroundResId;
                style.tile = mTile;
                style.textColorResId = mTextColorResId;
                style.textSize = mTextSize;
                style.textShadowColorResId = mTextShadowColorResId;
                style.textShadowRadius = mTextShadowRadius;
                style.textShadowDx = mTextShadowDx;
                style.textShadowDy = mTextShadowDy;
                style.textAppearanceResId = mTextAppearanceResId;
                style.inAnimationResId = mInAnimationResId;
                style.outAnimationResId = mOutAnimationResId;
                return style;
            }
        }
    }
}
