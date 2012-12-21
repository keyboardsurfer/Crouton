package de.keyboardsurfer.app.demo.crouton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author bweiss
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
    TextView credits = (TextView) view.findViewById(R.id.credits);
      credits.setText(
        Html.fromHtml(getString(R.string.credits, createLink(getString(R.string.gplus_url), "Benjamin Weiss"),
          createLink(getString(R.string.repo_url), "GitHub"))));
    credits.setMovementMethod(LinkMovementMethod.getInstance());
  }

  private String createLink(String url, String title) {
    return String.format("<a href=\"%s\">%s</a>", url, title);  }
}
