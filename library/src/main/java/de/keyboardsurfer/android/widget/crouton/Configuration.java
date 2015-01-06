/*
 * Copyright 2012 - 2014 Benjamin Weiss
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

/**
 * Allows configuring a {@link Crouton}s behaviour aside from the actual view,
 * which is defined via {@link Style}.
 * <p/>
 * This allows to re-use a {@link Style} while modifying parameters that only have to be applied
 * when the {@link Crouton} is being displayed.
 *
 * @author chris
 * @since 1.8
 */
public class Configuration {


  /**
   * Display a {@link Crouton} for an infinite amount of time or
   * until {@link Crouton#cancel()} has been called.
   */
  public static final int DURATION_INFINITE = -1;
  /** The default short display duration of a {@link Crouton}. */
  public static final int DURATION_SHORT = 3000;
  /** The default long display duration of a {@link Crouton}. */
  public static final int DURATION_LONG = 5000;

  /** The default {@link Configuration} of a {@link Crouton}. */
  public static final Configuration DEFAULT;

  static {
    DEFAULT = new Builder().setDuration(DURATION_SHORT).build();
  }

  /** The durationInMilliseconds the {@link Crouton} will be displayed in milliseconds. */
  final int durationInMilliseconds;
  /** The resource id for the in animation. */
  final int inAnimationResId;
  /** The resource id for the out animation. */
  final int outAnimationResId;

  private Configuration(Builder builder) {
    this.durationInMilliseconds = builder.durationInMilliseconds;
    this.inAnimationResId = builder.inAnimationResId;
    this.outAnimationResId = builder.outAnimationResId;
  }

  /** Creates a {@link Builder} to build a {@link Configuration} upon. */
  public static class Builder {
    private int durationInMilliseconds = DURATION_SHORT;
    private int inAnimationResId = 0;
    private int outAnimationResId = 0;

    /**
     * Set the durationInMilliseconds option of the {@link Crouton}.
     *
     * @param duration
     *   The durationInMilliseconds the crouton will be displayed
     *   {@link Crouton} in milliseconds.
     *
     * @return the {@link Builder}.
     */
    public Builder setDuration(final int duration) {
      this.durationInMilliseconds = duration;

      return this;
    }

    /**
     * The resource id for the in animation.
     *
     * @param inAnimationResId
     *   The resource identifier for the animation that's being shown
     *   when the {@link Crouton} is going to be displayed.
     *
     * @return the {@link Builder}.
     */
    public Builder setInAnimation(final int inAnimationResId) {
      this.inAnimationResId = inAnimationResId;

      return this;
    }

    /**
     * The resource id for the out animation
     *
     * @param outAnimationResId
     *   The resource identifier for the animation that's being shown
     *   when the {@link Crouton} is going to be removed.
     *
     * @return the {@link Builder}.
     */
    public Builder setOutAnimation(final int outAnimationResId) {
      this.outAnimationResId = outAnimationResId;

      return this;
    }

    /**
     * Builds the {@link Configuration}.
     *
     * @return The built {@link Configuration}.
     */
    public Configuration build() {
      return new Configuration(this);
    }
  }

  @Override
  public String toString() {
    return "Configuration{" +
      "durationInMilliseconds=" + durationInMilliseconds +
      ", inAnimationResId=" + inAnimationResId +
      ", outAnimationResId=" + outAnimationResId +
      '}';
  }
}