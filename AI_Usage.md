# AI Usage Report — SIS 2 (CI Pipeline for Playwright Java)

## Model Used
- **Assistant / Model:** Gemini (Google AI)
- **Purpose:** Adapt the SIS 2 guide (written for Node.js / `@playwright/test`) to the **Java + Maven + JUnit 5 + Playwright Java** stack, configure the GitHub Actions workflow, update `BaseTest.java`, and diagnose test behavior in CI.

## Summary of Assistance & Changes

1. **Adaptation of the pipeline architecture (Node.js → Java/Maven):**
    - Instead of `actions/setup-node` and `npm ci`, `actions/setup-java@v4` was configured (JDK 23, `distribution: ‘temurin’`, `cache: ‘maven’`).
    - Instead of CLI sharding with `--shard=i/n`, parallel test sharding was implemented via `strategy.matrix` in GitHub Actions and Maven Surefire class filtering (`mvn test -Dtest=...`).
    - Instead of `playwright.config.ts`, the `isCI` and `BASE_URL` configurations have been moved to `BaseTest.java`.

2. **Update to `BaseTest.java`:**
    - Added a check for the `CI` environment variable (`Boolean.parseBoolean(System.getenv(“CI”))`).
    - Added a check for `BASE_URL` with a fallback to `https://practicesoftwaretesting.com`.
    - Added diagnostic logging of the `baseURL` used in `@BeforeEach` and the final URL + `page.title()` in `@AfterEach`.

3. **Troubleshooting Failures in GitHub Actions:**
    - Based on the logs `[CI] Final page URL: ... | Title: Just a moment...`, it was determined that the cause of the `TimeoutError` on GitHub Actions runners is Cloudflare’s anti-bot check on the public demo site `practicesoftwaretesting.com`, which blocks page rendering for the runners’ cloud IP addresses.
    - Increasing the timeout in CI mode up to 60 seconds (`Timeout 60000ms exceeded`) did not resolve the issue, confirming that the failure is caused by an active challenge screen rather than slow application loading or insufficient wait time. 
    - While local runs pass, CI runs fail on the initial element interaction (`getByTestId`), and Maven Surefire XML/text reports in `target/surefire-reports` are uploaded as CI artifacts (retained for 14 days) as evidence of execution and failure details.
