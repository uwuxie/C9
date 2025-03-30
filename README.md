<div align="center">
<img src='./app/src/main/ic_launcher-playstore.png' width=100>
</div>

---

# C9: Click on 9 keys
![GitHub release (latest by date)](https://img.shields.io/github/v/release/austinauyeung/C9) ![GitHub all releases](https://img.shields.io/github/downloads/austinauyeung/C9/total) ![License](https://img.shields.io/github/license/austinauyeung/C9) ![API Level](https://img.shields.io/badge/API-26%2B-brightgreen)

<div align="center">
<img src='./docs/imgs/Screenshot_20250319_213956.png' width=200>
<img src='./docs/imgs/Screenshot_20250319_214019.png' width=200>
</div>

C9 is a dual-cursor application that takes inspiration from T9 to provide clicks using the numpad on Android feature phones. Features of the application include:

- 🤖 Android 8.0+ support
- 🌎 Universal Android 11 compatibility via Shizuku as needed
- ⚡ Introduction of a grid cursor focused on efficiency
- 🖱️ Standard cursor to provide a traditional proxy for touchscreen gestures
- ⚙️ Remappable cursor activation keys and integration with button mappers
- 🔀 Translation of key presses into near-native taps, double taps, long press (and drag), scrolling, and zoom
- ✨ Additional features such as landscape orientation support and cursor auto-hide in text fields

## Table of Contents
- [Overview](#overview)
  - [Grid Cursor](#grid-cursor)
    - [Instructions](#instructions)
    - [Usage](#usage)
  - [Standard Cursor](#standard-cursor)
    - [Instructions](#instructions-1)
    - [Usage](#usage-1)
- [Recommendations](#recommendations)
- [Installation](#installation)
- [Troubleshooting](#troubleshooting)
- [Known Issues](#known-issues)
- [FAQs](#faqs)
- [License](#license)
- [Acknowledgment](#acknowledgments)

## Overview
Because of their different navigation paradigms, each cursor mode maps gestures uniquely as will be shown below. While both modes can be **enabled** simultaneously (by setting an activation key), only one cursor can be **active** at a time. As a final note, <ins>all buttons in the numpad and D-pad are generally reserved/intercepted while the cursor is active</ins>.

The following options can be configured, which affects scrolls and zooms in both modes:
- Natural scrolling
- Gesture visualizations
- Gesture style
  - `Fixed`: gestures are controlled and fixed distance
  - `Inertia`: gestures resemble touchscreen flicks
- Gesture duration
- Scroll distance

Additionally, the following options can be configured to adjust the behavior of the cursors:
- Auto-hide in text fields
  - This is application-dependent and may not work reliably.

### Grid Cursor
<br />

<div align="center">
<img src='./docs/gifs/Screen_recording_20250321_015105.gif' width=200>
</div>

<br />

The grid cursor trades precision for efficiency, taking advantage of the fact that many interactions with UI elements do not require pixel-by-pixel precision. `n` grid levels produce `9^n` points onscreen that can be reached with at most `n` numpad clicks. The visualizations below show the points that can be reached with two grid levels/clicks (81 points), three grid levels/clicks (729 points), and four grid levels/clicks (6561 points).

<br />

<div align="center">
<img src='./docs/imgs/Screenshot_20250319_003605.png' width=200>
<img src='./docs/imgs/Screenshot_20250319_003623.png' width=200>
<img src='./docs/imgs/Screenshot_20250319_003643.png' width=200>
</div>

<br />

The following options can be configured:
- Grid cursor activation key
- Number of grid levels
- Grid persistence after clicking in the final grid
- Grid opacity
- Grid number visibility
- Grid line visibility

#### Instructions
- The default activation key is the pound (#) key.
- To activate the grid cursor:
    - Hold the activation key.
    - Alternatively, you can use a button mapper to map the "Activate Grid Cursor" shortcut. However, an activation key must still be assigned.
- See the table below for gesture dispatch.
- When activated, press the activation key to quickly reset any grid back to the main grid.
- When activated, press any number to advance to the next subgrid.
- To deactivate, hold the activation key.
    - If you are using a button mapper, it may be possible to use your button mapper to deactivate the cursor as long as it does not conflict with buttons reserved and intercepted by the cursor.

#### Usage
| Gesture | Mapped buttons | Dispatch location | Advances grid |
| --- | --- | --- | --- |
| Navigate grid | Click numpad 1-9. | The selected number. | True |
| Tap | Click numpad 1-9 in the final grid level. | The center of the selected number's cell. | True |
| Tap | Click D-pad center. | If a number is first held, the center of its cell in the current grid. Else, center of the screen. | False |
| Double Tap | Double click D-pad center. | If a number is first held, the center of its cell in the current grid. Else, center of the screen. | False |
| Scroll | Click D-pad directions. Hold for continuous scrolling. | If a number is first held, the center of its cell in the current grid. Else, center of the screen. | False |
| Zoom | Click star (*) and numpad 0. | If a number is first held, the center of its cell in the current grid. Else, center of the screen. | False |

### Standard Cursor

<br />

<div align="center">
<img src='./docs/gifs/Screen_recording_20250319_004530.gif' width=300>
</div>

<br />

A standard cursor is included for actions requiring more precision and for those who strictly prefer a traditional pointer.

The following options can be configured:
- Standard cursor activation key
- Control scheme
  - Standard: D-pad moves, numpad scrolls
  - Swapped: D-pad scrolls, numpad moves
  - Toggle: D-pad scrolls and moves (toggle using activation key)
- Enable cursor wrap around
- Cursor speed
- Cursor acceleration: accelerated cursor speed when held
- Cursor acceleration threshold: duration after which cursor speed is accelerated
- Cursor size

#### Instructions
- The default activation key is the star (*) key.
- To activate the standard cursor:
    - Hold the activation key.
    - Alternatively, you can use a button mapper to map the "Activate Grid Cursor" shortcut. However, an activation key must still be assigned.
- See the table below for gesture dispatch.
- When activated and if in the toggle control scheme, press the activation key to toggle between cursor movement and scrolling.
- To deactivate, hold the activation key.
    - If you are using a button mapper, it may be possible to use your button mapper to deactivate the cursor as long as it does not conflict with buttons reserved and intercepted by the cursor.

#### Usage
All gestures are dispatched at the cursor's current location:

| Gesture | Mapped buttons |
| --- | --- |
| Cursor Movement | Click D-pad directions or numpad 2/4/6/8 (depends on control scheme). |
| Tap | Click D-pad center or numpad 5. |
| Double Tap | Double click D-pad center or numpad 5. |
| Long Press/Drag | Hold D-pad center or numpad 5 to long press, then move cursor to drag. Release D-pad center or numpad 5 to end the gesture. |
| Scroll | Click D-pad directions or numpad 2/4/6/8 (depends on control scheme). Hold for continuous scrolling. |
| Zoom | Numpad 1 and 3. |

### Recommendations
- For precise clicks, you can use a) grid cursor mode or b) standard cursor with a low cursor speed and high cursor acceleration.
- In the standard cursor mode with the standard control scheme, it may be easier to long press numpad 5 instead of D-pad center and then press one of the D-pad directions to long press and drag.
- If the device is in landscape orientation, the appropriate buttons in each mode can also rotate with the screen if `C9 > Rotate Buttons With Orientation` is enabled:
  - Grid cursor: D-pad rotates, numpad 1-9 rotates (along with the on-screen grid), zoom and activation remain unchanged
  - Standard cursor: D-pad rotates, numpad 2/4/6/8 rotates, zoom and activation remain unchanged
  - Example: If the device is rotated to the left, D-pad left becomes D-pad down.

## Installation
The latest version can be found under [releases](https://github.com/austinauyeung/C9/releases). You can use GitHub's `Watch > Custom > Releases` option to be notified of new releases.

### Option 1
Install using the standard package installer. Allow the accessibility service using the banner in the application.

### Option 2
Install using adb:
```
>> adb install path/to/apk
>> adb shell settings put secure enabled_accessibility_services com.austinauyeung.nyuma.c9/com.austinauyeung.nyuma.c9.accessibility.service.OverlayAccessibilityService
```

### Additional installation for Android 11
If you are on Android 11, please first try the application as-is. If gestures cannot be dispatched successfully, or if you have had trouble in the past with other cursor apps, you will need to [install Shizuku](https://shizuku.rikka.app/guide/setup/) to use this application. Once installed, navigate to, and enable, `C9 > Debug Options > Enable Shizuku Integration`.

Note that unless your device is rooted, you will need to restart the Shizuku service upon reboot.

## Troubleshooting
### Generating cursor logs
<div align="center">
<img src='./docs/gifs/Screen_recording_20250328_195409.gif' width=200>
</div>

If gestures do not work as expected, logs can be generated to help identify and fix any issues. Navigate to `C9 > Debug Options > Log Screen`, activate the cursor, and perform the corresponding gesture(s). Copy the logs and submit a GitHub issue. The example logs below correspond to the screen recording above.

```
--- SYSTEM INFORMATION ---
Device: Google sdk_gphone64_x86_64
Android Version: 15 (SDK 35)
Screen: 720 x 1232 pixels (density 2.0)

--- LOG ENTRIES ---
[19:53:58.362] [D] Key event: KeyEvent { action=ACTION_DOWN, keyCode=KEYCODE_EQUALS, scanCode=13, metaState=0, flags=0x8, repeatCount=0, eventTime=14387767052000, downTime=14387767052000, deviceId=0, source=0x301, displayId=-1 }
[19:53:58.665] [D] Overlay mode changed: NONE -> CURSOR
[19:53:58.669] [D] Cursor state changed: true
[19:53:58.682] [D] Overlay view created and added to window manager
[19:53:58.886] [D] Key event: KeyEvent { action=ACTION_UP, keyCode=KEYCODE_EQUALS, scanCode=13, metaState=0, flags=0x8, repeatCount=0, eventTime=14388291421000, downTime=14387767052000, deviceId=0, source=0x301, displayId=-1 }
[19:53:59.716] [D] Key event: KeyEvent { action=ACTION_DOWN, keyCode=KEYCODE_ENTER, scanCode=28, metaState=0, flags=0x8, repeatCount=0, eventTime=14389121655000, downTime=14389121655000, deviceId=0, source=0x301, displayId=-1 }
[19:53:59.717] [D] Starting tap gesture at (360.0, 640.0)
[19:53:59.718] [D] StandardGestureStrategy: starting tap at (360.0, 640.0)
[19:53:59.718] [D] Gesture paths changed: 1 paths
[19:53:59.746] [D] StandardGestureStrategy: start tap completed successfully
[19:53:59.771] [D] Gesture paths changed: 0 paths
[19:53:59.798] [D] Key event: KeyEvent { action=ACTION_UP, keyCode=KEYCODE_ENTER, scanCode=28, metaState=0, flags=0x8, repeatCount=0, eventTime=14389190365000, downTime=14389121655000, deviceId=0, source=0x301, displayId=-1 }
[19:53:59.799] [D] Ending tap at (360.0, 640.0)
[19:53:59.799] [D] StandardGestureStrategy: ending tap at (360.0, 640.0)
[19:53:59.808] [D] StandardGestureStrategy: end tap completed successfully
[19:54:01.307] [D] Key event: KeyEvent { action=ACTION_DOWN, keyCode=KEYCODE_EQUALS, scanCode=13, metaState=0, flags=0x8, repeatCount=0, eventTime=14390712608000, downTime=14390712608000, deviceId=0, source=0x301, displayId=-1 }
[19:54:01.609] [D] Overlay mode changed: CURSOR -> NONE
[19:54:01.612] [D] Cursor state changed: false
[19:54:01.626] [D] Overlay view removed
[19:54:01.875] [D] Key event: KeyEvent { action=ACTION_UP, keyCode=KEYCODE_EQUALS, scanCode=13, metaState=0, flags=0x8, repeatCount=0, eventTime=14391281290000, downTime=14390712608000, deviceId=0, source=0x301, displayId=-1 }
[19:54:02.231] [D] Key event: KeyEvent { action=ACTION_DOWN, keyCode=KEYCODE_DPAD_RIGHT, scanCode=106, metaState=0, flags=0x8, repeatCount=0, eventTime=14391636575000, downTime=14391636575000, deviceId=0, source=0x301, displayId=-1 }
[19:54:02.322] [D] Key event: KeyEvent { action=ACTION_UP, keyCode=KEYCODE_DPAD_RIGHT, scanCode=106, metaState=0, flags=0x8, repeatCount=0, eventTime=14391727695000, downTime=14391636575000, deviceId=0, source=0x301, displayId=-1 }
[19:54:02.869] [D] Key event: KeyEvent { action=ACTION_DOWN, keyCode=KEYCODE_DPAD_RIGHT, scanCode=106, metaState=0, flags=0x8, repeatCount=0, eventTime=14392275611000, downTime=14392275611000, deviceId=0, source=0x301, displayId=-1 }
[19:54:02.950] [D] Key event: KeyEvent { action=ACTION_UP, keyCode=KEYCODE_DPAD_RIGHT, scanCode=106, metaState=0, flags=0x8, repeatCount=0, eventTime=14392344310000, downTime=14392275611000, deviceId=0, source=0x301, displayId=-1 }
[19:54:03.460] [D] Key event: KeyEvent { action=ACTION_DOWN, keyCode=KEYCODE_ENTER, scanCode=28, metaState=0, flags=0x8, repeatCount=0, eventTime=14392865662000, downTime=14392865662000, deviceId=0, source=0x301, displayId=-1 }
[19:54:03.507] [D] Key event: KeyEvent { action=ACTION_UP, keyCode=KEYCODE_ENTER, scanCode=28, metaState=0, flags=0x8, repeatCount=0, eventTime=14392912821000, downTime=14392865662000, deviceId=0, source=0x301, displayId=-1 }

```

### Verifying Shizuku authorization
A green banner on the main page indicates that Shizuku authorization has been granted to C9. Only the third screenshot below indicates successful authorization.
<div align="center">
<img src='./docs/imgs/Screenshot_20250328_194724.png' width=200>
<img src='./docs/imgs/Screenshot_20250328_194745.png' width=200>
<img src='./docs/imgs/Screenshot_20250328_194815.png' width=200>
</div>

### Activation key stops working
If you are unable to deactivate the cursor, clear the activation key, which disables that cursor and hides any active cursor.

## Known Issues
- On the Vortex V3, the numpad backlight may not function when the cursor is active.
  - This is likely due to the cursors' interception of key presses. There is an experimental setting "Allow Passthrough" that may fix this at the expense of unintended behavior in the underlying application.
- Scrolling inconsistencies between applications (currently being investigated):
  - When using a gesture style of `Fixed`, some applications stutter at the end of the scroll.
  - When using a gesture style of `Inertia`, some applications show unexpected behavior when scrolling continuously.
- The cursor does not adapt to landscape orientation (a fix is planned for v1.3).

## FAQs
### Where can I make feature suggestions or report bugs?
Thanks for using and testing C9! You can use the [issues](https://github.com/austinauyeung/C9/issues) tab for both. For bugs, please provide logs using the built-in logger (see [Generating cursor logs](#generating-cursor-logs)) or `adb logcat C9App:V *:S`.

### How else can I contribute?
Please feel free to submit a pull request, create a video walkthrough, or provide anything else you think would be helpful!

### What is Shizuku?
Shizuku allows applications in general to perform actions that require elevated privileges. In C9, it is required to dispatch gestures on Android 11 using [InputManager](https://developer.android.com/reference/android/hardware/input/InputManager) instead of the standard dispatch using [AccessibilityService](https://developer.android.com/reference/android/accessibilityservice/AccessibilityService).

### What does it mean for the cursor to intercept button presses?
The cursors sit between your button presses and the underlying application. If a button is used by the cursor, the cursor will consume it and prevent the underlying application from receiving the button press.

### Why are button mappers allowed for activation but not deactivation?
As a result of the cursors' interception, there is no guarantee that there will not be conflict with your button mapper shortcut. Because of this, and for now, deactivation must be performed through the cursor.

## License
[Apache License Version 2.0](./LICENSE)

## Acknowledgments
- Allegra, [Arlie](./docs/imgs/IMG_5199.jpg), and [Nyuma](./docs/imgs/IMG_3226.jpg) for their support
- `sam-club` for extensive testing
- `Dev-in-the-BM` for testing and the Shizuku suggestion
- `anonymousfliphones` for testing