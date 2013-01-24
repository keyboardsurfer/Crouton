/*
 * Copyright 2012 - 2013 Benjamin Weiss
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

package de.keyboardsurfer.app.demo.crouton;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author keyboardsurfer
 * @since 14.12.12
 */
public class AboutFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.about, null);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    final String gitHubLink = createLink(getString(R.string.repo_url), "GitHub");
    
    TextView credits = (TextView) view.findViewById(R.id.credits);
    credits.setText(
      Html.fromHtml(getString(R.string.credits, createLink(getString(R.string.gplus_url), "Benjamin Weiss"))));
    
    TextView feedback = (TextView) view.findViewById(R.id.feedback);
    feedback.setText(Html.fromHtml(getString(R.string.feedback, gitHubLink)));
    
    TextView attributions = (TextView) view.findViewById(R.id.attributions);
    attributions.setText(Html.fromHtml(
      getString(R.string.attributions, createLink("http://www.apache.org/licenses/LICENSE-2.0 ", "Apache License, V2"),
        gitHubLink)));

    setLinkMovementMethod(credits, feedback, attributions);
  }

  private String createLink(String url, String title) {
    return String.format("<a href=\"%s\">%s</a>", url, title);
  }

  private void setLinkMovementMethod(TextView... textViews) {
    for (TextView view : textViews) {
      view.setMovementMethod(LinkMovementMethod.getInstance());
    }
  }
}
