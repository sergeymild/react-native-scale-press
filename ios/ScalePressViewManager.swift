import UIKit
import React

let SKIP_SCALE_PRESS = "skipScalePress"

@objc(ScalePressViewManager)
class ScalePressViewManager: RCTViewManager {
    override class func requiresMainQueueSetup() -> Bool {
        return true
    }
    
    override func view() -> UIView! {
        return ScalePress()
    }
    
    override func shadowView() -> RCTShadowView! {
        return RCTShadowView()
    }
}

class ScalePress: UIView {
    
    @objc
    private var onPress: RCTBubblingEventBlock?
    @objc
    private var onLongPress: RCTBubblingEventBlock? {
        didSet {
            let tap = UILongPressGestureRecognizer(target: self, action: #selector(didTap(_:)))
            addGestureRecognizer(tap)
        }
    }
    @objc
    private var scale: NSNumber?
    @objc
    private var durationIn: NSNumber?
    @objc
    private var durationOut: NSNumber?
    
    @objc
    func didTap(_ gesture: UIGestureRecognizer) {
        
        switch gesture.state {
        case .began:
            onLongPress?([:])
        case .ended:
            scaleToNormal()
        default:
            break
        }
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        super.touchesBegan(touches, with: event)

        var shouldSkip = false
        let touch = touches.first
        var targetView = touch?.view
        while (targetView != nil) {
            if targetView?.accessibilityLabel == SKIP_SCALE_PRESS {
                shouldSkip = true
                break
            }
            targetView = targetView?.superview
        }

        if shouldSkip { return }

        let dur = (durationIn?.doubleValue ?? 150.0) / 1000.0
        let sc = scale?.doubleValue ?? 0.95
        UIView.animate(withDuration: dur, delay: 0, options: [.allowUserInteraction]) {
            self.transform = CGAffineTransform(scaleX: sc, y: sc)
        }
    }

    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        super.touchesEnded(touches, with: event)
        if self.transform == .identity { return }
        let dur = (durationOut?.doubleValue ?? 150.0) / 1000.0
        UIView.animate(withDuration: dur) {
            self.transform = .identity
        }

        let touch = touches.first
        guard let location = touch?.location(in: touch?.view) else { return }
        guard let converted = touch?.view?.convert(location, to: self) else { return }

        if self.point(inside: converted, with: event) {
            onPress?([:])
        }
    }

    override func touchesCancelled(_ touches: Set<UITouch>, with event: UIEvent?) {
        super.touchesCancelled(touches, with: event)
        scaleToNormal()
    }
    
    func scaleToNormal() {
        if self.transform == .identity { return }
        let dur = (durationOut?.doubleValue ?? 500.0) / 1000.0
        UIView.animate(withDuration: dur) {
            self.transform = .identity
        }
    }
}
