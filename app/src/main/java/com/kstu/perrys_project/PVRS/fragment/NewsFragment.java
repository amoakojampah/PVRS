package com.kstu.perrys_project.PVRS.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kstu.perrys_project.PVRS.Constants;
import com.kstu.perrys_project.PVRS.NewsDetailActivity;
import com.kstu.perrys_project.PVRS.R;
import com.kstu.perrys_project.PVRS.model.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        //initialize recyclerview and FIrebase objects
        recyclerView = view.findViewById(R.id.news_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        mDatabase = FirebaseDatabase.getInstance().getReference().child(Constants.REFERENCE_CHILD_NEWS);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("News");
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Post, NewsViewHolder> FBRA = new FirebaseRecyclerAdapter<Post, NewsViewHolder>(
                Post.class,
                R.layout.news_card_items,
                NewsViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(NewsViewHolder viewHolder, final Post model, int position) {
                final String post_key = getRef(position).getKey();
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getContent());
                viewHolder.setImageUrl(getContext(), model.getImageUrl());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                        intent.putExtra("newsContent", model.getContent());
                        intent.putExtra("imageUrl", model.getImageUrl());
                        startActivity(intent);

                    }
                });
            }
        };
        recyclerView.setAdapter(FBRA);
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = mView.findViewById(R.id.post_title_txtview);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_desc = mView.findViewById(R.id.post_desc_txtview);
            post_desc.setText(desc);
        }

        public void setImageUrl(Context ctx, String imageUrl) {
            ImageView post_image = mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(imageUrl).into(post_image);
        }
    }


}
