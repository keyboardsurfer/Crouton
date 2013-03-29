package de.keyboardsurfer.android.widget.crouton;

/**
 * Created with Intellij with Android, BIZZBY product.
 * See licencing for usage of this code.
 * <p/>
 * User: chris
 * Date: 29/03/2013
 * Time: 18:12
 */
public class Configuration
{


    public static final int DURATION_SHORT = 3000;
    public static final int DURATION_LONG = 5000;
    /**
     * Display a {@link Crouton} for an infinite amount of time or
     * until {@link de.keyboardsurfer.android.widget.crouton.Crouton#cancel()} has been called.
     */
    public static final int DURATION_INFINITE = -1;
    /**
     * The durationInMilliseconds the {@link Crouton} will be displayed in
     * milliseconds.
     */
    public static final Configuration DEFAULT;

    static
    {
        DEFAULT = new Builder().setDuration(DURATION_SHORT).build();
    }

    final int durationInMilliseconds;

    private Configuration(Builder builder)
    {
        this.durationInMilliseconds = builder.durationInMilliseconds;
    }

    public static class Builder
    {
        private int durationInMilliseconds = DURATION_SHORT;

        /**
         * Set the durationInMilliseconds option of the {@link Crouton}.
         *
         * @param duration The durationInMilliseconds the crouton will be displayed
         *                 {@link Crouton} in milliseconds.
         * @return the {@link Builder}.
         */
        public Builder setDuration(int duration)
        {
            this.durationInMilliseconds = duration;

            return this;
        }

        /**
         * @return a configured {@link Style} object.
         */
        public Configuration build()
        {
            return new Configuration(this);
        }
    }
}
