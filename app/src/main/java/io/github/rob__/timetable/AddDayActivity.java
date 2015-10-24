package io.github.rob__.timetable;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.w3c.dom.Text;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddDayActivity extends AppCompatActivity {

    @Bind(R.id.btnDoneDay) Button btnDoneDay;
    @Bind(R.id.btnAddLesson) Button btnNewTime;
    @Bind(R.id.timeLayout) LinearLayout timeLayout;

    Lesson lesson = new Lesson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_day);

        ButterKnife.bind(this);

        btnNewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //newTimeCard(AddDayActivity.this);

                final AlertDialog.Builder builder = new AlertDialog.Builder(AddDayActivity.this);
                final AlertDialog dialog;

                final View view = getLayoutInflater().inflate(R.layout.dialog_add_lesson, null);

                builder.setView(view);
                dialog = builder.create();

                final Spinner spnrTimeStart = (Spinner) view.findViewById(R.id.spnrTimeStart);
                final Spinner spnrTimeEnd = (Spinner) view.findViewById(R.id.spnrTimeEnd);

                Button btnDoneLesson = (Button) view.findViewById(R.id.btnDoneLesson);
                Button btnCancelLesson = (Button) view.findViewById(R.id.btnCancelLesson);

                final Lesson lesson = new Lesson();

                spnrTimeStart.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() != MotionEvent.ACTION_UP) return true;

                        Calendar now = Calendar.getInstance();

                        TimePickerDialog tpd = TimePickerDialog.newInstance(
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(RadialPickerLayout radialPickerLayout, int hour, int minute) {
                                        // Sets the adapter to set the text
                                        // of the spinner to the time chosen.
                                        final String[] times = {String.valueOf(hour) + ":" + (minute < 10 ? "0" + String.valueOf(minute) : String.valueOf(minute))};
                                        spnrTimeStart.setAdapter(new ArrayAdapter<>(AddDayActivity.this, android.R.layout.simple_spinner_item, times));
                                        lesson.setTimeStart(hour, minute);
                                    }
                                },
                                now.get(Calendar.HOUR_OF_DAY),
                                now.get(Calendar.MINUTE),
                                true
                        );

                        tpd.setTitle(getResources().getString(R.string.title_lesson_start));
                        tpd.show(getFragmentManager(), getResources().getString(R.string.title_lesson_start));

                        return true;
                    }
                });

                spnrTimeEnd.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() != MotionEvent.ACTION_UP) return true;

                        Calendar now = Calendar.getInstance();

                        TimePickerDialog tpd = TimePickerDialog.newInstance(
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(RadialPickerLayout radialPickerLayout, int hour, int minute) {
                                        // Sets the adapter to set the text
                                        // of the spinner to the time chosen.
                                        final String[] times = {String.valueOf(hour) + ":" + (minute < 10 ? "0" + String.valueOf(minute) : String.valueOf(minute))};
                                        spnrTimeEnd.setAdapter(new ArrayAdapter<>(AddDayActivity.this, android.R.layout.simple_spinner_item, times));
                                        lesson.setTimeEnd(hour, minute);
                                    }
                                },
                                now.get(Calendar.HOUR_OF_DAY),
                                now.get(Calendar.MINUTE),
                                true
                        );

                        tpd.setTitle(getResources().getString(R.string.title_lesson_end));
                        tpd.show(getFragmentManager(), getResources().getString(R.string.title_lesson_end));

                        return true;
                    }
                });

                btnDoneLesson.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lesson.setSubject(((EditText) view.findViewById(R.id.etSubject)).getText().toString());
                        lesson.setTeacher(((EditText) view.findViewById(R.id.etTeacher)).getText().toString());
                        lesson.setRoom(((EditText) view.findViewById(R.id.etRoom)).getText().toString());
                    }
                });

                btnCancelLesson.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        /*btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence subject = etSubject.getText();
                CharSequence teacher = etTeacher.getText();
                CharSequence room = etRoom.getText();

                if(lesson.getTimeEndTotalMinutes() - lesson.getTimeStartTotalMinutes() <= 0){
                    Toast.makeText(AddDayActivity.this, "The time the lesson ends needs to be after the time the lesson starts.", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(subject)
                        || TextUtils.isEmpty(teacher)
                        || TextUtils.isEmpty(room)){
                    Toast.makeText(AddDayActivity.this, "Subject, teacher and room need to be entered.", Toast.LENGTH_SHORT).show();
                } else {
                    lesson.setSubject(subject.toString());
                    lesson.setTeacher(teacher.toString());
                    lesson.setRoom(room.toString());
                }


            }
        });*/
    }

    /*
    public void newTimeCard(Context context, final Lesson lesson){
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
    /*
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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
    }*/

