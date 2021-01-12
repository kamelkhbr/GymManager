package com.androidavenger.fatgymmembership.view.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidavenger.fatgymmembership.R;
import com.androidavenger.fatgymmembership.model.Member;

import java.util.ArrayList;

public class MemberItemAdapter extends BaseAdapter {

    private ArrayList<Member> availableMembers;
    private MemberDelegate memberDelegate;

    public interface MemberDelegate {
        void selectMember(Member selectedMember);
    }

    public MemberItemAdapter(ArrayList<Member> availableMembers, MemberDelegate memberDelegate) {
        this.availableMembers = availableMembers;
        this.memberDelegate = memberDelegate;
    }


    @Override
    public int getCount() {
        return availableMembers.size();
    }

    @Override
    public Object getItem(int position) {
        return availableMembers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Member item = availableMembers.get(position);

        View mainView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.member_item_layout,parent, false);

        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberDelegate.selectMember(item);
            }
        });
        TextView memberName = mainView.findViewById(R.id.first_name);
        TextView memberLastname = mainView.findViewById(R.id.last_name);
        TextView memberType = mainView.findViewById(R.id.Member_type);
        TextView memberphone = mainView.findViewById(R.id.phone_number);

        memberName.setText(item.getMemberName());
        memberLastname.setText(item.getMemberLastName());
        memberType.setText(item.getMemberType());
        memberphone.setText(item.getMemberPhone());

        return mainView;

    }
}
