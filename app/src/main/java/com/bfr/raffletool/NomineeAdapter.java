package com.bfr.raffletool;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NomineeAdapter extends RecyclerView.Adapter<NomineeAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView commentTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.tvName);
            commentTextView = (TextView) itemView.findViewById(R.id.tvComment);
        }
    }

    private List<Nomination> mNominations;
    private Context mContext;

    public NomineeAdapter(Context context, List<Nomination> nominations) {
        mNominations = nominations;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View nomineeView = inflater.inflate(R.layout.item_nominee, parent, false);

        ViewHolder viewHolder = new ViewHolder(nomineeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Nomination nominee = mNominations.get(position);

        TextView textView = holder.nameTextView;
        textView.setText(nominee.getNominee());
        TextView commentTextView = holder.commentTextView;
        commentTextView.setText(nominee.getComment());
    }

    @Override
    public int getItemCount() {
        return mNominations.size();
    }
}
