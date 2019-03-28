
#if __has_include("RCTBridgeModule.h")
#import "RCTViewManager.h"
#else
#import <React/RCTViewManager.h>
#endif

#import "AKPickerView.h"

@interface RNNumberSelector : RCTViewManager <AKPickerViewDataSource, AKPickerViewDelegate> {
}

@end
  
