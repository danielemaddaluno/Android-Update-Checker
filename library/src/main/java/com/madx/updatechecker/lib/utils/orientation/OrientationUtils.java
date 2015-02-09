/*
 * Copyright (C) 2014 Daniele Maddaluno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.madx.updatechecker.lib.utils.orientation;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.view.Surface;
import android.view.WindowManager;

/** Static methods related to device orientation. */
public class OrientationUtils {
    // See constant value of orientations on http://developer.android.com/reference/android/R.attr.html#screenOrientation
	private OrientationUtils() {}

	/** Locks the device window in landscape mode. */
	public static void lockOrientationLandscape(Activity activity) {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}

	/** Locks the device window in portrait mode. */
	public static void lockOrientationPortrait(Activity activity) {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	/** Locks the device window in actual screen mode. */
	public static void lockOrientation(Activity activity) {
		final int orientation = activity.getResources().getConfiguration().orientation;
	    final int rotation = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();

		// Copied from Android docs, since we don't have these values in Froyo 2.2
		int SCREEN_ORIENTATION_REVERSE_LANDSCAPE = 8;
		int SCREEN_ORIENTATION_REVERSE_PORTRAIT = 9;

		// Build.VERSION.SDK_INT <= Build.VERSION_CODES.FROYO
		if (!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD)) {
			SCREEN_ORIENTATION_REVERSE_LANDSCAPE = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
			SCREEN_ORIENTATION_REVERSE_PORTRAIT = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
		}

		if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_90){
			if (orientation == Configuration.ORIENTATION_PORTRAIT){
				activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			}
			else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
				activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			}
		}
		else if (rotation == Surface.ROTATION_180 || rotation == Surface.ROTATION_270) 
		{
			if (orientation == Configuration.ORIENTATION_PORTRAIT){
				activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
			}
			else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
				activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
			}
		}
	}

	/** Unlocks the device window in user defined screen mode. */
	public static void unlockOrientation(Activity activity) {
        activity.setRequestedOrientation(getManifestOrientation(activity));
	}

    private static int getManifestOrientation(Activity activity){
        try {
            ActivityInfo app = activity.getPackageManager().getActivityInfo(activity.getComponentName(), PackageManager.GET_ACTIVITIES|PackageManager.GET_META_DATA);
            return app.screenOrientation;
        } catch (PackageManager.NameNotFoundException e) {
            return ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
        }
    }

}
