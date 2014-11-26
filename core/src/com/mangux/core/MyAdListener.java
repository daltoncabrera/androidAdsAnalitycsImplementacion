/*
 * Copyright (C) 2013 The Android Open Source Project
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

package com.mangux.core;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * An ad listener that toasts all ad events.
 */
public class MyAdListener extends AdListener {
    private ActivityBase mContext;

    public MyAdListener(ActivityBase aBase) {
        this.mContext = aBase;
    }

    @Override
    public void onAdLoaded() {
        
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
        String errorReason = "";
        switch(errorCode) {
            case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                errorReason = "Internal error";
                break;
            case AdRequest.ERROR_CODE_INVALID_REQUEST:
                errorReason = "Invalid request";
                break;
            case AdRequest.ERROR_CODE_NETWORK_ERROR:
                errorReason = "Network Error";
                break;
            case AdRequest.ERROR_CODE_NO_FILL:
                errorReason = "No fill";
                break;
        }
       
       msg(errorReason);
    }

    @Override
    public void onAdOpened() {
        
    }

    @Override
    public void onAdClosed() {        
        mContext.onCloseAdds();
    }

    @Override
    public void onAdLeftApplication() {
        
    }
    
    void msg(String msg)
    {
    	log(msg);
    }
    
    void log(String msg)
    {
    	Log.v("Ads",msg);
    }
    
    void toast(String msg)
    {
    	Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
    
}