//    public void newTimeCard(Context context){
//        int margin = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
//        LayoutParams rlParamsWrap = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        LinearLayout.LayoutParams llParamsWrap = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//        TextView tvDismiss = new TextView(context);
//        tvDismiss.setLayoutParams(rlParamsWrap);
//        tvDismiss.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_clear_black_24dp, 0, 0, 0);
//
//        tvDismiss.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timeLayout.removeView((View) v.getParent().getParent());
//            }
//        });
//
//        Spinner spnrDay = new Spinner(context);
//        spnrDay.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.days)));
//        spnrDay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//
//        final Spinner spnrTimeStart = new Spinner(context);
//        spnrTimeStart.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, new String[]{"7:00"}));
//        spnrTimeStart.setLayoutParams(rlParamsWrap);
//
//        spnrTimeStart.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() != MotionEvent.ACTION_UP) return true;
//
//                Calendar now = Calendar.getInstance();
//
//                TimePickerDialog tpd = TimePickerDialog.newInstance(
//                        new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(RadialPickerLayout radialPickerLayout, int hour, int minute) {
//                                // Sets the adapter to set the text
//                                // of the spinner to the time chosen.
//                                final String[] times = {String.valueOf(hour) + ":" + (minute < 10 ? "0" + String.valueOf(minute) : String.valueOf(minute))};
//                                spnrTimeStart.setAdapter(new ArrayAdapter<>(AddDayActivity.this, android.R.layout.simple_spinner_item, times));
//                                lesson.setTimeStart(hour, minute);
//                            }
//                        },
//                        now.get(Calendar.HOUR_OF_DAY),
//                        now.get(Calendar.MINUTE),
//                        true
//                );
//
//                tpd.setTitle(getResources().getString(R.string.title_lesson_start));
//                tpd.show(getFragmentManager(), getResources().getString(R.string.title_lesson_start));
//
//                return true;
//            }
//        });
//
//        final Spinner spnrTimeEnd = new Spinner(context);
//        spnrTimeEnd.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, new String[]{"8:00"}));
//        spnrTimeEnd.setLayoutParams(rlParamsWrap);
//
//        spnrTimeEnd.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() != MotionEvent.ACTION_UP) return true;
//
//                Log.d("Dialog", "Displaying Time Picker Dialog for lesson start time.");
//                Calendar now = Calendar.getInstance();
//
//                TimePickerDialog tpd = TimePickerDialog.newInstance(
//                        new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(RadialPickerLayout radialPickerLayout, int hour, int minute) {
//                                // Sets the adapter to set the text
//                                // of the spinner to the time chosen.
//                                final String[] times = {String.valueOf(hour) + ":" + (minute < 10 ? "0" + String.valueOf(minute) : String.valueOf(minute))};
//                                spnrTimeEnd.setAdapter(new ArrayAdapter<>(AddDayActivity.this, android.R.layout.simple_spinner_item, times));
//                                lesson.setTimeEnd(hour, minute);
//                            }
//                        },
//                        now.get(Calendar.HOUR_OF_DAY),
//                        now.get(Calendar.MINUTE),
//                        true
//                );
//
//                tpd.setTitle(getResources().getString(R.string.title_lesson_end));
//                tpd.show(getFragmentManager(), getResources().getString(R.string.title_lesson_end));
//
//                return true;
//            }
//        });
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(margin, margin, margin, margin / 2);
//
//
//        CardView card = new CardView(new ContextThemeWrapper(context, R.style.CardViewStyle), null, 0);
//        card.setLayoutParams(params);
//
//        LinearLayout cardLayout = new LinearLayout(new ContextThemeWrapper(context, R.style.Widget_CardContent));
//        LinearLayout mainLayout = new LinearLayout(context);
//        LinearLayout spnrTimeLayout = new LinearLayout(context);
//
//        spnrTimeLayout.setOrientation(LinearLayout.HORIZONTAL);
//        spnrTimeLayout.addView(spnrTimeStart);
//        spnrTimeLayout.addView(spnrTimeEnd);
//
//        mainLayout.setOrientation(LinearLayout.VERTICAL);
//        mainLayout.setLayoutParams(llParamsWrap);
//        mainLayout.addView(spnrDay);
//        mainLayout.addView(spnrTimeLayout);
//
//        cardLayout.setOrientation(LinearLayout.HORIZONTAL);
//        cardLayout.addView(tvDismiss);
//        cardLayout.addView(mainLayout);
//
//        card.addView(cardLayout);
//        timeLayout.addView(card);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_lesson, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }
}
