/*
 * (c) Neofonie Mobile GmbH
 * 
 * This computer program is the sole property of Neofonie Mobile GmbH (http://mobile.neofonie.de)
 * and is protected under the German Copyright Act (paragraph 69a UrhG).
 * 
 * All rights are reserved. Making copies, duplicating, modifying, using or distributing
 * this computer program in any form, without prior written consent of Neofonie Mobile GmbH, is prohibited.
 * Violation of copyright is punishable under the German Copyright Act (paragraph 106 UrhG).
 * 
 * Removing this copyright statement is also a violation.
 */
package de.neofonie.mobile.app.android.widget.crouton;

import java.util.LinkedList;
import java.util.Queue;

import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * APPLibrary / Manager <br>
 * <br>
 * 
 * Manages the lifecycle of {@link Crouton}s.
 * 
 * @author weiss@neofonie.de
 * 
 */
final class Manager extends Handler {

  private static final int MESSAGE_DISPLAY_CROUTON     = 0xc2007;
  private static final int MESSAGE_ADD_CROUTON_TO_VIEW = 0xc20074dd;
  private static final int MESSAGE_REMOVE_CROUTON      = 0xc2007de1;
  private static Manager   INSTANCE;

  private Queue<Crouton>   croutonQueue;
  private Animation        inAnimation, outAnimation;

  private Manager() {
    croutonQueue = new LinkedList<Crouton>();
  }

  /**
   * @return The currently used instance of the {@link CroutonManager}.
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
    final Message msg;
    if (!currentCrouton.isShowing()) {
      //Display the Crouton
      msg = obtainMessage(MESSAGE_ADD_CROUTON_TO_VIEW);
      msg.obj = currentCrouton;
      sendMessage(msg);
    } else {
      msg = obtainMessage(MESSAGE_DISPLAY_CROUTON);
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
      Message msg = obtainMessage(MESSAGE_DISPLAY_CROUTON);
      sendMessage(msg);
    }
  }

  private void addCroutonToView(Crouton currentCrouton) {
    currentCrouton.setView(ViewHolder.viewForCrouton(currentCrouton));
    if (currentCrouton.getView().getParent() == null) {
      currentCrouton.getActivity().addContentView(currentCrouton.getView(),
                                                  currentCrouton.getView().getLayoutParams());
    }
    currentCrouton.getView().startAnimation(inAnimation);
    final Message msg = obtainMessage(MESSAGE_REMOVE_CROUTON);
    msg.obj = currentCrouton;
    sendMessageDelayed(msg, currentCrouton.getStyle().duration);
  }

  /* (non-Javadoc)
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

