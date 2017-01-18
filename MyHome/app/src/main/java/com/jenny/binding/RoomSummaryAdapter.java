package com.jenny.binding;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jenny.database.Room;
import com.jenny.database.Subject;
import com.jenny.myhome.MyHomeApplication;
import com.jenny.myhome.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by JennyPash on 1/17/2017.
 */

public class RoomSummaryAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Room> listDataHeader;
    private HashMap<Room, RoomSummary> listDataChild;

    public RoomSummaryAdapter(Context context, List<Room> listDataHeader, HashMap<Room, RoomSummary> listDataChild) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Room room = (Room) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.room_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(room.toString());

        final ExpandableListView listView = ((ExpandableListView)parent);

        lblListHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView.isGroupExpanded(groupPosition)) {
                    listView.collapseGroup(groupPosition);
                } else {
                    listView.expandGroup(groupPosition, true);
                }
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdapterView.OnItemClickListener onItemClickListener = listView.getOnItemClickListener();
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(listView, v, groupPosition, v.getId());
                }
            }
        };

        View editButton = convertView.findViewById(R.id.edit_room_button);
        editButton.setOnClickListener(onClickListener);
        View deleteButton = convertView.findViewById(R.id.delete_room_button);
        deleteButton.setOnClickListener(onClickListener);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        RoomSummary roomSummary = (RoomSummary) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.room_list_item, null);
        }

        LinearLayout linearLayout = (LinearLayout)convertView.findViewById(R.id.subjects_linear_layout);
        linearLayout.removeAllViews();

        //if there are added subjects add textview for each one
        //else show empty message
        if (roomSummary.getTotalSubjects() > 0) {
            for (Subject subject : roomSummary.getSubjects()) {
                TextView tv = new TextView(linearLayout.getContext());
                tv.setText(subject.toString());

                linearLayout.addView(tv);
            }
        } else {
            TextView tv = new TextView(linearLayout.getContext());
            tv.setText(MyHomeApplication.getContext().getString(R.string.no_subjects_added));
            tv.setGravity(Gravity.CENTER_HORIZONTAL);

            linearLayout.addView(tv);
        }

        //show budget info according to budget balance
        TextView textTextView = (TextView)convertView.findViewById(R.id.summary_text);
        String text = roomSummary.getDiff() >= 0d ? MyHomeApplication.getContext().getString(R.string.budget_left)
                : MyHomeApplication.getContext().getString(R.string.exceed_budget);
        textTextView.setText(text);

        //set budget balance
        TextView diffTextView = (TextView)convertView.findViewById(R.id.summary_diff);
        diffTextView.setText(String.format("%.2f", roomSummary.getDiff()));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
