package com.example.fingerprintvotingsystem.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fingerprintvotingsystem.R;

public class GridAdapter  extends BaseAdapter {
    Context context;
    String [] partyNames;
    String [] candidateNames;
    int [] imageIds;

    LayoutInflater inflater;

    public GridAdapter(Context context, String[] partyNames,String [] candidateNames, int[] imageIds) {
        this.context = context;
        this.partyNames = partyNames;
        this.imageIds = imageIds;
        this.candidateNames = candidateNames;
    }

    @Override
    public int getCount() {
        return candidateNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item,null);
        }
        ImageView candidateImage = convertView.findViewById(R.id.candidateImage);
        TextView candidateName= convertView.findViewById(R.id.candidateNameTextView);
        TextView partyName = convertView.findViewById(R.id.partyNameTextView);

        candidateImage.setImageResource(this.imageIds[position]);
        partyName.setText(this.partyNames[position]);
        candidateName.setText(this.candidateNames[position]);

        return convertView;
    }
}

