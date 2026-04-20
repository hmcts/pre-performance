import { Page } from '@playwright/test';


export const config = {
  target: 'cvp conference url goes here',
  engines: {
    playwright: {
      launchOptions: {
        headless: true,
        browserName: "chromium",
        args: [
          "--use-fake-ui-for-media-stream",
          "--use-fake-device-for-media-stream",
          "--allow-file-access-from-files"
        ],
        permissions: ["camera", "microphone"],
        timeout: 800000,
        actionTimeout: 60000,
        navigationTimeout: 60000
      },
      maxVusers: 20 ,
      thinkTime: 7000
    },
  },
  payload: {
    path: 'roomandpin_new.csv',
    fields: ['room','pin'],
    order: 'sequence',
    skipHeader: true
  },
  config: {
    ensure: true
  },
  phases: [
    { duration: 240, arrivalCount: 10, timeUnit: "10s",  maxVusers: 10, name: 'Run exactly 10 users' } // change maximum users and increase duration as needed
  ]
};

 
export const scenarios = [{
  engine: 'playwright',
  name: 'Join cvp Call for performance test',
  testFunction: cvpjoin, 
  think: 7
}
],plugins=  {
    playwright: {
      testFunction: {
        cvpjoin 
      }
    }
  };


  export async function cvpjoin( page: Page, vars?: any) {

  const nestedVars = vars?.vars ?? {};
  const room: string = nestedVars?.room ?? 'defaultRoom';
  const pin: string = nestedVars?.pin ?? '0000';
  console.log("Testing room:", room, "with PIN:", pin);
  try 
  {
  const url = 'cvp conference url goes here';
  await page.goto('cvp conference url' + room + '@staging.cvp.call.vc');
  await page.waitForTimeout(5000);
  console.log("navigating to test page.....................");
  await page.getByPlaceholder('Type your name here').fill('performance');
  await page.getByRole('button', { name: 'Connect' }).click();
  console.log("clicking on connect button.....................");
  const isPresent = await page.locator('#deviceChangeOK').count();
  console.log('Button exists:', isPresent > 0);
  await page.waitForTimeout(10000);
  await grantPermissionsWithRetry(page);
  //await page.context().grantPermissions(['camera', 'microphone']);
  await page.waitForSelector('#deviceChangeModal', { state: 'visible' });
  await page.waitForSelector('#deviceChangeOK', { state: 'visible' });
  await page.locator('#deviceChangeOK').scrollIntoViewIfNeeded();
  await page.click('#deviceChangeOK', { force: true }); 
  await page.waitForTimeout(7000);
  await page.getByRole('button', { name: 'Connect' }).click();
  await page.getByText('Host').click();
  await page.getByPlaceholder('Enter the host pin here').fill(String(pin));
  await page.getByRole('button', { name: 'Connect' }).click();
  await page.waitForTimeout(145000);
  await page.getByLabel('Disconnect from the call').click();
  await page.context().close();

  }
  catch (error) {
    console.log("Error:", error);
    console.error(`Error for room: ` + room + "error is....." + error);
    await page.screenshot({ path: `failure-screenshots/` +room + `_error.png`, fullPage: true });
  } finally {
    await page.context().close();
  }
}

async function grantPermissionsWithRetry(page: Page, retries = 5) {
  for (let attempt = 1; attempt <= retries; attempt++) {
      try {
          await page.context().grantPermissions(['camera', 'microphone']);
          console.log('Permissions granted successfully');
          return;
      } catch (error) {
          console.error(`Attempt ${attempt} to grant permissions failed:`, error);
          if (attempt === retries) throw error; // Fail after max retries
          await page.waitForTimeout(5000); // Wait 1 second before retrying
      }
  }
}