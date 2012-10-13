/*
 * Copyright 2012 Benjamin Weiss
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

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Manages the lifecycle of {@link Crouton}s.
 */
final class Manager extends Handler {

  private static final class Messages {
    private Messages() { /* noop */
    }

    public static final int DISPLAY_CROUTON = 0xc2007;
    public static final int ADD_CROUTON_TO_VIEW = 0xc20074dd;
    public static final int REMOVE_CROUTON = 0xc2007de1;
  }

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
   *          The {@link Crouton} to be displayed.
   */
  void add(Crouton crouton) {
    croutonQueue.add(crouton);
    displayCrouton();
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
      sendMessage(currentCrouton, Messages.ADD_CROUTON_TO_VIEW);
    } else {
      sendMessageDelayed(currentCrouton, Messages.DISPLAY_CROUTON, calculateCroutonDuration(currentCrouton));
    }
  }

  private long calculateCroutonDuration(Crouton crouton) {
    long croutonDuration = 0;
    croutonDuration += crouton.getStyle().durationInMilliseconds;
    croutonDuration += crouton.getInAnimation().getDuration();
    croutonDuration += crouton.getOutAnimation().getDuration();
    return croutonDuration;
  }

  /**
   * Sends a {@link Crouton} within a {@link Message}.
   * 
   * @param crouton
   *          The {@link Crouton} that should be sent.
   * @param messageId
   *          The {@link Message} id.
   */
  private void sendMessage(Crouton crouton, final int messageId) {
    final Message message = obtainMessage(messageId);
    message.obj = crouton;
    sendMessage(message);
  }

  /**
   * Sends a {@link Crouton} within a delayed {@link Message}.
   * 
   * @param crouton
   *          The {@link Crouton} that should be sent.
   * @param messageId
   *          The {@link Message} id.
   * @param delay
   *          The delay in milliseconds.
   */
  private void sendMessageDelayed(Crouton crouton, final int messageId, final long delay) {
    Message message = obtainMessage(messageId);
    message.obj = crouton;
    sendMessageDelayed(message, delay);
  }

  /*
   * (non-Javadoc)
   * 
   * @see android.os.Handler#handleMessage(android.os.Message)
   */
  @Override
  public void handleMessage(Message message) {
    final Crouton crouton = (Crouton) message.obj;

    switch (message.what) {
      case Messages.DISPLAY_CROUTON : {
        displayCrouton();
        break;
      }

      case Messages.ADD_CROUTON_TO_VIEW : {
        addCroutonToView(crouton);
        break;
      }

      case Messages.REMOVE_CROUTON : {
        removeCrouton(crouton);
        break;
      }

      default : {
        super.handleMessage(message);
        break;
      }
    }
  }

  /**
   * Adds a {@link Crouton} to the {@link ViewParent} of it's {@link Activity}.
   * 
   * @param crouton
   *          The {@link Crouton} that should be added.
   */
  private void addCroutonToView(Crouton crouton) {
    // don't add if it is already showing
    if (crouton.isShowing()) {
      return;
    }

    View croutonView = crouton.getView();
    if (croutonView.getParent() == null) {
      ViewGroup.LayoutParams params = croutonView.getLayoutParams();
      if (params == null) {
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      }
      crouton.getActivity().addContentView(croutonView, params);
    }
    croutonView.startAnimation(crouton.getInAnimation());
    sendMessageDelayed(crouton, Messages.REMOVE_CROUTON, crouton.getStyle().durationInMilliseconds + +crouton.getInAnimation().getDuration());
  }

  /**
   * Removes the {@link Crouton}'s view after it's display
   * durationInMilliseconds.
   * 
   * @param crouton
   *          The {@link Crouton} added to a {@link ViewGroup} and should be
   *          removed.
   */
  private void removeCrouton(Crouton crouton) {
    View croutonView = crouton.getView();
    ViewGroup croutonParentView = (ViewGroup) croutonView.getParent();

    if (croutonParentView != null) {
      croutonView.startAnimation(crouton.getOutAnimation());

      // Remove the Crouton from the queue.
      Crouton removed = croutonQueue.poll();

      // Remove the crouton from the view's parent.
      croutonParentView.removeView(croutonView);
      if (removed != null) {
        removed.detachActivity();
      }

      // Send a message to display the next crouton but delay it by the out
      // animation duration to make sure it finishes
      sendMessageDelayed(crouton, Messages.DISPLAY_CROUTON, crouton.getOutAnimation().getDuration());
    }
  }

  /**
   * Removes a {@link Crouton} immediately, even when it's currently being
   * displayed.
   * 
   * @param crouton
   *          The {@link Crouton} that should be removed.
   */
  void removeCroutonImmediately(Crouton crouton) {
    if (croutonQueue != null) {
      final Iterator<Crouton> croutonIterator = croutonQueue.iterator();
      while (croutonIterator.hasNext()) {
        final Crouton c = croutonIterator.next();
        if (c.equals(crouton) && (c.getActivity() != null)) {
          // remove the crouton from the content view
          if (crouton.isShowing()) {
            ((ViewGroup) c.getView().getParent()).removeView(c.getView());
          }

          // remove any messages pending for the crouton
          removeAllMessagesForCrouton(c);

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
    removeAllMessages();

    if (croutonQueue != null) {
      // remove any views that may already have been added to the activity's
      // content view
      for (Crouton crouton : croutonQueue) {
        if (crouton.isShowing()) {
          ((ViewGroup) crouton.getView().getParent()).removeView(crouton.getView());
        }
      }
      croutonQueue.clear();
    }
  }

  /**
   * Removes all {@link Crouton}s for the provided activity. This will remove
   * crouton from {@link Activity}s content view immediately.
   */
  void clearCroutonsForActivity(Activity activity) {
    if (croutonQueue != null) {
      Iterator<Crouton> croutonIterator = croutonQueue.iterator();
      while (croutonIterator.hasNext()) {
        Crouton crouton = croutonIterator.next();
        if ((crouton.getActivity() != null) && crouton.getActivity().equals(activity)) {
          // remove the crouton from the content view
          if (crouton.isShowing()) {
            ((ViewGroup) crouton.getView().getParent()).removeView(crouton.getView());
          }

          removeAllMessagesForCrouton(crouton);

          // remove the crouton from the queue
          croutonIterator.remove();
        }
      }
    }
  }

  private void removeAllMessages() {
    removeMessages(Messages.ADD_CROUTON_TO_VIEW);
    removeMessages(Messages.DISPLAY_CROUTON);
    removeMessages(Messages.REMOVE_CROUTON);
  }

  private void removeAllMessagesForCrouton(Crouton crouton) {
    removeMessages(Messages.ADD_CROUTON_TO_VIEW, crouton);
    removeMessages(Messages.DISPLAY_CROUTON, crouton);
    removeMessages(Messages.REMOVE_CROUTON, crouton);

  }
}
