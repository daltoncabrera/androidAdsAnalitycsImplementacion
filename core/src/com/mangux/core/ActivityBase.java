package com.mangux.core;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.Tracker;
import android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class ActivityBase extends Activity {

	protected Integer analitycsId;
	protected Integer admobLayoutId;
	protected String admobUnit;
	protected String admobInterstitial;
	protected String screenName;

	private AdView mAdView;
	private InterstitialAd mInterstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		preparePlayServices();
	}
	
	protected void initPlayServicesVars()
	{
		
	}
	
	protected void preparePlayServices()
	{
		initPlayServicesVars();
		prepareAnalitycs();
		prepareAdmob();
	}
	

	protected void prepareAdmob() {
		// TODO Auto-generated method stub
		mAdView = new AdView(this);
		mAdView.setAdUnitId(admobUnit);
		mAdView.setAdSize(AdSize.BANNER);
		mAdView.setAdListener(new MyAdListener(this));
		ViewGroup layout = (ViewGroup) findViewById(admobLayoutId);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		layout.addView(mAdView, params);
		mAdView.loadAd(new AdRequest.Builder().build());

		mInterstitial = new InterstitialAd(this);
		mInterstitial.setAdUnitId(admobInterstitial);
		mInterstitial.setAdListener(new MyAdListener(this));

	}

	protected void loadInterstitial(View unusedView) {
		mInterstitial.loadAd(new AdRequest.Builder().build());
	}

	public void showInterstitial(View v) {
		if (mInterstitial.isLoaded()) {
			mInterstitial.show();
		}
	}

	protected void prepareAnalitycs() {
		// TODO Auto-generated method stub
		Tracker t = CoreUtils.getTracker(this.analitycsId, this);
		t.setScreenName(this.screenName);
		t.send(new com.google.android.gms.analytics.HitBuilders.AppViewBuilder()
				.build());
	}

	@Override
	protected void onPause() {
		mAdView.pause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mAdView.resume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mAdView.destroy();
		super.onDestroy();
	}


	public void onCloseAdds() {
		// TODO Auto-generated method stub
		
	}

	
}
