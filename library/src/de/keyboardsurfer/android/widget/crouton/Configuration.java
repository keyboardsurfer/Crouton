package de.keyboardsurfer.android.widget.crouton;

/**
 * Created with Intellij with Android.
 * See licencing for usage of this code.
 * <p/>
 * User: chris
 * Date: 29/03/2013
 * Time: 18:12
 */
public class Configuration {


  /**
   * Display a {@link Crouton} for an infinite amount of time or
   * until {@link de.keyboardsurfer.android.widget.crouton.Crouton#cancel()} has been called.
   */
  public static final int DURATION_INFINITE = -1;
  /**
   * The default short display duration of a {@link Crouton}.
   */
  public static final int DURATION_SHORT = 3000;
  /**
   * The default long display duration of a {@link Crouton}.
   */
  public static final int DURATION_LONG = 5000;
  /**
   * The durationInMilliseconds the {@link Crouton} will be displayed in
   * milliseconds.
   */
  public static final Configuration DEFAULT;

  static {
    DEFAULT = new Builder().setDuration(DURATION_SHORT).build();
  }

  /**
   * The durationInMilliseconds the {@link Crouton} will be displayed in
   * milliseconds.
   */
  final int durationInMilliseconds;
  /**
   * The resource id for the in animation
   */
  final int inAnimationResId;
  /**
   * The resource id for the out animation
   */
  final int outAnimationResId;

  private Configuration(Builder builder) {
    this.durationInMilliseconds = builder.durationInMilliseconds;
    this.inAnimationResId = builder.inAnimationResId;
    this.outAnimationResId = builder.outAnimationResId;
  }

  public static class Builder {
    private int durationInMilliseconds = DURATION_SHORT;
    private int inAnimationResId = 0;
    private int outAnimationResId = 0;

    /**
     * Set the durationInMilliseconds option of the {@link Crouton}.
     *
     * @param duration The durationInMilliseconds the crouton will be displayed
     *                 {@link Crouton} in milliseconds.
     * @return the {@link Builder}.
     */
    public Builder setDuration(final int duration) {
      this.durationInMilliseconds = duration;

      return this;
    }

    /**
     * The resource id for the in animation
     */
    public Builder setInAnimation(final int inAnimationResId) {
      this.inAnimationResId = inAnimationResId;

      return this;
    }

    /**
     * The resource id for the out animation
     */
    public Builder setOutAnimation(final int outAnimationResId) {
      this.outAnimationResId = outAnimationResId;

      return this;
    }

    /**
     * @return a configured {@link Style} object.
     */
    public Configuration build() {
      return new Configuration(this);
    }
  }
}