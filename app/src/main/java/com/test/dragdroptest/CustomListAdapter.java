package com.test.dragdroptest;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pooja Gaikwad on 6/22/2016.
 */
public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.CustomListViewHolder> {

    Context mContext;
    List<CustomList> mCustomList;

    public CustomListAdapter(Context context, List<CustomList> customList) {
        this.mCustomList = customList;
        this.mContext = context;
    }

    @Override
    public CustomListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_row, parent, false);
        return new CustomListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomListViewHolder holder, int position) {
        CustomList customList = mCustomList.get(position);

        holder.textName.setText(customList.name);
        holder.textAddress.setText(customList.address);
        holder.textMobile.setText(customList.mobile);

        holder.textEstimatedTime.setText("Estimated Time: " + customList.estimatedTime);

        holder.cardView.setTag(position);
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });
        holder.cardView.setOnDragListener(new DragListener());
    }

    @Override
    public int getItemCount() {
        return mCustomList.size();
    }

    public List<CustomList> getCustomList() {
        return mCustomList;
    }

    public void updateCustomList(List<CustomList> customList) {
        this.mCustomList = customList;
    }

    public class CustomListViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.textName)
        TextView textName;

        @Bind(R.id.textMobile)
        TextView textMobile;

        @Bind(R.id.textAddress)
        TextView textAddress;

        @Bind(R.id.textEstimatedTime)
        TextView textEstimatedTime;

        @Bind(R.id.cardView)
        CardView cardView;

        public CustomListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public class DragListener implements View.OnDragListener {

        boolean isDropped = false;

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundColor(Color.LTGRAY);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundColor(Color.YELLOW);
                    break;

                case DragEvent.ACTION_DROP:

                    isDropped = true;
                    int positionSource = -1;
                    int positionTarget = -1;

                    View viewSource = (View) event.getLocalState();

                    if (v.getId() == R.id.cardView) {
                        RecyclerView source = (RecyclerView) viewSource.getParent();
                        RecyclerView target = (RecyclerView) v.getParent();

                        CustomListAdapter adapterSource = (CustomListAdapter) source.getAdapter();
                        positionSource = (int) viewSource.getTag();
                        positionTarget = (int) v.getTag();

                        CustomList customList = (CustomList) adapterSource.getCustomList().get(positionSource);
                        List<CustomList> customListSource = adapterSource.getCustomList();

                        customListSource.remove(positionSource);
                        adapterSource.updateCustomList(customListSource);
                        adapterSource.notifyDataSetChanged();

                        CustomListAdapter adapterTarget = (CustomListAdapter) target.getAdapter();
                        List<CustomList> customListTarget = adapterTarget.getCustomList();
                        if (positionTarget >= 0) {
                            customListTarget.add(positionTarget, customList);
                        } else {
                            customListTarget.add(customList);
                        }
                        adapterTarget.updateCustomList(customListTarget);
                        adapterTarget.notifyDataSetChanged();
                        v.setVisibility(View.VISIBLE);
                    }

                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundColor(0);
                    break;

                default:
                    break;
            }

            if (!isDropped) {
                View vw = (View) event.getLocalState();
                vw.setVisibility(View.VISIBLE);
            }

            return true;
        }

    }
}
