Download code from GitHub:

1. Pull the source (Portal_Details) from Github. Refer "GitHub_Help_Windows.txt" under "https://github.com/rpx/qaTestcases.git"
ex: D:\RPX\QA-TestNG

2. Source will be available in Portal_Details folder.


Steps to run selenium tests:

Method 1:
Pre-Requisite:
1. Install eclipse IDE.You can download IDE from any of the packages listed at http://www.eclipse.org/downloads/packages/

ex: Eclipse Standard 4.3

2. Launch Eclipse and select the parent directory as workspace.

ex: D:\RPX\QA-TestNG

3. Import the source

3.1. File -->Import -->Expand General -->Existing Projects in Workspace.
3.2. Select root directory -->Browse for the project "Portal_Details".

Project will be listed whitespace area of projects.

3.3. Finish



4. Follow below steps so that Eclipse identifies TestNG.

For the Eclipse plug-in, we suggest using the update site:

Select Help / Software updates / Find and Install.
Search for new features to install.
New remote site.
For Eclipse 3.4 and above, enter http://beust.com/eclipse.
For Eclipse 3.3 and below, enter http://beust.com/eclipse1.
Make sure the check box next to URL is checked and click Next.
Eclipse will then guide you through the process.

5. To execute the tests in chrome browser, path needs to be set in System Environment Vaiables (for Windows).
System-->Properties-->Advanced system settings-->Advanced Tab(Environment Variables)-->System Variables
Add Path to Chromedriver.

Path to chromedriver-->Right click on Chromedriver folder listed under Java project -->Properties-->Copy path from Location and add it under "PATH" variable.


6.Expand src folder to view package name that ends with pages.tests

7. To run the tests for Dashboard page :
1. Select "TestDashBoardPage.java" -->right click-->Run as -->TestNG Test.
OR
1. Click on "Run" or "play" button in top menu.

Note: Only tests that are marked for execution in test data xls will only run.





