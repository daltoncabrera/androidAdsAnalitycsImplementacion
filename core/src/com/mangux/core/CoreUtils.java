package com.mangux.core;


import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;
import android.content.Context;
import android.util.SparseArray;

public class CoreUtils {

	static SparseArray<Tracker> mTrackers = new SparseArray();

	public static synchronized Tracker getTracker(int trackerId, Context ctx) {

		if (mTrackers.get(trackerId) == null) {

			GoogleAnalytics analytics = GoogleAnalytics.getInstance(ctx);
			analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
			Tracker t = analytics.newTracker(trackerId);
			t.enableAdvertisingIdCollection(true);
			mTrackers.append(trackerId, t);
		}
		return mTrackers.get(trackerId);
	}

	
}
