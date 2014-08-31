# ![Logo](https://raw.githubusercontent.com/danielemaddaluno/Android-Update-Checker/master/app/src/main/res/drawable-mdpi/ic_launcher.png) Android Update Checker [![Google Play](http://developer.android.com/images/brand/en_generic_rgb_wo_60.png)](https://play.google.com/store/apps/details?id=com.madx.updatechecker)

The project aims to provide a reusable instrument to check asynchronously if exists any newer released update of your app on the Store.

It uses the Java HTML Parser [Jsoup](http://jsoup.org/) to test if a new update really exists parsing the app page on the Google Play Store.

![Screenshot](https://raw.githubusercontent.com/danielemaddaluno/Android-Update-Checker/master/images/readme_info/readme_info_dark.png)


## Quick Setup

### 1. Include library

**Manual - Using [Android Studio](https://developer.android.com/sdk/installing/studio.html):**
 * Download the UpdateCheckerLib folder and import to your root application folder. 
You can manually achieve this step with 3 steps: 
    1. Paste the folder UpdateCheckerLib into your application at the same level of your app, build and gradle folder
    2. Add to your settings.gradle file the following code line:
    "include ':app', ':UpdateCheckerLib'"
    3. Rebuild the project
 * File -> Project Structure -> in Modules section click on "app" -> Click on tab "Dependecies" -> Click on the green plus -> Module Dependecy -> Select ":UpdateCheckerLib"
 * Done =)

or

**Manual - Using [Eclipse ADT](http://developer.android.com/sdk/index.html)**
* Download the UpdateCheckerLib folder and import into the workspace
* Right click on the project imported -> Properties -> Android -> Check the box "Is Library"
* Right click on the Main Project -> Properties -> Android -> Add -> Select UpdateCheckerLib -> OK


### 2. Android Manifest
``` xml
<manifest>
	<!-- Include following permission -->
	<uses-permission android:name="android.permission.INTERNET" />
	...
</manifest>
```

### 3. Activity class
``` java
public class DemoUpdateCheckerActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_update_checker);
        /* Use this when you want to run a background update check */
        new UpdateRunnable(this, new Handler()).start();
    }

    /** Called when the user clicks the button */
    public void forceUpdateTest(View view) {
        if(view.getId() == R.id.button_force_update_test){
            /* Use this if an update check is explicitly requested by a user action */
            new UpdateRunnable(this, new Handler()).force(true).start();
        }
    }
}
```

### 4. Proguard
If you are using Proguard you have to add the following lines to your Proguard rules:
``` java
-keep public class org.jsoup.** {
    public *;
}
```


## License

If you use Android Update Checker code in your application you should inform the author about it ( *email: daniele.maddaluno[at]gmail[dot]com* ) like this:
> **Subject:** AUC usage notification<br />
> **Text:** I use Android Update Checker &lt;lib_version> in &lt;application_name> - http://link_to_google_play.
> I [allow | don't allow] to mention my app in section "Applications using Android Update Checker" on GitHub.

Also I'll be grateful if you mention AUC in application UI with string **"Using Android Update Checker (c) 2014, Daniele Maddaluno"** (e.g. in some "About" section).

    Copyright 2014 Daniele Maddaluno

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.