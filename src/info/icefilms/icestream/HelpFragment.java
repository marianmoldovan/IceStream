/*
 * IceStream - The Official IceFilms Android Application
 * Copyright (C) 2011-2013 Stonyx
 *
 * The IceStream application (and any "covered work" as defined by the GNU General Public
 * License Version 3) is licensed under the GNU General Public License Version 3 (henceforth
 * referred to as "GNU GPL V3") with the following amendments that supersede any relevant wording
 * in the GNU GPL V3:
 *
 * 1. The IceStream application (and any "covered work" as defined by the GNU GPL V3) can be
 *    statically and/or dynamically linked to any source code, library, or application developed
 *    or released by Stonyx (the original authors of the IceStream application), regardless of the
 *    type of license that such source code, library, or application is licensed under.
 *
 * 2. The IceStream application (and any "covered work" as defined by the GNU GPL V3) can not be
 *    distributed for a fee without the prior written consent provided by Stonyx (the original
 *    authors of the IceStream application).
 *
 * The preceding amendments make up part of the license that the IceStream application is licensed
 * under.  They apply to and need to be included (along with the GNU GPL V3) with any derivative
 * work as outlined in the GNU GPL V3.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU GPL V3 for more details.
 *
 * You should have received a copy of the GNU GPL V3 along with this program.  If not, see
 * http://www.gnu.org/licenses/.
 */

package info.icefilms.icestream;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.text.util.Linkify.TransformFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelpFragment extends Fragment
{
  // Overridden onCreateView method
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.help_fragment, container, false);
  }

  // Overridden onActivityCreated method
  @Override
  public void onActivityCreated(Bundle savedInstanceState)
  {
    // Call the base class method
    super.onActivityCreated(savedInstanceState);

    // Get the contact and about views
    TextView support = (TextView)getActivity().findViewById(R.id.support);
    TextView about = (TextView)getActivity().findViewById(R.id.about);

    // Linkify the support view
    Linkify.addLinks(support, Pattern.compile("(Ice Stream Support Thread)"), "", null,
        new TransformFilter()
        {
          public String transformUrl(Matcher match, String url)
          {
            // Return the proper link
            return "http://forum.icefilms.info/viewtopic.php?t=58350";
          }
        });

    // Get the version information
    String version;
    try
    {
      version = getActivity().getPackageManager().getPackageInfo(getActivity().
                getPackageName(), PackageManager.GET_META_DATA).versionName;
    }
    catch (PackageManager.NameNotFoundException exception)
    {
      version = "?.?.?";
    }

    // Format the about text with the correct info and add it to the view
    about.setText(String.format(getString(R.string.help_about),
        getString(R.string.app_name), version));

    // Linkify the about view
    Linkify.addLinks(about, Linkify.WEB_URLS);
  }
}