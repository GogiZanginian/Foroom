# Android 4 — Device Matrix

Test class: `com.example.foroom.tests.ConversationTests` (app `Foroom Training`, `debug` build)

The test scenarios were executed on each configured device separately. The same test class was also executed individually for every scenario

| # | Device / AVD | Environment | Android Version / API Level | Screen resolution | Scenario 1 — johnWeek | Scenario 2 — own chat | Scenario 3 — another account |
|---|---|---|---|---|---|---|---|
| 1 | Pixel 7 | Emulator | Android 15 / 35 | 1080x2400 | Pass | Pass | Pass |
| 2 | Samsung A6+ | Physical device | Android 10 / 29 | 1080x2220 | Pass | Pass | Pass |
| 3 | Medium Phone | Emulator | Android 14 / 34 | 1080x2400 | Pass | Pass | Pass |
| 4 | Pixel 6 | Emulator | Android 13 / 33 | 1080x2400 | Pass | Pass | Pass |

Test results were collected for all four Android configurations

Screenshots of the Android Studio test results: `screenshots/android4/`

### Additional note

No physical devices with Android 13 (API 33), Android 14 (API 34), or Android 15 (API 35) were available for testing. Therefore, the required configurations were tested using emulators

Additionally, I performed the tests on my available physical device, a Samsung A6+ running Android 10 (API 29), to provide an extra real-device test configuration