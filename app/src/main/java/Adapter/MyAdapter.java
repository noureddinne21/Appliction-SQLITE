package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nouroeddinne.sqlite.R;

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
    }

    public int getItemCount() {
        return personList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView id,name,age,address;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textView_item_show_id);
            name = itemView.findViewById(R.id.textView_item_show_name);
            age = itemView.findViewById(R.id.textView_item_show_age);
            address = itemView.findViewById(R.id.textView_item_show_address);
        }
    }






























}
