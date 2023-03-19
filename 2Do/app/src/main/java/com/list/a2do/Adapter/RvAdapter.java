package com.list.a2do.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.list.a2do.R;
import com.list.a2do.databinding.NoteLayoutBinding;
import com.list.a2do.mvvm.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RvAdapter  extends  ListAdapter<Note,RvAdapter.ViewHolder>{

private int LastPotion = -1;


    public  RvAdapter(){
        super(CALLBACK);
    }
    private static final  DiffUtil.ItemCallback<Note> CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {

            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {

            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDisc().equals(newItem.getDisc());
        }
    };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout,parent,false);

        return  new ViewHolder(view);
    }
   @SuppressLint({"RecylerView","NewApi"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Note note = getItem(position);
       holder.binding.Tittle.setText(note.getTitle());
       holder.binding.discp.setText(note.getDisc());
       holder.binding.AllList.setBackgroundColor(holder.itemView.getResources().getColor(getRandomColor(), null));
       
       setAnim(holder.itemView,position);





}
    public  Note getNote(int postion){
        return getItem(postion);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        NoteLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = NoteLayoutBinding.bind(itemView);
        }

    }

        private int getRandomColor() {
            List<Integer> colorCode = new ArrayList<>();
            colorCode.add(R.color.Red);
            colorCode.add(R.color.green);
            colorCode.add(R.color.blue);
            colorCode.add(R.color.purple);
            colorCode.add(R.color.DarkBlue);
            colorCode.add(R.color.pink);
            colorCode.add(R.color.sky);
            colorCode.add(R.color.skyblue);
            colorCode.add(R.color.skyBlack);
            colorCode.add(R.color.skygreen);
            colorCode.add(R.color.skypiink);
            colorCode.add(R.color.darkgreen);

            Random random = new Random();
            int number = random.nextInt(colorCode.size());
            return colorCode.get(number);


        }

        private  void setAnim (View view , int postion) {
            if (postion > LastPotion) {

                Animation slideAnim = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left);
                view.startAnimation(slideAnim);
                LastPotion = postion;
            }
        }

}

