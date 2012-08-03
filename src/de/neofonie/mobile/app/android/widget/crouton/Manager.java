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

import java.util.LinkedList;
import java.util.Queue;

import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Manager <br>
 * <br>
 * 
 * Manages the lifecycle of a {@link Crouton}.
 * 
 * @author weiss@neofonie.de
 * 
 */
final class Manager extends Handler {

  private static final int MESSAGE_DISPLAY_CROUTON     = Menu.FIRST;
  private static final int MESSAGE_ADD_CROUTON_TO_VIEW = Menu.FIRST + 1;
  private static final int MESSAGE_REMOVE_CROUTON      = Menu.FIRST + 2;

  private static Manager   manager;
  private Queue<Crouton>   croutonQueue;
  private Animation        inAnimation, outAnimation;

  private Manager() {
    croutonQueue = new LinkedList<Crouton>();
  }

  /**
   * @return The currently used instance of the {@link CroutonManager}.
   */
  static synchronized Manager getInstance() {
    if (manager == null) {
      manager = new Manager();
    }
    return manager;
  }

  /**
   * Inserts a {@link Crouton} to be displayed.
   * 
   * @param crouton
   */
  void add(Crouton crouton) {
    croutonQueue.add(crouton);
    if (inAnimation == null) {
      inAnimation = AnimationUtils.loadAnimation(crouton.getActivity(), android.R.anim.fade_in);
    }
    if (outAnimation == null) {
      outAnimation = AnimationUtils.loadAnimation(crouton.getActivity(), android.R.anim.fade_out);
    }
    displayCrouton();
  }

  /**
   * Removes all {@link Crouton}s from the queue.
   */
  void clearCroutonQueue() {
    if (croutonQueue != null) {
      croutonQueue.clear();
    }
    removeMessages(MESSAGE_DISPLAY_CROUTON);
    removeMessages(MESSAGE_ADD_CROUTON_TO_VIEW);
    removeMessages(MESSAGE_REMOVE_CROUTON);
  }

  /**
   * Displays the next crouton within the queue.
   */
  private void displayCrouton() {
    if (croutonQueue.isEmpty()) {
      return;
    }
    //First peek whether the Crouton is being displayed.
    final Crouton currentCrouton = croutonQueue.peek();
    //If the activity is null we throw away the Crouton.
    if (currentCrouton.getActivity() == null) {
      croutonQueue.poll();
    }
    if (!currentCrouton.isShowing()) {
      //Display the Crouton
      sendMessage(obtainMessage(MESSAGE_ADD_CROUTON_TO_VIEW));
    } else {
      Message msg = manager.obtainMessage(MESSAGE_DISPLAY_CROUTON);
      sendMessageDelayed(msg,
                         currentCrouton.getStyle().duration + inAnimation.getDuration()
                             + outAnimation.getDuration());
    }
  }

  /**
   * Removes the {@link Crouton}'s view after it's display duration.
   * 
   * @param crouton
   *          The {@link Crouton} added to a {@link ViewGroup} and should be removed.s
   */
  private void removeCrouton(final Crouton crouton) {
    ViewGroup parent = ((ViewGroup) crouton.getView().getParent());
    if (parent != null) {
      crouton.getView().startAnimation(outAnimation);
      //Remove the Crouton from the queue.
      croutonQueue.poll();
      //Remove the crouton from the view's parent.
      parent.removeView(crouton.getView());
    }
  }

  private void addCroutonToView(Crouton currentCrouton) {
    currentCrouton.setView(ViewHolder.viewForCrouton(currentCrouton));
    if (currentCrouton.getView().getParent() == null) {
      currentCrouton.getActivity().addContentView(currentCrouton.getView(),
                                                  currentCrouton.getView().getLayoutParams());
    }
    currentCrouton.getView().startAnimation(inAnimation);
    Message msg = manager.obtainMessage(MESSAGE_REMOVE_CROUTON);
    sendMessageDelayed(msg, currentCrouton.getStyle().duration);
  }

  /* (non-Javadoc)
   * @see android.os.Handler#handleMessage(android.os.Message)
   */
  @Override
  public void handleMessage(Message msg) {
    switch (msg.what) {
      case MESSAGE_DISPLAY_CROUTON:
        displayCrouton();
        break;
      case MESSAGE_ADD_CROUTON_TO_VIEW:
        addCroutonToView(croutonQueue.peek());
        break;
      case MESSAGE_REMOVE_CROUTON:
        removeCrouton(croutonQueue.peek());
        break;
      default:
        super.handleMessage(msg);
        break;
    }
  }
}
