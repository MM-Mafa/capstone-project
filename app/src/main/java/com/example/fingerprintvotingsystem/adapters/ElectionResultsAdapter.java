package com.example.fingerprintvotingsystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fingerprintvotingsystem.R;

public  class ElectionResultsAdapter extends BaseAdapter {

    Context context;
    String [] partyNames;
    int [] imageIds;
    String numberOfVotes;
    LayoutInflater inflater;

    public ElectionResultsAdapter(Context context, String[] partyNames, int[] imageIds, String numberOfVotes) {
        this.context = context;
        this.partyNames = partyNames;
        this.imageIds = imageIds;
        this.numberOfVotes = numberOfVotes;
    }



    @Override
    public int getCount() {
        return partyNames.length;
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
            convertView = inflater.inflate(R.layout.election_item,null);
        }
        ImageView candidateImage = convertView.findViewById(R.id.candidate_image_results);
        TextView _numberOfVotes= convertView.findViewById(R.id.number_of_votes_results);
        TextView partyName = convertView.findViewById(R.id.party_name_results);

        candidateImage.setImageResource(this.imageIds[position]);
        partyName.setText( "party name: "+this.partyNames[position]);
        _numberOfVotes.setText("Votes: "+this.numberOfVotes);
        return convertView;
    }
}