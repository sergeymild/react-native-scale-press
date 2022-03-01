#import "React/RCTViewManager.h"

@interface RCT_EXTERN_MODULE(ScalePressViewManager, RCTViewManager)
RCT_EXPORT_VIEW_PROPERTY(onPress, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onLongPress, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(scale, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(durationIn, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(durationOut, NSNumber)
@end
