package io.github.rob__.timetable;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.pager) ViewPager pager;
    @Bind(R.id.tabs) PagerSlidingTabStrip tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        pager.setAdapter(new TimeTableAdapter(getSupportFragmentManager()));
        tabs.setViewPager(pager);

        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        pager.setCurrentItem(day == 1 || day == 7 ? 0 : day - 2);

        if(!PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getBoolean("init", false)){
            this.startActivity(new Intent(this, AddDayActivity.class));
        }
    }

    private class TimeTableAdapter extends FragmentPagerAdapter {
        String[] titles = getResources().getStringArray(R.array.days);

        public TimeTableAdapter(FragmentManager fm)                 { super(fm); }
        @Override public Fragment getItem(int pos)                  { return DayFragment.newInstance(); }
        @Override public int getCount()                             { return titles.length; }
        @Override public CharSequence getPageTitle(int position)    { return titles[position]; }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if(id == R.id.action_add_lesson){
            //MainActivity.this.startActivity(new Intent(MainActivity.this, AddDayActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
