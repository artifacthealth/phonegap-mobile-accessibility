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

#import "CDVMobileAccessibility.h"
#import <Cordova/CDVAvailability.h>
#import <MediaAccessibility/MediaAccessibility.h>

@interface CDVMobileAccessibility ()
    // add any property overrides
    -(double) mGetTextZoom;
@end

@implementation CDVMobileAccessibility

@synthesize callbackId;
@synthesize commandCallbackId;

#define iOS7Delta (([[[UIDevice currentDevice] systemVersion] floatValue] >= 7.0 ) ? 20 : 0 )
#define iOS8Delta (([[[UIDevice currentDevice] systemVersion] floatValue] >= 8.0 ) ? 30 : 0 )

// //////////////////////////////////////////////////

- (void)pluginInitialize
{
}

// //////////////////////////////////////////////////

#pragma Plugin interface

-(double) mGetFontScale
{
    double fontScale = 1;
    if (iOS7Delta)  {
        fontScale = [[UIFont preferredFontForTextStyle:UIFontTextStyleBody] pointSize] / BASE_UI_FONT_TEXT_STYLE_BODY_POINT_SIZE;
    }
    return fontScale;
}

- (void) getTextZoom:(CDVInvokedUrlCommand *)command
{
    double zoom = [self mGetFontScale];
    [self.commandDelegate runInBackground:^{
        CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDouble: zoom];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

@end
