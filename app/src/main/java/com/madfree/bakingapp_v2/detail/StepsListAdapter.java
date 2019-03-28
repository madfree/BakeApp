package com.madfree.bakingapp_v2.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madfree.bakingapp_v2.R;
import com.madfree.bakingapp_v2.data.model.Step;

import java.util.List;

public class StepsListAdapter extends RecyclerView.Adapter<StepsListAdapter.StepViewHolder> {

    public static final String LOG_TAG = StepsListAdapter.class.getSimpleName();

    private final Context mContext;
    final private ItemClickListener mListener;
    private List<? extends Step> mStepsList;

    public StepsListAdapter(Context context, ItemClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.step_list_item, viewGroup, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder stepViewHolder, int position) {
        stepViewHolder.stepNameView.setText(mStepsList.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (mStepsList == null) {
            return 0;
        }
        return mStepsList.size();

    }

    public void setRecipes(List<? extends Step> stepsList) {
        mStepsList = stepsList;
        Log.d(LOG_TAG, "number of recipes in adapter is: " + mStepsList.size());
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(Step clickedStep);
    }

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView stepNameView;

        StepViewHolder(@NonNull View itemView) {
            super(itemView);
            stepNameView = itemView.findViewById(R.id.stepNameTv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = getAdapterPosition();
            Step step = mStepsList.get(elementId);
            mListener.onItemClickListener(step);
        }
    }
}

