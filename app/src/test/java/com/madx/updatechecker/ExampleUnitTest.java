package com.madx.updatechecker;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    public static class Item {
        public String id;
        public String value;

        public Item(String id, String value) {
            this.id = id;
            this.value = value;
        }
    }

    private static final String APPLICATION_ID = "com.fireflystudios.strongholdkingdoms"; // BuildConfig.APPLICATION_ID

    @Test
    public void getStartTime() throws IOException {
        String new_version = Jsoup.connect("https://play.google.com/store/apps/details?id=" + APPLICATION_ID + "&hl=it")
                .timeout(30000)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .get()
                .select(".W4P4ne div.hAyfc span.htlgb span.htlgb")
                .get(3)
                .ownText();
        assertNotNull(new_version);
        System.out.println("new_version: " + new_version);
    }

}