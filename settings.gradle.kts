AutoMarkQuickShare/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── assets/
│       │   ├── logo_default.png
│       │   └── offline_cities_bbox.json
│       └── java/com/automark/quickshare/
│           ├── domain/
│           │   └── model/
│           │       ├── MediaCaptureEvent.kt
│           │       └── WatermarkTemplate.kt
│           ├── data/
│           │   ├── preferences/
│           │   │   └── WatermarkTemplateRepository.kt
│           │   ├── location/
│           │   │   └── OfflineGeolocationManager.kt
│           │   └── media/
│           │       ├── UniversalMediaContentObserver.kt
│           │       └── WatermarkRenderer.kt
│           ├── service/
│           │   ├── AutoMarkWatcherService.kt
│           │   ├── QuickShareNotificationHelper.kt
│           │   └── OemBackgroundKillerHelper.kt
│           └── ui/
│               └── MainActivity.kt



Layer & Modul

Komponen Utama

Spesifikasi Teknis & Kompatibilitas API 26–35

Media Watcher

UniversalMediaContentObserver.kt

Memantau MediaStore.Images & MediaStore.Video via ContentObserver, debounce delay 700 ms (rentang 500–1000 ms), validasi ukuran byte konstan untuk mencegah IO write lock, serta filter anti-loop (DCIM/AutoMark dan prefix AutoMark_).

Watermark Engine (Foto)

WatermarkRenderer.kt

Dekode & kanvas Bitmap.Config.ARGB_8888 berpresisi proporsional (12 MP, 50 MP, 64 MP, hingga 108 MP pada rasio 1:1, 4:3, 16:9, 9:16), penyalinan penuh tag ExifInterface, serta penyimpanan kompatibel ganda (RELATIVE_PATH + IS_PENDING di API 29+ dan File + MediaScannerConnection di API 26–28).

Watermark Engine (Video)

WatermarkRenderer.kt

Pipeline OpenGL/GPU hardware-accelerated via AndroidX Media3 Transformer dan OverlayEffect (BitmapOverlay + StaticOverlaySettings) yang menjaga resolusi dan framerate asli (30/60 FPS).

Offline Geolocation

OfflineGeolocationManager.kt

Ekstraksi GPS dari EXIF foto/video dengan fallback pasif ke FusedLocationProviderClient.getLastLocation() & LocationManager, dipetakan ke nama kota/kabupaten melalui SQLiteDatabase bounding-box + rumus Haversine 100% offline.

Template & Custom Builder

WatermarkTemplate.kt & WatermarkTemplateRepository.kt

data class WatermarkTemplate dengan serialisasi JSON (org.json.JSONObject) ke DataStore / SharedPreferences, mencakup 4 preset bawaan (Minimalist Corner, Modern Pill / Badge, Flagship / Studio Footer, Cinematic Letterbox) dan Custom Builder (TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, BOTTOM_BANNER).

Service, OEM Helper & Quick Share

AutoMarkWatcherService.kt, QuickShareNotificationHelper.kt, OemBackgroundKillerHelper.kt

Foreground Service persisten, panduan Intent Auto-Launch / Battery Saver untuk ColorOS (Oppo/Realme), MIUI/HyperOS (Xiaomi), dan OneUI (Samsung), serta Heads-Up Notification (NotificationCompat) untuk kirim instan ke IG Stories, WhatsApp, dan TikTok.
