package com.jenny.binding;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jenny.database.Entity;
import com.jenny.database.Project;
import com.jenny.database.Subject;
import com.jenny.myhome.Constants;
import com.jenny.myhome.HomeActivity;
import com.jenny.myhome.MyHomeApplication;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        EntityListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.entity_list_item, parent, false);

        final Entity entity = entities.get(position);
        binding.setEntity(entity);

        Button deleteButton = (Button)binding.getRoot().findViewById(R.id.delete_item);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyHomeApplication.getDatabase().delete(entity) > 0) {
                    entities.remove(entity);
                }
            }
        });

        TextView itemTextView = (TextView)binding.getRoot().findViewById(R.id.item_text);
        itemTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (entity.getClass() == Project.class) {
                    Intent intent = new Intent(view.getContext(), HomeActivity.class);
                    intent.putExtra(Constants.PROJECT_ID, entity.getId());
                    view.getContext().startActivity(intent);
                }

                if (entity.getClass() == Subject.class) {
                    ///Intent intent = new Intent(view.getContext(), SubjectActivity.class);
                }
            }
        });

        return binding.getRoot();
    }
}