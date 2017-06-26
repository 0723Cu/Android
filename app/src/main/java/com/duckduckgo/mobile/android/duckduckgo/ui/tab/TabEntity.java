package com.duckduckgo.mobile.android.duckduckgo.ui.tab;

import android.graphics.Bitmap;

import com.duckduckgo.mobile.android.duckduckgo.domain.tab.Tab;

import java.util.UUID;

/**
 * Created by fgei on 6/14/17.
 */

public class TabEntity implements Tab {

    private String id;
    private String title = "";
    private String currentUrl = "";
    private boolean canGoBack = false;
    private boolean canGoForward = false;
    private Bitmap favicon;

    private TabEntity() {

    }

    public TabEntity(Tab tab) {
        id = tab.getId();
        title = tab.getTitle();
        currentUrl = tab.getCurrentUrl();
        canGoBack = tab.canGoBack();
        canGoForward = tab.canGoForward();
    }

    public static TabEntity create() {
        TabEntity tab = new TabEntity();
        tab.id = UUID.randomUUID().toString();
        return tab;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    @Override
    public boolean canGoBack() {
        return canGoBack;
    }

    public void setCanGoBack(boolean canGoBack) {
        this.canGoBack = canGoBack;
    }

    @Override
    public boolean canGoForward() {
        return canGoForward;
    }

    public void setCanGoForward(boolean canGoForward) {
        this.canGoForward = canGoForward;
    }

    public Bitmap getFavicon() {
        return favicon;
    }

    public void setFavicon(Bitmap favicon) {
        this.favicon = favicon;
    }
}
