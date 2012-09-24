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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Manager <br>
 * <br>
 * 
 * Manages the lifecycle of {@link Crouton}s.
 * 
 * @author weiss@neofonie.de
 * 
 */
final class Manager extends Handler {

	private static final int MESSAGE_DISPLAY_CROUTON = 0xc2007;
	private static final int MESSAGE_ADD_CROUTON_TO_VIEW = 0xc20074dd;
	private static final int MESSAGE_REMOVE_CROUTON = 0xc2007de1;
	private static Manager INSTANCE;

	private Queue<Crouton> croutonQueue;

	private Manager() {
		croutonQueue = new LinkedBlockingQueue<Crouton>();
	}

	/**
	 * @return The currently used instance of the {@link Manager}.
	 */
	static synchronized Manager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Manager();
		}
		return INSTANCE;
	}

	/**
	 * Inserts a {@link Crouton} to be displayed.
	 * 
	 * @param crouton
	 */
	void add(Crouton crouton) {
		croutonQueue.add(crouton);
		displayCrouton();
	}

	/**
	 * Removes a {@link Crouton} immediately, even when it's currently being
	 * displayed.
	 * 
	 * @param crouton
	 *            The {@link Crouton} that should be removed.
	 */
	void removeCroutonImmediately(Crouton crouton) {
		if (croutonQueue != null) {

            final Iterator<Crouton> croutonIterator = croutonQueue.iterator();
            while (croutonIterator.hasNext()) {
                final Crouton c = croutonIterator.next();
                if (c.equals(crouton) && c.getActivity() != null) {

                    // remove the crouton from the content view
                    if (crouton.isShowing()) {
                        ((ViewGroup)c.getView().getParent()).removeView(c.getView());
                    }
                    // remove any messages pending for the crouton
                    removeMessages(MESSAGE_DISPLAY_CROUTON, c);
                    removeMessages(MESSAGE_ADD_CROUTON_TO_VIEW, c);
                    removeMessages(MESSAGE_REMOVE_CROUTON, c);

                    // remove the crouton from the queue
                    croutonIterator.remove();

                    // we have found our crouton so just break
                    break;
                }
            }
        }
	}

	/**
	 * Removes all {@link Crouton}s from the queue.
	 */
	void clearCroutonQueue() {
		removeMessages(MESSAGE_DISPLAY_CROUTON);
		removeMessages(MESSAGE_ADD_CROUTON_TO_VIEW);
		removeMessages(MESSAGE_REMOVE_CROUTON);
        if (croutonQueue != null) {

            // remove any views that may already have been added to the activity's content view
            for (Crouton crouton : croutonQueue) {
                if (crouton.isShowing()) {
                    ((ViewGroup)crouton.getView().getParent()).removeView(crouton.getView());
                }
            }
            croutonQueue.clear();
        }
	}

    /**
     * Removes all {@link Crouton}s for the provided activity. This will remove crouton from {@link Activity}s content view
     * immediately.
     */
    void clearCroutonsForActivity(Activity activity) {
        if (croutonQueue != null) {

            Iterator<Crouton> croutonIterator = croutonQueue.iterator();
            while (croutonIterator.hasNext()) {
                Crouton crouton = croutonIterator.next();
                if (crouton.getActivity() != null && crouton.getActivity().equals(activity)) {

                    // remove the crouton from the content view
                    if (crouton.isShowing()) {
                        ((ViewGroup)crouton.getView().getParent()).removeView(crouton.getView());
                    }

                    removeMessages(MESSAGE_DISPLAY_CROUTON, crouton);
                    removeMessages(MESSAGE_ADD_CROUTON_TO_VIEW, crouton);
                    removeMessages(MESSAGE_REMOVE_CROUTON, crouton);

                    // remove the crouton from the queue
                    croutonIterator.remove();
                }
            }
        }
    }

	/**
	 * Displays the next {@link Crouton} within the queue.
	 */
	private void displayCrouton() {
		if (croutonQueue.isEmpty()) {
			return;
		}
		// First peek whether the Crouton has an activity.
		final Crouton currentCrouton = croutonQueue.peek();
		// If the activity is null we poll the Crouton off the queue.
		if (currentCrouton.getActivity() == null) {
			croutonQueue.poll();
		}
		if (!currentCrouton.isShowing()) {
			// Display the Crouton
			sendMessage(currentCrouton, MESSAGE_ADD_CROUTON_TO_VIEW);
		} else {
			sendMessageDelayed(
					currentCrouton,
					MESSAGE_DISPLAY_CROUTON,
					currentCrouton.getStyle().duration
							+ currentCrouton.getInAnimation().getDuration()
							+ currentCrouton.getOutAnimation().getDuration());
		}
	}

	/**
	 * Removes the {@link Crouton}'s view after it's display duration.
	 * 
	 * @param crouton
	 *            The {@link Crouton} added to a {@link ViewGroup} and should be
	 *            removed.
	 */
	private void removeCrouton(Crouton crouton) {
		ViewGroup parent = ((ViewGroup) crouton.getView().getParent());
		if (parent != null) {

			crouton.getView().startAnimation(crouton.getOutAnimation());
			// Remove the Crouton from the queue.
			Crouton removed = croutonQueue.poll();
			// Remove the crouton from the view's parent.
			parent.removeView(crouton.getView());
            // Remove the activity to prevent memory leaks
            if (removed != null) {
                removed.detachActivity();
            }
            // Send a message to display the next crouton but delay it by the out animation duration to make sure it finishes
            sendMessageDelayed(crouton, MESSAGE_DISPLAY_CROUTON, crouton.getOutAnimation().getDuration());
		}
	}

	/**
	 * Adds a {@link Crouton} to the {@link ViewParent} of it's {@link Activity}
	 * .
	 * 
	 * @param crouton
	 *            The {@link Crouton} that should be added.
	 */
	private void addCroutonToView(Crouton crouton) {
        // don't add if it is already showing
        if (crouton.isShowing()) {
            return;
        }

        // inflate the view
		crouton.setView(ViewHolder.viewForCrouton(crouton));
		if (crouton.getView().getParent() == null) {
			crouton.getActivity().addContentView(crouton.getView(),
                    crouton.getView().getLayoutParams());
		}

		crouton.getView().startAnimation(crouton.getInAnimation());
		sendMessageDelayed(crouton, MESSAGE_REMOVE_CROUTON, crouton.getStyle().duration + crouton.getInAnimation().getDuration());
	}

	/**
	 * Sends a {@link Crouton} within a delayed {@link Message}.
	 * 
	 * @param crouton
	 *            The {@link Crouton} that should be sent.
	 * @param messageId
	 *            The {@link Message} id.
	 * @param delay
	 *            The delay in milliseconds.
	 */
	private void sendMessageDelayed(Crouton crouton, final int messageId,
			final long delay) {
		Message msg = obtainMessage(messageId);
		msg.obj = crouton;
		sendMessageDelayed(msg, delay);
	}

	/**
	 * Sends a {@link Crouton} within a {@link Message}.
	 * 
	 * @param crouton
	 *            The {@link Crouton} that should be sent.
	 * @param messageId
	 *            The {@link Message} id.
	 */
	private void sendMessage(Crouton crouton, final int messageId) {
		final Message msg = obtainMessage(messageId);
		msg.obj = crouton;
		sendMessage(msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.Handler#handleMessage(android.os.Message)
	 */
	@Override
	public void handleMessage(Message msg) {
		final Crouton crouton;
		switch (msg.what) {
		case MESSAGE_DISPLAY_CROUTON:
			displayCrouton();
			break;
		case MESSAGE_ADD_CROUTON_TO_VIEW:
			crouton = (Crouton) msg.obj;
			addCroutonToView(crouton);
			break;
		case MESSAGE_REMOVE_CROUTON:
			crouton = (Crouton) msg.obj;
			removeCrouton(crouton);
			break;
		default:
			super.handleMessage(msg);
			break;
		}
	}
}
