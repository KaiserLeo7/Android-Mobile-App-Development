package com.example.gameofthrones_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.TasksViewHolder>  {

    public interface OnItemClickListener {
        void onItemClick(Character item);
        void onImageClicked(Character item);
    }

    private Context mCtx;
    private List<Character> characterList;
    OnItemClickListener listner;

    public CharacterAdapter(Context mCtx, List<Character> characterList) {
        this.mCtx = mCtx;
        this.characterList = characterList;
        this.listner = listner;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_character, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Character t = characterList.get(position);
        holder.characterTextView.setText(t.getCharacterName());

        if ( t.getActorName() == null)
            holder.actorTextView.setText("No actor name");
        else
            holder.actorTextView.setText(t.getActorName());

        if (t.getCharacterImageThumb() == null)
            holder.imageText.setText("no image");
        else
            holder.imageText.setText("character image");

        // update to check the db.
        if (t.isFav()){
            holder.isFavoriteImage.setImageResource(R.drawable.like);
        }
        else
            holder.isFavoriteImage.setImageResource(R.drawable.unliked);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView characterTextView;
        TextView actorTextView;
        TextView imageText;
        ImageView isFavoriteImage;

        public TasksViewHolder(View itemView) {
            super(itemView);

            characterTextView = itemView.findViewById(R.id.character);
            actorTextView = itemView.findViewById(R.id.actor);
            imageText = itemView.findViewById(R.id.image);
            isFavoriteImage = itemView.findViewById(R.id.favorit_icon);
            imageText.setOnClickListener(this);
            isFavoriteImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.image:
                Character character = characterList.get(getAdapterPosition());
                listner.onItemClick(character);
                break;

                case R.id.favorit_icon:
                    System.out.println("image clicked");
                    Character character2 = characterList.get(getAdapterPosition());
                    listner.onImageClicked(character2);
                    isFavoriteImage.setImageResource(R.drawable.like);
                    break;
            }
        }
    }
}
