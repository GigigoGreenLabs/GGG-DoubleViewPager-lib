package com.emoiluj.doubleviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.List;

public class DoubleViewPagerAdapter extends PagerAdapter {

  private Context mContext;
  private ArrayList<PagerAdapter> mAdapters;
  private List<VerticalViewPager> verticalViewPagerList = new ArrayList<>();

  public DoubleViewPagerAdapter(Context context, ArrayList<PagerAdapter> verticalAdapters) {
    mContext = context;
    mAdapters = verticalAdapters;
  }

  @Override public int getCount() {
    return mAdapters.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object view) {
    container.removeView((View) view);
  }

  @Override public Object instantiateItem(final ViewGroup container, int position) {
    VerticalViewPager childVP = new VerticalViewPager(mContext);
    childVP.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    childVP.setAdapter(mAdapters.get(position));
    container.addView(childVP);

    if (position < verticalViewPagerList.size()) {
      verticalViewPagerList.set(position, childVP);
    } else {
      verticalViewPagerList.add(childVP);
    }

    return childVP;
  }

  public VerticalViewPager getVerticalViewPager(int position) {
    if (verticalViewPagerList == null || verticalViewPagerList.size() <= position) {
      return null;
    }
    return verticalViewPagerList.get(position);
  }
}