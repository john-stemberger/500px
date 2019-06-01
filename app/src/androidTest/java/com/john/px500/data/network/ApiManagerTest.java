package com.john.px500.data.network;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * this is an integration test and I want as few of these as possible.
 * I would only run these tests as part of the nightly build and not as part of a pull request
 * level required check. They take a long time and they can be flaky due to network issues.
 */
public class ApiManagerTest {

    private ApiManager manager;
    @Before
    public void setUp() throws Exception {
        manager = new ApiManager();
    }

    @After
    public void tearDown() throws Exception {
        manager = null;
    }

    @Test
    public void getPopularPhotos() {
        manager.getPhotos("popular");
    }
}