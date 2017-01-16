package com.jenny.binding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jenny.database.Entity;
import com.jenny.myhome.R;
import com.jenny.myhome.databinding.EntityListItemBinding;

/**
 * Created by JennyPash on 1/15/2017.
 */

public class EntityArrayAdapter<T extends Entity> extends ArrayAdapter<Entity> {
    private ObservableArrayList<Entity> entities;

    public EntityArrayAdapter(Context context, int resource, ObservableArrayList<Entity> objects) {
        super(context, resource, objects);
        this.entities = objects;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        EntityListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.entity_list_item, parent, false);

        final Entity entity = entities.get(position);
        binding.setEntity(entity);

        View deleteButton = binding.getRoot().findViewById(R.id.delete_item);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListView listView = (ListView)parent;
                AdapterView.OnItemClickListener listener = listView.getOnItemClickListener();
                if (listener != null) {
                    listener.onItemClick(listView, view, position, view.getId());
                }
            }
        });

        View itemTextView = binding.getRoot().findViewById(R.id.item_text);
        itemTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListView listView = (ListView)parent;
                AdapterView.OnItemClickListener listener = listView.getOnItemClickListener();
                if (listener != null) {
                    listener.onItemClick(listView, view, position, view.getId());
                }
            }
        });

        return binding.getRoot();
    }
}
