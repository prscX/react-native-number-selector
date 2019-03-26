
#import "RNNumberSelector.h"

@interface RNNumberSelector () <AKPickerViewDataSource, AKPickerViewDelegate>
@property (nonatomic, strong) NSArray *_items;
@property (nonatomic, strong) NSNumber *_selectedItem;
@end


@implementation RNNumberSelector

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

- (AKPickerView *)view {
    AKPickerView *pickerView = [[AKPickerView alloc] init];
    pickerView.delegate = self;
    pickerView.dataSource = self;
    pickerView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
//    [self.view addSubview:self.pickerView];
    
//    self.pickerView.font = [UIFont fontWithName:@"HelveticaNeue-Light" size:25];
//    self.pickerView.highlightedFont = [UIFont fontWithName:@"HelveticaNeue" size:35];
//    self.pickerView.interitemSpacing = 50.0;
    pickerView.fisheyeFactor = 0.001;
//    self.pickerView.pickerViewStyle = AKPickerViewStyle3D;
    pickerView.maskDisabled = false;

    return pickerView;
//    return nil;
}

RCT_CUSTOM_VIEW_PROPERTY(items, NSArray *, AKPickerView) {
    self._items = json;

    [view reloadData];
    [view selectItem:[self._selectedItem integerValue] animated: NO notifySelection: NO];
}

RCT_CUSTOM_VIEW_PROPERTY(selectedItem, NSInteger *, AKPickerView) {
    self._selectedItem = [NSNumber numberWithInteger: [json intValue]];
    
    if ([self._items count] > 0) {
        [view selectItem:[self._selectedItem integerValue] animated: NO notifySelection: NO];
    }
}

RCT_CUSTOM_VIEW_PROPERTY(spacing, NSInteger *, AKPickerView) {
    view.interitemSpacing = [json intValue];
}

RCT_CUSTOM_VIEW_PROPERTY(fontSize, NSInteger *, AKPickerView) {
    view.font = [UIFont fontWithName:@"HelveticaNeue-Light" size: [json intValue]];
}

RCT_CUSTOM_VIEW_PROPERTY(highlightedFontSize, NSInteger *, AKPickerView) {
    view.highlightedFont = [UIFont fontWithName:@"HelveticaNeue" size: [json intValue]];
}

RCT_CUSTOM_VIEW_PROPERTY(textColor, NSString *, AKPickerView) {
    view.textColor = [RNNumberSelector colorFromHexCode: json];
}

RCT_CUSTOM_VIEW_PROPERTY(highlightedTextColor, NSString *, AKPickerView) {
    view.highlightedTextColor = [RNNumberSelector colorFromHexCode: json];
}

RCT_CUSTOM_VIEW_PROPERTY(viewAnimation, NSInetger *, AKPickerView) {
    if ([json intValue] == 0) {
        view.pickerViewStyle = AKPickerViewStyleFlat;
    } else {
        view.pickerViewStyle = AKPickerViewStyle3D;
    }
}

+ (UIColor *) colorFromHexCode:(NSString *)hexString {
    NSString *cleanString = [hexString stringByReplacingOccurrencesOfString:@"#" withString:@""];
    if([cleanString length] == 3) {
        cleanString = [NSString stringWithFormat:@"%@%@%@%@%@%@",
                       [cleanString substringWithRange:NSMakeRange(0, 1)],[cleanString substringWithRange:NSMakeRange(0, 1)],
                       [cleanString substringWithRange:NSMakeRange(1, 1)],[cleanString substringWithRange:NSMakeRange(1, 1)],
                       [cleanString substringWithRange:NSMakeRange(2, 1)],[cleanString substringWithRange:NSMakeRange(2, 1)]];
    }
    if([cleanString length] == 6) {
        cleanString = [cleanString stringByAppendingString:@"ff"];
    }
    
    unsigned int baseValue;
    [[NSScanner scannerWithString:cleanString] scanHexInt:&baseValue];
    
    float red = ((baseValue >> 24) & 0xFF)/255.0f;
    float green = ((baseValue >> 16) & 0xFF)/255.0f;
    float blue = ((baseValue >> 8) & 0xFF)/255.0f;
    float alpha = ((baseValue >> 0) & 0xFF)/255.0f;
    
    return [UIColor colorWithRed:red green:green blue:blue alpha:alpha];
}


#pragma mark - AKPickerViewDataSource

- (NSUInteger)numberOfItemsInPickerView:(AKPickerView *)pickerView
{
    return [self._items count];
}

/*
 * AKPickerView now support images!
 *
 * Please comment '-pickerView:titleForItem:' entirely
 * and uncomment '-pickerView:imageForItem:' to see how it works.
 *
 */

- (NSString *)pickerView:(AKPickerView *)pickerView titleForItem:(NSInteger)item
{
    return [NSString stringWithFormat:@"%i", [self._items[item] intValue]];
}

/*
 - (UIImage *)pickerView:(AKPickerView *)pickerView imageForItem:(NSInteger)item
 {
 return [UIImage imageNamed:self.titles[item]];
 }
 */

#pragma mark - AKPickerViewDelegate

- (void)pickerView:(AKPickerView *)pickerView didSelectItem:(NSInteger)item
{
    NSDictionary *event = @{
                            @"target": pickerView.reactTag,
                            @"value": self._items[item],
                            @"name": @"tap"
                            };

    [self.bridge.eventDispatcher sendInputEventWithName:@"topChange" body:event];
}


/*
 * Label Customization
 *
 * You can customize labels by their any properties (except font,)
 * and margin around text.
 * These methods are optional, and ignored when using images.
 *
 */

/*
 - (void)pickerView:(AKPickerView *)pickerView configureLabel:(UILabel *const)label forItem:(NSInteger)item
 {
 label.textColor = [UIColor lightGrayColor];
 label.highlightedTextColor = [UIColor whiteColor];
 label.backgroundColor = [UIColor colorWithHue:(float)item/(float)self.titles.count
 saturation:1.0
 brightness:1.0
 alpha:1.0];
 }
 */

/*
 - (CGSize)pickerView:(AKPickerView *)pickerView marginForItem:(NSInteger)item
 {
 return CGSizeMake(40, 20);
 }
 */

#pragma mark - UIScrollViewDelegate

/*
 * AKPickerViewDelegate inherits UIScrollViewDelegate.
 * You can use UIScrollViewDelegate methods
 * by simply setting pickerView's delegate.
 *
 */

- (void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    // Too noisy...
    // NSLog(@"%f", scrollView.contentOffset.x);
}


@end
  
