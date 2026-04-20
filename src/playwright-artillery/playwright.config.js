import { defineConfig } from '@playwright/test';

export default defineConfig({
  testDir: './', // Directory for test files
  timeout: 60000, // 60 seconds timeout per test
  use: {
    headless: false, // Set to true for CI/CD or non-UI tests
    browserName: 'chromium', // Choose 'chromium', 'firefox', or 'webkit'
    launchOptions: {
      args: [
        '--use-fake-ui-for-media-stream', // Fake UI for camera/mic permissions
        '--use-fake-device-for-media-stream', // Fake input devices for WebRTC
        '--disable-features=WebRtcHideLocalIpsWithMdns', // Avoids WebRTC IP masking
      ],
    },
    viewport: { width: 1280, height: 720 }, // Default viewport size
    ignoreHTTPSErrors: true, // Ignore HTTPS certificate errors
    permissions: ['camera', 'microphone'], // Grant permissions for WebRTC tests
    video: 'on-first-retry', // Record video only on first failure
    screenshot: 'only-on-failure', // Take screenshots only if the test fails
    trace: 'on', // Enable tracing for debugging
  },
  projects: [
    {
      name: 'Chromium',
      use: { browserName: 'chromium' },
    }
  ],
});