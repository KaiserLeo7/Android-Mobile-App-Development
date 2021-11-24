package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.TasksViewHolder> {

    interface AlertDialogListener {
        public void alertDialogFinishSavingCar (Car insertedCar);
    }

    private Context context;
    static private List<Car> CarList;
    AlertDialogListener listener;

    public CarAdapter(Context context, List<Car> carListNew){
        this.context = context;
        this.CarList = carListNew;
        listener = (AlertDialogListener) context;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_car, parent, false);
        return new TasksViewHolder(view);
    }

    //attach car data to views
    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {

        Car car = CarList.get(position);
        holder.carMakeView.setText(car.getCarModel1());
        holder.carModelView.setText(car.getCarModel2());

        if (car.getIsFavorite()){
            holder.favoriteView.setImageResource(R.drawable.like);
        } else {
            holder.favoriteView.setImageResource(R.drawable.unlike);
        }
    }

    @Override
    public int getItemCount() {
        return CarList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView carMakeView, carModelView;
        ImageView favoriteView;

        //get View references
        public TasksViewHolder(View itemView) {
            super(itemView);

            carMakeView = itemView.findViewById(R.id.carMake);
            carModelView = itemView.findViewById(R.id.carModel);
            favoriteView = itemView.findViewById(R.id.favorite);
            itemView.setOnClickListener(this);
        }

        //Alerts onClick
        @Override
        public void onClick(View view) {
            Car car = CarList.get(getAdapterPosition());

            if(!car.getIsFavorite()) {
                new AlertDialog.Builder(context)
                        .setTitle("Favorite Car?")
                        .setMessage("Insert "+ car.getCarModel1() +" "+ car.getCarModel2() +" to Favorites?")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            car.setIsFavorite(true);
                            DatabaseClient.insertNewCar(car);

                            listener.alertDialogFinishSavingCar(car);

                            MainActivity.getInstance().refreshAddRecycler(car);
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else {
                new AlertDialog.Builder(context)
                    .setTitle("Delete From Favorites?")
                    .setMessage("Delete "+ car.getCarModel1() +" "+ car.getCarModel2() +" from Favorites?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        car.setIsFavorite(false);
                        DatabaseClient.deleteNewCar(car.getId());

                        listener.alertDialogFinishSavingCar(car);

                        MainActivity.getInstance().refreshRemoveRecycler(car);
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            }
        }
    }
}
