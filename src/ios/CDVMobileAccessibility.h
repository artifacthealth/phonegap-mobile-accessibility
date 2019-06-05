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

#import <Cordova/CDVPlugin.h>

static const int BASE_UI_FONT_TEXT_STYLE_BODY_POINT_SIZE = 16;

@interface CDVMobileAccessibility : CDVPlugin {
    NSString* callbackId;
    NSString* commandCallbackId;
}

@property (strong) NSString* callbackId;
@property (strong) NSString* commandCallbackId;

- (void) getTextZoom:(CDVInvokedUrlCommand*)command;

@end