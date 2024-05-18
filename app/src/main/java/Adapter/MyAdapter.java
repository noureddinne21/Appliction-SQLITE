package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nouroeddinne.sqlite.R;
import com.nouroeddinne.sqlite.ShowandUpdateActivity;

import java.util.List;

import Model.Person;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<Person> personList;


    public MyAdapter(Context context, List<Person> listitems) {
        this.context = context;
        this.personList = listitems;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viwe = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(viwe);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person item = personList.get(position);
        holder.id.setText(String.valueOf(item.getId()));
        holder.name.setText(item.getName());
        holder.age.setText(String.valueOf(item.getAge()));
        holder.address.setText(item.getAddress());

        holder.linear_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowandUpdateActivity.class);
                intent.putExtra("id", String.valueOf(item.getId()));
                intent.putExtra("name", item.getName());
                intent.putExtra("age", String.valueOf(item.getAge()));
                intent.putExtra("address", item.getAddress());
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return personList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView id,name,age,address;
        private LinearLayout linear_show;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textView_item_show_id);
            name = itemView.findViewById(R.id.textView_item_show_name);
            age = itemView.findViewById(R.id.textView_item_show_age);
            address = itemView.findViewById(R.id.textView_item_show_address);
            linear_show = itemView.findViewById(R.id.linear_show);
        }
    }






























}
