package com.jakefallin.wsw;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JakeFallin on 9/30/2016.
 */

public class RestaurantFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    int info;
    ScrollView scrollView;



    public static RestaurantFragment newInstance(int index) {
        RestaurantFragment rf = new RestaurantFragment();
        return rf;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle bundle = this.getArguments();
        info = bundle.getInt("fragmentInfo", 0);
        View view = inflater.inflate(R.layout.restaurant_fragment, container, false);
        TextView t = (TextView) view.findViewById(R.id.hours);
        t.setText("" + getRestaurantName(info));
        scrollView = (ScrollView) view.findViewById(R.id.scrollview);


        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListDetail = HoursData.getData(info);
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new HoursAdapter(view.getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        Button directions = (Button) view.findViewById(R.id.buttonDirections);
        directions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:40.7440, 74.0324?q=" + getRestaurantName(info));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        Button call = (Button) view.findViewById(R.id.buttonCall);
        call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getPhoneNumber(info)));
                startActivity(intent);
            }
        });

        ImageView imageView = (ImageView) view.findViewById(R.id.image);

        switch(info){
            case(0):
                Glide.with(this).load("http://i.imgur.com/YSwZZik.png").into(imageView);
                break;
            case(1):
                Glide.with(this).load("http://i.imgur.com/dJMbWde.png").into(imageView);
                break;
            case(2):
                Glide.with(this).load("http://i.imgur.com/vNYtc8o.png").into(imageView);
                break;
            case(3):
                Glide.with(this).load("http://i.imgur.com/AfbLnnS.png").into(imageView);
                break;
            case(4):
                Glide.with(this).load("http://i.imgur.com/dOUbv4o.png ").into(imageView);
                break;
        }

        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
                        try {
                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {


                                if(info < 4) {
                                    RestaurantFragment rf = new RestaurantFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("fragmentInfo", info + 1);
                                    rf.setArguments(bundle);
                                    getFragmentManager().beginTransaction()
                                            .replace(((ViewGroup) getView().getParent()).getId(), rf)
                                            .addToBackStack(null)
                                            .commit();

                                }
                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                if(info > 0) {
                                    RestaurantFragment rf = new RestaurantFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("fragmentInfo", info - 1);
                                    rf.setArguments(bundle);
                                    getFragmentManager().beginTransaction()
                                            .replace(((ViewGroup) getView().getParent()).getId(), rf)
                                            .addToBackStack(null)
                                            .commit();

                                }
                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });

        return view;

    }

    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }


    @Override
    public void onInflate(Context context, AttributeSet attrs,
                          Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
    }

    public String getRestaurantName(int i)
    {
        switch(i) {
            case(0):
                return "Benny Tudinos";
            case(1):
                return "Boardwalk";
            case(2):
                return "Flatbread Grill";
            case(3):
                return "Hansel n Griddle";
            case(4):
                return "Stacks Pancake House";
        }
        return "null";
    }

    public String getPhoneNumber(int i)
    {
        switch(i) {
            case(0):
                return "2017924132";
            case(1):
                return "2016830313";
            case(2):
                return "2016596560";
            case(3):
                return "2014200555";
            case(4):
                return "S2017105777";
        }
        return "null";
    }

}
