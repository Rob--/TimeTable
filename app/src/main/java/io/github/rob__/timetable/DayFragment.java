package io.github.rob__.timetable;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class DayFragment extends android.support.v4.app.Fragment {

    View v;
    int margin;
    CardView card;
    LinearLayout cardLayout;
    TextView tvSubject;
    TextView tvTime;
    ImageView ivSubject;
    LinearLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_day, container, false);
        margin = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
        layout = (LinearLayout) v.findViewById(R.id.layout);

        /*

            [
                {
                    subject: string",
                    time: time,
                    break: boolean,
                    week: int
                }
            ]

         */

        String[] subjects = {"Maths", "Science", "Geography", "English", "Computer Science"};
        String[] times = {"9:00 am", "10:00 am", "11:25 am", "12:25 pm", "2:00 pm"};

        final CardView[] cv = new CardView[5];

        for(int i = 0; i < subjects.length; i++){
            if(i == 2 || i == 4){
                View sep = new View(getContext());
                sep.setBackgroundColor(Color.parseColor("#A1A1A1"));
                LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 4);
                llParams.setMargins(margin, margin / 2, margin, margin / 4);
                sep.setLayoutParams(llParams);
                layout.addView(sep);
            }
            cardLayout = new LinearLayout(new ContextThemeWrapper(getContext(), R.style.Widget_CardContent));

            // Subject text view
            tvSubject = new TextView(getContext());
            tvSubject.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            tvSubject.setTextAppearance(getActivity(), R.style.TextAppearance_AppCompat_Title);
            tvSubject.setText(subjects[i]);

            // Sub-heading text view
            tvTime = new TextView(getContext());
            tvTime.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            tvTime.setText(times[i]);

            // Icon
            /*ivSubject = new ImageView(getContext());
            RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(120, 120);
            rlParams.addRule(RelativeLayout.RIGHT_OF, tvSubject.getId());
            ivSubject.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.maths));
            ivSubject.setLayoutParams(rlParams);*/

            // Card layout
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(margin, margin, margin, margin / 2);
            card = new CardView(new ContextThemeWrapper(getContext(), R.style.CardViewStyle), null, 0);
            card.setLayoutParams(params);

            // Add widgets
            cardLayout.addView(tvSubject);
            cardLayout.addView(tvTime);
            //cardLayout.addView(ivSubject);

            //card.setBackgroundColor(Color.parseColor("#bdc3c7"));
            //card.setBackgroundColor(Color.parseColor("#80FFFFFF"));
            //tvSubject.setTextColor(Color.parseColor("#80000000"));
            //tvTime.setTextColor(Color.parseColor("#80000000"));

            card.addView(cardLayout);
            cv[i] = card;

            layout.addView(card);
        }

        /*v.findViewById(R.id.scrollView).setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                double pct = (double) scrollY / ((double) ((ScrollView) v).getMaxScrollAmount());

                for(int i = 0; i < cv.length; i++){
                    cv[i].setCardElevation(5 + (float) (20 * pct));
                }
            }
        });*/

        return v;
    }

    // pos = index of tab, correlates to days
    public static DayFragment newInstance() {
        return new DayFragment();
    }
}
