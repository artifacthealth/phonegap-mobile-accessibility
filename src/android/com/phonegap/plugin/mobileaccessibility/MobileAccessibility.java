/**
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

package com.phonegap.plugin.mobileaccessibility;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;

import android.os.Build;
import android.webkit.WebView;
import android.view.View;
import android.webkit.WebSettings;

import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This class provides information on the status of native accessibility services to JavaScript.
 */
public class MobileAccessibility extends CordovaPlugin {

    View mView;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        WebView view;
        try {
            view = (WebView) this.webView;
            mView = view;
        } catch(ClassCastException ce) {  // cordova-android 4.0+
            try {
                Method getView = this.webView.getClass().getMethod("getView");
                mView = (View) getView.invoke(this.webView);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {

        if(action.equals("getTextZoom")) {
            getTextZoom(callbackContext);
            return true;
        }
        return false;
    }

    private void getTextZoom(final CallbackContext callbackContext) {

        float fontScale = cordova.getActivity().getResources().getConfiguration().fontScale;

        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                setTextZoom(100);
                if (callbackContext != null) {
                    callbackContext.success((int) fontScale);
                }
            }
        });
    }

    private void setTextZoom(double textZoom) {
        
        try {
            Method getSettings = mView.getClass().getMethod("getSettings");
            Object wSettings = getSettings.invoke(mView);
            Method setTextZoom = wSettings.getClass().getMethod("setTextZoom", Integer.TYPE);
            setTextZoom.invoke(wSettings, (int) textZoom);
        } catch (ClassCastException ce) {
            ce.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
