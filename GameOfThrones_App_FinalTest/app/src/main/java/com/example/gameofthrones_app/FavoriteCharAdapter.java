package com.example.gameofthrones_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteCharAdapter extends RecyclerView.Adapter<FavoriteCharAdapter.TasksViewHolder> implements NetworkingClass.APIDataListner {

    private Context mCtx;
    private List<Character> characterList;
    Bitmap bitmap;

    public FavoriteCharAdapter(Context mCtx, List<Character> characterList) {
        this.mCtx = mCtx;
        this.characterList = characterList;
    }

    @Override
    public com.example.gameofthrones_app.FavoriteCharAdapter.TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_favorite_list, parent, false);
        return new com.example.gameofthrones_app.FavoriteCharAdapter.TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        Character t = characterList.get(position);
        holder.favoriteCharacterView.setText(t.getCharacterName());

        if ( t.getActorName() == null)
            holder.favoriteActorView.setText("No actor name");
        else
            holder.favoriteActorView.setText(t.getActorName());

        if(t.getCharacterImageThumb() != null) {
            NetworkingClass networkingClass = new NetworkingClass(this, mCtx);
            String imageURL = t.getCharacterImageThumb();

            networkingClass.getImageFromURL(imageURL);

            holder.favoriteImageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    @Override
    public void returnAPIData(String data) {
    }

    @Override
    public void returnBitmapImage(Bitmap image) {
        bitmap = image;
    }

    class TasksViewHolder extends RecyclerView.ViewHolder {

        TextView favoriteCharacterView, favoriteActorView;
         ImageView favoriteImageView;

        public TasksViewHolder(View itemView) {
            super(itemView);
            favoriteCharacterView = itemView.findViewById(R.id.favoriteCharacter);
            favoriteActorView = itemView.findViewById(R.id.favoriteActor);
            favoriteImageView = itemView.findViewById(R.id.favoriteImage);

        }

   }
}


