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

/**
 * Style <br>
 * <br>
 * 
 * The style for a {@link Crouton}.
 * 
 * @author weiss@neofonie.de
 * 
 */

public enum Style {
	ALERT(5000, android.R.color.holo_red_light), CONFIRM(3000,
			android.R.color.holo_green_light), INFO(3000,
			android.R.color.holo_blue_bright);
	/**
	 * The duration the {@link Crouton} will be displayed in milliseconds.
	 */
	final int duration;
	/**
	 * The color's resource id.
	 */
	final int color;

	Style(int duration, int color) {
		this.duration = duration;
		this.color = color;
	}
}
