package com.sasalatti.vinay.pdf_viewpager.pdf;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by vinay on 5/17/16.
 */
public class PdfpaperFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();

    public PdfpaperFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((PdfpaperItemFragment) fragments.get(position)).getTitle();
    }

    public static class Holder {
        private final List<Fragment> fragments = new ArrayList<>();
        private FragmentManager manager;

        public Holder(FragmentManager manager) {
            this.manager = manager;
        }

        public Holder add(Fragment f) {
            fragments.add(f);
            return this;
        }

        public PdfpaperFragmentAdapter set() {
            return new PdfpaperFragmentAdapter(manager, fragments);
        }
    }
}
