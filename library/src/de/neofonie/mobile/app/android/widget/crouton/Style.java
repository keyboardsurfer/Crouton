/*
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

package de.neofonie.mobile.app.android.widget.crouton;


import android.R;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;


/**
 * Style <br>
 * <br>
 * <p/>
 * The style for a {@link Crouton}.
 */

public class Style {
	public static final Style ALERT;
	public static final Style CONFIRM;
	public static final Style INFO;

	static {
		ALERT = new Builder().setDuration(5000).setBackgroundColor(
			R.color.holo_red_light).setHeight(LayoutParams.WRAP_CONTENT).build();
		CONFIRM = new Builder().setDuration(3000).setBackgroundColor(
			R.color.holo_green_light).setHeight(LayoutParams.WRAP_CONTENT).build();
		INFO = new Builder().setDuration(3000).setBackgroundColor(
			R.color.holo_blue_light).setHeight(LayoutParams.WRAP_CONTENT).build();
	}

	/**
	 * The durationInMilliseconds the {@link Crouton} will be displayed in milliseconds.
	 */
	final int durationInMilliseconds;

	/**
	 * The backgroundColorResourceId's resource id.
	 */
	final int backgroundColorResourceId;

	/**
	 * The resource id of the backgroundDrawableResourceId.
	 * <p/>
	 * 0 for no backgroundDrawableResourceId.
	 */
	final int backgroundDrawableResourceId;

	/**
	 * Whether we should isTileEnabled the backgroundDrawableResourceId or not.
	 */
	final boolean isTileEnabled;

	/**
	 * The text backgroundColorResourceId's resource id.
	 * <p/>
	 * 0 sets the text backgroundColorResourceId to the system theme default.
	 */
	final int textColorResourceId;

	/**
	 * The heightInPixels of the {@link Crouton} in pixels.
	 */
	final int heightInPixels;

	/**
	 * The text's gravity as provided by {@link Gravity}.
	 */
	final int gravity;

	/**
	 * An additional image to display in the {@link Crouton}.
	 */
	final Drawable image;

	private Style(final Builder builder) {
		this.durationInMilliseconds = builder.durationInMilliseconds;
		this.backgroundColorResourceId = builder.backgroundColorResourceId;
		this.backgroundDrawableResourceId = builder.backgroundDrawableResourceId;
		this.isTileEnabled = builder.isTileEnabled;
		this.textColorResourceId = builder.textColorResourceId;
		this.heightInPixels = builder.heightInPixels;
		this.gravity = builder.gravity;
		this.image = builder.image;
	}

	/**
	 * Builder for the {@link Style} object.
	 */
	public static class Builder {
		private int durationInMilliseconds;
		private int backgroundColorResourceId;
		private int backgroundDrawableResourceId;
		private boolean isTileEnabled;
		private int textColorResourceId;
		private int heightInPixels;
		private int gravity;
		private Drawable image;

		public Builder() {
			durationInMilliseconds = 3000;
			backgroundColorResourceId = R.color.holo_blue_light;
			backgroundDrawableResourceId = 0;
			isTileEnabled = false;
			textColorResourceId = android.R.color.white;
			heightInPixels = ViewGroup.LayoutParams.WRAP_CONTENT;
			gravity = Gravity.CENTER;
			image = null;
		}

		/**
		 * Set the durationInMilliseconds option of the {@link Crouton}.
		 *
		 * @param durationInMilliseconds The durationInMilliseconds the crouton will be displayed {@link Crouton}
		 * in milliseconds.
		 * @return the {@link Builder}.
		 */
		public Builder setDuration(int durationInMilliseconds) {
			this.durationInMilliseconds = durationInMilliseconds;

			return this;
		}

		/**
		 * Set the backgroundColorResourceId option of the {@link Crouton}.
		 *
		 * @param backgroundColorResourceId The backgroundColorResourceId's resource id.
		 * @return the {@link Builder}.
		 */
		public Builder setBackgroundColor(int backgroundColorResourceId) {
			this.backgroundColorResourceId = backgroundColorResourceId;

			return this;
		}

		/**
		 * Set the backgroundDrawableResourceId option for the {@link Crouton}.
		 *
		 * @param backgroundDrawableResourceId Resource ID of a backgroundDrawableResourceId image drawable.
		 * @return the {@link Builder}.
		 */
		public Builder setBackgroundDrawable(int backgroundDrawableResourceId) {
			this.backgroundDrawableResourceId = backgroundDrawableResourceId;

			return this;
		}

		/**
		 * Set the isTileEnabled option for the {@link Crouton}.
		 *
		 * @param isTileEnabled <code>true</code> if you want the backgroundDrawableResourceId to be tiled,
		 * else <code>false</code>.
		 * @return the {@link Builder}.
		 */
		public Builder setTileEnabled(boolean isTileEnabled) {
			this.isTileEnabled = isTileEnabled;

			return this;
		}

		/**
		 * Set the textColorResourceId option for the {@link Crouton}.
		 *
		 * @param textColorResourceId The resource id of the text backgroundColorResourceId.
		 * @return the {@link Builder}.
		 */
		public Builder setTextColor(int textColorResourceId) {
			this.textColorResourceId = textColorResourceId;

			return this;
		}

		/**
		 * Set the heightInPixels option for the {@link Crouton}.
		 *
		 * @param heightInPixels The heightInPixels of the {@link Crouton} in pixel. Can also be
		 * {@link LayoutParams#MATCH_PARENT} or
		 * {@link LayoutParams#WRAP_CONTENT}.
		 * @return the {@link Builder}.
		 */
		public Builder setHeight(int heightInPixels) {
			this.heightInPixels = heightInPixels;

			return this;
		}

		/**
		 * Set the gravity option for the {@link Crouton}.
		 *
		 * @param gravity The text's gravity as provided by {@link Gravity}.
		 * @return the {@link Builder}.
		 */
		public Builder setGravity(int gravity) {
			this.gravity = gravity;

			return this;
		}

		/**
		 * Set the image option for the {@link Crouton}.
		 *
		 * @param image An additional image to display in the {@link Crouton}.
		 * @return the {@link Builder}.
		 */
		public Builder setImage(Drawable image) {
			this.image = image;

			return this;
		}

		/**
		 * @return a configured {@link Style} object.
		 */
		public Style build() {
			return new Style(this);
		}
	}
}
